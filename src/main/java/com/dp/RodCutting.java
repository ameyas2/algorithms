package com.dp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RodCutting {
    public static void main(String[] args) {
        int[] size = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] cost = {1, 5, 8, 9, 10, 17, 17, 20};
        int length = 8;
        log.info(cutRod(size, cost, length));
    }

    public static int cutRod(int[] size, int[] cost, int length) {
        int n = size.length;
        int[][] arr = new int[n + 1][length + 1];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= length; c++) {
                if(c < r) {
                    arr[r][c] = arr[r - 1][c];
                } else {
                    int up = arr[r - 1][c];
                    int left = cost[r - 1] + arr[r][c - r];
                    arr[r][c] = max(up, left);
                }
            }
        }

        int[] path = path(arr);
        for (int i = 0; path[i] != 0; i++)
            log.info(path[i] + ", ");
        return arr[n][n];
    }

    public static int[] path(int[][] costMatrix) {
        int[] path = new int[costMatrix[0].length];

        int i = 0;
        for (int r = costMatrix.length - 1; r >= 0;) {
            for (int c = costMatrix[0].length - 1; c >= 0;) {
                if(costMatrix[r][c] == 0)
                    return path;
                int up = costMatrix[r - 1][c];
                int current = costMatrix[r][c];
                if(current == up)
                    r--;
                else {
                    path[i++] = r;
                    c = c - r;
                }
            }
        }

        return path;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }
}
