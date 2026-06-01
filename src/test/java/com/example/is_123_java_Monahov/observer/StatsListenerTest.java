package com.example.is_123_java_Monahov.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsListenerTest {

    private StatsListener statsListener;

    @BeforeEach
    void setUp() {
        statsListener = new StatsListener();
    }

    @Test
    void testOnVoteCast_ShouldIncrementTotalVotes() {
        statsListener.onVoteCast(1L, 25, 10L);
        statsListener.onVoteCast(2L, 30, 10L);
        statsListener.onVoteCast(3L, 35, 10L);

        assertEquals(3, statsListener.getTotalVotes());
    }

    @Test
    void testOnVoteCast_WithAge_ShouldIncrementVotesWithAge() {
        statsListener.onVoteCast(1L, 25, 10L);
        statsListener.onVoteCast(2L, 30, 10L);

        assertEquals(2, statsListener.getVotesWithAge());
        assertEquals(2, statsListener.getTotalVotes());
    }

    @Test
    void testOnVoteCast_WithoutAge_ShouldNotIncrementVotesWithAge() {
        statsListener.onVoteCast(1L, null, 10L);
        statsListener.onVoteCast(2L, 30, 10L);
        statsListener.onVoteCast(3L, null, 10L);

        assertEquals(1, statsListener.getVotesWithAge());
        assertEquals(3, statsListener.getTotalVotes());
    }

    @Test
    void testInitialState_ShouldBeZero() {
        assertEquals(0, statsListener.getTotalVotes());
        assertEquals(0, statsListener.getVotesWithAge());
    }
}
