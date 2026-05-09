SET NAMES WIN1251;
SET SQL DIALECT 3;
CONNECT 'localhost:3050/${user.dir}/src/main/resources/VOTINGDB.fdb' USER 'SYSDBA' PASSWORD 'masterkey';
SET AUTODDL ON;

-- ДОБАВЛЯЕМ ОПРОСЫ
INSERT INTO SURVEY (TITLE) VALUES ('Опрос о предпочтениях');
INSERT INTO SURVEY (TITLE) VALUES ('Опрос о работе');
INSERT INTO SURVEY (TITLE) VALUES ('Опрос об отдыхе');

-- ВОПРОСЫ ДЛЯ ОПРОСА 1 (SURVEY_ID = 1)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой ваш любимый цвет?', 1);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой язык программирования вы предпочитаете?', 1);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какое время года вам нравится больше всего?', 1);

-- ВОПРОСЫ ДЛЯ ОПРОСА 2 (SURVEY_ID = 2)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Сколько лет вы работаете?', 2);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Какой у вас график работы?', 2);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Удовлетворены ли вы своей зарплатой?', 2);

-- ВОПРОСЫ ДЛЯ ОПРОСА 3 (SURVEY_ID = 3)
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Как вы предпочитаете отдыхать?', 3);
INSERT INTO POLL (QUESTION, SURVEY_ID) VALUES ('Как часто вы путешествуете?', 3);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Любимый цвет" (POLL_ID = 1)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Красный', 1);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Синий', 1);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Зеленый', 1);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Желтый', 1);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Черный', 1);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Белый', 1);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Язык программирования" (POLL_ID = 2)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Java', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Python', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('JavaScript', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('C++', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('C#', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Go', 2);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Ruby', 2);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Время года" (POLL_ID = 3)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Весна', 3);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Лето', 3);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Осень', 3);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Зима', 3);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Сколько лет работаете?" (POLL_ID = 4)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Менее 1 года', 4);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('1-3 года', 4);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('3-5 лет', 4);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('5-10 лет', 4);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Более 10 лет', 4);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "График работы" (POLL_ID = 5)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Полный день', 5);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Гибкий график', 5);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Удаленная работа', 5);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Сменный график', 5);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Удовлетворены зарплатой?" (POLL_ID = 6)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Да, полностью', 6);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Скорее да', 6);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Скорее нет', 6);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Нет, не удовлетворен', 6);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Как отдыхаете?" (POLL_ID = 7)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Активный отдых', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Пляжный отдых', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Культурный туризм', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Домашний уют', 7);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Экстремальный', 7);

-- ВАРИАНТЫ ДЛЯ ВОПРОСА "Как часто путешествуете?" (POLL_ID = 8)
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Несколько раз в год', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Раз в год', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Раз в несколько лет', 8);
INSERT INTO "OPTION" (TEXT, POLL_ID) VALUES ('Практически не путешествую', 8);

-- ТЕСТОВЫЕ ГОЛОСА ДЛЯ ПРИМЕРА
-- Голоса для вопроса "Любимый цвет" (OPTION_ID: 1-6)
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (25, 1); -- Красный
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (30, 2); -- Синий
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (22, 2); -- Синий
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (28, 3); -- Зеленый
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (35, 1); -- Красный
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (40, 2); -- Синий
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (19, 4); -- Желтый

-- Голоса для вопроса "Язык программирования" (OPTION_ID: 7-13)
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (25, 7);  -- Java
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (28, 7);  -- Java
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (22, 8);  -- Python
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (30, 9);  -- JavaScript
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (26, 9);  -- JavaScript
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (32, 9);  -- JavaScript
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (29, 7);  -- Java
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (24, 10); -- C++
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (27, 11); -- C#

-- Голоса для вопроса "Время года" (OPTION_ID: 14-17)
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (25, 14); -- Весна
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (30, 15); -- Лето
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (22, 16); -- Осень
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (28, 17); -- Зима
INSERT INTO VOTE (AGE, OPTION_ID) VALUES (35, 15); -- Лето

-- ТЕСТОВЫЕ ВРЕМЕННЫЕ ССЫЛКИ
-- Ссылка активна 24 часа, без лимита голосов
INSERT INTO VOTING_LINK (TOKEN, SURVEY_ID, EXPIRES_AT, MAX_VOTES, VOTE_COUNT) 
VALUES ('abc12345', 1, CURRENT_TIMESTAMP + 24, NULL, 0);

-- Ссылка активна 7 дней, максимум 50 голосов
INSERT INTO VOTING_LINK (TOKEN, SURVEY_ID, EXPIRES_AT, MAX_VOTES, VOTE_COUNT) 
VALUES ('xyz67890', 2, CURRENT_TIMESTAMP + 168, 50, 0);

-- Ссылка активна 2 часа, максимум 10 голосов
INSERT INTO VOTING_LINK (TOKEN, SURVEY_ID, EXPIRES_AT, MAX_VOTES, VOTE_COUNT) 
VALUES ('testlink', 3, CURRENT_TIMESTAMP + 2, 10, 0);

-- ПРОСМОТР РЕЗУЛЬТАТОВ
SELECT 'ОПРОСЫ' AS INFO;
SELECT ID, TITLE FROM SURVEY;

SELECT 'ВОПРОСЫ' AS INFO;
SELECT P.ID, P.QUESTION, S.TITLE AS SURVEY_TITLE 
FROM POLL P JOIN SURVEY S ON P.SURVEY_ID = S.ID;

SELECT 'ВАРИАНТЫ ОТВЕТОВ' AS INFO;
SELECT O.ID, O.TEXT, P.QUESTION, S.TITLE AS SURVEY_TITLE
FROM "OPTION" O 
JOIN POLL P ON O.POLL_ID = P.ID 
JOIN SURVEY S ON P.SURVEY_ID = S.ID
ORDER BY S.ID, P.ID, O.ID;

SELECT 'ГОЛОСА ПО ВАРИАНТАМ' AS INFO;
SELECT O.TEXT, COUNT(V.ID) AS VOTES_COUNT
FROM "OPTION" O
LEFT JOIN VOTE V ON V.OPTION_ID = O.ID
GROUP BY O.ID, O.TEXT
ORDER BY VOTES_COUNT DESC;

SELECT 'ВРЕМЕННЫЕ ССЫЛКИ' AS INFO;
SELECT TOKEN, S.TITLE AS SURVEY_TITLE, EXPIRES_AT, MAX_VOTES, VOTE_COUNT
FROM VOTING_LINK VL
JOIN SURVEY S ON VL.SURVEY_ID = S.ID;