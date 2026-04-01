package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Option> optionRowMapper = (rs, rowNum) -> {
        Option option = new Option();
        option.setId(rs.getLong("id"));
        option.setText(rs.getString("text"));
        option.setPollId(rs.getLong("poll_id"));
        return option;
    };

    public Option findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM OPTION WHERE ID = ?",
                    optionRowMapper,
                    id
            );
        } catch (Exception e) {
            return null;
        }
    }

    public List<Option> findByPollId(Long pollId) {
        return jdbcTemplate.query(
                "SELECT * FROM OPTION WHERE POLL_ID = ?",
                optionRowMapper,
                pollId
        );
    }

    public void save(Option option) {
        jdbcTemplate.update(
                "INSERT INTO OPTION (TEXT, POLL_ID) VALUES (?, ?)",
                option.getText(),
                option.getPollId()
        );
    }
}