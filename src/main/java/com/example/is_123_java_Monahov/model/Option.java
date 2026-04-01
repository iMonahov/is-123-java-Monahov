package com.example.is_123_java_Monahov.model;

import java.util.ArrayList;
import java.util.List;

public class Option {
    private Long id;
    private String text;
    private Long pollId;
    private List<Vote> votes = new ArrayList<>();

    public Option() {}

    public Option(String text, Long pollId) {
        this.text = text;
        this.pollId = pollId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Long getPollId() { return pollId; }
    public void setPollId(Long pollId) { this.pollId = pollId; }
    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}