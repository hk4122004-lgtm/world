package com.napier.sem;

import java.util.List;
import java.util.Scanner;

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


            CapitalCityReport capitalCityReport =
                    new CapitalCityReport(database.getConnection());

            List<CapitalCity> capitalCities =
                    capitalCityReport.getAllCapitalCitiesByPopulation();

            System.out.println("\nALL CAPITAL CITIES BY POPULATION\n");
            capitalCityReport.printCapitalCityReport(capitalCities);


            Scanner scanner = new Scanner(System.in);

            System.out.print(
                    "\nEnter the number of top populated cities to display: "
            );

            if (scanner.hasNextInt()) {
                int numberOfCities = scanner.nextInt();

                if (numberOfCities > 0) {
                    List<City> topCities =
                            cityReport.getTopNCitiesByPopulation(numberOfCities);

                    System.out.println(
                            "\nTOP " + numberOfCities
                                    + " POPULATED CITIES IN THE WORLD\n"
                    );

                    cityReport.printCityReport(topCities);
                } else {
                    System.out.println(
                            "Please enter a number greater than zero."
                    );
                }
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
            }
            PopulationReport populationReport =
                    new PopulationReport(database.getConnection());

            List<Population> populations =
                    populationReport.getPopulationByCountry();

            System.out.println(
                    "\nPOPULATION LIVING IN CITIES AND NOT LIVING IN CITIES BY COUNTRY\n"
            );

            populationReport.printPopulationReport(populations);
            WorldPopulationReport worldPopulationReport =
                    new WorldPopulationReport(database.getConnection());

            long worldPopulation =
                    worldPopulationReport.getWorldPopulation();

            System.out.println("\nWORLD POPULATION");
            System.out.println("----------------");
            System.out.println(worldPopulation);
            PopulationLookupReport lookupReport =
                    new PopulationLookupReport(database.getConnection());

            long asiaPopulation =
                    lookupReport.getContinentPopulation("Asia");

            long easternAsiaPopulation =
                    lookupReport.getRegionPopulation("Eastern Asia");

            long chinaPopulation =
                    lookupReport.getCountryPopulation("China");

            long maharashtraPopulation =
                    lookupReport.getDistrictPopulation("Maharashtra");

            long seoulPopulation =
                    lookupReport.getCityPopulation("Seoul");

            System.out.println("\nPOPULATION LOOKUPS");
            System.out.println("------------------");
            System.out.println("Asia: " + asiaPopulation);
            System.out.println("Eastern Asia: " + easternAsiaPopulation);
            System.out.println("China: " + chinaPopulation);
            System.out.println("Maharashtra: " + maharashtraPopulation);
            System.out.println("Seoul: " + seoulPopulation);
            scanner.close();
        }

        database.disconnect();
    }
}