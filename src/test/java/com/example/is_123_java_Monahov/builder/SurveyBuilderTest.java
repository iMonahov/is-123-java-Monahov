package com.example.is_123_java_Monahov.builder;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Survey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyBuilderTest {

    private SurveyBuilder surveyBuilder;

    @BeforeEach
    void setUp() {
        surveyBuilder = new SurveyBuilder();
    }

    @Test
    void testSetTitle_ShouldSetTitle() {
        SurveyBuilder result = surveyBuilder.setTitle("Тестовый опрос");
        assertNotNull(result);
    }

    @Test
    void testAddPoll_WithValidOptions_ShouldAddPoll() {
        List<String> options = List.of("Вариант 1", "Вариант 2", "Вариант 3");

        surveyBuilder.setTitle("Мой опрос");
        surveyBuilder.addPoll("Какой ваш любимый цвет?", options);
        Survey survey = surveyBuilder.build();

        assertEquals(1, survey.getPolls().size());
        assertEquals("Какой ваш любимый цвет?", survey.getPolls().get(0).getQuestion());
        assertEquals(3, survey.getPolls().get(0).getOptions().size());
    }

    @Test
    void testAddPoll_WithNullOptions_ShouldSkipNulls() {
        List<String> options = List.of("Вариант 1", null, "Вариант 3", "");

        surveyBuilder.setTitle("Тест");
        surveyBuilder.addPoll("Вопрос?", options);
        Survey survey = surveyBuilder.build();

        assertEquals(2, survey.getPolls().get(0).getOptions().size());
        assertEquals("Вариант 1", survey.getPolls().get(0).getOptions().get(0).getText());
        assertEquals("Вариант 3", survey.getPolls().get(0).getOptions().get(1).getText());
    }

    @Test
    void testAddPollWithOptions_Varargs_ShouldAddPoll() {
        surveyBuilder.setTitle("Тест");
        surveyBuilder.addPollWithOptions("Вопрос?", "Опция A", "Опция B", "Опция C");
        Survey survey = surveyBuilder.build();

        assertEquals(1, survey.getPolls().size());
        assertEquals(3, survey.getPolls().get(0).getOptions().size());
    }

    @Test
    void testBuild_WithoutTitle_ShouldThrowException() {
        surveyBuilder.addPoll("Вопрос?", List.of("Опция 1", "Опция 2"));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            surveyBuilder.build();
        });
        assertEquals("Название опроса не может быть пустым", exception.getMessage());
    }

    @Test
    void testBuild_WithEmptyTitle_ShouldThrowException() {
        surveyBuilder.setTitle("");
        surveyBuilder.addPoll("Вопрос?", List.of("Опция 1", "Опция 2"));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            surveyBuilder.build();
        });
        assertEquals("Название опроса не может быть пустым", exception.getMessage());
    }

    @Test
    void testBuild_WithoutPolls_ShouldThrowException() {
        surveyBuilder.setTitle("Пустой опрос");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            surveyBuilder.build();
        });
        assertEquals("Опрос должен содержать хотя бы один вопрос", exception.getMessage());
    }

    @Test
    void testBuild_ValidSurvey_ShouldCreateCorrectSurvey() {
        surveyBuilder.setTitle("Полный тест");
        surveyBuilder.addPoll("Вопрос 1", List.of("A", "B"));
        surveyBuilder.addPoll("Вопрос 2", List.of("C", "D", "E"));

        Survey survey = surveyBuilder.build();

        assertEquals("Полный тест", survey.getTitle());
        assertEquals(2, survey.getPolls().size());
        assertEquals("Вопрос 1", survey.getPolls().get(0).getQuestion());
        assertEquals("Вопрос 2", survey.getPolls().get(1).getQuestion());
    }

    @Test
    void testReset_ShouldClearBuilder() {
        surveyBuilder.setTitle("Тест");
        surveyBuilder.addPoll("Вопрос?", List.of("A", "B"));
        surveyBuilder.reset();

        assertThrows(IllegalStateException.class, () -> surveyBuilder.build());

        surveyBuilder.setTitle("Новый тест");
        surveyBuilder.addPoll("Новый вопрос?", List.of("X", "Y"));
        Survey survey = surveyBuilder.build();

        assertEquals("Новый тест", survey.getTitle());
        assertEquals(1, survey.getPolls().size());
    }
}