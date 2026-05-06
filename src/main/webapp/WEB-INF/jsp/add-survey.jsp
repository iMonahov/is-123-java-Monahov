<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить опрос - JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .engine-switch { position: fixed; bottom: 20px; right: 20px; z-index: 1000; }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <span class="navbar-brand"><i class="bi bi-shield-lock"></i> Добавление опроса (JSP)</span>
        <a href="/jsp/admin" class="btn btn-outline-light btn-sm">Назад</a>
    </div>
</nav>

<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4>Создать новый опрос</h4>
                </div>
                <div class="card-body">
                    <form action="/jsp/add-survey" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название опроса:</label>
                            <input type="text" name="title" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Вопрос 1:</label>
                            <input type="text" name="questions" class="form-control mb-2" required>
                            <label class="form-label">Варианты ответов:</label>
                            <input type="text" name="options" class="form-control mb-1" placeholder="Вариант 1" required>
                            <input type="text" name="options" class="form-control mb-1" placeholder="Вариант 2" required>
                            <input type="text" name="options" class="form-control mb-1" placeholder="Вариант 3">
                            <input type="text" name="options" class="form-control mb-1" placeholder="Вариант 4">
                        </div>
                        <button type="submit" class="btn btn-success">Создать</button>
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
                <a href="/jsp/" class="btn btn-success active">JSP</a>
                <a href="/freemarker/" class="btn btn-outline-light">FreeMarker</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>