<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="th.ac.kku.*,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      body {
        font-family: Arial, Helvetica, sans-serif;
      }

      * {
        box-sizing: border-box;
      }

      /* Full-width input fields */
      input[type="text"],
      input[type="password"] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
      }

      input[type="text"]:focus,
      input[type="password"]:focus {
        background-color: #ddd;
        outline: none;
      }

      hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
      }

      /* Set a style for all buttons */
      button {
        background-color: #04aa6d;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
      }

      button:hover {
        opacity: 1;
      }

      /* Extra styles for the cancel button */
      .cancelbtn {
        padding: 14px 20px;
        background-color: #f44336;
      }

      /* Float cancel and signup buttons and add an equal width */
      .cancelbtn,
      .signupbtn {
        float: left;
        width: 50%;
      }

      /* Add padding to container elements */
      .container {
        padding: 16px;
        max-width: 400px; /* Added for better readability on larger screens */
        margin: 0 auto; /* Center align on larger screens */
      }

      /* Clear floats */
      .clearfix::after {
        content: "";
        clear: both;
        display: table;
      }

      /* Change styles for cancel button and signup button on extra small screens */
      @media screen and (max-width: 600px) {
        .cancelbtn,
        .signupbtn {
          width: 100%;
        }
      }

      .imgcontainer {
        text-align: center;
        margin: 24px 0 12px 0;
      }

      img.avatar {
        width: 20%;
        border-radius: 20%;
      }
    </style>
  </head>
  <body>
    <form
      action="signup_action.jsp"
      method="post"
      style="border: 1px solid #ccc"
    >
      <div class="imgcontainer">
        <img src="images/04.jpg" alt="Avatar" class="avatar" />
      </div>

      <div class="container">
        <h1 style="text-align: center">Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr />

        <input type="hidden" name="id" value="" />

        <label for="citizen_id"><b>Citizen ID</b></label>
        <input
          type="text"
          placeholder="Enter Citizen ID"
          name="citizen_id"
          required
        />

        <label for="firstname"><b>First Name</b></label>
        <input
          type="text"
          placeholder="Enter Firstname"
          name="firstname"
          required
        />

        <label for="lastname"><b>Last Name</b></label>
        <input
          type="text"
          placeholder="Enter Lastname"
          name="lastname"
          required
        />

        <label for="gender"><b>Gender</b></label>
        <select name="gender" required>
          <option value="0">Male</option>
          <option value="1">Female</option>
        </select>

        <label for="birthdate"><b>Birth Date</b></label>
        <input type="date" name="birthdate" required />

        <label for="address"><b>Address</b></label>
        <input
          type="text"
          placeholder="Enter Address"
          name="address"
          required
        />

        <label for="mobile"><b>Mobile</b></label>
        <input type="text" placeholder="Enter Mobile" name="mobile" required />

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required />

        <label for="password"><b>Password</b></label>
        <input
          type="password"
          placeholder="Enter Password"
          name="password"
          required
        />

        <label for="password_confirm"><b>Confirm Password</b></label>
        <input
          type="password"
          placeholder="Confirm Password"
          name="password_confirm"
          required
        />

        <input type="hidden" name="accessLevel" value="1" />

        <div class="clearfix">
          <a href="index.jsp">
            <button type="button" class="cancelbtn">Cancel</button>
          </a>
          <button type="submit" class="signupbtn">Sign Up</button>
        </div>
      </div>
    </form>
  </body>
</html>
