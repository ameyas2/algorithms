package com;

public class Fibo {
    public static void main(String[] args) {
        System.out.println(fib(8));
        System.out.println(fib(100, new Long[101]));
        System.out.println(fibTab(100));
        System.out.println(fibOpt(100));
    }

    public static long fibOpt(int n) {
        //long[] fib = new long[n + 1];
        long prev2 = 0;
        long pre = 1;

        for(int i = 2; i <= n; i++) {
            long cur = prev2 + pre;
            prev2 = pre;
            pre = cur;
        }

        return pre;
    }

    public static long fibTab(int n) {
        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for(int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    public static long fib(int n, Long[] cache) {
        if(n <= 0) return 0;
        if(n == 1) return 1;

        if(cache[n] != null)
            return cache[n];

        cache[n] = fib(n - 1, cache) + fib(n - 2, cache);
        return cache[n];
    }

    public static long fib(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }


}
