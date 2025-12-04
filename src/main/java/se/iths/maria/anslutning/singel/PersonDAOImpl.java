package se.iths.maria.anslutning.singel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public List<Person> findAll() {
        String sql = "select * from person";
        List<Person> list = new ArrayList<>();
        try (Connection conn = Singleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Person p = new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("dob"),
                            rs.getDouble("income")
                    );
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    String sql = "INSERT INTO person(first_name, last_name, dob, income) VALUES(?,?,?,?)";

    @Override
    public void insert(Person person) {
        try (Connection conn = Singleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setDate(3, person.getDob());
            ps.setDouble(4, person.getIncome());

            int rowsInserted = ps.executeUpdate();
            System.out.println("rows inserted: " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
