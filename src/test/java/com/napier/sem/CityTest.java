package com.napier.sem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTest {

    @Test
    void cityStoresCorrectValues() {

        City city = new City(
                "Edinburgh",
                "United Kingdom",
                "Scotland",
                450180
        );

        assertEquals("Edinburgh", city.getName());
        assertEquals("United Kingdom", city.getCountry());
        assertEquals("Scotland", city.getDistrict());
        assertEquals(450180, city.getPopulation());
    }
}