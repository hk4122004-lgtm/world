package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PopulationReport {

    private final Connection connection;

    public PopulationReport(Connection connection) {
        this.connection = connection;
    }

    public List<Population> getPopulationByCountry() {

        List<Population> populationReports = new ArrayList<>();

        String sql =
                "SELECT co.Name AS CountryName, " +
                        "co.Population AS TotalPopulation, " +
                        "COALESCE(SUM(ci.Population), 0) AS CityPopulation " +
                        "FROM country co " +
                        "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                        "GROUP BY co.Code, co.Name, co.Population " +
                        "ORDER BY co.Population DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                String countryName =
                        resultSet.getString("CountryName");

                long totalPopulation =
                        resultSet.getLong("TotalPopulation");

                long cityPopulation =
                        resultSet.getLong("CityPopulation");

                long nonCityPopulation =
                        totalPopulation - cityPopulation;

                if (nonCityPopulation < 0) {
                    nonCityPopulation = 0;
                }

                double cityPercentage = 0.0;
                double nonCityPercentage = 0.0;

                if (totalPopulation > 0) {
                    cityPercentage =
                            ((double) cityPopulation / totalPopulation) * 100;

                    nonCityPercentage =
                            ((double) nonCityPopulation / totalPopulation) * 100;
                }

                Population population = new Population(
                        countryName,
                        totalPopulation,
                        cityPopulation,
                        nonCityPopulation,
                        cityPercentage,
                        nonCityPercentage
                );

                populationReports.add(population);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not generate population report: "
                            + e.getMessage()
            );
        }

        return populationReports;
    }

    public void printPopulationReport(
            List<Population> populationReports) {

        System.out.printf(
                "%-35s %-18s %-18s %-12s %-20s %-12s%n",
                "Country",
                "Total Population",
                "City Population",
                "City %",
                "Non-City Population",
                "Non-City %"
        );

        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------"
        );

        for (Population population : populationReports) {

            System.out.printf(
                    "%-35s %-18d %-18d %-11.2f%% %-20d %-11.2f%%%n",
                    population.getCountry(),
                    population.getTotalPopulation(),
                    population.getCityPopulation(),
                    population.getCityPercentage(),
                    population.getNonCityPopulation(),
                    population.getNonCityPercentage()
            );
        }
    }
}