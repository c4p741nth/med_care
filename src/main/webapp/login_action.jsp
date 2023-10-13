<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>

<%
    // Retrieve the email and password from the request parameters
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Create an instance of UserDAO
    UserDAO userDAO = new UserDAO();

    // Call the login method
    User user = userDAO.login(email, password);

    // Check if the user exists
    if (user != null) {
        // Start a session and set necessary attributes
        session.setAttribute("loggedInUser", user);
        response.sendRedirect("homepage.jsp"); // Redirect to the homepage after successful login
    } else {
        // If the login fails, set an error message and redirect back to the login page
        session.setAttribute("loginError", "Invalid email or password. Please try again.");
        response.sendRedirect("login.jsp");
    }

    // Close the database connection
    userDAO.closeConnection();
%>