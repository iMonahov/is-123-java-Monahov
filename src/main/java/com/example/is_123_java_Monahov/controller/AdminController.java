package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("surveys", surveyService.getAllSurveys());
        return "admin";
    }

    @GetMapping("/add-survey")
    public String addSurveyForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "add-survey";
    }

    @PostMapping("/add-survey")
    public String addSurvey(@RequestParam("title") String title,
                            @RequestParam("questions") List<String> questions,
                            @RequestParam("options") List<String> allOptions) {

        Survey survey = new Survey();
        survey.setTitle(title);

        List<Poll> polls = new ArrayList<>();
        int optionIndex = 0;

        for (String questionText : questions) {
            if (questionText != null && !questionText.trim().isEmpty()) {
                Poll poll = new Poll();
                poll.setQuestion(questionText);

                // Добавляем варианты ответов для этого вопроса
                List<Option> options = new ArrayList<>();
                // Предполагаем, что на каждый вопрос приходит 4 варианта
                for (int i = 0; i < 4; i++) {
                    if (optionIndex < allOptions.size()) {
                        String opt = allOptions.get(optionIndex);
                        if (opt != null && !opt.trim().isEmpty()) {
                            options.add(new Option(opt.trim(), null));
                        }
                        optionIndex++;
                    }
                }
                poll.setOptions(options);
                polls.add(poll);
            }
        }

        survey.setPolls(polls);
        surveyService.saveSurvey(survey);

        return "redirect:/admin";
    }
}