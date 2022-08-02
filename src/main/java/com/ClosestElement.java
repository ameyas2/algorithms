
package com;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClosestElement {
    public static void main(String[] args) {
        int[] arr = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
        int index = binarySearch(arr, 0, arr.length - 1, 35);
        int[] result = closestElement(arr, index, 4);
        for (int i : result) {
            log.info(i);
        }
    }

    public static int binarySearch(int[] a, int l, int r, int num) {
        if(l >= r)
            return -1;
        int m = (l + r) / 2;
        if(a[m] == num)
            return m;

        return a[m] > num ? binarySearch(a, l, m, num) : binarySearch(a, m + 1, r, num);
    }

    public static int[] closestElement(int[] arr, int index, int k) {
        int[] result = new int[k];
        int n = arr.length;
        int x = arr[index];

        int previous = index - 1, next = index + 1;
        for(int i = 0; i < k; i++) {
            if(previous >= 0 && next < n) {
                result[i] = x - arr[previous] < arr[next] - x ? arr[previous--] : arr[next++];
            } else if(previous < 0 && next < n) {
                result[i] = arr[next++];
            } else if(previous >= 0 && next > n) {
                result[i] = arr[previous--];
            }
        }

        return result;
    }

}
