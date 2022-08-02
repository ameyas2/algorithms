package com;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        System.out.println(unboundedKnapsack(new int[]{1, 2, 3, 4}, new int[]{5, 6, 8, 8}, 4));
    }

    public static int unboundedKnapsack(int[] len, int[] price, int l) {
        return unboundedKnapsack(len, price, len.length, l);
    }

    public static int unboundedKnapsack(int[] len, int[] price, int n, int l) {
        if(n == 0 || l == 0)
            return 0;
        if(len[n - 1] <= l)
            return Math.max(price[n - 1] + unboundedKnapsack(len, price, n, l - len[n - 1]), unboundedKnapsack(len, price, n - 1, l));
        else
            return unboundedKnapsack(len, price, n - 1, l);
    }

    public static int unboundedKnapsackMemoized(int[] len, int[] price, int n, int l, int[][] cache) {
        if(n == 0 || l == 0)
            return 0;
        if(cache[n][l] > -1)
            return cache[n][l];

        if(len[n - 1] <= l)
            cache[n][l] = Math.max(price[n - 1] + unboundedKnapsack(len, price, n, l - len[n - 1]), unboundedKnapsack(len, price, n - 1, l));
        else
            cache[n][l] = unboundedKnapsack(len, price, n - 1, l);

        return cache[n][l];
    }
}
