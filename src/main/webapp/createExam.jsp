<%@ page import="uz.pdp.testmaster.web.Repo.CourseRepo" %>
<%@ page import="uz.pdp.testmaster.web.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.testmaster.web.entity.Groups" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/10/2024
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Exam Page</title>
</head>
<body>
<%
    List<Course> coursesList = CourseRepo.getCoursesList();
%>
<select name="courseId" class="form-select" required>
    <option value="" disabled selected>Select Course</option>
    <% for (Course course : coursesList) { %>
    <option value="<%= course.getId() %>"><%= course.getCourseName() %>
    </option>
    <% } %>
</select>




</body>
</html>
