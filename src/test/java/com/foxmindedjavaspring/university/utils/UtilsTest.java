package com.foxmindedjavaspring.university.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {
    private Utils utils;

    @BeforeEach
    void setUp() {
        utils = new Utils();
    }

    @Test
    void shouldCheckCountOfPairsInReturnMap_whenGivenSinglePairOfParametersForMap() {
        String key = "key";
        String value = "value";
        int expected = 1;

        int actual = utils.getMapSinglePair(key, value).size();

        assertEquals(expected, actual);
    }
}
