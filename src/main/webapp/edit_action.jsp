<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="th.ac.kku.UserDAO" %>
<%@ page import="th.ac.kku.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>

<%
    request.setCharacterEncoding("UTF-8");
    // Check if the user is logged in
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser != null) {
        try {
            // Get the form data from edit_profile.jsp
            int id = Integer.parseInt(request.getParameter("id"));
            String citizen_id = request.getParameter("citizen_id");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            int gender = "female".equalsIgnoreCase(request.getParameter("gender")) ? 1 : 0;
            String birth_date = request.getParameter("birth_date");
            java.sql.Date birthdate = java.sql.Date.valueOf(birth_date); 
            String address = request.getParameter("address");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String allergic = request.getParameter("allergic");
            String bloodGroup = request.getParameter("blood_group");
            double weight = Double.parseDouble(request.getParameter("weight"));
            double height = Double.parseDouble(request.getParameter("height"));
            String chronic_disease = request.getParameter("chronic_disease");

            // Create a User object with the updated information
            User updatedUser = new User();
            updatedUser.setID(id);
            updatedUser.setCitizenID(citizen_id);
            updatedUser.setFirstname(firstname);
            updatedUser.setLastname(lastname);
            updatedUser.setGender(gender);
            updatedUser.setBirthDate(birthdate);
            updatedUser.setAddress(address);
            updatedUser.setMobile(mobile);
            updatedUser.setEmail(email);
            updatedUser.setAllergic(allergic);
            updatedUser.setBlood_group(bloodGroup);
            updatedUser.setWeight(weight);
            updatedUser.setHeight(height);
            updatedUser.setChronic_disease(chronic_disease);

            // Call the UserDAO method to update the user profile
            UserDAO userDAO = new UserDAO();
            userDAO.editUserProfile(updatedUser);

            // Update the loggedInUser session attribute
            session.setAttribute("loggedInUser", updatedUser);

            // Redirect back to the profile page
            response.sendRedirect("profile.jsp");
        } catch (SQLException | IOException e) {
            out.println("Error: " + e.getMessage());
        }
    } else {
        // If the user is not logged in, redirect back to the login page
        response.sendRedirect("login.jsp");
    }
%>
