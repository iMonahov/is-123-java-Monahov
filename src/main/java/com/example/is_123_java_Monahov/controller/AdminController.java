package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.Option;
import com.example.is_123_java_Monahov.model.Poll;
import com.example.is_123_java_Monahov.service.PollService;
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
    private PollService pollService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("polls", pollService.getAllPolls());
        return "admin";
    }

    @GetMapping("/add-poll")
    public String addPollForm(Model model) {
        model.addAttribute("poll", new Poll());
        return "add-poll";
    }

    @PostMapping("/add-poll")
    public String addPoll(@RequestParam("question") String question,
                          @RequestParam(value = "options", required = false) List<String> options) {

        // Создаем новый опрос
        Poll poll = new Poll();
        poll.setQuestion(question);

        // Создаем список вариантов ответов
        List<Option> optionList = new ArrayList<>();
        if (options != null) {
            for (String opt : options) {
                if (opt != null && !opt.trim().isEmpty()) {
                    optionList.add(new Option(opt.trim(), null));
                }
            }
        }

        poll.setOptions(optionList);

        // Сохраняем опрос в базу данных
        if (!optionList.isEmpty()) {
            pollService.savePoll(poll);
        }

        return "redirect:/admin";
    }
}