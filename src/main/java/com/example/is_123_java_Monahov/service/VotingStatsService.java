package com.example.is_123_java_Monahov.service;

import com.example.is_123_java_Monahov.model.OptionResult;
import com.example.is_123_java_Monahov.model.Poll;

import java.util.List;

public interface VotingStatsService {
    List<OptionResult> getPollResults(Poll poll);
    long getTotalPollVotes(Poll poll);
}