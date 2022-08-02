package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class Tabulation {
    public static void main(String[] args) {
        //log.info(fib(100));
        log.info(gridTraveller(3, 3));
    }

    public static long fib(int n) {
        long[] arr = new long[n+1];
        Arrays.fill(arr, 0);
        arr[1] = 1;
        for(int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    public static long gridTraveller(int n, int m) {
        long[][] a = new long[n + 1][m + 1];
        a[1][1] = 1;
        for(int r = 1; r <= n; r++) {
            for(int c = 1; c <= m; c++) {
                if(r < n)
                    a[r + 1][c] = a[r][c] + a[r + 1][c];
                if(c < m)
                    a[r][c + 1] = a[r][c] + a[r][c + 1];
            }
        }
        return a[n][n];
    }
}
