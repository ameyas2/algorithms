package com;

import java.math.BigInteger;
import java.util.Arrays;

public class GridTraveller {

    static long[][] cache;

    static {
        int n = 10000;
        cache = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
    }


    public static void main(String[] args) {
        //System.out.println(gridTraveller(3,3));
        System.out.println(gridTravellerMemoized(3, 3));
        System.out.println(gridTravellerTab(3, 3));
    }



    public static int gridTraveller(int row, int col) {
        if(row == 0 || col == 0)
            return 0;

        if(row == 1 && col == 1)
            return 1;

        return gridTraveller(row - 1, col) + gridTraveller(row, col - 1);
    }

    public static int gridTraveller2(int row, int col) {
        if(row == 0 || col == 0)
            return 0;

        if(row == 1 && col == 1)
            return 1;

        int up = gridTraveller2(row - 1, col);
        int left = gridTraveller2(row, col - 1);

        return up + left;
    }

    public static long gridTravellerMemoized(int row, int col) {
        if(row == 0 || col == 0) return 0;
        if(row == 1 & col == 1) return 1;
        if(cache[row][col] > -1) return cache[row][col];

        cache[row][col] = gridTravellerMemoized(row - 1, col) + gridTravellerMemoized(row, col - 1);
        return cache[row][col];
    }

    public static long gridTravellerMemoized2(int row, int col) {
        if(row == 0 || col == 0) return 0;
        if(row == 1 & col == 1) return 1;
        if(cache[row][col] > -1) return cache[row][col];

        long up = gridTravellerMemoized2(row - 1, col);
        long left = gridTravellerMemoized2(row - 1, col);

        cache[row][col] = up + left;
        return cache[row][col];
    }

    public static long gridTravellerTab(int row, int col) {
        long dp[][] = new long[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if(i == 1 && j == 1)
                    dp[i][j] = 1;
                else {
                    long up = 0, left = 0;
                    if (i > 0) up = dp[i - 1][j];
                    if (j > 0) left = dp[i][j - 1];
                    dp[i][j] = up + left;
                }
            }
        }

        return dp[row][col];
    }


    // takes long time
    /*public static BigInteger gridTravellerMemoized(int row, int col) {
        if(row == 0 || col == 0)
            return BigInteger.ZERO;

        if(row == 1 & col == 1)
            return BigInteger.ONE;

        if(cache[row][col].compareTo(BigInteger.valueOf(-1))  > 1)
            return cache[row][col];

        cache[row][col] = gridTravellerMemoized(row - 1, col).add(gridTravellerMemoized(row, col - 1));
        return cache[row][col];
    }*/
}
