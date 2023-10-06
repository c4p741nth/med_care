package th.ac.kku;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Migrate {
    private static Connection connection;

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/med_care?characterEncoding=utf-8";
        String username = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE members ("
                    + "id SERIAL PRIMARY KEY,"
                    + "citizen_id VARCHAR(13),"
                    + "firstname VARCHAR(50),"
                    + "lastname VARCHAR(50),"
                    + "gender INT(1),"
                    + "birthDate DATE,"
                    + "address TEXT,"
                    + "mobile VARCHAR(10),"
                    + "email VARCHAR(255),"
                    + "password VARCHAR(255),"
                    + "allergic TEXT,"
                    + "blood_group VARCHAR(2),"
                    + "weight DOUBLE PRECISION,"
                    + "height DOUBLE PRECISION,"
                    + "chronic_disease TEXT,"
                    + "accessLevel INT(1)"
                    + ")";

            statement.executeUpdate(createTableSQL);

            statement.close();
            connection.close();

            System.out.println("The 'members' table has been created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating 'members' table: " + e.getMessage());
        }
    }
}
