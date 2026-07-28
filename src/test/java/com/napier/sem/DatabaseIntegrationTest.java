package com.napier.sem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseIntegrationTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
        database.connect();
    }

    @AfterEach
    void tearDown() {
        database.disconnect();
    }

    @Test
    void databaseConnectionWorks() {
        assertNotNull(database.getConnection());
    }

    @Test
    void countryTableContainsCountries() throws Exception {

        Connection connection = database.getConnection();

        assertNotNull(connection);

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet =
                        statement.executeQuery(
                                "SELECT COUNT(*) FROM country"
                        )
        ) {
            assertTrue(resultSet.next());
            assertTrue(resultSet.getInt(1) > 0);
        }
    }
}