package com.example.is_123_java_Monahov.service.strategy;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Vote;
import com.example.is_123_java_Monahov.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleVotingStrategy implements VotingStrategy {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void castVote(Long optionId, Integer age) {
        Option option = optionRepository.findById(optionId).orElseThrow();
        Vote vote = new Vote();
        vote.setOption(option);
        vote.setAge(age);
        option.getVotes().add(vote);
        optionRepository.save(option);
    }
}