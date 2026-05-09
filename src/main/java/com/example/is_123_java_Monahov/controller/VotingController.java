package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.model.*;
import com.example.is_123_java_Monahov.repository.VotingLinkRepository;
import com.example.is_123_java_Monahov.service.PollService;
import com.example.is_123_java_Monahov.service.SurveyService;
import com.example.is_123_java_Monahov.service.VotingStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VotingController {

    @Autowired
    private PollService pollService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private VotingLinkRepository linkRepository;

    @Autowired
    private VotingStatsService statsService;

    // Голосование по временной ссылке
    @GetMapping("/vote/{token}")
    public String voteByLink(@PathVariable String token, Model model) {
        VotingLink link = linkRepository.findByToken(token);
        if (link == null || !link.isActive()) {
            model.addAttribute("message", "Ссылка недействительна или истекла");
            return "link-expired";
        }

        Survey survey = surveyService.getSurveyById(link.getSurveyId());
        if (survey == null) {
            return "redirect:/";
        }

        model.addAttribute("survey", survey);
        model.addAttribute("linkToken", token);
        model.addAttribute("link", link);
        return "vote";
    }

    // Обработка голосования по ссылке
    @PostMapping("/vote/{token}")
    public String submitVote(@PathVariable String token,
                             @RequestParam Map<String, String> params,
                             @RequestParam(required = false) Integer age) {
        VotingLink link = linkRepository.findByToken(token);
        if (link == null || !link.isActive()) {
            return "link-expired";
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().startsWith("poll_")) {
                try {
                    Long optionId = Long.parseLong(entry.getValue());
                    pollService.vote(optionId, age);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка голосования: " + e.getMessage());
                }
            }
        }

        linkRepository.incrementVoteCount(link.getId());
        return "redirect:/results/" + token;
    }

    // Просмотр результатов
    @GetMapping("/results/{token}")
    public String showResults(@PathVariable String token, Model model) {
        VotingLink link = linkRepository.findByToken(token);
        if (link == null) {
            return "redirect:/";
        }

        Survey survey = surveyService.getSurveyById(link.getSurveyId());
        if (survey == null) {
            return "redirect:/";
        }

        Map<Long, List<OptionResult>> resultsByPoll = new LinkedHashMap<>();
        Map<Long, Long> totalVotesByPoll = new LinkedHashMap<>();

        for (Poll poll : survey.getPolls()) {
            List<OptionResult> results = statsService.getPollResults(poll);
            resultsByPoll.put(poll.getId(), results);
            totalVotesByPoll.put(poll.getId(), statsService.getTotalPollVotes(poll));
        }

        model.addAttribute("survey", survey);
        model.addAttribute("resultsByPoll", resultsByPoll);
        model.addAttribute("totalVotesByPoll", totalVotesByPoll);
        model.addAttribute("linkExpired", link.isExpired());
        model.addAttribute("totalVotes", link.getVoteCount());
        model.addAttribute("link", link);

        return "results";
    }

    // Legacy метод для обратной совместимости
    @PostMapping("/vote")
    public String voteLegacy(@RequestParam Map<String, String> allParams,
                             @RequestParam(required = false) Integer age,
                             @RequestParam Long surveyId) {
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("poll_")) {
                try {
                    Long optionId = Long.parseLong(entry.getValue());
                    pollService.vote(optionId, age);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка голосования: " + e.getMessage());
                }
            }
        }
        return "redirect:/survey/" + surveyId + "?voted=true";
    }
}