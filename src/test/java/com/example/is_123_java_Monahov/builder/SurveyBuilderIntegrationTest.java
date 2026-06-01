package com.example.is_123_java_Monahov.builder;

import com.example.is_123_java_Monahov.model.Survey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyBuilderIntegrationTest {

    private SurveyBuilder surveyBuilder;

    @BeforeEach
    void setUp() {
        surveyBuilder = new SurveyBuilder();
    }

    @Test
    void testComplexSurveyCreation() {
        Survey survey = surveyBuilder
                .setTitle("Комплексный опрос")
                .addPoll("Первый вопрос", List.of("Да", "Нет", "Возможно"))
                .addPoll("Второй вопрос", List.of("Вариант A", "Вариант B"))
                .addPollWithOptions("Третий вопрос", "Опция 1", "Опция 2", "Опция 3", "Опция 4")
                .build();

        assertAll(
                () -> assertEquals("Комплексный опрос", survey.getTitle()),
                () -> assertEquals(3, survey.getPolls().size()),
                () -> assertEquals(3, survey.getPolls().get(0).getOptions().size()),
                () -> assertEquals(2, survey.getPolls().get(1).getOptions().size()),
                () -> assertEquals(4, survey.getPolls().get(2).getOptions().size()),
                () -> assertEquals("Первый вопрос", survey.getPolls().get(0).getQuestion()),
                () -> assertEquals("Второй вопрос", survey.getPolls().get(1).getQuestion()),
                () -> assertEquals("Третий вопрос", survey.getPolls().get(2).getQuestion())
        );
    }

    @Test
    void testBuilderChaining() {
        SurveyBuilder builder = surveyBuilder.setTitle("Тест");

        assertSame(builder, surveyBuilder.setTitle("Тест"));
        assertSame(builder, surveyBuilder.addPoll("Вопрос", List.of("A", "B")));
        assertSame(builder, surveyBuilder.addPollWithOptions("Вопрос2", "A", "B"));
    }
}
