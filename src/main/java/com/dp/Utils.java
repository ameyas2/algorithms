package com.dp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Utils {
    public static void print(Object[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void print(Object[] arr) {
        for (int r = 0; r < arr.length; r++) {
            if(arr[r] != null)
                System.out.print(arr[r] + " ");
        }
        System.out.println();
    }
}
