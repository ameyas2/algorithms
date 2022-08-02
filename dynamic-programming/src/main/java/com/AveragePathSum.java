package com;

public class AveragePathSum {
    public static void main(String[] args) {
        System.out.println(avgSum(new int[][]{{1, 2, 3},
                {6, 5, 4},
                {7, 3, 9}}, 3, 3, 1));
    }

    public static int avgSum(int[][] arr, int i, int j, int n) {
        if(i == 0 || j == 0)
            return 0;

        int up = arr[i - 1][j - 1] + avgSum(arr, i - 1, j, n + 1);
        int left = arr[i - 1][j - 1] + avgSum(arr, i, j - 1, n + 1);

        return Math.max(up / n, left / n);
    }
}
