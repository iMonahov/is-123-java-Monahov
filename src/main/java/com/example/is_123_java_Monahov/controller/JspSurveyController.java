package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.service.PollService;
import com.example.is_123_java_Monahov.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class JspSurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private PollService pollService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("surveys", surveyService.getAllSurveys());
        return "index";
    }

    @GetMapping("/survey")
    public String survey(@RequestParam Long id, Model model) {
        Survey survey = surveyService.getSurveyById(id);
        model.addAttribute("survey", survey);
        return "survey";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Map<String, String> params,
                       @RequestParam(required = false) Integer age,
                       @RequestParam Long surveyId) {
        for (var entry : params.entrySet()) {
            if (entry.getKey().startsWith("poll_")) {
                try {
                    Long optionId = Long.parseLong(entry.getValue());
                    pollService.vote(optionId, age);
                } catch (NumberFormatException e) {}
            }
        }
        return "redirect:/jsp/survey?id=" + surveyId + "&voted=true";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("surveys", surveyService.getAllSurveys());
        return "admin";
    }

    @GetMapping("/add-survey")
    public String addSurveyForm() {
        return "add-survey";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin() {
        return "redirect:/jsp/admin";
    }
}