<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Голосование - FreeMarker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .navbar { background: #343a40; }
        .card { transition: all 0.3s; }
        .card:hover { transform: translateY(-5px); }
        .btn-free { background: linear-gradient(135deg, #ff6b6b, #ee5a24); border: none; }
        .engine-switch { position: fixed; bottom: 20px; right: 20px; z-index: 1000; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/freemarker/">
            <i class="bi bi-chat-dots"></i> Голосование (FreeMarker)
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
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow mb-4">
                <div class="card-body text-center">
                    <i class="bi bi-shield-lock-fill display-1 text-primary"></i>
                    <h3>Админ панель</h3>
                    <a href="/freemarker/login" class="btn btn-primary btn-lg px-5 mt-3">Войти</a>
                </div>
            </div>

            <#if surveys?has_content>
                <#list surveys as survey>
                    <div class="card shadow mb-3">
                        <div class="card-body d-flex justify-content-between align-items-center">
                            <div>
                                <h3>${survey.title}</h3>
                                <p>${survey.polls?size} вопросов</p>
                            </div>
                            <a href="/freemarker/survey?id=${survey.id}" class="btn btn-free text-white px-4 py-2">
                                Пройти <i class="bi bi-arrow-right"></i>
                            </a>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="alert alert-info">Нет активных опросов</div>
            </#if>
        </div>
    </div>
</div>

<div class="engine-switch">
    <div class="card shadow-sm bg-dark">
        <div class="card-body p-2">
            <small class="text-white-50 d-block text-center mb-1">Движок шаблонов:</small>
            <div class="btn-group btn-group-sm">
                <a href="/" class="btn btn-outline-light">Thymeleaf</a>
                <a href="/jsp/" class="btn btn-outline-light">JSP</a>
                <a href="/freemarker/" class="btn btn-success active">FreeMarker</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>