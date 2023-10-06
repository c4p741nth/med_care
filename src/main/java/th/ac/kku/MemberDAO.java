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
}
