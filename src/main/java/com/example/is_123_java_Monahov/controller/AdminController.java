package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.builder.SurveyBuilder;
import com.example.is_123_java_Monahov.factory.SurveyFactory;
import com.example.is_123_java_Monahov.factory.SurveyType;
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

    @Autowired
    private SurveyBuilder surveyBuilder;

    @Autowired
    private SurveyFactory surveyFactory;

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

    @GetMapping("/create-sample/{type}")
    public String createSampleSurvey(@PathVariable String type) {
        SurveyType surveyType;
        try {
            surveyType = SurveyType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "redirect:/admin?error=invalid_type";
        }

        Survey survey = surveyFactory.createSurvey(surveyType, null);
        surveyService.saveSurvey(survey);
        return "redirect:/admin";
    }

    @PostMapping("/add-survey")
    public String addSurvey(@RequestParam("title") String title,
                            @RequestParam("questions") List<String> questions,
                            @RequestParam("options") List<String> allOptions) {

        surveyBuilder.reset();
        surveyBuilder.setTitle(title);

        int optionIndex = 0;
        for (String questionText : questions) {
            if (questionText != null && !questionText.trim().isEmpty()) {
                List<String> optionTexts = new ArrayList<>();
                for (int i = 0; i < 4 && optionIndex < allOptions.size(); i++) {
                    String opt = allOptions.get(optionIndex);
                    if (opt != null && !opt.trim().isEmpty()) {
                        optionTexts.add(opt.trim());
                    }
                    optionIndex++;
                }
                if (!optionTexts.isEmpty()) {
                    surveyBuilder.addPoll(questionText, optionTexts);
                }
            }
        }

        Survey survey = surveyBuilder.build();
        surveyService.saveSurvey(survey);

        return "redirect:/admin";
    }
}