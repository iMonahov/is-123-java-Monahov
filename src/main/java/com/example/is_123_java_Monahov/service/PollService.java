package com.example.is_123_java_Monahov.service;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Vote;

import java.util.List;

public interface PollService {

    List<Poll> getAllPolls();
    Poll getPollById(Long id);
    void savePoll(Poll poll);
    void vote(Long optionId, Integer age);

    // Для статистики
    long getVotesCountByOption(Option option);
    List<Vote> getVotesByOption(Option option);
}