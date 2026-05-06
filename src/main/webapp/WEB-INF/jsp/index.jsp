<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Система голосования - JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .navbar { background: linear-gradient(135deg, #343a40, #212529); }
        .card { transition: all 0.3s; cursor: pointer; }
        .card:hover { transform: translateY(-5px); box-shadow: 0 20px 40px rgba(0,0,0,0.2); }
        .btn-start { background: linear-gradient(135deg, #28a745, #20c997); border: none; }
        .engine-switch { position: fixed; bottom: 20px; right: 20px; z-index: 1000; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/jsp/">
            <i class="bi bi-chat-dots"></i> Голосование (JSP)
        </a>
        <div>
            <form action="/logout" method="post" class="d-inline">
                <button type="submit" class="btn btn-outline-light btn-sm">
                    <i class="bi bi-box-arrow-right"></i> Выйти
                </button>
            </form>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="text-center text-white mb-5">
        <h1 class="display-4 fw-bold">Онлайн голосование</h1>
        <p class="lead">Выберите опрос для участия</p>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mb-4 shadow">
                <div class="card-body text-center">
                    <i class="bi bi-shield-lock-fill display-1 text-primary"></i>
                    <h3>Панель администратора</h3>
                    <a href="/jsp/login" class="btn btn-primary btn-lg px-5 mt-3">Войти как админ</a>
                </div>
            </div>

            <c:choose>
                <c:when test="${empty surveys}">
                    <div class="alert alert-info text-center">Нет активных опросов</div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${surveys}" var="survey">
                        <div class="card shadow mb-3">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h3 class="card-title">${survey.title}</h3>
                                    <p class="text-muted">${survey.polls.size()} вопросов</p>
                                </div>
                                <a href="/jsp/survey?id=${survey.id}" class="btn btn-start text-white px-4 py-2">
                                    Пройти <i class="bi bi-arrow-right"></i>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<div class="engine-switch">
    <div class="card shadow-sm bg-dark">
        <div class="card-body p-2">
            <small class="text-white-50 d-block text-center mb-1">Движок шаблонов:</small>
            <div class="btn-group btn-group-sm">
                <a href="/" class="btn btn-outline-light">Thymeleaf</a>
                <a href="/jsp/" class="btn btn-success active">JSP</a>
                <a href="/freemarker/" class="btn btn-outline-light">FreeMarker</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>