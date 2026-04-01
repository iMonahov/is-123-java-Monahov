package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Vote> voteRowMapper = (rs, rowNum) -> {
        Vote vote = new Vote();
        vote.setId(rs.getLong("id"));
        vote.setAge(rs.getInt("age"));
        if (rs.wasNull()) vote.setAge(null);
        vote.setOptionId(rs.getLong("option_id"));
        return vote;
    };

    public void save(Vote vote) {
        jdbcTemplate.update(
                "INSERT INTO VOTE (AGE, OPTION_ID) VALUES (?, ?)",
                vote.getAge(),
                vote.getOptionId()
        );
    }

    public List<Vote> findByOptionId(Long optionId) {
        return jdbcTemplate.query(
                "SELECT * FROM VOTE WHERE OPTION_ID = ?",
                voteRowMapper,
                optionId
        );
    }

    public long countByOptionId(Long optionId) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM VOTE WHERE OPTION_ID = ?",
                Long.class,
                optionId
        );
    }
}