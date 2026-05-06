package com.example.is_123_java_Monahov.observer;

import org.springframework.stereotype.Component;

@Component
public class StatsListener implements VoteListener {

    private int totalVotes = 0;
    private int votesWithAge = 0;

    @Override
    public void onVoteCast(Long optionId, Integer age, Long pollId) {
        totalVotes++;
        if (age != null) {
            votesWithAge++;
        }

        System.out.println("[STATS] Всего голосов: " + totalVotes);
        System.out.println("[STATS] С указанием возраста: " + votesWithAge);
    }

    public int getTotalVotes() { return totalVotes; }
    public int getVotesWithAge() { return votesWithAge; }
}