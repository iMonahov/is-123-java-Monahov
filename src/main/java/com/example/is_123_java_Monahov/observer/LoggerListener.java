package com.example.is_123_java_Monahov.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerListener implements VoteListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggerListener.class);

    @Override
    public void onVoteCast(Long optionId, Integer age, Long pollId) {
        logger.info("Голосование: optionId={}, возраст={}, pollId={}", optionId, age, pollId);
        System.out.println("[LOG] Записан голос за вариант ID: " + optionId);
    }
}