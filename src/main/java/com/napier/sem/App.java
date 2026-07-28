package com.napier.sem;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Database database = new Database();
        database.connect();

        if (database.getConnection() != null) {
            CountryReport countryReport =
                    new CountryReport(database.getConnection());

            List<Country> countries =
                    countryReport.getAllCountriesByPopulation();

            countryReport.printCountryReport(countries);
        }

        database.disconnect();
    }
}