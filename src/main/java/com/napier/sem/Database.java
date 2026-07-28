package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    public void connect() {

        try {
            String host = System.getenv("DB_HOST");
            String port;

            // Running from IntelliJ
            if (host == null || host.isBlank()) {
                host = "localhost";
                port = "33060";
            }
            // Running inside Docker Compose
            else {
                port = "3306";
            }

            String url =
                    "jdbc:mysql://" + host + ":" + port +
                            "/world?allowPublicKeyRetrieval=true&useSSL=false";

            connection = DriverManager.getConnection(
                    url,
                    "root",
                    "password"
            );

            System.out.println("Connected to World Database!");

        } catch (SQLException e) {
            System.out.println(
                    "Connection failed: " + e.getMessage()
            );
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println(
                    "Could not close connection: " + e.getMessage()
            );
        }
    }
}