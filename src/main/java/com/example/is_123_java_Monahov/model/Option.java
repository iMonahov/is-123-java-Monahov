package com.example.is_123_java_Monahov.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private Poll poll;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<Vote> votes = new java.util.ArrayList<>();

    public Option() {}
    public Option(String text, Poll poll) {
        this.text = text;
        this.poll = poll;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }
    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}