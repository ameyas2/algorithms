package com;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class Main {
    public static void main(String[] args) {
        //log.info(StringReversal.reverse("hello"));
        //log.info(Pallindrome.isPallindrome("kayakk"));
        //log.info(DecimalToBinary.toBinary(new String(), 233));
        /*log.info(sum(10));
        log.info(sum2(10));*/
        /*int[] arr = {-1, 5, 10, 15, 32, 66, 78, 93};
        log.info(binarySearch(arr, 0, arr.length - 1, 78));
        log.info(binarySearch(arr, 0, arr.length - 1, 100));*/

        /*int[] arr = {30, 26, 4, -8, 89, 2, 65, 10, 0};
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }*/

        //System.out.println(StringReversal.rev("Hello"));
        //System.out.println(Pallindrome.palindrome("helloh"));
        //System.out.println(DecimalToBinary.decToBin(233, ""));
        //System.out.println(sum(10));
       // System.out.println(binarySearch(79, new int[]{-1, 5, 10, 15, 32, 66, 78, 93}, 0, 7));
        long[] cache = new long[1000];
        Arrays.fill(cache, -1);
        System.out.println(fibMem(100, cache));
    }




    public static int fib(int n) {
        return n <= 1 ? 1 : fib(n - 1) + fib(n - 2);
    }

    public static long fibMem(int n, long[] cache) {
        if(n <= 1)
            return 1;
        if(cache[n] != -1)
            return cache[n];

        cache[n] = fibMem(n - 1, cache) + fibMem(n - 2, cache);
        return cache[n];
    }

    public static int sum(int num) {
        return num <= 0 ? 1 : sum(num - 1) + num;
    }

    public static int binarySearch(int target, int[] arr, int l, int r) {
        if(l >= r)
            return -1;

        int m = ( r + l ) / 2;

        if(arr[m] == target)
            return m;

        return arr[m] < target ? binarySearch(target, arr, m + 1, r) : binarySearch(target, arr, 0, m - 1) ;
    }

    public static long sum(long num) {
        return num <= 1 ? num : sum(num - 1) + num;
        //     base case        sub problem
    }

    public static long sum2(long num) {
        if(num <= 1)
            return num;
        return sum2(num - 1) + num;
    }

    public static int binarySearch(int[] arr, int left, int right, int find) {
        if(left > right)
            return -1;

        int mid = (left + right) / 2;

        if(arr[mid] == find)
            return mid;

        if(find > arr[mid])
            left = mid + 1;
        else
            right = mid - 1;

        return binarySearch(arr, left, right, find);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            // Divide the array(problem)
            mergeSort(arr, start, mid); // sort the first half
            mergeSort(arr, mid + 1, end); // sort the second half
            merge(arr, start, mid, end); // merge both the halves
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start; // index to the start of the first array
        int j = mid + 1; // index to the start of the second array
        int k = 0; // index to the start of the temp array

        // merging both the arrays in the sorted order
        while (i <= mid && j <= end) {
            if(arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        // filling temp array with remaining sub array data
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= end)
            temp[k++] = arr[j++];

        // copying the sorted data from temporary array to original
        for(i = start; i <= end; i++)
            arr[i] = temp[i - start];
    }
}
