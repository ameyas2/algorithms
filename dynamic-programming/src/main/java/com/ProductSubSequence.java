package com;

public class ProductSubSequence {
    public static void main(String[] args) {
        System.out.println(prodSub(new int[]{1,2,3,4}, 4, 10));
    }

    public static int prodSub(int[] arr, int n, int k) {
        if(n == 0 || k == 1)
            return 0;

        if(k >= arr[n - 1]) {
            int inc = 1 + prodSub(arr, n - 1, k / arr[n - 1]);
            int exclude = prodSub(arr, n - 1, k);
            return inc + exclude;
        } else return prodSub(arr, n - 1, k);
    }
}
