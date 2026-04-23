package com.example.is_123_java_Monahov.observer;

public interface VoteListener {
    void onVoteCast(Long optionId, Integer age, Long pollId);
}