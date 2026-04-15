-- Добавляем опросы
INSERT INTO SURVEY (TITLE) VALUES ('Опрос о предпочтениях');
INSERT INTO SURVEY (TITLE) VALUES ('Опрос о работе');
INSERT INTO SURVEY (TITLE) VALUES ('Опрос об отдыхе');

-- Добавляем вопросы для опроса 1 (SURVEY_ID = 1)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой ваш любимый цвет?', 1);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой язык программирования вы предпочитаете?', 1);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какое время года вам нравится больше всего?', 1);

-- Добавляем вопросы для опроса 2 (SURVEY_ID = 2)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Сколько лет вы работаете?', 2);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой у вас график работы?', 2);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Удовлетворены ли вы своей зарплатой?', 2);

-- Добавляем вопросы для опроса 3 (SURVEY_ID = 3)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Как вы предпочитаете отдыхать?', 3);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Как часто вы путешествуете?', 3);

==

-- Варианты для "Любимый цвет" (POLL_ID = 7, если вопросы новые)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Красный', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Синий', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Зеленый', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Желтый', 7);

-- Варианты для "Язык программирования" (POLL_ID = 8)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Java', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Python', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('JavaScript', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('C++', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('C#', 8);

-- Варианты для "Время года" (POLL_ID = 9)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Весна', 9);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Лето', 9);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Осень', 9);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Зима', 9);