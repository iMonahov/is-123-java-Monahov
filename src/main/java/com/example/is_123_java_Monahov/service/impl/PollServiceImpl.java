package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;
import com.example.is_123_java_Monahov.repository.PollRepository;
import com.example.is_123_java_Monahov.service.PollService;
import com.example.is_123_java_Monahov.service.strategy.VotingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VotingStrategy votingStrategy;

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.findById(id);
    }

    @Override
    public void savePoll(Poll poll) {
        // Сохраняем опрос в базу данных
        pollRepository.save(poll);
    }

    @Override
    public void vote(Long optionId, Integer age) {
        votingStrategy.castVote(optionId, age);
    }

    @Override
    public long getVotesCountByOption(Option option) {
        return pollRepository.getVotesCountByOption(option.getId());
    }

    @Override
    public List<Vote> getVotesByOption(Option option) {
        return pollRepository.getVotesByOption(option.getId());
    }
}