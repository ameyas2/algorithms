package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SubSequence {
    public static void main(String[] args) {
        List<List<Integer>> res = new LinkedList<>();
        subsequence(new int[]{3,1,2}, 0, res, new LinkedList<>());
        res.forEach(r -> {
            r.stream().forEach(val -> System.out.print(val + " "));
            System.out.println();
        });

        List<Integer> res1 = new LinkedList<>();
        subsequenceSum(new int[]{3,1,2}, 0, res1, 0);
        res1.stream().forEach(val -> System.out.print(val + " "));
        System.out.println();

        res1 = new LinkedList<>();
        subsequenceSum(new int[]{3,1,2}, 0, res1, 0, 3);
        res1.stream().forEach(val -> System.out.print(val + " "));
    }

    public static void subsequence(int[] arr, int index, List<List<Integer>> res, List<Integer> temp) {
        if(index >= arr.length) {
            res.add(temp);
            return;
        }

        temp.add(arr[index]);
        subsequence(arr, index + 1, res, new LinkedList<>(temp));
        temp.remove(temp.size() - 1);
        subsequence(arr, index + 1, res, new LinkedList<>(temp));
    }

    public static void subsequenceSum(int[] arr, int index, List<Integer> res, int sum) {
        if(index >= arr.length) {
            res.add(sum);
            return;
        }

        sum += arr[index];
        subsequenceSum(arr, index + 1, res, sum);
        sum -= arr[index];
        subsequenceSum(arr, index + 1, res, sum);
    }

    public static void subsequenceSum(int[] arr, int index, List<Integer> res, int sum, int k) {
        if(index >= arr.length) {
            if(sum == k)
                res.add(sum);
            return;
        }

        sum += arr[index];
        subsequenceSum(arr, index + 1, res, sum, k);
        sum -= arr[index];
        subsequenceSum(arr, index + 1, res, sum, k);
    }
}
