package com.example.is_123_java_Monahov.factory;

import com.example.is_123_java_Monahov.builder.SurveyBuilder;
import com.example.is_123_java_Monahov.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyFactory {

    @Autowired
    private SurveyBuilder builder;

    public Survey createSurvey(SurveyType type, String customTitle) {
        builder.reset();

        switch (type) {
            case SATISFACTION:
                return builder
                        .setTitle(customTitle != null ? customTitle : "Опрос удовлетворённости")
                        .addPollWithOptions("Как вы оцениваете качество обслуживания?",
                                "Отлично", "Хорошо", "Удовлетворительно", "Плохо")
                        .addPollWithOptions("Как вы оцениваете скорость работы?",
                                "Очень быстро", "Быстро", "Нормально", "Медленно")
                        .addPollWithOptions("Рекомендуете ли вы нас друзьям?",
                                "Обязательно", "Возможно", "Нет", "Затрудняюсь ответить")
                        .build();

            case FEEDBACK:
                return builder
                        .setTitle(customTitle != null ? customTitle : "Опрос обратной связи")
                        .addPollWithOptions("Что вам больше всего нравится?",
                                "Качество", "Цена", "Сервис", "Ассортимент")
                        .addPollWithOptions("Что нужно улучшить?",
                                "Обслуживание", "Цены", "Качество", "Скорость")
                        .build();

            case PREFERENCE:
                return builder
                        .setTitle(customTitle != null ? customTitle : "Опрос предпочтений")
                        .addPollWithOptions("Какой формат вам удобнее?",
                                "Онлайн", "Офлайн", "Смешанный")
                        .addPollWithOptions("В какое время вам удобно?",
                                "Утро", "День", "Вечер", "Выходные")
                        .build();

            default:
                throw new IllegalArgumentException("Неизвестный тип опроса: " + type);
        }
    }
}