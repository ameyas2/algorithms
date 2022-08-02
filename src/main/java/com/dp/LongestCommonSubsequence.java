package com.dp;

import com.Searching;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        subSequence("ABCDGH".split(""), "AEDFHR".split(""));
        subSequence("AGGTAB".split(""), "GXTXAYB".split(""));
        repeatingSequence("AABB".split(""));
        repeatingSequence("AABEBCDD".split(""));
    }

    public static void subSequence(String[] seq1, String[] seq2) {
        int nc = seq1.length;
        int nr = seq2.length;

        Integer[][] arr = new Integer[nr + 1][nc + 1];

        for (int i = 0; i <= nr; i++) {
            Arrays.fill(arr[i], 0);
        }

        for (int r = 1; r <= nr; r++) {
            for (int c = 1; c <= nc; c++) {
                if(seq2[r - 1].equals(seq1[c - 1])) {
                    arr[r][c] = arr[r - 1][c - 1] + 1;
                } else {
                    int up = arr[r - 1][c];
                    int left = arr[r][c - 1];
                    arr[r][c] = Utils.max(up, left);
                }
            }
        }

        TileCount.print(arr);
        String[] sequence = sequence(arr, seq1);
        for (int i = sequence.length - 1; i >= 0; i--) {
            if (sequence[i] != null)
                System.out.print(sequence[i] + " ");
        }
        System.out.println();
    }

    public static void repeatingSequence(String[] seq1) {
        int nc = seq1.length;
        int nr = seq1.length;

        Integer[][] arr = new Integer[nr + 1][nc + 1];

        for (int i = 0; i <= nr; i++) {
            Arrays.fill(arr[i], 0);
        }

        for (int r = 1; r <= nr; r++) {
            for (int c = 1; c <= nc; c++) {
                if(seq1[r - 1].equals(seq1[c - 1]) && r != c) {
                    arr[r][c] = arr[r - 1][c - 1] + 1;
                } else {
                    int up = arr[r - 1][c];
                    int left = arr[r][c - 1];
                    arr[r][c] = Utils.max(up, left);
                }
            }
        }

        TileCount.print(arr);
        String[] sequence = sequence(arr, seq1);
        for (int i = sequence.length - 1; i >= 0; i--) {
            if (sequence[i] != null)
                System.out.print(sequence[i] + " ");
        }
        System.out.println();
    }

    public static String[] sequence(Integer[][] arr, String[] sequence) {
        int nr = arr.length;
        int nc = arr[0].length;
        String[] commonSequence = new String[arr.length];

        for (int r = nr - 1, i = 0; r >= 0;) {
            for (int c = nc - 1; c >= 0;) {
                if(arr[r][c] == 0)
                    return commonSequence;

                int up = arr[r - 1][c];
                int left = arr[r][c - 1];

                if(arr[r][c] == left)
                    c--;
                else if(arr[r][c] == up)
                    r--;
                else {
                    commonSequence[i++] = sequence[c - 1];
                    r--;
                    c--;
                }
            }
        }

        return commonSequence;
    }
}
