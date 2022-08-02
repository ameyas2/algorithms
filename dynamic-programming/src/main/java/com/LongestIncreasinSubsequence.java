package com;

public class LongestIncreasinSubsequence {
    public static void main(String[] args) {
        System.out.println(lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 0, -1));
        System.out.println(lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 0, -1, new Integer[9][9]));
    }

    /*public static int lis(int[] arr, int index, int prev, Integer[][] dp, int[] result, int count) {
        if(index > arr.length - 1)
            return 0;

        if(dp[index][prev + 1] != null)
            return dp[index][prev];

        int len = lis(arr, index + 1, prev);
        if(prev == -1 || arr[index] > arr[prev]) {
            len = Math.max(len, 1 + lis(arr, index + 1, index, result, count + 1));
        }

        return dp[index][prev + 1] = len;
    }*/

    // memoization not working
    public static int lis(int[] arr, int index, int prev, Integer[][] dp) {
        if(index > arr.length - 1)
            return 0;

        if(dp[index][prev + 1] != null)
            return dp[index][prev];

        int len = lis(arr, index + 1, prev, dp);
        if(prev == -1 || arr[index] > arr[prev]) {
            len = Math.max(len, 1 + lis(arr, index + 1, index, dp));
        }

        return dp[index][prev + 1] = len;
    }


    public static int lis(int[] arr, int index, int prev) {
        if(index > arr.length - 1)
            return 0;

        int len = lis(arr, index + 1, prev);
        if(prev == -1 || arr[index] > arr[prev]) {
            len = Math.max(len, 1 + lis(arr, index + 1, index));
        }
        return len;
    }
}
