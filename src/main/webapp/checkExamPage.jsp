<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="uz.pdp.testmaster.web.Repo.CourseRepo" %>
<%@ page import="uz.pdp.testmaster.web.entity.Course" %>
<%@ page import="uz.pdp.testmaster.web.entity.Groups" %>
<%@ page import="uz.pdp.testmaster.web.Repo.GroupRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Imtihon Tekshirish Sahifasi</title>

    <!-- Bootstrap 5 uchun CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f0f0f0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .container {
            margin-top: 50px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
        }

        .btn {
            font-size: 1.1em;
            padding: 12px 20px;
            border-radius: 5px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border: 1px solid #ddd;
            font-size: 1.1em;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        .table-container {
            margin-top: 20px;
            font-size: 1.1em;
        }

        .table th, .table td {
            padding: 15px 20px;
        }

        .table td {
            font-weight: bold;
        }

        .text-center {
            text-align: center;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border: 1px solid #ddd;
            font-size: 1.1em;
            font-weight: bold; /* Qalin harflar */
            color: #000000; /* Qora rang */
        }

        td {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Imtihon ma'lumotlarini tekshirish</h1>

    <form action="/createExam" method="post">
        <%
            Integer groupsId = Integer.parseInt(request.getParameter("courseId"));
            Groups group = GroupRepo.getById(groupsId);
            String courseName = group.getCourse().getCourseName();
            Integer moduleId = Integer.parseInt(request.getParameter("moduleId"));
            String date = request.getParameter("date");
            String time = request.getParameter("time");

            // Vaqtni hisoblash
            LocalTime startTime = LocalTime.parse(time);
            LocalTime endTime = startTime.plusHours(3); // 3 soat qo'shish
        %>


        <!-- Jadval -->
        <div class="table-container">
            <table class="table table-bordered">
                <tr>
                    <th>Yo'nalish</th>
                    <td><%= courseName %></td>
                </tr>
                <tr>
                    <th>Guruh</th>
                    <td><%= group.getGroupName() %></td>
                </tr>
                <tr>
                    <th>Modul</th>
                    <td><%= moduleId %></td>
                </tr>
                <tr>
                    <th>Imtihon sanasi</th>
                    <td><%= date %></td>
                </tr>
                <tr>
                    <th>Boshlanish vaqti</th>
                    <td><%= time %> dan</td>
                </tr>
                <tr>
                    <th>Tugash vaqti</th>
                    <td><%= endTime %> gacha</td>
                </tr>
                <tr>
                    <th>Imtihon davomiyligi(majburiy)</th>
                    <td>3 SOAT</td>
                </tr>
            </table>
        </div>

        <!-- Tasdiqlash va orqaga qaytish tugmalari -->
        <div class="form-group text-center">
            <input type="hidden" name="courseId" value="<%=group.getCourse().getId()%>">
            <input type="hidden" name="groupId" value="<%=group.getId()%>">
            <input type="hidden" name="modulNumber" value="<%=moduleId%>">
            <input type="hidden" name="startTime" value="<%=startTime%>">
            <input type="hidden" name="endTime" value="<%=endTime%>">
            <input type="hidden" name="date" value="<%=date%>">
            <button type="submit" class="btn btn-primary">Tasdiqlash</button>
            <a href="javascript:history.back()" class="btn btn-secondary">Tahrirlash</a>
        </div>
    </form>
</div>

<!-- Bootstrap JS uchun script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
