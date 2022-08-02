package com;

import java.util.Arrays;

public class SubSequenceSumNonAdjacent {

    static int[] dp;

    static {
        dp = new int[10001];
        Arrays.fill(dp, -1);
    }

    public static void main(String[] args) {
        /*System.out.println(maxSum(new int[] {2, 1, 4, 9}, 4));
        System.out.println(maxSumMemoized(new int[] {2, 1, 4, 9}, 4));
        System.out.println(maxSumTab(new int[] {2, 1, 4, 9}, 4));*/
        System.out.println(nonAdjacentSumTab(new int[] {2, 1, 4, 9}, 4));
    }

    public static int nonAdjacentSumTab(int[] arr, int n) {
        int prev = arr[0];
        int prev2 = 0;
        for(int i = 1; i < n; i++) {
            int take = arr[i] + prev2;
            int notTake = prev;
            int cur = Math.max(take, notTake);
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }

    public static int maxSum(int[] arr, int n) {
        if(n < 0)
            return 0;

        int pick = arr[n - 1] + maxSum(arr, n - 2);
        int notPick = 0 + maxSum(arr, n - 1);

        return Math.max(pick, notPick);
    }

    public static int maxSumMemoized(int[] arr, int n) {
        if(n <= 0)
            return 0;

        if(dp[n] != -1)
            return dp[n];

        int pick = arr[n - 1] + maxSumMemoized(arr, n - 2);
        int notPick = 0 + maxSumMemoized(arr, n - 1);

        dp[n] = Math.max(pick, notPick);
        return dp[n];
    }

    public static int maxSumTab(int[] arr, int n) {
        dp = new int[n + 1];
        dp[0] = arr[0];
        for(int i = 1; i < n; i++) {
            int take = arr[i];
            if(i > 1)
                take += dp[i - 2];
            int notTake = dp[i - 1];
            dp[i] = Math.max(take, notTake);
        }

        return dp[n];
    }
}
