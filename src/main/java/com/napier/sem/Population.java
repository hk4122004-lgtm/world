package com.napier.sem;

public class Population {

    private String country;
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;
    private double cityPercentage;
    private double nonCityPercentage;

    public Population(String country,
                      long totalPopulation,
                      long cityPopulation,
                      long nonCityPopulation,
                      double cityPercentage,
                      double nonCityPercentage) {

        this.country = country;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
        this.cityPercentage = cityPercentage;
        this.nonCityPercentage = nonCityPercentage;
    }

    public String getCountry() {
        return country;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    public double getCityPercentage() {
        return cityPercentage;
    }

    public double getNonCityPercentage() {
        return nonCityPercentage;
    }
}