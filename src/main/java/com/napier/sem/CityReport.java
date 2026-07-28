package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityReport {
    private final Connection connection;

    public CityReport(Connection connection) {
        this.connection = connection;
    }

    public List<City> getAllCitiesByPopulation() {
        List<City> cities = new ArrayList<>();

        String sql =
                "SELECT ci.Name, co.Name AS CountryName, " +
                        "ci.District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.CountryCode = co.Code " +
                        "ORDER BY ci.Population DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                City city = new City(
                        resultSet.getString("Name"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getLong("Population")
                );

                cities.add(city);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not generate city report: " + e.getMessage()
            );
        }

        return cities;
    }
    public List<City> getTopNCitiesByPopulation(int numberOfCities) {
        List<City> cities = new ArrayList<>();

        String sql =
                "SELECT ci.Name, co.Name AS CountryName, " +
                        "ci.District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.CountryCode = co.Code " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, numberOfCities);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getString("Name"),
                            resultSet.getString("CountryName"),
                            resultSet.getString("District"),
                            resultSet.getLong("Population")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not generate top cities report: " + e.getMessage()
            );
        }

        return cities;
    }
    public void printCityReport(List<City> cities) {
        System.out.printf(
                "%-35s %-35s %-30s %-15s%n",
                "Name",
                "Country",
                "District",
                "Population"
        );

        System.out.println(
                "----------------------------------------------------------------------------------------------------------------"
        );

        for (City city : cities) {
            System.out.printf(
                    "%-35s %-35s %-30s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation()
            );
        }
    }
}