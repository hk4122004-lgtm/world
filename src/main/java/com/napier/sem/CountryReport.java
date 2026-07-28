package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryReport {
    private final Connection connection;

    public CountryReport(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getAllCountriesByPopulation() {
        List<Country> countries = new ArrayList<>();

        String sql =
                "SELECT co.Code, co.Name, co.Continent, co.Region, " +
                        "co.Population, ci.Name AS CapitalName " +
                        "FROM country co " +
                        "LEFT JOIN city ci ON co.Capital = ci.ID " +
                        "ORDER BY co.Population DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Country country = new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region"),
                        resultSet.getLong("Population"),
                        resultSet.getString("CapitalName")
                );

                countries.add(country);
            }

        } catch (SQLException e) {
            System.out.println("Could not generate country report: "
                    + e.getMessage());
        }

        return countries;
    }

    public void printCountryReport(List<Country> countries) {
        System.out.printf(
                "%-6s %-35s %-20s %-30s %-15s %-30s%n",
                "Code",
                "Name",
                "Continent",
                "Region",
                "Population",
                "Capital"
        );

        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------"
        );

        for (Country country : countries) {
            String capital = country.getCapital();

            if (capital == null) {
                capital = "N/A";
            }

            System.out.printf(
                    "%-6s %-35s %-20s %-30s %-15d %-30s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    capital
            );
        }
    }
}
