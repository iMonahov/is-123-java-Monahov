package com.example.is_123_java_Monahov.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VotingLinkTest {

    @Test
    void testConstructor_ShouldInitializeCorrectly() {
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);
        VotingLink link = new VotingLink("token123", 1L, expiresAt, 100);

        assertEquals("token123", link.getToken());
        assertEquals(1L, link.getSurveyId());
        assertEquals(expiresAt, link.getExpiresAt());
        assertEquals(100, link.getMaxVotes());
        assertEquals(0, link.getVoteCount());
    }

    @Test
    void testIsExpired_WhenNotExpired_ShouldReturnFalse() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), null);
        assertFalse(link.isExpired());
    }

    @Test
    void testIsExpired_WhenExpired_ShouldReturnTrue() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().minusHours(1), null);
        assertTrue(link.isExpired());
    }

    @Test
    void testIsFull_WhenNoMaxVotes_ShouldReturnFalse() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), null);
        assertFalse(link.isFull());
    }

    @Test
    void testIsFull_WhenMaxVotesReached_ShouldReturnTrue() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), 10);
        link.setVoteCount(10);
        assertTrue(link.isFull());
    }

    @Test
    void testIsFull_WhenMaxVotesNotReached_ShouldReturnFalse() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), 10);
        link.setVoteCount(5);
        assertFalse(link.isFull());
    }

    @Test
    void testIsActive_WhenActive_ShouldReturnTrue() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), 10);
        assertTrue(link.isActive());
    }

    @Test
    void testIsActive_WhenExpired_ShouldReturnFalse() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().minusHours(1), 10);
        assertFalse(link.isActive());
    }

    @Test
    void testIsActive_WhenFull_ShouldReturnFalse() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), 10);
        link.setVoteCount(10);
        assertFalse(link.isActive());
    }

    @Test
    void testIncrementVoteCount_ShouldIncrease() {
        VotingLink link = new VotingLink("token", 1L, LocalDateTime.now().plusHours(24), null);
        assertEquals(0, link.getVoteCount());

        link.setVoteCount(link.getVoteCount() + 1);
        assertEquals(1, link.getVoteCount());
    }
}