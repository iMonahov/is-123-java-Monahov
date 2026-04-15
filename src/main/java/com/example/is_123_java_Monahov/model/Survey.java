package com.example.is_123_java_Monahov.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private Long id;
    private String title;
    private List<Poll> polls = new ArrayList<>();

    public Survey() {}

    public Survey(String title) {
        this.title = title;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Poll> getPolls() { return polls; }
    public void setPolls(List<Poll> polls) { this.polls = polls; }
}