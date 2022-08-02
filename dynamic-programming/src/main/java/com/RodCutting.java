package com;

import java.util.Arrays;

public class RodCutting {
    public static void main(String[] args) {
        //System.out.println(rodCutting(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8));
        //System.out.println(rodCuttingMemoized());
        System.out.println(rodCuttingTabulation(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8));
        System.out.println("Rod recursion -> " + rod(new int[]{1,2,3,4}, new int[]{1,5,8,9}, 4, 4));
        Integer[][] cache = new Integer[1001][1001];
        System.out.println("Rod memoized -> " + rod(new int[]{1,2,3,4}, new int[]{1,5,8,9}, 4, 4, cache));
    }

    public static int rod(int[] len, int[] price, int n, int l, Integer[][] cache) {
        if(n == 0 || l == 0) return 0;
        if(cache[n][l] != null) return cache[n][l];

        if(l >= len[n - 1]) {
            int include = price[n - 1] + rod(len, price, n, l - len[n - 1]);
            int exclude = rod(len, price, n - 1, l);
            return cache[n][l] = Math.max(include, exclude);
        } else
            return cache[n][l] = rod(len, price, n - 1, l);
    }

    public static int rod(int[] len, int[] price, int n, int l) {
        if(n == 0 || l == 0) return 0;

        if(l >= len[n - 1]) {
            int include = price[n - 1] + rod(len, price, n, l - len[n - 1]);
            int exclude = rod(len, price, n - 1, l);
            return Math.max(include, exclude);
        } else
            return rod(len, price, n - 1, l);
    }

    public static int rodCutting(int[] length, int[] price, int n, int l) {
        if(n == 0 || l == 0)
            return 0;

        if(length[n - 1] <= l)
            return Math.max(price[n - 1] + rodCutting(length, price, n, l - length[n - 1]), rodCutting(length, price, n - 1, l));
        else
            return rodCutting(length, price, n - 1, l);
    }

    public static int rodCuttingMemoized() {
        int[][] cache = new int[9][9];

        for(int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        return rodCuttingMemoized(new int[]{1,2,3,4,5,6,7,8}, new int[]{1,5,8,9,10,17,17,20}, 8, 8,  cache);
    }

    public static int rodCuttingMemoized(int[] length, int[] price, int n, int l, int[][] cache) {
        if(n == 0 || l == 0)
            return 0;

        if(cache[n][l] > -1)
            return cache[n][l];

        if(length[n - 1] <= l)
            cache[n][l] = Math.max(price[n - 1] + rodCutting(length, price, n, l - length[n - 1]), rodCutting(length, price, n - 1, l));
        else
            cache[n][l] = rodCutting(length, price, n - 1, l);

        return cache[n][l];
    }

    public static int rodCuttingTabulation(int[] length, int[] price, int n, int l) {
        int[][] t = new int[n + 1][l + 1];

        for(int i = 0; i <= l; i++) {
            t[0][i] = 0;
            if(i <= n)
                t[i][0] = 0;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= l; j++) {
                if(length[i - 1] <= j)
                    t[i][j] = Math.max(price[i - 1] + t[i][j - length[i - 1]], t[i - 1][j]);
                else
                    t[i][j] =  t[i - 1][j];
            }
        }

        return t[n][l];

    }
}
