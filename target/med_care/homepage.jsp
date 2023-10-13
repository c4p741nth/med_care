<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%
    // Check if the user is logged in
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        // If the user is not logged in, redirect back to the login page
        response.sendRedirect("login.jsp");
    } else {
        // If the user is logged in, display the homepage
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        /* CSS styles */
    </style>
</head>
<body>
    <div class="container">
        <div class="inner-container">
            This is homepage!
        </div>
    </div>
</body>
</html>
<%
    }
%>
