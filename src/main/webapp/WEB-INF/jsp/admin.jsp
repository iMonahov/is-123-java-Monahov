<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Админ панель - JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .navbar { background: linear-gradient(135deg, #343a40, #212529); }
        .engine-switch { position: fixed; bottom: 20px; right: 20px; z-index: 1000; }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <span class="navbar-brand"><i class="bi bi-shield-lock"></i> Админ панель (JSP)</span>
        <form action="/logout" method="post">
            <button class="btn btn-outline-light btn-sm">Выйти</button>
        </form>
    </div>
</nav>

<div class="container py-4">
    <div class="d-flex justify-content-between mb-4">
        <h1>Управление опросами</h1>
        <a href="/jsp/add-survey" class="btn btn-success">+ Создать опрос</a>
    </div>

    <div class="row">
        <c:forEach items="${surveys}" var="survey">
            <div class="col-md-6 mb-3">
                <div class="card">
                    <div class="card-header bg-dark text-white">${survey.title}</div>
                    <div class="card-body">
                        <p>Вопросов: ${survey.polls.size()}</p>
                        <c:forEach items="${survey.polls}" var="poll">
                            <div class="border-bottom mb-2 pb-2">
                                <strong>${poll.question}</strong>
                                <ul class="mt-1">
                                    <c:forEach items="${poll.options}" var="opt">
                                        <li>${opt.text} (${opt.votes.size()} голосов)</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
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