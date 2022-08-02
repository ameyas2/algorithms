package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.IntStream;

@Log4j2
public class HowSum {
    public static void main(String[] args) {
        int target = 7;
        List<Integer> r = new LinkedList<>();
        log.info(howSum(7, IntStream.of(2,3).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(howSum(7, IntStream.of(5, 3, 4, 7).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(howSum(7, IntStream.of(2, 4).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(howSum(8, IntStream.of(2, 3, 5).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(howSum(300, IntStream.of(7, 15).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(howSum(0, IntStream.of(7, 15).toArray(), new HashMap<>(), r)  + " " + r.toString());
    }

    public static boolean howSum(int target, int[] arr, Map<Integer, Boolean> cache, List<Integer> r) {
        if(cache.containsKey(target)) return cache.get(target);
        if (target == 0) return true;
        if (target < 0) return false;

        for (int i : arr) {
            cache.put(target, howSum(target - i, arr, cache, r));
            if(cache.get(target) == true) {
                r.add(i);
                return true;
            }
        }
        return false;
    }
}
