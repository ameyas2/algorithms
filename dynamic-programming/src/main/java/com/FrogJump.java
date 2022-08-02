package com;

public class FrogJump {
    public static void main(String[] args) {
        /*System.out.println(jump(new int[]{30, 10, 60, 10, 50, 60}, 6));
        System.out.println(jumpTab(new int[]{30, 10, 60, 10, 50, 60}, 6));
        System.out.println(jump(new int[]{30, 10, 60, 10, 50, 60}, 6, new int[7]));*/
        System.out.println(jump(new int[]{30, 10, 60, 10, 50, 60}, 5, 2, new int[7]));
    }

    public static int jump(int[] arr, int n, int k, int[] dp) {
        if(n == 0) return 0;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++) {
            if(n - i >= 0) {
                int step = jump(arr, n - i, k, dp) + Math.abs(arr[n] + arr[n - i]);
                min = Math.min(min, step);
            }
        }
        return min;
    }


    public static int jump(int[] arr, int n, int[] dp) {
        if(n == 0) return 0;
        if(dp[n] > -1) return dp[n];

        int left = jump(arr, n - 1, dp) + Math.abs(arr[n - 1] - arr[n - 2]);
        int right = Integer.MAX_VALUE;
        if(n > 1)
            right = jump(arr, n - 2, dp) + Math.abs(arr[n - 1] - arr[n - 3]);

        dp[n] = Math.min(left, right);
        return dp[n];
    }

    public static int jump(int[] arr, int index) {
        if(index == 0)
            return 0;

        int left = jump(arr, index - 1) + Math.abs(arr[index - 1] - arr[index - 2]);
        int right = Integer.MAX_VALUE;
        if(index > 1)
            right = jump(arr, index - 2) + Math.abs(arr[index - 1] - arr[index - 3]);

        return Math.min(left, right);
    }

    public static int jumpTab(int[] arr, int index) {
        int[] dp = new int[index + 1];
        dp[0] = 0;
        for (int i = 1; i <= index; i++) {
            int first = dp[index - 1] +  Math.abs(arr[index - 1] + arr[index - 2]);
            int sec = Integer.MAX_VALUE;
            if(index > 1)
                sec = dp[index - 2] + Math.abs(arr[index - 1] - arr[index - 3]);
            dp[index] = Math.min(first, sec);
        }
        return dp[index];
    }
}
