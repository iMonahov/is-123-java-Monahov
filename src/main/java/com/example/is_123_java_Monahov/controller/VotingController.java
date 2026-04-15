package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class VotingController {

    @Autowired
    private PollService pollService;

    @PostMapping("/vote")
    public String vote(@RequestParam Map<String, String> allParams,
                       @RequestParam(required = false) Integer age,
                       @RequestParam Long surveyId) {

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String paramName = entry.getKey();

            if (paramName.startsWith("question_")) {
                try {
                    Long optionId = Long.parseLong(entry.getValue());
                    pollService.vote(optionId, age);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }

        return "redirect:/survey/" + surveyId + "?voted=true";
    }
}