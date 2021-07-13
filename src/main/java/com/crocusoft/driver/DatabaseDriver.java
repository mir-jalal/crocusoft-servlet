package com.crocusoft.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseDriver {
    private static String POSTGRES_URL;
    private static String POSTGRES_USER;
    private static String POSTGRES_PASS;

    Connection connection;
    static private DatabaseDriver databaseDriver = null;

    private DatabaseDriver() throws ClassNotFoundException, SQLException, IOException {
        try(InputStream input = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();

            properties.load(input);

            POSTGRES_URL = properties.getProperty("db.url");
            POSTGRES_USER = properties.getProperty("db.username");
            POSTGRES_PASS = properties.getProperty("db.password");

        }catch (IOException ex){
            throw new IOException("File not found");
        }

        Class.forName("org.postgresql.Driver");

        this.connection = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASS);
    }

    public static Connection getInstanceOfConnection() throws SQLException, IOException, ClassNotFoundException {
        if(databaseDriver == null){
            databaseDriver = new DatabaseDriver();
        }

        return databaseDriver.connection;
    }
}
