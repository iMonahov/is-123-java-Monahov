package com.example.is_123_java_Monahov.builder;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Survey;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyBuilder {
    private String title;
    private List<Poll> polls = new ArrayList<>();

    public SurveyBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SurveyBuilder addPoll(String question, List<String> optionTexts) {
        Poll poll = new Poll();
        poll.setQuestion(question);

        List<Option> options = new ArrayList<>();
        for (String text : optionTexts) {
            if (text != null && !text.trim().isEmpty()) {
                options.add(new Option(text.trim(), null));
            }
        }
        poll.setOptions(options);
        polls.add(poll);
        return this;
    }

    public SurveyBuilder addPollWithOptions(String question, String... options) {
        return addPoll(question, List.of(options));
    }

    public Survey build() {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalStateException("Название опроса не может быть пустым");
        }
        if (polls.isEmpty()) {
            throw new IllegalStateException("Опрос должен содержать хотя бы один вопрос");
        }

        Survey survey = new Survey();
        survey.setTitle(title);
        survey.setPolls(polls);
        return survey;
    }

    public void reset() {
        this.title = null;
        this.polls = new ArrayList<>();
    }
}