package com.dp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UglyNumber {
    public static void main(String[] args) {
        log.info(uglyNumOpt(1500));
    }

    public static long uglyNum(int num) {
        long result = 0;

        for(long i = 1, count = 0; count < num; i++) {
            long val = i;
            result = i;

            while (val % 2 == 0) val /= 2;
            while (val % 3 == 0) val /= 3;
            while (val % 5 == 0) val /= 5;

            if(val == 1) {
                count++;
            }
        }
        return result;
    }

    public static long uglyNumOpt(int num) {
        long[] ugly = new long[num + 1];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < num; i++) {
            long v2 = ugly[i2] * 2;
            long v3 = ugly[i3] * 3;
            long v5 = ugly[i5] * 5;

            ugly[i] = min(v2, v3, v5);

            if(ugly[i] == v2)
                i2++;
            if(ugly[i] == v3)
                i3++;
            if(ugly[i] == v5)
                i5++;

        }
        return ugly[num - 1];
    }

    public static long min(long a, long b, long c) {
        if (a <= b && a <= c)
            return a;
        else if (b <= a && b <= c)
            return b;
        else return c;
    }
}
