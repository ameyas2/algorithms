package com;

import java.util.Arrays;

public class Knapsack {

    static Integer[][] dp;

    static {
        int n = 1001;
        dp = new Integer[n + 1][n + 1];

        for(int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
    }

    public static void clearCache() {
        for(int i = 0; i < 1001; i++)
            Arrays.fill(dp[i], -1);
    }


    public static void main(String[] args) {
        //System.out.println(knapsack01(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 20, 6));
        //System.out.println(knapsack());
        //System.out.println(knapsackTopDown(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 20, 6));
        //System.out.println(knapsackNew01(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 6, 20));
        System.out.println(knapsackNew01Memoized(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 6, 20));
        System.out.println(knapsackTabulation(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 6, 20));
        System.out.println(knapsackTabulationShort(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10}, 6, 20));
        System.out.println("New " + knapsack(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20));
        System.out.println("Coins " + coinChange(new int[]{1, 2, 5}, 3, 11));
        clearCache();
        System.out.println("Coins Mem " + coinChangeMem(new int[]{1, 2, 5}, 3, 11));
        clearCache();
        System.out.println("Coins Tab " + coinChangeTab(new int[]{1, 2, 5}, 3, 11));
        clearCache();
        System.out.println("Coins Tab " + coinChangeTabShort(new int[]{1, 2, 5}, 3, 11));
    }

    public static int coinChange(int[] coins, int n, int sum) {
        if(sum == 0) return 0;
        if(sum < 0 || ( sum > 0 && n == 0)) return Integer.MAX_VALUE;

        if(sum >= coins[n - 1]) {
            int include = coinChange(coins, n, sum - coins[n - 1]);
            int exclude = coinChange(coins, n - 1, sum);
            return 1 + Math.min(include, exclude);
        } else {
            return coinChange(coins, n - 1, sum);
        }
    }

    public static int coinChangeMem(int[] coins, int n, int sum) {
        if(sum == 0) return 0;
        if(sum < 0 || ( sum > 0 && n == 0)) return Integer.MAX_VALUE;

        if(dp[n][sum] != -1) return dp[n][sum];

        if(sum >= coins[n - 1]) {
            int include = coinChange(coins, n, sum - coins[n - 1]);
            int exclude = coinChange(coins, n - 1, sum);
            dp[n][sum] = 1 + Math.min(include, exclude);
        } else {
            dp[n][sum] = coinChange(coins, n - 1, sum);
        }
        return dp[n][sum];
    }

    public static int coinChangeTab(int[] coins, int n, int sum) {
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) dp[i][j] = 0;
                else if(i == 0) dp[i][j] = Integer.MAX_VALUE;
                else {
                    if(sum >= coins[n - 1]) {
                        int exclude = dp[i - 1][j];
                        int include = dp[i - 1][j - coins[i - 1]];
                        dp[i][j] = 1 + Math.min(include, exclude);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][sum];
    }

    public static int coinChangeTabShort(int[] coins, int n, int sum) {
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) dp[i][j] = 0;
                else if(i == 0) dp[i][j] = Integer.MAX_VALUE;
                else {
                    dp[i][j] = sum >= coins[n - 1] ? 1 + Math.min(dp[i - 1][j], dp[i - 1][j - coins[i - 1]]) : dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static int knapsack(int[] wt, int[] val, int n, int w) {
        if(n == 0 || w == 0) return 0;

        if(w >= wt[n - 1]) {
            int add = val[n - 1] + knapsack(wt, val, n - 1, w - wt[n - 1]);
            int notAdd = knapsack(wt, val, n - 1, w);
            return Math.max(add, notAdd);
        } else {
            return knapsack(wt, val, n - 1, w);
        }
    }

    public static int knapsack() {
        int w = 20;
        int n = 6;
        int[][] cache = new int[n + 1][w + 1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        return knapsack01Memoized(new int[]{3, 5, 6, 7, 9, 10}, new int[]{2, 6, 1, 4, 5, 10},  cache, w, n);
    }

    public static int knapsackTabulation(int[] val, int[] wt, int n, int w) {
        int[][] tab = new int[n + 1][w + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= w; j++) {

                if(i == 0 || j == 0) {// base condition
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= wt[i - 1]) {
                    int value = val[i - 1];
                    int weight = wt[i - 1];
                    int add = value + tab[i - 1][j - weight];
                    int noAdd = tab[i - 1][j];
                    tab[i][j] = Math.max(add, noAdd);
                } else {
                    int noAdd = tab[i - 1][j];
                    tab[i][j] = noAdd;
                }
            }
        }

        return tab[n][w];
    }

    public static int knapsackTabulationShort(int[] val, int[] wt, int n, int w) {
        int[][] tab = new int[n + 1][w + 1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= w; j++) {
                if(i == 0 || j == 0) {// base condition
                    tab[i][j] = 0;
                    continue;
                }
                tab[i][j] = j >= wt[i - 1] ? Math.max(val[i - 1] + tab[i - 1][j - wt[i - 1]], tab[i - 1][j]) : tab[i - 1][j];
            }
        }

        return tab[n][w];
    }

    public static int knapsackNew01Memoized(int[] val, int[] wt, int n, int w) {
        if(n == 0 || w == 0)
            return 0;

        if(dp[n][w] > -1)
            return dp[n][w];

        if(w >= wt[n - 1]) {
            int itemAdd = val[n - 1] + knapsackNew01Memoized(val, wt, n - 1, w - wt[n - 1]);
            int noItemAdd = knapsackNew01Memoized(val, wt, n - 1, w);
            dp[n][w] = Math.max(itemAdd, noItemAdd);
        } else {
            dp[n][w] = knapsackNew01Memoized(val, wt, n - 1, w);
        }
        return dp[n][w];
    }

    public static int knapsackNew01(int[] val, int[] wt, int n, int w) {
        if(n == 0 || w == 0)
            return 0;

        if(w >= wt[n - 1]) {
            int itemAdd = val[n - 1] + knapsackNew01(val, wt, n - 1, w - wt[n - 1]);
            int noItemAdd = knapsackNew01(val, wt, n - 1, w);
            return Math.max(itemAdd, noItemAdd);
        } else {
            return knapsackNew01(val, wt, n - 1, w); // noItemAdd
        }
    }

    public static int knapsack01(int[] wt, int[] val, int w, int n) {
        if(n == 0 || w == 0)
            return 0;

        if(wt[n - 1] < w) {
            return Math.max(val[n - 1] + knapsack01(wt, val, w - wt[n - 1], n - 1), knapsack01(wt, val, w, n - 1));
        } else {
            return knapsack01(wt, val, w, n - 1);
        }
    }

    public static int knapsack01_2(int[] wt, int[] val, int w, int n) {
        if(n == 0 || w == 0)
            return 0;

        return wt[n - 1] < w ? Math.max(val[n - 1] + knapsack01(wt, val, w - wt[n - 1], n - 1), knapsack01(wt, val, w, n - 1)) : knapsack01(wt, val, w, n - 1);
    }

    public static int knapsack01Memoized(int[] wt, int[] val, int[][] cache, int w, int n) {
        if(n == 0 || w == 0)
            return 0;

        if(cache[n][w] > -1)
            return cache[n][w];

        if(wt[n - 1] < w) {
            cache[n][w] = Math.max(val[n - 1] + knapsack01(wt, val, w - wt[n - 1], n - 1), knapsack01(wt, val, w, n - 1));
        } else {
            cache[n][w] =  knapsack01(wt, val, w, n - 1);
        }

        return cache[n][w];
    }


    /* to do
    *   - understand the working of above code
    *   - knapsack memoization - done
    * */

    public static int knapsackTopDown(int[] wt, int[] val, int w, int n) {
        int[][] t = new int[n + 1][w + 1];

        /*for (int i = 0; i < n; i++)
            t[i][0] = 0;

        for (int i = 0; i < w; i++)
            t[0][i] = 0;*/

        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= w; col++) {
                if(wt[row - 1] < col) {
                    t[row][col] = Math.max(val[row - 1] + t[row - 1][col - wt[row - 1]], t[row - 1][col]);
                } else
                    t[row][col] = t[row - 1][col];
            }
        }

        return t[n][w];
    }

}
