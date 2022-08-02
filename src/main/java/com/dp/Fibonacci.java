package com.dp;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Log4j2
public class Fibonacci {
    public static void main(String[] args) {
        int n = 100;
        long[] cache = new long[n + 1];
        Arrays.fill(cache, -1);
        log.info(fib(n, cache));

    }

    public static long fib(int n, long[] cache) {
        if(cache[n] != -1)
            return cache[n];

        if(n < 2) {
            return 1;
        }

        cache[n] = fib(n-1, cache) + fib(n-2, cache);
        return cache[n];
    }
}
