package com.example.is_123_java_Monahov.model;

import java.time.LocalDateTime;

public class VotingLink {
    private Long id;
    private String token;
    private Long surveyId;
    private LocalDateTime expiresAt;
    private Integer maxVotes;
    private int voteCount;

    public VotingLink() {}

    public VotingLink(String token, Long surveyId, LocalDateTime expiresAt, Integer maxVotes) {
        this.token = token;
        this.surveyId = surveyId;
        this.expiresAt = expiresAt;
        this.maxVotes = maxVotes;
        this.voteCount = 0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Long getSurveyId() { return surveyId; }
    public void setSurveyId(Long surveyId) { this.surveyId = surveyId; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public Integer getMaxVotes() { return maxVotes; }
    public void setMaxVotes(Integer maxVotes) { this.maxVotes = maxVotes; }
    public int getVoteCount() { return voteCount; }
    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }
    public boolean isExpired() { return LocalDateTime.now().isAfter(expiresAt); }
    public boolean isFull() { return maxVotes != null && voteCount >= maxVotes; }
    public boolean isActive() { return !isExpired() && !isFull(); }
}