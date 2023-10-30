<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%@ page import="th.ac.kku.User" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Site Metas -->
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Mico</title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">  <!-- fonts style -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&amp;display=swap" rel="stylesheet"> 
        <!--owl slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <!-- nice select -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha256-mLBIhmBvigTFWPSCtvdu6a76T+3Xyt+K571hupeFLg4=" crossorigin="anonymous">
        <!-- datepicker -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css">
        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet">
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet">
        <!-- logout style -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .center-label1 {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 10vh;
                margin: 0;
            }
            .center-label2 {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
            }
        </style>
      </head>
<body>
    <header class="header_section">
        <div class="header_bottom">
          <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container ">
              <a class="navbar-brand" href="homepage.jsp">
                <img src="images/logo.png" alt="">
              </a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="d-flex mr-auto flex-column flex-lg-row align-items-center">
                  <ul class="navbar-nav  ">
                    <li class="nav-item active">
                      <a class="nav-link" href="homepage.jsp">Home <span class="sr-only"></span></a>
                    </li>                  
                  </ul>
                </div>
                <div class="quote_btn-container">
                  <a href="profile.jsp">
                    <span>
                      Profile
                    </span>
                  </a>
                  <a href="logout.jsp">  
                      <span>
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                      </span>
                    </a>       
                </div>
              </div>
            </nav>
          </div>
        </div>
      </header>
    <h1 class="center-label1">User Profile</h1>

    <%
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
    %>
    <p class="center-label2">Welcome, <%= loggedInUser.getFirstname() %> <%= loggedInUser.getLastname() %></p>
    <p class="center-label2">Citizen ID: <%= loggedInUser.getCitizenID() %></p>
    <p class="center-label2">Gender: <%= loggedInUser.getGender() == 1 ? "Male" : "Female" %></p>
    <p class="center-label2">Birth Date: <%= loggedInUser.getBirthDate() %></p>
    <p class="center-label2">Address: <%= loggedInUser.getAddress() %></p>
    <p class="center-label2">Mobile: <%= loggedInUser.getMobile() %></p>
    <p class="center-label2">Email: <%= loggedInUser.getEmail() %></p>
    <p class="center-label2">Allergic: <%= loggedInUser.getAllergic() %></p>
    <p class="center-label2">Blood Group: <%= loggedInUser.getBlood_group() %></p>
    <p class="center-label2">Weight: <%= loggedInUser.getWeight() %> kg</p>
    <p class="center-label2">Height: <%= loggedInUser.getHeight() %> cm</p>
    <p class="center-label2">Chronic Disease: <%= loggedInUser.getChronic_disease() %></p>
    <!-- You can display more user information as needed -->
    <a class="center-label2" href="homepage.jsp">
          Back
      </a>
    <a class="center-label2" href="edit_profile.jsp">
        Edit
    </a>
    <%
        } else {
            // If the user is not logged in, redirect back to the login page
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
