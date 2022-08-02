package com;

import java.util.Arrays;

public class Util {
    public static void fill(int[][] arr, int val) {
        for (int i = 0; i < arr.length; i++)
            Arrays.fill(arr[i], -1);
    }
}
