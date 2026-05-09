package com.example.is_123_java_Monahov.model;

public class OptionResult {
    private Long optionId;
    private String optionText;
    private long votesCount;
    private double percentage;

    public OptionResult(Long optionId, String optionText, long votesCount, long totalVotes) {
        this.optionId = optionId;
        this.optionText = optionText;
        this.votesCount = votesCount;
        this.percentage = totalVotes > 0 ? (votesCount * 100.0 / totalVotes) : 0.0;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public long getVotesCount() {
        return votesCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getPercentageInt() {
        return (int) Math.round(percentage);
    }
}