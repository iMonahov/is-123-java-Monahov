package com.example.is_123_java_Monahov.model;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private Long id;
    private String question;
    private Long surveyId;  // НОВОЕ ПОЛЕ - ссылка на опрос
    private List<Option> options = new ArrayList<>();

    public Poll() {}

    public Poll(String question, Long surveyId) {
        this.question = question;
        this.surveyId = surveyId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Long getSurveyId() { return surveyId; }
    public void setSurveyId(Long surveyId) { this.surveyId = surveyId; }

    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }
}