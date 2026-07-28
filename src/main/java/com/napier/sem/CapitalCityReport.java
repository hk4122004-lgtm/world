package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapitalCityReport {
    private final Connection connection;

    public CapitalCityReport(Connection connection) {
        this.connection = connection;
    }

    public List<CapitalCity> getAllCapitalCitiesByPopulation() {
        List<CapitalCity> capitals = new ArrayList<>();

        String sql =
                "SELECT ci.Name, co.Name AS CountryName, ci.Population " +
                        "FROM country co " +
                        "JOIN city ci ON co.Capital = ci.ID " +
                        "ORDER BY ci.Population DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                capitals.add(new CapitalCity(
                        resultSet.getString("Name"),
                        resultSet.getString("CountryName"),
                        resultSet.getLong("Population")
                ));
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not generate capital city report: " + e.getMessage()
            );
        }

        return capitals;
    }

    public void printCapitalCityReport(List<CapitalCity> capitals) {
        System.out.printf(
                "%-35s %-35s %-15s%n",
                "Name",
                "Country",
                "Population"
        );

        System.out.println(
                "------------------------------------------------------------------------------------------"
        );

        for (CapitalCity capital : capitals) {
            System.out.printf(
                    "%-35s %-35s %-15d%n",
                    capital.getName(),
                    capital.getCountry(),
                    capital.getPopulation()
            );
        }
    }
}