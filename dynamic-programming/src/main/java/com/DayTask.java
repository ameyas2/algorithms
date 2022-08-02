package com;

import java.util.Arrays;

public class DayTask {

    static int[][] dp;

    static {
        int n = 1000;
        dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
    }

    public static void main(String[] args) {
        System.out.println(maxTaskPoints(new int[][]{{2,1,3}, {3,4,6}, {10, 1, 6}, {8,3,7}}, 3, 3));
        System.out.println(maxTasksPointsMemoized(new int[][]{{2,1,3}, {3,4,6}, {10, 1, 6}, {8,3,7}}, 3, 3));
    }

    public static int maxTaskPoints(int[][] arr, int day, int last) {
        if(day == 0) {
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < arr[0].length; i++) {
                if(i != last) {
                    max = Math.max(max, arr[day][i]);
                }
            }
            return max;
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr[0].length; i++) {
            if(i != last) {
                int point = arr[day][i] + maxTaskPoints(arr, day - 1, i);
                max = Math.max(max, point);
            }
        }
        return max;
    }

    public static int maxTasksPointsMemoized(int[][] arr, int day, int last) {
        if(day == 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < arr[0].length; i++) {
                if(i != last) {
                    max = Math.max(max, arr[0][i]);
                }
            }
            return max;
        }

        if(dp[day][last] != -1)
            return dp[day][last];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr[day].length; i++) {
            if(i != last) {
                int point = arr[day][i] + maxTasksPointsMemoized(arr, day - 1, i);
                max = Math.max(max, point);
            }
        }
        dp[day][last] = max;
        return dp[day][last];
    }
}
