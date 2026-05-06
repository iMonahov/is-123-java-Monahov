package com.example.is_123_java_Monahov.config;

import com.example.is_123_java_Monahov.observer.LoggerListener;
import com.example.is_123_java_Monahov.observer.StatsListener;
import com.example.is_123_java_Monahov.observer.VotePublisher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    @Autowired
    private VotePublisher votePublisher;

    @Autowired
    private LoggerListener loggerListener;

    @Autowired
    private StatsListener statsListener;

    @PostConstruct
    public void init() {
        votePublisher.subscribe(loggerListener);
        votePublisher.subscribe(statsListener);
    }
}