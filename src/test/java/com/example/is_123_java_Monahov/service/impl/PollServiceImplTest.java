package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.observer.VotePublisher;
import com.example.is_123_java_Monahov.repository.PollRepository;
import com.example.is_123_java_Monahov.service.strategy.VotingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PollServiceImplTest {

    @Mock
    private PollRepository pollRepository;

    @Mock
    private VotingStrategy votingStrategy;

    @Mock
    private VotePublisher votePublisher;

    @InjectMocks
    private PollServiceImpl pollService;

    private Option mockOption;

    @BeforeEach
    void setUp() {
        mockOption = new Option();
        mockOption.setId(100L);
        mockOption.setPollId(10L);
    }

    @Test
    void testVote_WithValidOption_ShouldCallStrategyAndPublisher() {
        when(pollRepository.findOptionById(100L)).thenReturn(mockOption);

        pollService.vote(100L, 25);

        verify(votingStrategy, times(1)).castVote(100L, 25);
        verify(votePublisher, times(1)).notifyListeners(100L, 25, 10L);
    }

    @Test
    void testVote_WithOptionNotFound_ShouldCallStrategyButNotPublisher() {
        when(pollRepository.findOptionById(200L)).thenReturn(null);

        pollService.vote(200L, 30);

        verify(votingStrategy, times(1)).castVote(200L, 30);
        verify(votePublisher, never()).notifyListeners(anyLong(), any(), anyLong());
    }

    @Test
    void testVote_WithOptionWithoutPollId_ShouldCallStrategyOnly() {
        mockOption.setPollId(null);
        when(pollRepository.findOptionById(100L)).thenReturn(mockOption);

        pollService.vote(100L, 25);

        verify(votingStrategy, times(1)).castVote(100L, 25);
        verify(votePublisher, never()).notifyListeners(anyLong(), any(), anyLong());
    }

    @Test
    void testGetVotesCountByOption_ShouldCallRepository() {
        pollService.getVotesCountByOption(mockOption);

        verify(pollRepository, times(1)).getVotesCountByOption(100L);
    }
}