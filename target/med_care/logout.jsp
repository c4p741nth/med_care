<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="th.ac.kku.*" %>
<%
    // Invalidate the session to log out the user
    session.invalidate();

    // Redirect the user to the login page after logging out
    response.sendRedirect("login-user.jsp");
%>
