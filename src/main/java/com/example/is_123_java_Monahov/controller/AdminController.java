package com.example.is_123_java_Monahov.controller;

import com.example.is_123_java_Monahov.builder.SurveyBuilder;
import com.example.is_123_java_Monahov.factory.SurveyFactory;
import com.example.is_123_java_Monahov.factory.SurveyType;
import com.example.is_123_java_Monahov.model.Survey;
import com.example.is_123_java_Monahov.model.VotingLink;
import com.example.is_123_java_Monahov.repository.VotingLinkRepository;
import com.example.is_123_java_Monahov.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private VotingLinkRepository votingLinkRepository;

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

    // ========================================
    // УПРАВЛЕНИЕ ВРЕМЕННЫМИ ССЫЛКАМИ
    // ========================================

    /**
     * Страница управления ссылками для конкретного опроса
     */
    @GetMapping("/links/{surveyId}")
    public String manageLinks(@PathVariable Long surveyId, Model model, @RequestParam(required = false) String url) {
        Survey survey = surveyService.getSurveyById(surveyId);
        if (survey == null) {
            return "redirect:/admin";
        }
        List<VotingLink> links = votingLinkRepository.findBySurveyId(surveyId);
        model.addAttribute("survey", survey);
        model.addAttribute("links", links);
        model.addAttribute("createdUrl", url);
        return "survey-links";
    }

    /**
     * Форма создания новой ссылки (опционально, можно использовать прямо на странице links)
     */
    @GetMapping("/create-link/{surveyId}")
    public String createLinkForm(@PathVariable Long surveyId, Model model) {
        Survey survey = surveyService.getSurveyById(surveyId);
        if (survey == null) {
            return "redirect:/admin";
        }
        model.addAttribute("survey", survey);
        return "create-link";
    }

    /**
     * Создание временной ссылки для голосования
     * @param surveyId ID опроса
     * @param hoursValid срок действия в часах (по умолчанию 24)
     * @param maxVotes максимальное количество голосов (опционально)
     */
    @PostMapping("/create-link")
    public String createVotingLink(@RequestParam Long surveyId,
                                   @RequestParam(required = false) Integer hoursValid,
                                   @RequestParam(required = false) Integer maxVotes) {

        // Генерируем уникальный токен (8 символов)
        String token = UUID.randomUUID().toString().substring(0, 8);

        // Срок действия: если не указан, то 24 часа
        int hours = (hoursValid != null && hoursValid > 0) ? hoursValid : 24;
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(hours);

        // Максимум голосов: если не указан или <=0, то без ограничений
        Integer maxVotesLimit = (maxVotes != null && maxVotes > 0) ? maxVotes : null;

        VotingLink link = new VotingLink(token, surveyId, expiresAt, maxVotesLimit);
        votingLinkRepository.save(link);

        // Формируем полную ссылку для отправки
        String shareUrl = "http://localhost:8080/vote/" + token;

        return "redirect:/admin/links/" + surveyId + "?url=" + shareUrl;
    }
}