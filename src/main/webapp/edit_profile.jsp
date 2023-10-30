<!-- The edit profile page -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%@ page import="th.ac.kku.User" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Add the necessary meta tags and CSS links here -->
    <title>Edit Profile</title>
</head>
<body>
    <%
    request.setCharacterEncoding("UTF-8");
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
    %>
    <h1>Edit Profile</h1>
    <form action="edit_action.jsp" method="post">
        <%-- Assuming you have a form to edit user profile data --%>
        <input name="id" value="<%=loggedInUser.getID()%>" type="hidden">
        <label for="firstname">Firstname:</label>
        <input type="text" id="firstname" name="firstname" value="<%= loggedInUser.getFirstname() %>" readonly><br><br>

        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" name="lastname" value="<%= loggedInUser.getLastname() %>" readonly><br><br>

        <label for="citizen_id">Citizen ID:</label>
        <input type="text" id="citizen_id" name="citizen_id" value="<%= loggedInUser.getCitizenID() %>" readonly><br><br>

        <label for="gender">Gender:</label>
<%
    int genderValue = loggedInUser.getGender();
    String genderString = (genderValue == 1) ? "Female" : "Male";
%>
<input type="text" id="gender" name="gender" value="<%= genderString %>" readonly><br><br>


        <label for="birth_date">Birth Date:</label>
        <input type="date" id="birth_date" name="birth_date" value="<%= loggedInUser.getBirthDate() %>" readonly><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= loggedInUser.getAddress() %>"><br><br>

        <label for="mobile">Mobile:</label>
        <input type="text" id="mobile" name="mobile" value="<%= loggedInUser.getMobile() %>"><br><br>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="<%= loggedInUser.getEmail() %>"><br><br>

        <label for="allergic">Allergic:</label>
        <input type="text" id="allergic" name="allergic" value="<%= loggedInUser.getAllergic() %>"><br><br>

        <label for="blood_group">Blood Group:</label>
        <input type="text" id="blood_group" name="blood_group" value="<%= loggedInUser.getBlood_group() %>"><br><br>

        <label for="weight">Weight:</label>
        <input type="text" id="weight" name="weight" value="<%= loggedInUser.getWeight() %>"><br><br>

        <label for="height">Height:</label>
        <input type="text" id="height" name="height" value="<%= loggedInUser.getHeight() %>"><br><br>

        <label for="chronic_disease">Chronic Disease:</label>
        <input type="text" id="chronic_disease" name="chronic_disease" value="<%= loggedInUser.getChronic_disease() %>"><br><br>

        <input type="submit" value="Submit">
    </form>
    <a href="profile.jsp">Back to Profile</a>
    <%
        } else {
            // If the user is not logged in, redirect back to the login page
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
