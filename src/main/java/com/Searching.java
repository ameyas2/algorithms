package com;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Random;

@Log4j2
public class Searching {
    public static void main(String[] args) {
        //binarySearch();
        mergeSort();
    }

    public static void selectionSort() {
        int num = 10000;
        Integer arr[] = array(num, num * 10);
        //print(arr);
        long start = System.currentTimeMillis();
        for(int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for(int j = i; j < arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            if(min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        log.info((System.currentTimeMillis() - start) + " ms ");
        //print(arr);
    }

    public static void binarySearch() {
        int arr[] = { 2, 3, 4, 10, 15, 22, 28, 32, 34, 40, 62, 75, 80};
        int index = binarySearchImpl(arr, 0, arr.length - 1, 3);
        if(index == -1)
            log.info("Not found");
        else
            log.info("Element found at {}", index + 1);
    }

    public static int binarySearchImpl(int arr[], int l, int r, int search) {
        if(r >= l) {
            int m = (l + r) / 2;

            if (arr[m] == search)
                return m;
            if (arr[m] > search)
                return binarySearchImpl(arr, 0, m - 1, search);

            return binarySearchImpl(arr, m + 1, r, search);
        }
        return -1;
    }

    public static void bubbleSort() {
        int num = 10000;
        Integer[] a = array(num, num);
        int h = a.length;
        //print(a);
        long start = System.currentTimeMillis();
        for(int i = 0; i <= h; i++) {
            boolean swap = false;
            for(int j = 0; j < h - 1; j++) {
                if(a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swap = true;
                }
            }
            if(!swap)
                break;
            h--;
        }
        //print(a);
        log.info(System.currentTimeMillis() - start + " ms");

    }

    public static void print(Object[] arr) {
        StringBuilder builder = new StringBuilder("");
        for (Object i : arr) {
            builder.append(i + " ");
        }
        log.info(builder.toString());
    }

    public static Integer[] array(int size, int limit) {
        Random random = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(limit) + 1;
        }
        return arr;
    }

    public static void mergeSort() {
        int num = 10000000;
        Integer[] a = array(num, num);
        long start = System.currentTimeMillis();
        //System.out.println(Arrays.asList(a));
        mergeSort(a, 0, a.length - 1);
        System.out.println(System.currentTimeMillis() - start + " ms");
        //System.out.println(Arrays.asList(a));
    }

    public static void mergeSort(Integer arr[], int l, int r)  {
        int m = (l + r) / 2;
        if(l >= r) {
            return;
        }
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    public static void merge(Integer a[], int l, int m, int r) {
        // create 2 new arrays to hold 2 halves data
        int lArrSize = m + 1 - l, rArrSize = r - m;

        Integer[] leftArr = new Integer[lArrSize];
        Integer[] rightArr = new Integer[rArrSize];

        int j = l;
        for(int i = 0; i < lArrSize; i++) {
            leftArr[i] = a[j++];
        }

        j = m + 1;
        for(int i = 0; i < rArrSize; i++) {
            rightArr[i] = a[j++];
        }

        // loop through the 2 arrays and fill in the original array with sorted data
        int lArrIndex = 0, rArrIndex = 0, arrIndex = l;
        for(; lArrIndex < lArrSize && rArrIndex < rArrSize; arrIndex++) {
            if(leftArr[lArrIndex] <= rightArr[rArrIndex]) {
                a[arrIndex] = leftArr[lArrIndex++];
            } else {
                a[arrIndex] = rightArr[rArrIndex++];
            }
        }

        // loop through the 2 arrays to put the remaining data in the original array
        while (lArrIndex < lArrSize) {
            a[arrIndex++] = leftArr[lArrIndex++];
        }
        while (rArrIndex < rArrSize) {
            a[arrIndex++] = rightArr[rArrIndex++];
        }
        //System.out.println(Arrays.asList(a) + " " + Arrays.asList(leftArr) + " " + Arrays.asList(rightArr));
    }
}
