package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PollRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper для Poll
    private final RowMapper<Poll> pollRowMapper = (rs, rowNum) -> {
        Poll poll = new Poll();
        poll.setId(rs.getLong("id"));
        poll.setQuestion(rs.getString("question"));
        return poll;
    };

    // RowMapper для Option
    private final RowMapper<Option> optionRowMapper = (rs, rowNum) -> {
        Option option = new Option();
        option.setId(rs.getLong("id"));
        option.setText(rs.getString("text"));
        option.setPollId(rs.getLong("poll_id"));
        return option;
    };

    // RowMapper для Vote
    private final RowMapper<Vote> voteRowMapper = (rs, rowNum) -> {
        Vote vote = new Vote();
        vote.setId(rs.getLong("id"));
        vote.setAge(rs.getInt("age"));
        if (rs.wasNull()) vote.setAge(null);
        vote.setOptionId(rs.getLong("option_id"));
        return vote;
    };

    // Получить все опросы с вариантами и голосами
    public List<Poll> findAll() {
        List<Poll> polls = jdbcTemplate.query(
                "SELECT * FROM POLL ORDER BY ID",
                pollRowMapper
        );

        for (Poll poll : polls) {
            // Загружаем варианты для опроса
            List<Option> options = jdbcTemplate.query(
                    "SELECT * FROM OPTION WHERE POLL_ID = ?",
                    optionRowMapper,
                    poll.getId()
            );

            // Загружаем голоса для каждого варианта
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

    // Получить опрос по ID
    public Poll findById(Long id) {
        Poll poll = jdbcTemplate.queryForObject(
                "SELECT * FROM POLL WHERE ID = ?",
                pollRowMapper,
                id
        );

        if (poll != null) {
            List<Option> options = jdbcTemplate.query(
                    "SELECT * FROM OPTION WHERE POLL_ID = ?",
                    optionRowMapper,
                    id
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

        return poll;
    }

    // Сохранить новый опрос
    public void save(Poll poll) {
        // Вставляем опрос
        jdbcTemplate.update(
                "INSERT INTO POLL (QUESTION) VALUES (?)",
                poll.getQuestion()
        );

        // Получаем сгенерированный ID
        Long pollId = jdbcTemplate.queryForObject(
                "SELECT MAX(ID) FROM POLL",
                Long.class
        );

        // Вставляем варианты
        for (Option option : poll.getOptions()) {
            jdbcTemplate.update(
                    "INSERT INTO OPTION (TEXT, POLL_ID) VALUES (?, ?)",
                    option.getText(),
                    pollId
            );
        }
    }

    // Добавить голос
    public void addVote(Long optionId, Integer age) {
        jdbcTemplate.update(
                "INSERT INTO VOTE (AGE, OPTION_ID) VALUES (?, ?)",
                age,
                optionId
        );
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
}