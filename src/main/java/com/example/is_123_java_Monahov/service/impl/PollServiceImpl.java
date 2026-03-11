package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;
import com.example.is_123_java_Monahov.repository.OptionRepository;
import com.example.is_123_java_Monahov.repository.PollRepository;
import com.example.is_123_java_Monahov.repository.VoteRepository;
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
    private OptionRepository optionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VotingStrategy votingStrategy;   // ← Strategy паттерн

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    @Override
    public void savePoll(Poll poll) {
        pollRepository.save(poll);
    }

    @Override
    public void vote(Long optionId, Integer age) {
        votingStrategy.castVote(optionId, age);   // используем стратегию
    }

    @Override
    public long getVotesCountByOption(Option option) {
        return option.getVotes().size();
    }

    @Override
    public List<Vote> getVotesByOption(Option option) {
        return option.getVotes();
    }
}