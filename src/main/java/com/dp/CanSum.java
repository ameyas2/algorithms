package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

@Log4j2
public class CanSum {
    public static void main(String[] args) {
        int target = 7;
        log.info(canSum(7, IntStream.of(2,3).toArray(), new HashMap<>()));
        log.info(canSum(7, IntStream.of(5, 3, 4, 7).toArray(), new HashMap<>()));
        log.info(canSum(7, IntStream.of(2, 4).toArray(), new HashMap<>()));
        log.info(canSum(8, IntStream.of(2, 3, 5).toArray(), new HashMap<>()));
        log.info(canSum(300, IntStream.of(7, 15 ).toArray(), new HashMap<>()));
    }

    public static boolean canSum(int target, int[] arr, Map<Integer, Boolean> cache) {
        //log.info(target);
        if(cache.containsKey(target))
            return cache.get(target);
        if(target == 0) return true;
        if(target < 0) return false;

        for (int i : arr) {
            cache.put(target, canSum(target - i, arr, cache));
            if(cache.get(target) == true)
                return true;
        }
        cache.put(target, false);
        return false;
    }
}

class Difference {
    Integer target, num;

    public Difference(Integer target, Integer num) {
        this.target = target;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Difference diff = (Difference) o;
        return (Objects.equals(target, diff.target) &&
                Objects.equals(num, diff.num)) ||
                (Objects.equals(target, diff.num) &&
                        Objects.equals(num, diff.target));
    }

    @Override
    public int hashCode() {
        return Objects.hash(target + num);
    }
}