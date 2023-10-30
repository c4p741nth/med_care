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

            String createTableSQL = "CREATE TABLE users (" +
                    "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "citizen_id VARCHAR(13) NOT NULL," +
                    "firstname VARCHAR(50) NOT NULL," +
                    "lastname VARCHAR(50) NOT NULL," +
                    "gender INT NOT NULL," +
                    "birth_date DATE NOT NULL," +
                    "address VARCHAR(255)," +
                    "mobile VARCHAR(10)," +
                    "email VARCHAR(100)," +
                    "password VARCHAR(100)," +
                    "allergic VARCHAR(255)," +
                    "blood_group VARCHAR(2)," +
                    "weight DOUBLE PRECISION," +
                    "height DOUBLE PRECISION," +
                    "chronic_disease VARCHAR(255)" +
                    ")";

            statement.executeUpdate(createTableSQL);

            statement.close();
            connection.close();

            System.out.println("The 'users' table has been created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating 'users' table: " + e.getMessage());
        }
    }
}
