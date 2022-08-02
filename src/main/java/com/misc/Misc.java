package com.misc;

import java.util.HashMap;
import java.util.Map;

public class Misc {
    public static void main(String[] args) {

    }

    public static void diffRowSum(int[][] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] rowIndex = new int[1000];
        int[][] res = new int[1000][2];

        for(int row = 0, i = 0; row < arr.length; row++) {
            for(int col = 0; col < arr[0].length; col++) {
                // sum = 11 arr[row][col] = 8, 3 - row 0, 8 row 1
                rowIndex[arr[row][col]] = row;
                int currentValue = arr[row][col];
                int diff = sum - currentValue;

                if(map.containsKey(diff) && rowIndex[diff] != rowIndex[currentValue]) {
                    res[i][0] = currentValue;
                    res[i][1] = diff;
                } else {
                    map.put(diff, currentValue);
                    map.put(currentValue, diff);
                    rowIndex[currentValue] = row;
                }
            }
        }
    }

}
