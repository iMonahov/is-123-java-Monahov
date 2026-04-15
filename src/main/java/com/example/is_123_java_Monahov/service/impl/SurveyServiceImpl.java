package com.example.is_123_java_Monahov.service.impl;

import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.repository.SurveyRepository;
import com.example.is_123_java_Monahov.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id);
    }

    @Override
    public void saveSurvey(Survey survey) {
        surveyRepository.save(survey);
    }
}