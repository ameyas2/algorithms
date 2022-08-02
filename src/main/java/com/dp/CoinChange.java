package com.dp;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 5, 6, 9};
        int sum = 10;
        System.out.println(coinChange(coins, sum));
        System.out.println(minCoins(coins, sum));
    }

    public static int coinChange(int[] coins, int sum) {
        Integer[][] mat = new Integer[coins.length + 1][sum + 1];
        Arrays.fill(mat[0], 0);
        for(int i = 1; i < coins.length + 1; i++) {
            mat[i][0] = 1;
        }

        for(int r = 1; r <= coins.length; r++) {
            for(int c = 1; c < mat[0].length; c++) {
                if(coins[r - 1] > c)
                    mat[r][c] = mat[r - 1][c];
                else {
                    mat[r][c] = mat[r - 1][c] + mat[r][c - coins[r - 1]];
                }
            }
        }

        print(mat);

        return mat[mat.length - 1][mat[0].length - 1];
    }

    public static int minCoins(int[] coins, int sum) {
        Integer[][] mat = new Integer[coins.length + 1][sum + 1];
        Arrays.fill(mat[0], Integer.MAX_VALUE);
        for(int i = 1; i < coins.length + 1; i++) {
            mat[i][0] = 0;
        }

        for(int r = 1; r <= coins.length; r++) {
            for (int c = 1; c <= sum; c++) {
                if(coins[r - 1] > c)
                    mat[r][c] = mat[r - 1][c];
                else {
                    int upperVal = mat[r - 1][c];
                    int newVal = 1 + mat[r][c - coins[r - 1]];
                    mat[r][c] =  upperVal < newVal ? upperVal : newVal;
                }
            }
        }

        print(mat);

        return mat[mat.length - 1][mat[0].length - 1];
    }

    public static void print(Object[][] array) {
        for(int r = 0; r < array.length; r++) {
            for(int c = 0; c < array[0].length; c++) {
                System.out.print(array[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
