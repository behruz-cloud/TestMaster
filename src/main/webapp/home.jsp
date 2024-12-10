<%@ page import="uz.pdp.testmaster.web.Repo.ExamRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.testmaster.web.entity.Exam" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam List</title>
    <!-- Bootstrap CSS Link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h1 {
            color: #343a40;
            margin-top: 20px;
        }

        .btn-create {
            background-color: #198754;
            color: white;
            font-weight: bold;
        }

        .btn-create:hover {
            background-color: #157347;
        }

        .btn-qr {
            background-color: #0d6efd;
            color: white;
        }

        .btn-qr:hover {
            background-color: #0a58ca;
        }

        table {
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .pagination button {
            margin: 5px;
        }

        .pagination button.active {
            background-color: #0d6efd;
            color: white;
            font-weight: bold;
        }

        .search-bar input {
            width: 250px;
            padding: 5px;
        }

        .search-bar button {
            background-color: #0d6efd;
            color: white;
        }

        .search-bar button:hover {
            background-color: #0a58ca;
        }
    </style>
</head>
<body>
<%
    // Imtihonlar ro'yxatini olish
    int pagejon = Integer.parseInt(Objects.requireNonNullElse(request.getParameter("page"), "0"));
    String search = Objects.requireNonNullElse(request.getParameter("search"), "");
    List<Exam> exams = ExamRepo.getAllExams(search,pagejon);


    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>
<form action="/createExam.jsp" class="text-center" method="post">
    <button class="btn btn-create">EXAM YARATISH</button>
</form>

<form class="search-bar mt-4 d-flex justify-content-center" action="">
    <input type="search" name="search" placeholder="Qidiruv..." class="form-control me-2" value="<%= search %>">
    <button class="btn btn-primary">Qidiruv 🔎</button>
</form>

<div class="container">
    <h1 class="text-center">Imtihonlar ro'yxati</h1>
    <table class="table table-hover table-bordered mt-4">
        <thead class="table-dark">
        <tr>
            <th>Yo'nalish</th>
            <th>Guruh</th>
            <th>Modul</th>
            <th>Imtihon sanasi</th>
            <th>Boshlanish vaqti</th>
            <th>Tugash vaqti</th>
            <th>Action (QR Code)</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Exam exam : exams) {
        %>
        <tr>
            <td><%= exam.getCourse().getCourseName() %></td>
            <td><%= exam.getGroup().getGroupName() %></td>
            <td><%= exam.getModuleNumber() %></td>
            <td><%= exam.getDate().format(dateFormatter) %></td>
            <td><%= exam.getStartTime().format(timeFormatter) %></td>
            <td><%= exam.getEndTime().format(timeFormatter) %></td>
            <td>
                <form action="">
                    <button class="btn btn-qr">QR code</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div class="pagination text-center">
        <%
            long count = ExamRepo.count(search);
            int pageCount = (int) Math.ceil(count / 2.0);
            for (int i = 0; i <= pageCount; i++) {
                String activeClass = (i == pagejon) ? "active" : "";
        %>
        <form action="?page=<%= i %>&search=<%= search %>" method="post" class="d-inline">
            <button class="btn <%= activeClass %>"><%= i %></button>
        </form>
        <%
            }
        %>
</div>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
