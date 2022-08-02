package com;

public class GridDP {
    public static void main(String[] args) {
        /*int[][] arr = {{5,9,6}, {11,5,2}};
        System.out.println(minPathSum(arr));*/
        int[][] arr2 = {{2,1,3}, {6,5,4}, {7,8,9}};
        System.out.println(minFallingPathSum(arr2));

        int[][] arr3 = {{-19, 57}, {-40, -5}};
        System.out.println(minFallingPathSum(arr3));
    }

    public static int minFallingPathSum(int[][] matrix) {
        int min = 1000000000;
        Integer[][] dp = new Integer[matrix.length][matrix[0].length];

        for(int j = 0; j < matrix[0].length; j++) {
            int m = minFallingPathSum(matrix, matrix.length - 1, j, dp);
            if(m < min)
                min = m;
        }
        return min;
    }

    public static int minFallingPathSum(int[][] matrix, int i, int j, Integer[][] dp) {
        if(j < 0 || j > matrix[0].length - 1) return 1000000000;

        if(dp[i][j] != null)
            return dp[i][j];

        if(i == 0) return matrix[0][j];

        int ul = matrix[i][j] + minFallingPathSum(matrix, i - 1, j - 1, dp);
        int u = matrix[i][j] + minFallingPathSum(matrix, i - 1, j, dp);
        int ur = matrix[i][j] + minFallingPathSum(matrix, i - 1, j + 1, dp);
        /*if(j - i > 0) dp[i][j - 1] = ul;
        if(j + i < matrix[0].length - 1) dp[i][j + 1] = ur;
        dp[i][j] = u;*/

        return dp[i][j] = Math.min(ul, Math.min(u, ur));
    }


    public static int minPathSum(int[][] arr) {
        //return minPathSum(arr, arr.length - 1, arr[0].length - 1, 0);
        return minPathSum(arr, arr.length - 1, arr[0].length - 1, new Integer[arr.length + 1][arr[0].length + 1]);
    }


    public static int minPathSum(int[][] arr, int i, int j) {
        if(i == 0 && j == 0)
            return arr[i][j];

        if(i < 0 || j < 0) return Integer.MAX_VALUE;

        int up = arr[i][j] + minPathSum(arr, i - 1, j);
        int left = arr[i][j] + minPathSum(arr, i, j - 1);
        return Math.min(up, left);
    }

    public static int minPathSum(int[][] arr, int i, int j, Integer[][] dp) {
        if(i == 0 && j == 0)
            return arr[i][j];

        if(i < 0 || j < 0) return 1000000000;

        if(dp[i][j] != null)
            return dp[i][j];

        int up = arr[i][j] + minPathSum(arr, i - 1, j, dp);
        int left = arr[i][j] + minPathSum(arr, i, j - 1, dp);
        return dp[i][j] = Math.min(up, left);
    }

    public static int minSum(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for(int col = 0; col < arr[0].length; col++) {
            min = Math.min(min, minSum(arr, 0, col));
        }
        return min;
    }

    public static int minSum(int[][] arr, int i, int j) {
       if(i == arr.length - 1) return arr[i][j];

       int dl  = j > 0 ? arr[i][j] + minSum(arr, i + 1, j - 1) : 1000000000;
       int d  = arr[i][j] + minSum(arr, i + 1, j);
       int dr  = j < arr[0].length ? arr[i][j] + minSum(arr, i + 1, j + 1) : 1000000000;

       return Math.min(Math.min(dl, d), dr);
    }


}
