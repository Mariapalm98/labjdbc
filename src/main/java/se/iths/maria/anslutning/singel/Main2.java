package se.iths.maria.anslutning.singel;

import java.sql.Connection;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {

        try {
            Connection conn = Singleton.getInstance().getConnection();
            System.out.println("Connected to database. " + conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PersonDAOImpl personDAO = new PersonDAOImpl();

        personDAO.insert(new Person("Musse", "Pigge", java.sql.Date.valueOf("1869-01-03"), 123));


        for (Person p : personDAO.findAll()) {
            System.out.println("Lista av " + p);
        }

    }
}
