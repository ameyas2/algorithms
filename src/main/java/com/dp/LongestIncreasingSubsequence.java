package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        //System.out.println(sequence(new int[]{10,22,9,33,21,50,41,60,80,1}));
    }

    public static int sequence(int[] arr) {
        int n = arr.length;
        Integer[] seq = new Integer[n];
        Integer[][] path = new Integer[n + 1][n + 1];

        Arrays.fill(seq, 1);

        for(int i = 0, j = 1, k = 0; i < n && j < n;) {
            if(i == j) {
                path[j][j] = arr[j];
                j++;
                i = 0;
                k = 0;
            } else {
                if(arr[j] > arr[i]) {
                    if(seq[i] + 1 > seq[j]) {
                        seq[j] = Utils.max(seq[j], seq[i] + 1);
                        path[j][k++] = arr[i];
                    }
                }
                i++;
            }
        }

        Utils.print(seq);

        int max = 0, index = 0;
        for(int i = 0; i < n; i++) {
            if(seq[i] > max) {
                max = seq[i];
                index = i;
            }
        }

        Utils.print(path[index]);

        return max;
    }
}
