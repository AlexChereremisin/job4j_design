package ru.job4j.io.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        try (InputStream input = new FileInputStream("./chapter_002/data/app.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String url = properties.getProperty("jdbc.url");
            String login = properties.getProperty("jdbc.login");
            String password = properties.getProperty("jdbc.password");
            Connection connection = DriverManager.getConnection(url, login, password);
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
