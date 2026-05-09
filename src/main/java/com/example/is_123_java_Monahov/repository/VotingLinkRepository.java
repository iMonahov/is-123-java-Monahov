package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.VotingLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VotingLinkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<VotingLink> rowMapper = (rs, rowNum) -> {
        VotingLink link = new VotingLink();
        link.setId(rs.getLong("id"));
        link.setToken(rs.getString("token"));
        link.setSurveyId(rs.getLong("survey_id"));
        Timestamp ts = rs.getTimestamp("expires_at");
        if (ts != null) {
            link.setExpiresAt(ts.toLocalDateTime());
        }
        link.setMaxVotes(rs.getInt("max_votes"));
        if (rs.wasNull()) link.setMaxVotes(null);
        link.setVoteCount(rs.getInt("vote_count"));
        return link;
    };

    public void save(VotingLink link) {
        jdbcTemplate.update(
                "INSERT INTO voting_link (token, survey_id, expires_at, max_votes, vote_count) VALUES (?, ?, ?, ?, ?)",
                link.getToken(), link.getSurveyId(), link.getExpiresAt(), link.getMaxVotes(), link.getVoteCount()
        );
    }

    public VotingLink findByToken(String token) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM voting_link WHERE token = ?",
                    rowMapper, token
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void incrementVoteCount(Long id) {
        jdbcTemplate.update("UPDATE voting_link SET vote_count = vote_count + 1 WHERE id = ?", id);
    }

    public List<VotingLink> findBySurveyId(Long surveyId) {
        return jdbcTemplate.query(
                "SELECT * FROM voting_link WHERE survey_id = ? ORDER BY id DESC",
                rowMapper, surveyId
        );
    }
}