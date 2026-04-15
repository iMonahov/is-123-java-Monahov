package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PollRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Poll> pollRowMapper = (rs, rowNum) -> {
        Poll poll = new Poll();
        poll.setId(rs.getLong("ID"));
        poll.setQuestion(rs.getString("QUESTION"));
        poll.setSurveyId(rs.getLong("SURVEY_ID"));
        return poll;
    };

    private final RowMapper<Option> optionRowMapper = (rs, rowNum) -> {
        Option option = new Option();
        option.setId(rs.getLong("ID"));
        option.setText(rs.getString("TEXT"));
        option.setPollId(rs.getLong("POLL_ID"));
        return option;
    };

    private final RowMapper<Vote> voteRowMapper = (rs, rowNum) -> {
        Vote vote = new Vote();
        vote.setId(rs.getLong("ID"));
        vote.setAge(rs.getInt("AGE"));
        if (rs.wasNull()) vote.setAge(null);
        vote.setOptionId(rs.getLong("OPTION_ID"));
        return vote;
    };

    // Найти вопросы по ID опроса
    public List<Poll> findBySurveyId(Long surveyId) {
        List<Poll> polls = jdbcTemplate.query(
                "SELECT * FROM POLL WHERE SURVEY_ID = ? ORDER BY ID",
                pollRowMapper,
                surveyId
        );

        for (Poll poll : polls) {
            List<Option> options = jdbcTemplate.query(
                    "SELECT * FROM OPTION WHERE POLL_ID = ?",
                    optionRowMapper,
                    poll.getId()
            );

            for (Option option : options) {
                List<Vote> votes = jdbcTemplate.query(
                        "SELECT * FROM VOTE WHERE OPTION_ID = ?",
                        voteRowMapper,
                        option.getId()
                );
                option.setVotes(votes);
            }

            poll.setOptions(options);
        }

        return polls;
    }

    // Сохранить вопрос
    public void save(Poll poll) {
        jdbcTemplate.update(
                "INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES (?, ?)",
                poll.getQuestion(),
                poll.getSurveyId()
        );

        Long pollId = jdbcTemplate.queryForObject(
                "SELECT MAX(ID) FROM POLL",
                Long.class
        );

        for (Option option : poll.getOptions()) {
            jdbcTemplate.update(
                    "INSERT INTO OPTION (TEXT, POLL_ID) VALUES (?, ?)",
                    option.getText(),
                    pollId
            );
            option.setPollId(pollId);
        }
    }

    // ========================================
    // ДОБАВЛЯЕМ МЕТОД addVote (который отсутствовал)
    // ========================================

    // Добавить голос
    public void addVote(Long optionId, Integer age) {
        jdbcTemplate.update(
                "INSERT INTO VOTE (AGE, OPTION_ID) VALUES (?, ?)",
                age,
                optionId
        );
        System.out.println("Голос добавлен: optionId=" + optionId + ", age=" + age);
    }

    // Получить количество голосов для варианта
    public long getVotesCountByOption(Long optionId) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM VOTE WHERE OPTION_ID = ?",
                Long.class,
                optionId
        );
    }

    // Получить голоса для варианта
    public List<Vote> getVotesByOption(Long optionId) {
        return jdbcTemplate.query(
                "SELECT * FROM VOTE WHERE OPTION_ID = ?",
                voteRowMapper,
                optionId
        );
    }

    // Получить вариант по ID
    public Option findOptionById(Long optionId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM OPTION WHERE ID = ?",
                    optionRowMapper,
                    optionId
            );
        } catch (Exception e) {
            return null;
        }
    }
}