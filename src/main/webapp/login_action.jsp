<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UserDAO userDAO = new UserDAO();
    User user = userDAO.login(email, password);

    if (user != null) {
        // Start a session and set necessary attributes
        session.setAttribute("loggedInUser", user);
        response.sendRedirect("homepage.jsp"); // Redirect to the homepage after successful login
    } else {
        // If the login fails, set an error message and redirect back to the login page
        session.setAttribute("loginError", "Invalid email or password. Please try again.");
        response.sendRedirect("login-user.jsp");
    }
    // Close the database connection
    userDAO.closeConnection();
%>