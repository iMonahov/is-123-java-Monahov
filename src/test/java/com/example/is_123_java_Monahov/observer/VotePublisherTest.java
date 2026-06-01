package com.example.is_123_java_Monahov.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class VotePublisherTest {

    private VotePublisher votePublisher;
    private VoteListener mockListener1;
    private VoteListener mockListener2;

    @BeforeEach
    void setUp() {
        votePublisher = new VotePublisher();
        mockListener1 = mock(VoteListener.class);
        mockListener2 = mock(VoteListener.class);
    }

    @Test
    void testSubscribe_AndNotifyListeners_ShouldCallAllListeners() {
        votePublisher.subscribe(mockListener1);
        votePublisher.subscribe(mockListener2);

        votePublisher.notifyListeners(100L, 25, 10L);

        verify(mockListener1, times(1)).onVoteCast(100L, 25, 10L);
        verify(mockListener2, times(1)).onVoteCast(100L, 25, 10L);
    }

    @Test
    void testUnsubscribe_ShouldRemoveListener() {
        votePublisher.subscribe(mockListener1);
        votePublisher.subscribe(mockListener2);

        votePublisher.unsubscribe(mockListener1);
        votePublisher.notifyListeners(100L, 25, 10L);

        verify(mockListener1, never()).onVoteCast(anyLong(), any(), anyLong());
        verify(mockListener2, times(1)).onVoteCast(100L, 25, 10L);
    }

    @Test
    void testNotifyListeners_WithNoSubscribers_ShouldNotThrowException() {
        assertDoesNotThrow(() -> {
            votePublisher.notifyListeners(100L, null, 10L);
        });
    }

    @Test
    void testNotifyListeners_WithNullAge_ShouldPassNull() {
        votePublisher.subscribe(mockListener1);

        votePublisher.notifyListeners(100L, null, 10L);

        verify(mockListener1).onVoteCast(100L, null, 10L);
    }
}
