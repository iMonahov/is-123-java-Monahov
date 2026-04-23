package com.example.is_123_java_Monahov.observer;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class VotePublisher {
    private final List<VoteListener> listeners = new ArrayList<>();

    public void subscribe(VoteListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(VoteListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(Long optionId, Integer age, Long pollId) {
        for (VoteListener listener : listeners) {
            listener.onVoteCast(optionId, age, pollId);
        }
    }
}