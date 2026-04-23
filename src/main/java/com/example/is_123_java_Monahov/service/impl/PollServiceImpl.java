package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;
import com.example.is_123_java_Monahov.observer.VotePublisher;
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

    @Autowired
    private VotePublisher votePublisher;

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findBySurveyId(null);
    }

    @Override
    public Poll getPollById(Long id) {
        List<Poll> polls = pollRepository.findBySurveyId(null);
        for (Poll poll : polls) {
            if (poll.getId().equals(id)) {
                return poll;
            }
        }
        return null;
    }

    @Override
    public void savePoll(Poll poll) {
        pollRepository.save(poll);
    }

    @Override
    public void vote(Long optionId, Integer age) {
        votingStrategy.castVote(optionId, age);

        Option option = pollRepository.findOptionById(optionId);
        if (option != null && option.getPollId() != null) {
            votePublisher.notifyListeners(optionId, age, option.getPollId());
        }
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