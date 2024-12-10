<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        body {
            background-color: #f7f7f7;
        }

        .login-container {
            max-width: 600px;
            margin: auto;
            padding: 40px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            margin-top: 100px;
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
            color: #343a40;
        }

        .form-control {
            border-radius: 10px;
        }

        .btn-dark {
            width: 100%;
            padding: 12px;
            border-radius: 10px;
        }

        .card {
            border-radius: 10px;
        }

        .card-header {
            background-color: #343a40;
            color: white;
        }

        .card-body {
            padding: 40px;
        }

        .text-center a {
            color: #343a40;
            text-decoration: none;
        }

        .text-center a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="card">
        <div class="card-header">
            <h1 style="color: white">Login sahifa</h1>
        </div>
        <div class="card-body">
            <form action="/loginServlet" method="post">
                <div class="mb-3">
                    <input type="text" class="form-control" name="gmail" placeholder="Gmail" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" name="password" placeholder="Parol" required>
                </div>
                <button type="submit" class="btn btn-dark">KIRISH</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
