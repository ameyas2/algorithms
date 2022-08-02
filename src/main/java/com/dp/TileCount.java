package com.dp;

import java.util.Arrays;

public class TileCount {
    public static void main(String[] args) {
        int[][] goldMine = { {1, 3, 1, 5},
            {2, 2, 4, 1},
            {5, 0, 2, 3},
            {0, 6, 1, 2}
        };

        System.out.println(tileCount(goldMine));
    }

    public static int tileCount(int[][] goldMine) {
        // create a new array to store the summed value
        int cn = goldMine[0].length;
        int rn = goldMine.length;

        Integer[][] result = new Integer[rn][cn];
        Integer[][] path = new Integer[rn][2];

        // fill the last column with the original values
        for(int r = 0; r < rn; r++) {
            for (int c = 0; c < cn; c++) {
                result[r][c] = goldMine[r][c];
            }
        }

        print(result);

        int i = 0;
        for (int c = cn - 2; c >= 0; c--) {
            for(int r = 0; r < rn; r++) {
                int up = r - 1 < 0 ? 0 : result[r][c] + result[r - 1][c + 1];
                int right = result[r][c] + result[r][c + 1];
                int down = r + 1 >= rn ? 0 : result[r][c] + result[r + 1][c + 1];

                if(up >= down && up >= right) {
                    result[r][c] = up;
                    path[i][0] = r - 1;
                    path[i][1] = c + 1;
                } else if(right >= down && right >= up) {
                    result[r][c] = right;
                    path[i][0] = r;
                    path[i][1] = c + 1;
                } else {
                    result[r][c] = down;
                    path[i][0] = r + 1;
                    path[i][1] = c + 1;
                }
                i++;
            }
            i = 0;
        }
        print(result);
        print(path);
        Integer max = 0;
        int r = 0;
        for(; r < rn; r++) {
            if(result[r][0] > max)
                max = result[r][0];
        }
        // the result matrix
        /*for (int r = 0; r < rn; r++) {
            for (int c = 0; c < cn; c++) {
                System.out.print(result[r][c] + " ");
            }
            System.out.println();
        }*/



        return max;
    }

    public static void print(Object[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(Object[] arr) {
        for (int r = 0; r < arr.length; r++) {
            System.out.print(arr[r] + " ");
        }
        System.out.println();
    }
}
