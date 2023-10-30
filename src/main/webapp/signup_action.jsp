<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.User, th.ac.kku.UserDAO" %>

<%
    request.setCharacterEncoding("UTF-8");
    // Retrieve form data
    String citizenId = request.getParameter("citizen_id");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    int gender = Integer.parseInt(request.getParameter("gender"));
    String birthdateString = request.getParameter("birthdate");
    java.sql.Date birthdate = java.sql.Date.valueOf(birthdateString); // Assuming the date format is yyyy-MM-dd
    String address = request.getParameter("address");
    String mobile = request.getParameter("mobile");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("password_confirm");

    // Check if the password and confirm password match
    if (!password.equals(confirmPassword)) {
        // Handle password mismatch error
        out.println("Passwords do not match.");
    } else {
        // Create a new User object
        User user = new User();
        user.setCitizenID(citizenId);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(gender);
        user.setBirthDate(birthdate);
        user.setAddress(address);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setPassword(password);
        user.setAllergic("");
        user.setBlood_group("");
        user.setWeight(0.0);
        user.setHeight(0.0);
        user.setChronic_disease("");

        // Initialize UserDAO and register the user
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.registerUser(user);
            userDAO.closeConnection();
            // Redirect to a success page or display a success message
            String message = "User registered successfully.";
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + message + "');");
            out.println("window.location.href='index.jsp';");
            out.println("</script>");
        } catch (Exception e) {
            // Handle exceptions appropriately
            out.println("An error occurred while registering the user: " + e.getMessage());
            e.printStackTrace();
        }
    }
%>
