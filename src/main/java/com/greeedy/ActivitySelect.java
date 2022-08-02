package com.greeedy;

import lombok.AllArgsConstructor;

import java.util.*;

public class ActivitySelect {
    public static void main(String[] args) {
        int[] start =  {0, 8, 1, 5, 3, 5};
        int[] finish =  {6, 9, 2, 7, 4, 9};

        List<Time> task = new ArrayList<>();
        for(int i = 0; i < start.length; i++) {
            task.add(new Time(start[i], finish[i]));
        }
        Collections.sort(task);
        for(int i = 0; i < start.length; i++) {
            task.add(new Time(start[i], finish[i]));
        }
    }

    @AllArgsConstructor
    public static class Time implements Comparable<Time>{
        public int start;
        public int end;

        @Override
        public int compareTo(Time o) {
            return  this.end - o.end;
        }
    }
}
