package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationLookupReport {

    private final Connection connection;

    public PopulationLookupReport(Connection connection) {
        this.connection = connection;
    }

    public long getContinentPopulation(String continent) {
        String sql =
                "SELECT SUM(Population) AS Population " +
                        "FROM country WHERE Continent = ?";

        return getPopulation(sql, continent);
    }

    public long getRegionPopulation(String region) {
        String sql =
                "SELECT SUM(Population) AS Population " +
                        "FROM country WHERE Region = ?";

        return getPopulation(sql, region);
    }

    public long getCountryPopulation(String country) {
        String sql =
                "SELECT Population " +
                        "FROM country WHERE Name = ?";

        return getPopulation(sql, country);
    }

    public long getDistrictPopulation(String district) {
        String sql =
                "SELECT SUM(Population) AS Population " +
                        "FROM city WHERE District = ?";

        return getPopulation(sql, district);
    }

    public long getCityPopulation(String city) {
        String sql =
                "SELECT Population " +
                        "FROM city WHERE Name = ?";

        return getPopulation(sql, city);
    }

    private long getPopulation(String sql, String value) {

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, value);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getLong("Population");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not retrieve population: " + e.getMessage()
            );
        }

        return 0;
    }
}