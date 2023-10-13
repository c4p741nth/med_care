<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>

<%
    // Get form parameters
    String citizenId = request.getParameter("citizen_id");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    int gender = Integer.parseInt(request.getParameter("gender"));
    String birthdate = request.getParameter("birthdate");
    String address = request.getParameter("address");
    String mobile = request.getParameter("mobile");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("password_confirm");
    int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));

    // Check if the password and confirm password match
    if (!password.equals(confirmPassword)) {
        // Handle password mismatch error
        out.println("Passwords do not match.");
    } else {
        // Create a new User object
        User user = new User();
        user.setCitizenId(citizenId);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(gender);
        // Convert birthdate to the appropriate format
        java.sql.Date sqlBirthdate = java.sql.Date.valueOf(birthdate);
        user.setBirthDate(sqlBirthdate);
        user.setAddress(address);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setPassword(password);
        user.setAccessLevel(accessLevel);

        // Initialize UserDAO and register the user
        UserDAO userDAO = new UserDAO();
        userDAO.registerUser(user);
        userDAO.closeConnection();

        // Redirect to a success page or display a success message
        out.println("User registered successfully.");
    }
%>
