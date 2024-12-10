<%@ page import="uz.pdp.testmaster.web.Repo.CourseRepo" %>
<%@ page import="uz.pdp.testmaster.web.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.testmaster.web.entity.Groups" %>
<%@ page import="uz.pdp.testmaster.web.Repo.GroupRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Imtihon yaratish</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery va jQuery UI -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }
        .container {
            margin-top: 50px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-label {
            font-weight: bold;
            color: #495057;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #343a40;
        }
        footer {
            margin-top: 20px;
            text-align: center;
            font-size: 0.9em;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>IMTIHON YARATISH</h1>
    <form action="/checkExamPage.jsp" method="post">
        <%
            List<Groups> groups = GroupRepo.getAllGroups();
        %>
        <div class="mb-3">
            <label for="courseId" class="form-label">Guruhni tanlang</label>
            <select name="courseId" id="courseId" class="form-select" required>
                <option value="" disabled selected>Guruh tanlangani yo'q</option>
                <% for (Groups group : groups) { %>
                <option value="<%= group.getId() %>"><%= group.getGroupName() %></option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="moduleId" class="form-label">Modulni tanlang</label>
            <select name="moduleId" id="moduleId" class="form-select" required>
                <option value="" disabled selected>Modul tanlangani yo'q</option>
                <% for (int i = 1; i <= 10; i++) { %>
                <option value="<%= i %>"><%= i %> - modul</option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="datePicker" class="form-label">Imtihon sanasini tanlang</label>
            <input type="text" id="datePicker" name="date" class="form-control" placeholder="YYYY-MM-DD" required>
        </div>

        <div class="mb-3">
            <label for="timePicker" class="form-label">Boshlanish vaqi</label>
            <select name="time" id="timePicker" class="form-select" required>
                <option value="" disabled selected>Vaqt tanlamadi</option>
                <% for (int hour = 9; hour <= 21; hour += 3) { %>
                <option value="<%= String.format("%02d:00", hour) %>">
                    <%= String.format("%02d:00", hour) %>
                </option>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary w-100">Imtihonni yaratish</button>
    </form>
</div>

<footer>
    © 2024 TestMaster. Ishonchli, tez va qulay.
</footer>

<script>
    $(function () {
        $("#datePicker").datepicker({
            dateFormat: "yy-mm-dd",
            minDate: 0
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
