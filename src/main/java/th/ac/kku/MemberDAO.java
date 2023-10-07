package th.ac.kku;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
    private Connection connection;

    public MemberDAO() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/med_care?characterEncoding=utf-8",
                "postgres", "root");
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    // Administrator access
    public ArrayList<Member> getMembers() throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM members");
        ResultSet resultSet = pStatement.executeQuery();

        ArrayList<Member> memberList = new ArrayList<Member>();
        while (resultSet.next()) {
            Member member = new Member();
            member.setID(resultSet.getInt("id"));
            member.setFirstname(resultSet.getString("firstname"));
            member.setLastname(resultSet.getString("lastname"));
            member.setGender(resultSet.getInt("gender"));
            member.setBirthDate(resultSet.getDate("birthdate"));
            member.setAddress(resultSet.getString("address"));
            member.setMobile(resultSet.getString("mobile"));
            member.setEmail(resultSet.getString("email"));
            member.setPassword(resultSet.getString("password"));

            member.setAllergic(resultSet.getString("allergic"));
            member.setBlood_group(resultSet.getString("blood_group"));
            member.setWeight(resultSet.getDouble("weight"));
            member.setHeight(resultSet.getDouble("height"));
            member.setChronic_disease(resultSet.getString("chronic_disease"));

            member.setAccessLevel(resultSet.getInt("access_level"));

            memberList.add(member);
        }
        return memberList;
    }

    // Get user
    public Member getMember(String id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM members WHERE id = ?");
        pStatement.setString(1, id);
        ResultSet resultSet = pStatement.executeQuery();

        if (resultSet.next()) {
            Member member = new Member();
            member.setID(resultSet.getInt("id"));
            member.setFirstname(resultSet.getString("firstname"));
            member.setLastname(resultSet.getString("lastname"));
            member.setGender(resultSet.getInt("gender"));
            member.setBirthDate(resultSet.getDate("birthdate"));
            member.setAddress(resultSet.getString("address"));
            member.setMobile(resultSet.getString("mobile"));
            member.setEmail(resultSet.getString("email"));
            member.setPassword(resultSet.getString("password"));

            member.setAllergic(resultSet.getString("allergic"));
            member.setBlood_group(resultSet.getString("blood_group"));
            member.setWeight(resultSet.getDouble("weight"));
            member.setHeight(resultSet.getDouble("height"));
            member.setChronic_disease(resultSet.getString("chronic_disease"));

            member.setAccessLevel(resultSet.getInt("access_level"));

            return member;
        } else {
            return null;
        }
    }

    // Register a new member
    public void registerMember(Member member) throws SQLException {
        PreparedStatement pStatement = connection
                .prepareStatement("INSERT INTO members VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pStatement.setInt(1, member.getID());
        pStatement.setString(2, member.getFirstname());
        pStatement.setString(3, member.getLastname());
        pStatement.setInt(4, member.getGender());
        pStatement.setDate(5, member.getBirthDate());
        pStatement.setString(6, member.getAddress());
        pStatement.setString(7, member.getMobile());
        pStatement.setString(8, member.getEmail());
        pStatement.setString(9, member.getPassword());
        pStatement.setString(10, member.getAllergic());
        pStatement.setString(11, member.getBlood_group());
        pStatement.setDouble(12, member.getWeight());
        pStatement.setDouble(13, member.getHeight());
        pStatement.setString(14, member.getChronic_disease());
        pStatement.setInt(15, member.getAccessLevel());
    }

    // Login method
    public Member login(String email, String password) {
        try {
            PreparedStatement pStatement = connection
                    .prepareStatement("SELECT * FROM members WHERE email = ? AND password = ?");
            pStatement.setString(1, email);
            pStatement.setString(2, password);
            ResultSet resultSet = pStatement.executeQuery();

            if (resultSet.next()) {
                Member member = new Member();
                member.setID(resultSet.getInt("id"));
                member.setFirstname(resultSet.getString("firstname"));
                member.setLastname(resultSet.getString("lastname"));
                member.setGender(resultSet.getInt("gender"));
                member.setBirthDate(resultSet.getDate("birthdate"));
                member.setAddress(resultSet.getString("address"));
                member.setMobile(resultSet.getString("mobile"));
                member.setEmail(resultSet.getString("email"));
                member.setPassword(resultSet.getString("password"));

                member.setAllergic(resultSet.getString("allergic"));
                member.setBlood_group(resultSet.getString("blood_group"));
                member.setWeight(resultSet.getDouble("weight"));
                member.setHeight(resultSet.getDouble("height"));
                member.setChronic_disease(resultSet.getString("chronic_disease"));

                member.setAccessLevel(resultSet.getInt("access_level"));

                return member;
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

    public void editUserProfile(int accessLevel, int id, Member updatedMember) throws SQLException {
        // Check if the user with the given ID exists
        Member existingMember = getMember(String.valueOf(id));

        if (existingMember != null) {
            // Check if the admin's access level is sufficient to edit this user's profile
            if (accessLevel >= existingMember.getAccessLevel()) {
                // Define the SQL query based on the user's access level
                String sqlQuery;

                if (accessLevel == 1) {
                    // Admin with access level 1 can edit all user profile fields
                    sqlQuery = "UPDATE members SET firstname=?, lastname=?, gender=?, birthdate=?, address=?, mobile=?, email=?, password=?, allergic=?, blood_group=?, weight=?, height=?, chronic_disease=?, access_level=? WHERE id=?";
                } else if (accessLevel == 0) {
                    // Admin with access level 0 can only edit specific fields
                    sqlQuery = "UPDATE members SET address=?, mobile=?, email=?, password=?, allergic=?, blood_group=?, weight=?, height=?, chronic_disease=? WHERE id=?";
                } else {
                    // Handle other access levels as needed
                    // You can define custom logic for other access levels
                    throw new SQLException("Invalid access level.");
                }

                // Prepare and execute the SQL update query
                PreparedStatement pStatement = connection.prepareStatement(sqlQuery);
                pStatement.setString(1, updatedMember.getFirstname());
                pStatement.setString(2, updatedMember.getLastname());
                pStatement.setInt(3, updatedMember.getGender());
                pStatement.setDate(4, updatedMember.getBirthDate());
                pStatement.setString(5, updatedMember.getAddress());
                pStatement.setString(6, updatedMember.getMobile());
                pStatement.setString(7, updatedMember.getEmail());
                pStatement.setString(8, updatedMember.getPassword());
                pStatement.setString(9, updatedMember.getAllergic());
                pStatement.setString(10, updatedMember.getBlood_group());
                pStatement.setDouble(11, updatedMember.getWeight());
                pStatement.setDouble(12, updatedMember.getHeight());
                pStatement.setString(13, updatedMember.getChronic_disease());
                pStatement.setInt(14, updatedMember.getAccessLevel());
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
        PreparedStatement pStatement = connection.prepareStatement("DELETE FROM members WHERE id = ?");
        pStatement.setString(1, id);
        pStatement.executeUpdate();
    }
}
