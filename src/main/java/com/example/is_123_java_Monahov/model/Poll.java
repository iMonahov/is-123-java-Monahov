package com.example.is_123_java_Monahov.model;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private Long id;
    private String question;
    private List<Option> options = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }
}