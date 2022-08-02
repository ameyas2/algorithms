package com;

public class MaxNonAdjacentSubsequence {
    public static void main(String[] args) {
        //System.out.println(maxSum(new int[]{5, 5, 10, 100, 10, 5}, 6));
        System.out.println(maxSum(new int[]{2, 1, 4, 9}, 4, new Integer[5]));
    }

    // both below approaches are giving wrong output
    public static int maxSum(int[] arr, int index, Integer[] dp) {
        if(index == 0) return arr[0];
        if (index < 0) return 0;

        if(dp[index] != null)
            return dp[index];

        int inc = arr[index - 1] + maxSum(arr, index - 2, dp);
        int exc = maxSum(arr, index - 1, dp);
        return dp[index] = Math.max(inc, exc);
    }

    public static int maxSum(int[] arr, int index) {
        if(index == 0) return arr[0];
        if (index < 0) return 0;

        int inc = arr[index - 1] + maxSum(arr, index - 2);
        int exc = maxSum(arr, index - 1);
        return Math.max(inc, exc);
    }
}
