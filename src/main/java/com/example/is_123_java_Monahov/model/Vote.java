package com.example.is_123_java_Monahov.model;

import jakarta.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;  // Опционально, для статистики

    @ManyToOne
    private Option option;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Option getOption() { return option; }
    public void setOption(Option option) { this.option = option; }
}