package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Log4j2
public class BestSum {
    public static void main(String[] args) {
        int target = 7;
        List<Integer> r = new LinkedList<>();
        log.info(bestSum(7, IntStream.of(2,3).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(bestSum(7, IntStream.of(5, 3, 4, 7).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(bestSum(7, IntStream.of(2, 4).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(bestSum(8, IntStream.of(2, 3, 5).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(bestSum(300, IntStream.of(7, 15).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
        log.info(bestSum(0, IntStream.of(7, 15).toArray(), new HashMap<>(), r)  + " " + r.toString());
        r.clear();
    }

    public static boolean bestSum(int target, int[] arr, Map<Integer, Boolean> cache, List<Integer> r) {
        if(cache.containsKey(target)) return cache.get(target);
        if (target == 0) return true;
        if (target < 0) return false;

        for (int i : arr) {
            cache.put(target, bestSum(target - i, arr, cache, r));
            if(cache.get(target) == true) {
                r.add(i);
                return true;
            }
        }
        return false;
    }
}
