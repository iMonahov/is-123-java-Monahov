package com.example.is_123_java_Monahov.factory;

import com.example.is_123_java_Monahov.builder.SurveyBuilder;
import com.example.is_123_java_Monahov.model.Survey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyFactoryTest {

    @Mock
    private SurveyBuilder surveyBuilder;

    @InjectMocks
    private SurveyFactory surveyFactory;

    private Survey mockSurvey;

    @BeforeEach
    void setUp() {
        mockSurvey = new Survey();
        mockSurvey.setTitle("Тестовый опрос");

        when(surveyBuilder.setTitle(anyString())).thenReturn(surveyBuilder);
        // Исправлено: правильное мокирование varargs метода
        when(surveyBuilder.addPollWithOptions(anyString(), ArgumentMatchers.<String>any()))
                .thenReturn(surveyBuilder);
        when(surveyBuilder.build()).thenReturn(mockSurvey);
    }

    @Test
    void testCreateSurvey_Satisfaction_ShouldCreateCorrectSurvey() {
        Survey survey = surveyFactory.createSurvey(SurveyType.SATISFACTION, null);

        assertNotNull(survey);
        verify(surveyBuilder, times(1)).reset();
        verify(surveyBuilder, times(1)).setTitle("Опрос удовлетворённости");
        verify(surveyBuilder, times(3)).addPollWithOptions(anyString(), ArgumentMatchers.<String>any());
        verify(surveyBuilder, times(1)).build();
    }

    @Test
    void testCreateSurvey_Satisfaction_WithCustomTitle() {
        Survey survey = surveyFactory.createSurvey(SurveyType.SATISFACTION, "Мой опрос");

        assertNotNull(survey);
        verify(surveyBuilder).setTitle("Мой опрос");
    }

    @Test
    void testCreateSurvey_Feedback_ShouldCreateCorrectSurvey() {
        Survey survey = surveyFactory.createSurvey(SurveyType.FEEDBACK, null);

        assertNotNull(survey);
        verify(surveyBuilder).setTitle("Опрос обратной связи");
        verify(surveyBuilder, times(2)).addPollWithOptions(anyString(), ArgumentMatchers.<String>any());
    }

    @Test
    void testCreateSurvey_Preference_ShouldCreateCorrectSurvey() {
        Survey survey = surveyFactory.createSurvey(SurveyType.PREFERENCE, null);

        assertNotNull(survey);
        verify(surveyBuilder).setTitle("Опрос предпочтений");
        verify(surveyBuilder, times(2)).addPollWithOptions(anyString(), ArgumentMatchers.<String>any());
    }

    @Test
    void testCreateSurvey_UnknownType_ShouldThrowException() {
        assertThrows(NullPointerException.class, () -> {
            surveyFactory.createSurvey(null, null);
        });
    }
}