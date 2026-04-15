package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    // Главная страница со списком опросов
    @GetMapping("/")
    public String index(Model model) {
        List<Survey> surveys = surveyService.getAllSurveys();
        model.addAttribute("surveys", surveys);
        return "index";
    }

    // Страница с вопросами конкретного опроса
    @GetMapping("/survey/{id}")
    public String survey(@PathVariable Long id, Model model) {
        Survey survey = surveyService.getSurveyById(id);
        if (survey == null) {
            return "redirect:/";
        }
        model.addAttribute("survey", survey);
        return "survey";
    }
}