package se.iths.maria.anslutning;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String user = "appuser";
        String pass = "password123";
        String sql = "INSERT INTO person (first_name, last_name, do\n" +
                "b, income) VALUES (?,?,?,?)";
        String sqll = "SELECT * FROM person";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            //System.out.println("Anslutning etablerad.");
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "Hanna");
            pstmt.setString(2, "Larsson");
            pstmt.setDate(3, java.sql.Date.valueOf("1999-10-24"));
            pstmt.setDouble(4, 2500.0);

//            int rowsInserted = pstmt.executeUpdate();
//            System.out.println("rows inserted: " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement pstmt = conn.prepareStatement(sqll);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("person_id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    Date dob = rs.getDate("dob");
                    double income = rs.getDouble("income");

                    System.out.println(id + " " + first + " " + last + " "
                            + dob + " " + income);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

