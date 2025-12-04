package se.iths.maria.anslutning.singel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {
    private static Singleton instance;
    private Properties propertie;

    private Singleton() {
        propertie = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream("application.propertie")) {
            propertie.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getProperty(String key) {
        return propertie.getProperty(key);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getProperty("db.url"),
                getProperty("db.username"), getProperty("db.password"));
    }

}
