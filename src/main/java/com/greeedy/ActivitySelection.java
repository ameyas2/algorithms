package com.greeedy;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class ActivitySelection {
    public static void main(String[] args) {
        Integer s[] =  {1, 3, 0, 5, 8, 5};
        int f[] =  {2, 4, 6, 7, 9, 9};

        Arrays.sort(s, (a, b) -> {
            if(a > b) return -1;
            else if(a < b) return 1;
            else return 0;
        });
        for (int i : s) {
            System.out.print(i + " ");
        }

        int n = s.length;
        int[] selectedActivity = new int[n];
        selectedActivity[0] = 0;
        for(int pf = 0, st = 1, i = 1; st < n && pf < n;) {
            if(s[st] >= f[pf]) {
                selectedActivity[i++] = st;
                pf = st;
                st++;
            } else
                st++;
        }

        for (int i : selectedActivity) {
            log.info(i);
        }

        ExecutorService es = Executors.newFixedThreadPool(19);
        es.submit(() -> System.out.println("hey"));
        es.shutdown();
    }
}
