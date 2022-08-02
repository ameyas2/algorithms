package com;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        Integer a[] = {15, 5, 24, 8, 1, 3, 16, 10, 20};
        mergeSort(a, 0, a.length - 1);
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
        int lArrIndex = 0, rArrIndex = 0, arrIndex = l;
        for(; lArrIndex < lArrSize && rArrIndex < rArrSize; arrIndex++) {
            if(leftArr[lArrIndex] <= rightArr[rArrIndex]) {
                a[arrIndex] = leftArr[lArrIndex++];
            } else {
                a[arrIndex] = rightArr[rArrIndex++];
            }
        }
        while (lArrIndex < lArrSize) {
            a[arrIndex++] = leftArr[lArrIndex++];
        }
        while (rArrIndex < rArrSize) {
            a[arrIndex++] = rightArr[rArrIndex++];
        }
        //System.out.println(Arrays.asList(a) + " " + Arrays.asList(leftArr) + " " + Arrays.asList(rightArr));
    }


}
