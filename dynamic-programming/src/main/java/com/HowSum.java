package com;

public class HowSum {
    public static void main(String[] args) {
        int[] res = new int[10];
        System.out.println(howSum(new int[]{2,3,5,7}, 12, res, 4, 0));

    }

    public static boolean howSum(int[] arr, int sum, int[] res, int n, int count) {
        if(sum == 0) return true;
        if(sum < 0 || (sum > 0 && n == 0)) return false;

        if(sum < arr[n - 1])
            return howSum(arr, sum, res, n - 1, count);

        boolean add = howSum(arr, sum - arr[n - 1], res, n - 1, count++);
        if(add)
            res[count] = arr[n - 1];
        boolean notAdd = howSum(arr, sum, res, n - 1, count);
        if(notAdd)
            res[count] = 0;

        return add || notAdd;
    }
}
