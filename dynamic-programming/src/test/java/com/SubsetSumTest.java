package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubsetSumTest {

    @Test
    //@DisplayName("subsetSumLong")
    public void subsetSumLong() {
        Assertions.assertTrue(SubsetSum.subsetSumLong(new int[]{2,3,7,8,10}, 5, 15));
        Assertions.assertFalse(SubsetSum.subsetSumLong(new int[]{2,3,7,8,10}, 5, 24));
        Assertions.assertFalse(SubsetSum.subsetSumLong(new int[]{2,3,7,8,10}, 5, 16));
        Assertions.assertTrue(SubsetSum.subsetSumLong(new int[]{2,3,7,8,10}, 5, 12));
    }

}