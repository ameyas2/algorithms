package com;

import java.util.Arrays;

public class StairsClimbing {

    static int[] cache;

    static {
         cache = new int[100001];
        Arrays.fill(cache, -1);
    }

    public static void main(String[] args) {
        //System.out.println(stairs(1000));
        System.out.println(stairsTabulation(9));
    }

    public static int stairs(int n) {
        if(n <= 1)
            return 1;

        return stairs(n - 1) + stairs(n - 2);
    }

    public static int stairsMemoized(int n) {
        if(n <= 1)
            return 1;
        if(cache[n] != -1)
            return cache[n];
        cache[n] = stairsMemoized(n - 1) + stairsMemoized(n - 2);
        return cache[n];
    }

    public static int stairsTabulation(int n) {
        if(n <= 1)
            return 1;
        int prev = 1, old = 0, cur = 0;
        for(int i = 2; i <= n; i++) {
            cur = prev + old;
            old = prev;
            prev = cur;
        }

        return cur;
    }
}
