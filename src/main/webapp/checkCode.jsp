<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Code Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #121212;
            color: #ffffff;
        }
        .container {
            background-color: #1e1e1e;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 300px;
        }
        .container h1 {
            font-size: 1.5em;
            margin-bottom: 10px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-size: 0.9em;
        }
        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #444;
            border-radius: 5px;
            background-color: #2b2b2b;
            color: #ffffff;
            outline: none;
        }
        input:focus {
            border-color: #1db954;
        }
        .btn {
            background-color: #1db954;
            color: #ffffff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            width: 100%;
            cursor: pointer;
            font-size: 1em;
        }
        .btn:hover {
            background-color: #17a74a;
        }
        .error {
            color: #ff5555;
            font-size: 0.9em;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%
    String code = String.valueOf(Objects.requireNonNullElse(request.getSession().getAttribute("code"), "0"));
%>
<div class="container">
    <h1>Verify Your Code</h1>
    <form action="/verifyCodeServlet" method="post">
        <div class="form-group">
            <label for="verificationCode">Enter Verification Code:</label>
            <input type="hidden" name="code" value="<%=code%>">
            <input type="text" id="verificationCode" name="verCode" placeholder="Enter code" required>
        </div>
        <button type="submit" class="btn">Verify</button>
    </form>
</div>
</body>
</html>
