package com.example.is_123_java_Monahov.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionResultTest {

    @Test
    void testConstructor_ShouldCalculatePercentage() {
        OptionResult result = new OptionResult(1L, "Опция A", 30, 100);

        assertEquals(1L, result.getOptionId());
        assertEquals("Опция A", result.getOptionText());
        assertEquals(30, result.getVotesCount());
        assertEquals(30.0, result.getPercentage(), 0.01);
        assertEquals(30, result.getPercentageInt());
    }

    @Test
    void testConstructor_WithZeroTotalVotes_ShouldReturnZeroPercentage() {
        OptionResult result = new OptionResult(1L, "Опция A", 0, 0);

        assertEquals(0.0, result.getPercentage(), 0.01);
        assertEquals(0, result.getPercentageInt());
    }

    @Test
    void testConstructor_WithFullVotes_ShouldReturnHundredPercent() {
        OptionResult result = new OptionResult(1L, "Опция A", 50, 50);

        assertEquals(100.0, result.getPercentage(), 0.01);
        assertEquals(100, result.getPercentageInt());
    }

    @Test
    void testConstructor_ShouldRoundPercentageCorrectly() {
        OptionResult result1 = new OptionResult(1L, "A", 33, 100);
        assertEquals(33, result1.getPercentageInt());

        OptionResult result2 = new OptionResult(1L, "B", 34, 100);
        assertEquals(34, result2.getPercentageInt());

        OptionResult result3 = new OptionResult(1L, "C", 33, 99);
        assertEquals(33, result3.getPercentageInt());
    }
}
