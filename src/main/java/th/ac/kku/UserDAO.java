package th.ac.kku;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private Connection connection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/med_care?characterEncoding=utf-8",
                "postgres", "root");
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    // Administrator access
    public ArrayList<User> getUsers() throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<User> userList = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setID(resultSet.getInt("id"));
            user.setCitizenID(resultSet.getString("citizen_id"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setGender(resultSet.getInt("gender"));
            user.setBirthDate(resultSet.getDate("birthdate"));
            user.setAddress(resultSet.getString("address"));
            user.setMobile(resultSet.getString("mobile"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            user.setAllergic(resultSet.getString("allergic"));
            user.setBlood_group(resultSet.getString("blood_group"));
            user.setWeight(resultSet.getDouble("weight"));
            user.setHeight(resultSet.getDouble("height"));
            user.setChronic_disease(resultSet.getString("chronic_disease"));

            userList.add(user);
        }
        return userList;
    }

    // Get user
    public User getUser(String id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        pStatement.setString(1, id);
        ResultSet resultSet = pStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setID(resultSet.getInt("id"));
            user.setCitizenID(resultSet.getString("citizen_id"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setGender(resultSet.getInt("gender"));
            user.setBirthDate(resultSet.getDate("birthdate"));
            user.setAddress(resultSet.getString("address"));
            user.setMobile(resultSet.getString("mobile"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            user.setAllergic(resultSet.getString("allergic"));
            user.setBlood_group(resultSet.getString("blood_group"));
            user.setWeight(resultSet.getDouble("weight"));
            user.setHeight(resultSet.getDouble("height"));
            user.setChronic_disease(resultSet.getString("chronic_disease"));

            return user;
        } else {
            return null;
        }
    }

    // Register a new member
    public void registerUser(User user) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO users (citizen_id, firstname, lastname, gender, birth_date, address, mobile, email, password, allergic, blood_group, weight, height, chronic_disease) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pStatement.setString(1, user.getCitizenID());
        pStatement.setString(2, user.getFirstname());
        pStatement.setString(3, user.getLastname());
        pStatement.setInt(4, user.getGender());
        pStatement.setDate(5, user.getBirthDate());
        pStatement.setString(6, user.getAddress());
        pStatement.setString(7, user.getMobile());
        pStatement.setString(8, user.getEmail());
        pStatement.setString(9, user.getPassword());
        pStatement.setString(10, user.getAllergic());
        pStatement.setString(11, user.getBlood_group());
        pStatement.setDouble(12, user.getWeight());
        pStatement.setDouble(13, user.getHeight());
        pStatement.setString(14, user.getChronic_disease());
        pStatement.executeUpdate();
    }

    public User login(String email, String password) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            pStatement.setString(1, email);
            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                // Verify password here
                String storedPassword = resultSet.getString("password");
                if (!storedPassword.equals(password)) {
                    System.out.println("Passwords don't match");
                    return null; // Passwords don't match
                }

                // Populate the user object with data from the result set
                User user = new User();
                user.setID(resultSet.getInt("id"));
                user.setCitizenID(resultSet.getString("citizen_id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setAddress(resultSet.getString("address"));
                user.setMobile(resultSet.getString("mobile"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAllergic(resultSet.getString("allergic"));
                user.setBlood_group(resultSet.getString("blood_group"));
                user.setWeight(resultSet.getDouble("weight"));
                user.setHeight(resultSet.getDouble("height"));
                user.setChronic_disease(resultSet.getString("chronic_disease"));

                System.out.println("User found: " + user); // Log the user object

                return user;
            } else {
                System.out.println("No matching user found");
                return null; // No matching user found
            }
        } catch (SQLException e) {
            // Handle the SQLException, log it, and return an error message or take
            // appropriate action
            e.printStackTrace(); // This is just an example; you should handle errors appropriately
            return null; // Return null or an error indicator to signify login failure
        }
    }

    public void editUserProfile(User updatedUser) throws SQLException {
        // Check if the admin's access level is sufficient to edit this user's profile
        String sqlQuery;
        // Admin with access level 1 can edit all user profile fields
        sqlQuery = "UPDATE users SET citizen_id=?, firstname=?, lastname=?, gender=?, birth_date=?, address=?, mobile=?, email=?, allergic=?, blood_group=?, weight=?, height=?, chronic_disease=? WHERE id=?";

        // Prepare and execute the SQL update query
        PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
        pStatement.setString(1, updatedUser.getCitizenID());
        pStatement.setString(2, updatedUser.getFirstname());
        pStatement.setString(3, updatedUser.getLastname());
        pStatement.setInt(4, updatedUser.getGender());
        pStatement.setDate(5, updatedUser.getBirthDate());
        pStatement.setString(6, updatedUser.getAddress());
        pStatement.setString(7, updatedUser.getMobile());
        pStatement.setString(8, updatedUser.getEmail());
        pStatement.setString(9, updatedUser.getAllergic());
        pStatement.setString(10, updatedUser.getBlood_group());
        pStatement.setDouble(11, updatedUser.getWeight());
        pStatement.setDouble(12, updatedUser.getHeight());
        pStatement.setString(13, updatedUser.getChronic_disease());
        pStatement.setInt(14, updatedUser.getID());

        pStatement.executeUpdate();
    }

    // Delete user
    public void deleteUser(String id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        pStatement.setString(1, id);
        pStatement.executeUpdate();
    }
}
