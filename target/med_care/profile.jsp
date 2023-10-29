<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%@ page import="th.ac.kku.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>User Profile</h1>

    <%
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
    %>
    <p>Welcome, <%= loggedInUser.getFirstname() %> <%= loggedInUser.getLastname() %></p>
    <p>Citizen ID: <%= loggedInUser.getCitizenID() %></p>
    <p>Gender: <%= loggedInUser.getGender() == 1 ? "Male" : "Female" %></p>
    <p>Birth Date: <%= loggedInUser.getBirthDate() %></p>
    <p>Address: <%= loggedInUser.getAddress() %></p>
    <p>Mobile: <%= loggedInUser.getMobile() %></p>
    <p>Email: <%= loggedInUser.getEmail() %></p>
    <p>Allergic: <%= loggedInUser.getAllergic() %></p>
    <p>Blood Group: <%= loggedInUser.getBlood_group() %></p>
    <p>Weight: <%= loggedInUser.getWeight() %> kg</p>
    <p>Height: <%= loggedInUser.getHeight() %> cm</p>
    <p>Chronic Disease: <%= loggedInUser.getChronic_disease() %></p>
    <!-- You can display more user information as needed -->
    <a href="homepage.jsp">
          Back
      </a>
    <%
        } else {
            // If the user is not logged in, redirect back to the login page
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
