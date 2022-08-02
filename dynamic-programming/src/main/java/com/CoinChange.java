package com;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        //System.out.println(coinChange(new int[]{1,2,3}, 5, 3, 0));
        //System.out.println(coinChangeMemoized(new int[]{1,2,3}, 5));
        System.out.println(coinChange(new int[]{1,2,3}, 4, 3));
        System.out.println(coinChangeMem(new int[]{1,2,3}, 4, 3, new Integer[4][5]));
        System.out.println(coinChangeTab(new int[]{1,2,3}, 4, 3));
    }

    public static int coinChangeTab(int[] coins, int sum, int n) {
        int[][] dp = new int[n + 1][sum + 1];
        for(int row = 0; row <= n; row++) {
            for(int col = 0; col <= sum; col++) {
                if(col == 0) {
                    dp[row][col] = 1;
                    continue;
                }
                if(row == 0) {
                    dp[row][col] = 0;
                    continue;
                }

                if(col >= coins[row - 1]) {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - coins[row - 1]];
                } else {
                    dp[row][col] = dp[row - 1][col];
                }

            }
        }

        return dp[n][sum];
    }

    public static int coinChangeMem(int[] coins, int sum, int n, Integer[][] dp) {
        if(sum == 0) return 1;
        if(sum < 0 || sum > 0 && n == 0) return 0;
        if(dp[n][sum] != null) return dp[n][sum];

        if(sum >= coins[n - 1]) {
            int include = coinChangeMem(coins, sum - coins[n - 1], n, dp);
            int exclude = coinChangeMem(coins, sum, n - 1, dp);
            return dp[n][sum] = include + exclude;
        } else
            return dp[n][sum] = coinChange(coins, sum, n - 1);
    }
    public static int coinChange(int[] coins, int sum, int n) {
        if(sum == 0) return 1;
        if(sum < 0 || sum > 0 && n == 0) return 0;

        if(sum >= coins[n - 1]) {
            int include = coinChange(coins, sum - coins[n - 1], n);
            int exclude = coinChange(coins, sum, n - 1);
            return include + exclude;
        } else
            return coinChange(coins, sum, n - 1);
    }

    public static int coinChange(int[] coins, int sum, int n, int count) {
        if(sum == 0) {
            return ++count;
        }

        if(sum < 0 || (sum > 0 && n == 0)) {
            return 0;
        }

        return coinChange(coins, sum - coins[n - 1], n, count) + coinChange(coins, sum, n - 1, count);
    }

    public static int coinChangeMemoized(int[] coins, int sum) {
        int n = coins.length;
        int[][] cache = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(cache[i], -1);
        return coinChangeMemoized(coins, sum, coins.length, 0, cache);
    }

    public static int coinChangeMemoized(int[] coins, int sum, int n, int count, int[][] cache) {
        if(sum == 0) {
            return ++count;
        }

        if(sum < 0 || (sum > 0 && n == 0))
            return 0;

        if(cache[n][sum] > -1)
            return cache[n][sum];

        if(sum >= coins[n - 1])
            cache[n][sum] = coinChangeMemoized(coins, sum - coins[n - 1], n, count, cache) + coinChangeMemoized(coins, sum, n - 1, count, cache);
        else
            cache[n][sum] = coinChangeMemoized(coins, sum, n - 1, count, cache);

        return cache[n][sum];
    }
}
