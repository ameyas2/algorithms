package com;

import java.util.Calendar;

public class LCS {
    public static void main(String[] args) {
        String a = "abedfgh";
        String b = "acbidrhn";
        System.out.println(lcs(a, b, 7, 8));
        System.out.println(lcs(a, b));
        System.out.println(lcsTabulation(a, b));
    }

    public static int lcs(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] cache = new int[n + 1][m + 1];
        Util.fill(cache, -1);
        return lcsMemoized(a, b, n, m, cache);
    }

    public static int lcs(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            return 1 + lcs(a, b, n - 1, m - 1);
        } else {
            return Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
        }
    }

    public static int lcsMemoized(String a, String b, int n, int m, int[][] cache) {
        if(n == 0 || m == 0)
            return 0;

        if(cache[n][m] > -1)
            return cache[n][m];

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            cache[n][m] = 1 + lcs(a, b, n - 1, m - 1);
        } else {
            cache[n][m] = Math.max(lcs(a, b, n - 1, m), lcs(a, b, n, m - 1));
        }

        return cache[n][m];
    }

    public static int lcsTabulation(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] t = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }

        return t[n][m];
    }
}
