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

            System.out.println("\nALL COUNTRIES BY POPULATION\n");
            countryReport.printCountryReport(countries);

            CityReport cityReport =
                    new CityReport(database.getConnection());

            List<City> cities =
                    cityReport.getAllCitiesByPopulation();

            System.out.println("\nALL CITIES BY POPULATION\n");
            cityReport.printCityReport(cities);
        }

        database.disconnect();
    }
}