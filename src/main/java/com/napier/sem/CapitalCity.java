package com.napier.sem;

public class CapitalCity {
    private String name;
    private String country;
    private long population;

    public CapitalCity(String name, String country, long population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }
}