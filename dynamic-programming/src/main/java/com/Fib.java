package com;

import java.math.BigInteger;
import java.util.Arrays;

public class Fib {
    public static void main(String[] args) {
        //System.out.println(fib(5600));
        System.out.println(fibIterativeScaled(1000));
    }

    public static long fib(int n) {
        /*long[] cache = new long[n + 1];
        Arrays.fill(cache, -1);
        return fibMemoized(n, cache);*/

        /*BigInteger[] cache = new BigInteger[n + 1];
        //Arrays.fill(cache, -1);
        for (int i = 0; i <= n; i++)
            cache[i] = BigInteger.valueOf(-1);
        return fibMemoized(n, cache);*/

        return fibIterative(n);
    }

    public static int fibRecursive(int n) {
        if(n <= 0)
            return 0;

        if(n == 1)
            return 1;

        int sum = fibRecursive(n - 1) + fibRecursive(n - 2);
        System.out.println(sum);
        return sum;
    }

    public static long fibMemoized(int n, long[] cache) {
        if(n <= 0)
            return 0;

        if(n == 1)
            return 1;

        if(cache[n] > -1)
            return cache[n];

        cache[n] = fibMemoized(n - 1, cache) + fibMemoized(n - 2, cache);
        //System.out.println(cache[n]);
        return cache[n];
    }

    // not working
    public static BigInteger fibMemoized(int n, BigInteger[] cache) {
        if(n <= 0)
            return BigInteger.ZERO;

        if(n == 1)
            return BigInteger.ONE;

        if(cache[n].compareTo(BigInteger.valueOf(-1)) > 1)
            return cache[n];

        cache[n] = fibMemoized(n - 1, cache).add(fibMemoized(n - 2, cache));
        //System.out.println(cache[n]);
        return cache[n];
    }

    public static long fibIterative(int n) {
        long[] cache = new long[n + 1];
        cache[0] = 0;
        cache[1] = 1;

        for(int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    public static BigInteger fibIterativeScaled(int n) {
        BigInteger[] cache = new BigInteger[n + 1];
        cache[0] = BigInteger.ZERO;
        cache[1] = BigInteger.ONE;

        for(int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1].add(cache[i - 2]);
        }

        return cache[n];
    }
}
