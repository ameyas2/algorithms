package com;

import java.util.*;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class GenericRecursion {

    static long[] cache;
    static long[][] dp;

    static {
        int n = 10001;
        cache = new long[n];
        Arrays.fill(cache, -1);

        dp = new long[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
    }

    public static void main(String[] args) {
        //printNameRecursive("Jane", 5, 1);
        //printNameBacktrackUp(5);
        //printNameBacktrackDown(1);
        //System.out.println(sumParameterized(5, 0));
        //System.out.println(sumFunctional(5));
        //System.out.println(printSumFunctional(5));
        //System.out.println(factorial(5));

        // Reverse Array
        /*int[] arr = {10, 1, 2, 5, 11, 4};
        printArray(arr);
        reverseArray(arr, 0, 5);
        printArray(arr);
        reverseArray(arr, 0);
        printArray(arr);*/

        // String palindrome
       /* System.out.println(palindrome("rtyytr", 0, 5));
        System.out.println(palindrome("rtyytr", 0));
        System.out.println(palindrome("rtyyt", 0, 4));
        System.out.println(palindrome("rtyyt", 0));*/

        // More than 1 call - fib
        /*System.out.println(fib(8));
        System.out.println(fibMem(100, cache));
        */

        // sub sequence
        /*subSequence(new int[]{3,1,2}, 0, new Stack<>());
        subSequenceSum(new int[]{1,1,2}, 0, new Stack<>(), 2);
        System.out.println(subSequenceSum(new int[]{1,1,2}, 0, 5));
        System.out.println(subSequenceCount(new int[]{1,1,2}, 3, 2));
        System.out.println(subsetCountMultiple(new int[]{2,3,5}, 3, 8));
        System.out.println(subsetCountMultiple(new int[]{2,3,5}, 3, 8, dp));
        */

        // subset sum
       /* List<List<Integer>> res = new LinkedList<>();
        subsetSum(new int[]{2,3,5}, 3, 8, dp, new Stack<>(), res);
        System.out.println(res);
        List<Integer> res1 = new LinkedList<>();
        allSubsetSum(new int[]{2,3,5}, 0, 0, res1);
        System.out.println(res1);*/

        // Permutation
        //permute(new int[]{1,2,3}, new HashSet<>(), new LinkedList<>());
        permute(new int[]{1,2,3}, 0);
    }

    public static void nQueen(int[][] grid, int queens, int j) {
        if(queens == grid.length) {
            return;
        }

        for(int i = 0; i < grid.length; i++) {
            for(int col = 0; col < grid.length; col++) {
                if(i > 0 && col > 0) {
                    //if(grid[i - 1][j] == 1 || grid[i - 1][j] == 1 )
                }
            }
        }
    }

    public static void permute(int[] arr, int index) {
        if(index == arr.length) {
            Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            swap(i, index, arr);
            permute(arr, index + 1);
            swap(i, index, arr);
        }
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void permute(int[] arr, Set<Integer> set, List<Integer> data) {
        if(data.size() == arr.length) {
            System.out.println(data);
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(set.contains(i))
                continue;

            data.add(arr[i]);
            set.add(i);

            permute(arr, new HashSet<>(set), new LinkedList<>(data));

            data.remove(data.size() - 1);
            set.remove(i);
        }
    }

    public static void allSubsetSum(int[] arr, int index, int sum, List<Integer> res) {
        if(index == arr.length) {
            res.add(sum);
            return;
        }

        allSubsetSum(arr, index + 1, sum + arr[index], res);
        allSubsetSum(arr, index + 1, sum, res);
    }

    public static void subsetSum(int[] arr, int n, int sum, long[][] dp, Stack<Integer> stack, List<List<Integer>> res) {
        if(sum == 0) {
            System.out.println();
            stack.forEach(data -> System.out.print(data));
            res.add(stack.stream().collect(Collectors.toList()));
            return;
        }

        if(sum < 0 || (sum > 0 && n == 0)) return;

        stack.add(arr[n - 1]);
        subsetSum(arr, n, sum - arr[n - 1], dp, stack, res);

        stack.pop();
        subsetSum(arr, n - 1, sum, dp, stack, res);
    }

    public static long subsetCountMultiple(int[] arr, int n, int sum, long[][] dp) {
        if(sum == 0) return 1;
        if(sum < n || (sum > 0 && n == 0)) return 0;
        if(dp[n][sum] > -1) return dp[n][sum];

        dp[n][sum] = subsetCountMultiple(arr, n, sum - arr[n - 1]) + subsetCountMultiple(arr, n - 1, sum);
        return dp[n][sum];
    }

    public static int subsetCountMultiple(int[] arr, int n, int sum) {
        if(sum == 0) return 1;
        if(sum < n || (sum > 0 && n == 0)) return 0;

        return subsetCountMultiple(arr, n, sum - arr[n - 1]) + subsetCountMultiple(arr, n - 1, sum);
    }

    public static int subSequenceCount(int[] arr, int n, int sum) {
        if(sum == 0) return 1;
        if(sum < 0 || (sum > 0 && n == 0)) return 0;

        return subSequenceCount(arr, n - 1, sum - arr[n - 1]) + subSequenceCount(arr, n - 1, sum);
    }

    public static boolean subSequenceSum(int[] arr, int index, int sum) {
        if(sum == 0) return true;
        if(sum < 0 || sum > 0 && index == arr.length) return false;

       if(sum >= arr[index])
           return subSequenceSum(arr, index + 1, sum - arr[index]) || subSequenceSum(arr, index + 1, sum);
       else
           return subSequenceSum(arr, index + 1, sum);
    }

    public static void subSequenceSum(int[] arr, int index, Stack<Integer> res, int sum) {
        if(sum == 0) {
            res.forEach(r -> System.out.print(r + " "));
            System.out.println();
            return;
        }

        if(sum < 0 || (index == arr.length && sum > 0))
            return;

        // take
        res.add(arr[index]);
        subSequenceSum(arr, index + 1, res, sum - arr[index]);

        res.pop();
        subSequenceSum(arr, index + 1, res, sum);

    }

    public static void subSequence(int[] arr, int index, Stack<Integer> res) {
        if(index >= arr.length) {
            if(res.isEmpty()) System.out.println("Empty");
            res.forEach(r -> System.out.print(r + " "));
            System.out.println();
            return;
        }

        // take
        res.add(arr[index]);
        subSequence(arr, index + 1, res);

        // not take
        res.pop();
        subSequence(arr, index + 1, res);
    }

    public static long fib(int n) {
        if(n <= 1) return n;

        long last = fib(n - 1);
        long sLast = fib(n - 2);
        return last + sLast;
    }

    public static long fibMem(int n, long[] cache) {
        if(n <= 1) return n;
        if(cache[n] > -1) return cache[n];

        cache[n] = fibMem(n - 1, cache) + fibMem(n - 2, cache);
        return cache[n];
    }


    public static boolean palindrome(String str, int l, int r) {
        if(l >= r) return true;
        if(str.charAt(l) != str.charAt(r)) return false;

        return palindrome(str, l + 1, r - 1);
    }

    public static boolean palindrome(String str, int i) {
        int l = i;
        int r = str.length() - i - 1;
        if(l >= r) return true;
        if(str.charAt(l) != str.charAt(r)) return false;

        return palindrome(str, l + 1);
    }

    public static void reverseArray(int[] arr, int index) {
        int mid = arr.length / 2;
        if(index >= mid)
            return;
        int left = index;
        int right = arr.length - index - 1;
        System.out.println("    reversing " + arr[left] + " " + arr[right]);
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
        reverseArray(arr, index + 1);
    }

    public static void reverseArray(int[] arr, int left, int right) {
        if(left >= right)
            return;

        System.out.println("    reversing " + arr[left] + " " + arr[right]);
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
        reverseArray(arr, left + 1, right - 1);
    }

    public static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(val -> System.out.print(val + " "));
        System.out.println();
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    public static int sumParameterized(int n, int sum) {
        if(n < 1)
            return sum;
        //System.out.println(sum);
        return sumParameterized(n - 1, sum += n);
    }

    public static int printSumParameterized(int n, int sum) {
        if(n < 1)
            return sum;
        System.out.println(n + " " + sum);
        return printSumParameterized(n - 1, sum += n);
    }


    public static int sumFunctional(int n) {
        if(n < 1)
            return n;
        return n + sumFunctional(n - 1);
    }

    public static int printSumFunctional(int n) {
        if(n < 1)
            return n;
        int sum = n + printSumFunctional(n - 1);
        System.out.println(sum);
        return sum;
    }


    public static void printNameRecursive(String name, int down, int up) {
        if(down == 0) return;
        System.out.println(name + " down " + down + " up " + up);
        printNameRecursive(name, down - 1, up + 1);
        System.out.println(name + " down " + down + " up " + up);
    }

    public static void printNameBacktrackUp(int up) {
        if(up < 1) return;
        printNameBacktrackUp(up - 1);
        System.out.println(" up " + up);
    }

    public static void printNameBacktrackDown(int down) {
        if(down > 5) return;
        printNameBacktrackDown(down + 1);
        System.out.println("down " + down);
    }

}
