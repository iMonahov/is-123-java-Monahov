package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VotingController {

    @Autowired
    private PollService pollService;

    // Главная страница (красивый выбор роли)
    @GetMapping("/")
    public String landing() {
        return "index";
    }

    // Страница голосования / тестирования
    @GetMapping("/voting")
    public String voting(Model model) {
        model.addAttribute("polls", pollService.getAllPolls());
        return "voting";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long optionId, @RequestParam(required = false) Integer age) {
        pollService.vote(optionId, age);
        return "redirect:/voting?voted=true";
    }
}
