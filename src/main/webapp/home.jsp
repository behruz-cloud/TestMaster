<%@ page import="uz.pdp.testmaster.web.Repo.ExamRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.testmaster.web.entity.Exam" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Objects" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exam List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #343a40;
        }

        .page-header {
            background-color: #198754;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 2rem;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
            border-bottom: 5px solid #157347;
            position: relative;
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
            text-align: center;
        }

        thead th {
            vertical-align: middle;
        }

        tbody td {
            vertical-align: middle;
        }

        .pagination-container {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: #f8f9fa;
            padding: 10px 0;
            box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
        }

        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .pagination .btn {
            margin: 5px;
            padding: 8px 12px;
            border-radius: 20px;
            background-color: #e9ecef;
            transition: all 0.3s ease-in-out;
        }

        .pagination .btn:hover {
            background-color: #198754;
            color: white;
            transform: scale(1.1);
        }

        .pagination .btn.active {
            background-color: #0d6efd;
            color: white;
            transform: scale(1.1);
        }

        .pagination .btn {
            animation: slideUp 0.5s ease-in-out;
        }

        .search-bar {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            margin-bottom: 20px;
        }

        .search-bar .search-input {
            width: 300px;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 25px 0 0 25px;
            outline: none;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .search-bar .search-input:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 8px rgba(13, 110, 253, 0.3);
        }

        .search-bar .search-btn {
            padding: 10px 20px;
            background-color: #0d6efd;
            color: white;
            border: none;
            border-radius: 0 25px 25px 0;
            cursor: pointer;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .search-bar .search-btn:hover {
            background-color: #0a58ca;
        }

        .search-bar .search-btn i {
            margin-right: 5px;
        }

        /* Default Day Mode */
        body {
            background-color: #f8f9fa;
            color: #343a40;
        }

        .page-header {
            background-color: #198754;
        }

        /* Styles for night mode table */
        body.night-mode table,
        body.night-mode th,
        body.night-mode td {
            background-color: #1e2e2e;
            color: #f8f9fa;
        }

        body.night-mode h1,
        body.night-mode .btn-create,
        body.night-mode .btn-qr {
            color: white;
        }

        body.night-mode .pagination .btn {
            background-color: #444;
            color: #f8f9fa;
        }

        body.night-mode .pagination .btn:hover {
            background-color: #198754;
            color: white;
        }

        body.night-mode .pagination .btn.active {
            background-color: #0d6efd;
            color: white;
        }

        /* Position the button in the top right corner */
        .theme-toggle-container {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        /* Styling for pagination buttons */
        .pagination .btn {
            border-radius: 8px; /* Rounded corners */
            padding: 10px 15px; /* Increased padding for a bigger button */
            font-weight: bold; /* Bold text */
        }
        .table-footer {
            display: flex;
            justify-content: flex-end; /* O'ng tomonga suradi */
            margin-top: 10px;
        }

        .table-footer button {
            padding: 10px 20px;
            background-color: #0d6efd;
            color: white;
            border-radius: 25px;
            font-weight: bold;
            transition: all 0.3s ease-in-out;
        }

        .table-footer button:hover {
            background-color: #0a58ca;
            transform: scale(1.1);
        }
        button {
            padding: 12px 25px;
            font-size: 1rem;
            font-weight: bold;
            border-radius: 30px; /* Rounded corners */
            border: none;
            outline: none;
            cursor: pointer;
            transition: all 0.3s ease-in-out; /* Smooth transition */
        }

        /* Base Button Style */
        .btn-create {
            background-color: #198754;
            color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow */
        }

        .btn-create:hover {
            background-color: #157347;
            transform: scale(1.1); /* Slight zoom effect */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15); /* Shadow grows on hover */
        }

        /* QR Button Style */
        .btn-qr {
            background-color: #0d6efd;
            color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .btn-qr:hover {
            background-color: #0a58ca;
            transform: scale(1.1);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        /* Additional Styling for Other Buttons */
        .btn-secondary {
            background-color: #6c757d;
            color: white;
            font-size: 1rem;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<%
    int pagejon = Integer.parseInt(Objects.requireNonNullElse(request.getParameter("page"), "0"));
    String search = Objects.requireNonNullElse(request.getParameter("search"), "");
    String sortOrder = Objects.requireNonNullElse(request.getParameter("sortOrder"), "asc"); // Default to ascending
    List<Exam> exams = ExamRepo.getAllExams(search, pagejon,sortOrder);

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>

<div class="page-header">
    <div class="theme-toggle-container">
        <button onclick="toggleMode()" id="modeText">Kun rejimi</button>
    </div>
    TEST MASTER
</div>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center">
        <form action="/createExam.jsp" method="post">
            <button class="btn btn-create">EXAM YARATISH</button>
        </form>


        <form class="search-bar" action="" method="get">
            <input type="search" name="search" placeholder="Qidiruv..." class="search-input" value="<%= search %>">
            <button class="search-btn">
                <i class="bi bi-search"></i> Qidiruv
            </button>
        </form>
    </div>

    <h1 class="text-center">Imtihonlar ro'yxati</h1>
    <table class="table table-hover table-bordered mt-4">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Yo'nalish</th>
            <th>Guruh</th>
            <th>Modul</th>
            <th>Imtihon sanasi <span id="dateSortIcon" class="bi bi-sort-down-alt"></span></th>
            <th>Boshlanish vaqti</th>
            <th>Tugash vaqti</th>
            <th>Action (QR Code)</th>
        </tr>
        </thead>
        <tbody>
        <%
            int index = pagejon * 5; // Assuming 10 items per page
            for (Exam exam : exams) {
                index++;
        %>
        <tr>
            <td><%= index %></td>
            <td><%= exam.getCourse().getCourseName() %></td>
            <td><%= exam.getGroup().getGroupName() %></td>
            <td><%= exam.getModuleNumber() %></td>
            <td><%= exam.getDate().format(dateFormatter) %></td>
            <td><%= exam.getStartTime().format(timeFormatter) %></td>
            <td><%= exam.getEndTime().format(timeFormatter) %></td>
            <td>
                <form action="/qrCodeGenerator" method="post">
                    <input type="hidden" name="examId" value="<%=exam.getId()%>">
                    <button class="btn btn-qr">QR code</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>
    <div class="table-footer">
        <button type="button" id="sortDateBtn" class="btn btn-secondary">Sana bo'yicha tartib</button>
    </div>
</div>

<div class="pagination-container">
    <div class="pagination">
        <%
            long count = ExamRepo.count(search);
            int totalPages = (int) Math.ceil((double) count / 5);
            for (int i = 0; i < totalPages; i++) {
        %>
        <a href="?page=<%= i %>&search=<%= search %>" class="btn <%= i == pagejon ? "active" : "" %>">
            <%= i + 1 %>
        </a>
        <%
            }
        %>
    </div>
</div>

<script>
    let isDayMode = true; // Boshlang'ich holat

    function toggleMode() {
        isDayMode = !isDayMode; // Kun/tun rejimini o'zgartirish
        updateUI(); // Foydalanuvchi interfeysini yangilash
    }

    function updateUI() {
        const modeText = document.getElementById("modeText"); // Matn elementi
        const body = document.body; // Butun sahifa uchun

        if (isDayMode) {
            body.classList.remove("night"); // Tun rejimidan chiqish
            body.classList.add("day"); // Kun rejimini qo'llash
            modeText.textContent = "Kun rejimi"; // Matnni yangilash
        } else {
            body.classList.remove("day"); // Kun rejimidan chiqish
            body.classList.add("night"); // Tun rejimini qo'llash
            modeText.textContent = "Tun rejimi"; // Matnni yangilash
        }
    }
    let isAscending = true;

    document.getElementById('sortDateBtn').addEventListener('click', function() {
        isAscending = !isAscending;
        const sortIcon = document.getElementById('dateSortIcon');
        sortIcon.classList.toggle('bi-sort-down-alt', !isAscending); // Kamayish
        sortIcon.classList.toggle('bi-sort-up-alt', isAscending);   // O'sish

        // Sana bo'yicha tartiblash
        const currentUrl = new URL(window.location.href);
        currentUrl.searchParams.set('sortDate', isAscending ? 'asc' : 'desc');
        currentUrl.searchParams.set('sortOrder', isAscending ? 'asc' : 'desc'); // SortOrder ni qo'shish
        window.location.href = currentUrl.toString();
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>