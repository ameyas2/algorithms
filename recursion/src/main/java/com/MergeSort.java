package com;

public class MergeSort {
    public static void mergeSort(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            /*System.out.print("l : " + l + " r : " + r + " ");
            for(int i = l; i < r; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();*/
            mergeSort(arr, 0, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= end) {
            temp[k++] = arr[j++];
        }

        for(i = start; i <= end; i++) {
            arr[i] = temp[i - start];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 44, 3, 56, 245, 76, 34, 67};
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
