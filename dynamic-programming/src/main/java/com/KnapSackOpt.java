package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class KnapSackOpt {
    public static void main(String[] args) {
        System.out.println("New " + knapsack(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20));
        System.out.println("Short " + knapsackShort(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20));
        System.out.println("Memoized " + knapsackMemoized(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20, new Integer[7][21]));
        System.out.println("Tabbed " + knapsackTab(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20));
        //System.out.println("Tabbed " + knapsackTabSet(new int[]{2, 6, 1, 4, 5, 10}, new int[]{3, 5, 6, 7, 9, 10}, 6, 20));
        Integer[] set = new Integer[4];
        System.out.println("Tabbed " + knapsackTabSet(new int[]{1,3,4,5}, new int[]{1, 4, 5, 7}, 4, 7, set));
        Arrays.stream(set).forEach(d -> System.out.print(d + " "));
    }

    /*
    * Bounded - include upto x amount
    * Unbpunded
    *
    * */

    public static int knapsackTabSet(int[] wt, int val[], int n, int w, Integer[] set) {
        Integer[][] tab = new Integer[n + 1][w + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if(i == 0 || j == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= wt[i - 1]) {
                    int include = val[i - 1] + tab[i - 1][j - wt[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = Math.max(include, exclude);
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }

        for (int i = n, k = 0; i > 0;) {
            for (int j = w; j > 0;) {
                if(tab[i][j] == tab[i - 1][j])
                    i--;
                else if(tab[i][j] == tab[i][j - 1])
                    j--;
                else {
                    set[k++] = wt[i - 1];
                    j = j - wt[i - 1];
                    i--;
                }
            }
            i--;
        }
        return tab[n][w];
    }
    public static int knapsackTab(int[] wt, int val[], int n, int w) {
        Integer[][] tab = new Integer[n + 1][w + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if(i == 0 || j == 0) {
                    tab[i][j] = 0;
                    continue;
                }

                if(j >= wt[i - 1]) {
                    int include = val[i - 1] + tab[i - 1][j - wt[i - 1]];
                    int exclude = tab[i - 1][j];
                    tab[i][j] = Math.max(include, exclude);
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }

        return tab[n][w];
    }

    public static int knapsackMemoized(int[] wt, int val[], int n, int w, Integer[][] cache) {
        if(n == 0 || w == 0)
            return 0;
        if(cache[n][w] != null)
            return cache[n][w];

        if(w >= wt[n - 1]) {
            int include = val[n - 1] + knapsackMemoized(wt, val, n - 1, w - wt[n - 1], cache);
            int exclude = knapsackMemoized(wt, val, n - 1, w, cache);
            cache[n][w] = Math.max(include, exclude);
        } else {
            cache[n][w] = knapsackMemoized(wt, val, n - 1, w, cache);
        }

        return cache[n][w];
    }

    public static int knapsackShort(int[] wt, int val[], int n, int w) {
        if(n == 0 || w == 0) return 0;
        return w >= wt[n - 1] ? Math.max(val[n - 1] + knapsackShort(wt, val, n - 1, w - wt[n - 1]), knapsackShort(wt, val, n - 1, w)) : knapsackShort(wt, val, n - 1, w);
    }
    public static int knapsack(int[] wt, int val[], int n, int w) {
        if(n == 0 || w == 0) return 0;

        if(w >= wt[n - 1]) {
            int include = val[n - 1] + knapsack(wt, val, n - 1, w - wt[n - 1]);
            int exclude = knapsack(wt, val, n - 1, w);
            return Math.max(include, exclude);
        } else {
            return knapsack(wt, val, n - 1, w);
        }
    }
}
