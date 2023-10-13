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

            user.setAccessLevel(resultSet.getInt("access_level"));

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

            user.setAccessLevel(resultSet.getInt("access_level"));

            return user;
        } else {
            return null;
        }
    }

    // Register a new member
    public void registerUser(User user) throws SQLException {
        PreparedStatement pStatement = connection
                .prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pStatement.setInt(1, user.getID());
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
        pStatement.setInt(15, user.getAccessLevel());
    }

    // Login method
    public User login(String email, String password) {
        try {
            PreparedStatement pStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            pStatement.setString(1, email);
            pStatement.setString(2, password);
            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setID(resultSet.getInt("id"));
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

                user.setAccessLevel(resultSet.getInt("access_level"));

                return user;
            } else {
                return null; // No matching user found
            }
        } catch (SQLException e) {
            // Handle the SQLException, log it, and return an error message or take
            // appropriate action
            e.printStackTrace(); // This is just an example; you should handle errors appropriately
            return null; // Return null or an error indicator to signify login failure
        }
    }

    public void editUserProfile(int accessLevel, int id, User updatedUser) throws SQLException {
        // Check if the user with the given ID exists
        User existingUser = getUser(String.valueOf(id));

        if (existingUser != null) {
            // Check if the admin's access level is sufficient to edit this user's profile
            if (accessLevel >= existingUser.getAccessLevel()) {
                // Define the SQL query based on the user's access level
                String sqlQuery;

                if (accessLevel == 1) {
                    // Admin with access level 1 can edit all user profile fields
                    sqlQuery = "UPDATE users SET firstname=?, lastname=?, gender=?, birthdate=?, address=?, mobile=?, email=?, password=?, allergic=?, blood_group=?, weight=?, height=?, chronic_disease=?, access_level=? WHERE id=?";
                } else if (accessLevel == 0) {
                    // Admin with access level 0 can only edit specific fields
                    sqlQuery = "UPDATE users SET address=?, mobile=?, email=?, password=?, allergic=?, blood_group=?, weight=?, height=?, chronic_disease=? WHERE id=?";
                } else {
                    // Handle other access levels as needed
                    // You can define custom logic for other access levels
                    throw new SQLException("Invalid access level.");
                }

                // Prepare and execute the SQL update query
                PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
                pStatement.setString(1, updatedUser.getFirstname());
                pStatement.setString(2, updatedUser.getLastname());
                pStatement.setInt(3, updatedUser.getGender());
                pStatement.setDate(4, updatedUser.getBirthDate());
                pStatement.setString(5, updatedUser.getAddress());
                pStatement.setString(6, updatedUser.getMobile());
                pStatement.setString(7, updatedUser.getEmail());
                pStatement.setString(8, updatedUser.getPassword());
                pStatement.setString(9, updatedUser.getAllergic());
                pStatement.setString(10, updatedUser.getBlood_group());
                pStatement.setDouble(11, updatedUser.getWeight());
                pStatement.setDouble(12, updatedUser.getHeight());
                pStatement.setString(13, updatedUser.getChronic_disease());
                pStatement.setInt(14, updatedUser.getAccessLevel());
                pStatement.setInt(15, id);

                pStatement.executeUpdate();
            } else {
                throw new SQLException("Insufficient access level to edit user profile.");
            }
        } else {
            throw new SQLException("User with ID " + id + " does not exist.");
        }
    }

    // Delete user
    public void deleteUser(String id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        pStatement.setString(1, id);
        pStatement.executeUpdate();
    }
}