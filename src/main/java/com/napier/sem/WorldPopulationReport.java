package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorldPopulationReport {

    private final Connection connection;

    public WorldPopulationReport(Connection connection) {
        this.connection = connection;
    }

    public long getWorldPopulation() {

        String sql = "SELECT SUM(Population) AS Population FROM country";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getLong("Population");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return 0;
    }
}