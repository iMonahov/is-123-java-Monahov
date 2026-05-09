package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.OptionResult;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.repository.PollRepository;
import com.example.is_123_java_Monahov.repository.VoteRepository;
import com.example.is_123_java_Monahov.service.VotingStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotingStatsServiceImpl implements VotingStatsService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public List<OptionResult> getPollResults(Poll poll) {
        List<OptionResult> results = new ArrayList<>();
        long totalPollVotes = 0;

        // Сначала считаем общее количество голосов за все варианты этого вопроса
        for (Option option : poll.getOptions()) {
            totalPollVotes += voteRepository.countByOptionId(option.getId());
        }

        // Затем формируем результаты для каждого варианта
        for (Option option : poll.getOptions()) {
            long votes = voteRepository.countByOptionId(option.getId());
            results.add(new OptionResult(option.getId(), option.getText(), votes, totalPollVotes));
        }
        return results;
    }

    @Override
    public long getTotalPollVotes(Poll poll) {
        long total = 0;
        for (Option option : poll.getOptions()) {
            total += voteRepository.countByOptionId(option.getId());
        }
        return total;
    }
}