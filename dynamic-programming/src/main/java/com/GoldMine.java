package com;

public class GoldMine {
    public static void main(String[] args) {
        int[][] mine = {{10, 33, 13, 15},
                {22, 21, 04, 1},
                {5, 0, 2, 3},
                {0, 6, 14, 2}};
        System.out.println(goldMine(mine));
    }

    public static int goldMine(int[][] mine) {
        for(int col = mine.length - 2; col >= 0; col--) {
            for(int row = 0; row < mine.length; row++) {
                int ur = row > 0 ? mine[row - 1][col + 1] : 0;
                int r = mine[row][col + 1];
                int dr = row < mine.length - 1 ? mine[row + 1][col + 1] : 0;

                mine[row][col] = mine[row][col] + Math.max(ur, Math.max(r, dr));
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < mine.length; i++) {
            max = Math.max(max, mine[i][0]);
        }
        return max;
    }
}
