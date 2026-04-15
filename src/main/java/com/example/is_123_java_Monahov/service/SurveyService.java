package com.example.is_123_java_Monahov.service;

import com.example.is_123_java_Monahov.model.Survey;
import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();
    Survey getSurveyById(Long id);
    void saveSurvey(Survey survey);
}