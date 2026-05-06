<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Голосование - ${survey.title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .navbar { background: #343a40; }
        .btn-vote { background: linear-gradient(135deg, #ff6b6b, #ee5a24); border: none; border-radius: 50px; padding: 12px 40px; }
        .question-card { transition: all 0.3s; }
        .engine-switch { position: fixed; bottom: 20px; right: 20px; z-index: 1000; }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/freemarker/">FreeMarker Голосование</a>
        <form action="/logout" method="post" class="d-inline">
            <button type="submit" class="btn btn-outline-light btn-sm">Выйти</button>
        </form>
    </div>
</nav>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <#if .request.getParameter("voted")??>
                <div class="alert alert-success text-center shadow mb-4">
                    <i class="bi bi-check-circle-fill fs-3"></i>
                    <h4 class="mt-2">Спасибо за участие!</h4>
                </div>
            </#if>

            <div class="card shadow">
                <div class="card-header bg-dark text-white text-center">
                    <h2>${survey.title}</h2>
                </div>
                <div class="card-body p-4">
                    <form action="/freemarker/vote" method="post">
                        <input type="hidden" name="surveyId" value="${survey.id}">

                        <#list survey.polls as poll>
                            <div class="question-card card mb-4">
                                <div class="card-header bg-light">
                                    <h5><span class="badge bg-primary me-2">${poll?counter}</span>${poll.question}</h5>
                                </div>
                                <div class="card-body">
                                    <#list poll.options as option>
                                        <div class="form-check mb-2">
                                            <input type="radio" class="form-check-input"
                                                   name="poll_${poll.id}" value="${option.id}" required>
                                            <label class="form-check-label">${option.text}</label>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </#list>

                        <div class="card mb-4">
                            <div class="card-header bg-info text-white">
                                <h5><i class="bi bi-person-badge"></i> О вас</h5>
                            </div>
                            <div class="card-body">
                                <label class="form-label">Возраст (необязательно):</label>
                                <input type="number" name="age" class="form-control" style="max-width:200px" min="10" max="120">
                            </div>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="/freemarker/" class="btn btn-secondary">Назад</a>
                            <button type="submit" class="btn btn-vote text-white">Проголосовать</button>
                        </div>
                    </form>
                </div>
            </div>
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