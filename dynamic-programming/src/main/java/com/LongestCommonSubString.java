package com;

import java.util.Arrays;

public class LongestCommonSubString {

    static int[][] dp;

    static {
        dp = new int[1001][1001];
        for (int i = 0; i < 1001; i++)
            Arrays.fill(dp[i], -1);
    }

    public static void main(String[] args) {
        int[] count = new int[1];
        String a = "abedfgh";
        String b = "abidrhn";
        lcSub(a, b, a.length(), b.length(), count);
        System.out.println(count[0]);
        System.out.println(lcs(a, b, a.length(), b.length()));
        System.out.println(lcsMem(a, b, a.length(), b.length()));

    }

    public static int lcs(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n -1) == b.charAt(m - 1)) {
            return 1 + lcs(a, b, n - 1, m - 1);
        } else {
            return Math.max(lcs(a, b, n, m - 1), lcs(a, b, n - 1, m));
        }
    }

    public static int lcsMem(String a, String b, int n, int m) {
        if(n == 0 || m == 0)
            return 0;

        if(dp[n][m] > -1)
            return dp[n][m];

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            dp[n][m] = 1 + lcsMem(a, b, n - 1, m - 1);
        } else {
            dp[n][m] = Math.max(lcsMem(a, b, n, m - 1), lcsMem(a, b, n - 1, m));
        }

        return dp[n][m];
    }

    public static int lcSub(String a, String b, int n, int m, int[] count) {
        if(n == 0 || m == 0)
            return 0;

        if(a.charAt(n - 1) == b.charAt(m - 1)) {
            count[0] = 1 + lcSub(a, b, n - 1, m - 1, count);
        } else {
            count[0] = Math.max(lcSub(a, b, n, m - 1, count), lcSub(a, b, n - 1, m, count));
        }

        return count[0];
    }
}
