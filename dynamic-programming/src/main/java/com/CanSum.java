package com;

public class CanSum {
    public static void main(String[] args) {
        System.out.println(canSum(16, new int[]{2,3,5,7}, 4));
        System.out.println(canSumMemoized(10000, new int[]{2,3,5,7}, 4, new boolean[10000000]));
    }

    public static boolean canSum(int sum, int[] arr, int n) {
        if(sum == 0) return true;
        if(sum < 0 || (sum > 0 && n == 0)) return false;

        if(sum >= arr[n - 1])
            return canSum(sum - arr[n - 1], arr, n) || canSum(sum, arr, n - 1);
        else
            return canSum(sum, arr, n - 1);
    }

    public static boolean canSumMemoized(int sum, int[] arr, int n, boolean[] cache) {
        if(sum == 0) return true;
        if(sum < 0 || (sum > 0 && n == 0)) return false;
        if(cache[sum] == true)
            return cache[sum];

        if(sum >= arr[n - 1])
            cache[sum] = canSum(sum - arr[n - 1], arr, n) || canSum(sum, arr, n - 1);
        else
            cache[sum] = canSum(sum, arr, n - 1);

        return cache[sum];
    }
}
