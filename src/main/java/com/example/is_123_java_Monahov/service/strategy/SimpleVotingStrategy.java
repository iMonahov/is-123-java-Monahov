package com.example.is_123_java_Monahov.service.strategy;

import com.example.is_123_java_Monahov.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleVotingStrategy implements VotingStrategy {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public void castVote(Long optionId, Integer age) {
        pollRepository.addVote(optionId, age);
        System.out.println("Голос добавлен: optionId=" + optionId + ", age=" + age);
    }
}