package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageReport {

    private final Connection connection;

    public LanguageReport(Connection connection) {
        this.connection = connection;
    }

    public List<LanguageReportItem> getLanguageReport(long worldPopulation) {

        List<LanguageReportItem> languages = new ArrayList<>();

        String sql =
                "SELECT cl.Language, " +
                        "ROUND(SUM(co.Population * cl.Percentage / 100)) AS Speakers " +
                        "FROM countrylanguage cl " +
                        "JOIN country co ON cl.CountryCode = co.Code " +
                        "WHERE cl.Language IN ('Chinese', 'English', 'Spanish') " +
                        "GROUP BY cl.Language " +
                        "ORDER BY Speakers DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                String language =
                        resultSet.getString("Language");

                long speakers =
                        resultSet.getLong("Speakers");

                double percentage = 0.0;

                if (worldPopulation > 0) {
                    percentage =
                            ((double) speakers / worldPopulation) * 100;
                }

                languages.add(new LanguageReportItem(
                        language,
                        speakers,
                        percentage
                ));
            }

        } catch (SQLException e) {
            System.out.println(
                    "Could not generate language report: "
                            + e.getMessage()
            );
        }

        return languages;
    }

    public void printLanguageReport(
            List<LanguageReportItem> languages) {

        System.out.printf(
                "%-20s %-20s %-20s%n",
                "Language",
                "Speakers",
                "% of World"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        for (LanguageReportItem item : languages) {
            System.out.printf(
                    "%-20s %-20d %-19.2f%%%n",
                    item.getLanguage(),
                    item.getSpeakers(),
                    item.getWorldPercentage()
            );
        }
    }
}