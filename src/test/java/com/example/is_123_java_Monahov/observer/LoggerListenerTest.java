package com.example.is_123_java_Monahov.observer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class LoggerListenerTest {

    @Test
    void testOnVoteCast_ShouldNotThrowException() {
        LoggerListener loggerListener = new LoggerListener();

        assertDoesNotThrow(() -> {
            loggerListener.onVoteCast(1L, 30, 10L);
        });

        assertDoesNotThrow(() -> {
            loggerListener.onVoteCast(2L, null, 20L);
        });
    }
}
