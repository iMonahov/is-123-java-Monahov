package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PollRepository pollRepository;

    private final RowMapper<Survey> surveyRowMapper = (rs, rowNum) -> {
        Survey survey = new Survey();
        survey.setId(rs.getLong("ID"));
        survey.setTitle(rs.getString("TITLE"));
        return survey;
    };

    // Получить все опросы
    public List<Survey> findAll() {
        List<Survey> surveys = jdbcTemplate.query(
                "SELECT * FROM SURVEY ORDER BY ID",
                surveyRowMapper
        );

        // Загружаем вопросы для каждого опроса
        for (Survey survey : surveys) {
            List<Poll> polls = pollRepository.findBySurveyId(survey.getId());
            survey.setPolls(polls);
        }

        return surveys;
    }

    // Получить опрос по ID
    public Survey findById(Long id) {
        try {
            Survey survey = jdbcTemplate.queryForObject(
                    "SELECT * FROM SURVEY WHERE ID = ?",
                    surveyRowMapper,
                    id
            );

            if (survey != null) {
                List<Poll> polls = pollRepository.findBySurveyId(survey.getId());
                survey.setPolls(polls);
            }

            return survey;
        } catch (Exception e) {
            return null;
        }
    }

    // Сохранить новый опрос
    public void save(Survey survey) {
        jdbcTemplate.update(
                "INSERT INTO SURVEY (TITLE) VALUES (?)",
                survey.getTitle()
        );

        // Получаем ID созданного опроса
        Long surveyId = jdbcTemplate.queryForObject(
                "SELECT MAX(ID) FROM SURVEY",
                Long.class
        );

        // Сохраняем вопросы
        for (Poll poll : survey.getPolls()) {
            poll.setSurveyId(surveyId);
            pollRepository.save(poll);
        }
    }
}