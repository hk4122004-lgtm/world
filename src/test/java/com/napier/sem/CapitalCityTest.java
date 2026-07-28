package com.napier.sem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapitalCityTest {

    @Test
    void capitalCityStoresCorrectValues() {

        CapitalCity capitalCity = new CapitalCity(
                "London",
                "United Kingdom",
                7285000
        );

        assertEquals("London", capitalCity.getName());
        assertEquals("United Kingdom", capitalCity.getCountry());
        assertEquals(7285000, capitalCity.getPopulation());
    }
}