package com.example.is_123_java_Monahov.service.strategy;

import com.example.is_123_java_Monahov.repository.PollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleVotingStrategyTest {

    @Mock
    private PollRepository pollRepository;

    @InjectMocks
    private SimpleVotingStrategy simpleVotingStrategy;

    @Test
    void testCastVote_WithAge_ShouldCallRepository() {
        simpleVotingStrategy.castVote(100L, 25);

        verify(pollRepository, times(1)).addVote(100L, 25);
    }

    @Test
    void testCastVote_WithoutAge_ShouldCallRepository() {
        simpleVotingStrategy.castVote(200L, null);

        verify(pollRepository, times(1)).addVote(200L, null);
    }

    @Test
    void testCastVote_ShouldHandleAnyOptionId() {
        simpleVotingStrategy.castVote(999L, 40);

        verify(pollRepository).addVote(999L, 40);
    }
}
