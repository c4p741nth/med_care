<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*,java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Vertical centering */
        }

        .inner-container {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .cancelbtn {
            width: 100%;
            padding: 10px 18px;
            background-color: #f44336;
        }

        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        img.avatar {
            width: 40%;
            border-radius: 40%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for screens smaller than 600px */
        @media screen and (max-width: 600px) {
            .inner-container {
                width: 90%;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="inner-container">
            <h2 style="text-align: center;">Login Form</h2>
            <form action="login_action.jsp" method="post">
                <div class="imgcontainer">
                    <img src="images/05.jpg" alt="Avatar" class="avatar">
                </div>
    
                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>
    
                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
    
                <button type="submit">Login</button>
    
                <label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>
    
                <a href="homepage.jsp">
                    <button type="button" class="cancelbtn">Cancel</button>
                </a>
    
                <!-- Display error message here if login fails -->
                <%
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
    
                    // Create an instance of UserDAO
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.login(email, password);
    
                    if (user == null) {
                        out.println("<p class=\"error\">Invalid email or password. Please try again.</p>");
                    }
                %>
    
            </form>
        </div>
    </div>

</body>
</html>
