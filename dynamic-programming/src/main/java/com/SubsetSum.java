package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {

    static Boolean[][] dp;

    static {
        dp = new Boolean[1001][1001];
    }

    public static void clear() {
        for(int i = 0; i < 1001; i++)
            Arrays.fill(dp[i], null);
    }

    public static void main(String[] args) {
        //System.out.println(subsetSum(new int[]{2,3,7,8,10}, 5, 5));
        //System.out.println(subsetSum());
        //System.out.println(subsetSumTabulationCount(new int[]{2,3,7,8,10}, 20));
        //System.out.println(subsetSumMinimumDifference(new int[]{1,2,7}));
        System.out.println("15 -> " + subsetSumLong(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumLong(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumLong(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumLong(new int[]{2,3,7,8,10}, 5, 12));
        System.out.println();
        System.out.println("15 -> " + subsetSumShort(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumShort(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumShort(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumShort(new int[]{2,3,7,8,10}, 5, 12));
        System.out.println("Memoized");
        System.out.println("15 -> " + subsetSumMemoized(new int[]{2,3,7,8,10}, 5, 15));
        clear();
        System.out.println("24 -> " + subsetSumMemoized(new int[]{2,3,7,8,10}, 5, 24));
        clear();
        System.out.println("16 -> " + subsetSumMemoized(new int[]{2,3,7,8,10}, 5, 16));
        clear();
        System.out.println("12 -> " + subsetSumMemoized(new int[]{2,3,7,8,10}, 5, 12));
        clear();
        System.out.println("Tabulation");
        /*System.out.println("15 -> " + subsetSumTabulation(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumTabulation(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumTabulation(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumTabulation(new int[]{2,3,7,8,10}, 5, 12));*/

        System.out.println("Subset sum count");
        System.out.println("15 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 12));
    }

    public static int subsetSumCount(int[] arr, int n, int sum) {
        int[][] t = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for(int j = 0;  j <= sum; j++) {
                if(j == 0) {
                    t[i][j] = 1;
                    continue;
                }
                if(i == 0) continue;

                if(j >= arr[i - 1]) {
                    t[i][j] = t[i - 1][j] + t[i - 1][j - arr[i - 1]];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return t[n][sum];
    }

    public static boolean subsetSumTabulation(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    System.out.println("i " + i + " j " + j);
                    dp[i][j] = true;
                    continue;
                }

                if(i > 0) {
                    if (j <= arr[i - 1]) {
                        System.out.println("i " + i + " j " + j);
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][sum];
    }

    public static boolean subsetSumMemoized(int[] arr, int n, int sum) {
        if(sum == 0)
            return true;
        if(sum < 0 || sum > 0 && n == 0)
            return false;
        if(dp[n][sum] != null)
            return dp[n][sum];
        dp[n][sum] = sum >= arr[n - 1] ? subsetSumMemoized(arr, n - 1, sum - arr[n - 1]) || subsetSumMemoized(arr, n - 1, sum) : subsetSumMemoized(arr, n - 1, sum);
        return dp[n][sum];
    }

    public static boolean subsetSumShort(int[] arr, int n, int sum) {
        if(sum == 0)
            return true;
        if(sum < 0 || sum > 0 && n == 0)
            return false;

        return sum >= arr[n - 1] ? subsetSumShort(arr, n - 1, sum - arr[n - 1]) || subsetSumShort(arr, n - 1, sum) : subsetSumShort(arr, n - 1, sum);
    }

    public static boolean subsetSumLong(int[] arr, int n, int sum) {
        if(sum == 0)
            return true;
        if(sum < 0 || sum > 0 && n == 0)
            return false;

        if(sum >= arr[n - 1]) {
            boolean add = subsetSumLong(arr, n - 1, sum - arr[n - 1]);
            boolean noAdd = subsetSumLong(arr, n - 1, sum);
            return  add || noAdd;
        } else {
            boolean noAdd = subsetSumLong(arr, n - 1, sum);
            return noAdd;
        }
    }

    public static boolean subsetSum() {
        /*int[] arr = new int[]{2,3,7,8,10};
        int sum = 5;
        int[][] t = new int[arr.length + 1][sum + 1];
        for (int i = 0; i < t.length; i++)
            Arrays.fill(t[i], -1);
        return subsetSum(arr, sum, arr.length, t);*/
        return false;
    }

    public static boolean subsetSum(int[] arr, int sum, int n) {
        if(sum == 0)
            return true;
        if(sum < 0 || (sum > 0 && n == 0))
            return false;

        return subsetSum(arr, sum - arr[n -1], n - 1) || subsetSum(arr, sum, n - 1);
    }

    public static boolean subsetSumTabulation(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] t = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            t[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if(arr[i - 1] <= j) {
                    t[i][j] = t[i - 1][j - arr[i - 1]] || t[i - 1][j];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return t[n][sum];
    }


    public static int subsetSumTabulationCount(int[] arr, int sum) {
        int n = arr.length;
        int count = 0;
        boolean[][] t = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            t[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if(arr[i - 1] <= j) {
                    t[i][j] = t[i - 1][j - arr[i - 1]] || t[i - 1][j];
                    if(t[i][j] && j == sum) {
                        count++;
                    }
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return count;
    }

    public static int subsetSumMinimumDifference(int[] arr) {
        int n = arr.length;
        int range = 0;
        for(int i = 0; i < arr.length; i++)
            range += arr[i];
        boolean[][] t = new boolean[n + 1][range + 1];
        for (int i = 0; i <= n; i++)
            t[i][0] = true;



        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= range; j++) {
                if(arr[i - 1] <= j) {
                    t[i][j] = t[i - 1][j - arr[i - 1]] || t[i - 1][j];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < range / 2; i++) {
            if(t[n][i])
                max = i;
        }

        return range - 2 * max;
    }

    // incorrect implementation
    /*public static boolean subsetSum(int[] arr, int sum, int n, int[][] t) {
        if(sum == 0)
            return true;
        if(sum < 0 || (sum > 0 && n == 0))
            return false;
        if(t[n][sum] > -1)
            return t[n][sum] == 1 ? true : false;
        t[n][sum] = subsetSum(arr, sum - arr[n -1], n - 1, t) ? 1 : 0;
        t[n][sum] = subsetSum(arr, sum, n - 1, t) ? 1 : 0;

        return t[n][sum] == 1 ? true : false;
    }*/


}
