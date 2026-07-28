package com.napier.sem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {

    @Test
    void countryStoresCorrectValues() {

        Country country = new Country(
                "GBR",
                "United Kingdom",
                "Europe",
                "British Islands",
                59623400,
                "London"
        );

        assertEquals("GBR", country.getCode());
        assertEquals("United Kingdom", country.getName());
        assertEquals("Europe", country.getContinent());
        assertEquals("British Islands", country.getRegion());
        assertEquals(59623400, country.getPopulation());
        assertEquals("London", country.getCapital());
    }
}