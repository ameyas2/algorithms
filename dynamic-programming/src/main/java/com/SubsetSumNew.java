package com;

import java.util.Map;

public class SubsetSumNew {

    private static Boolean[][] cache;
    private static Integer[][] dp;

    static
    {
        cache = new Boolean[1001][1001];
        dp = new Integer[30][30];
    }

    public static void clearCache() {
        cache = new Boolean[1001][1001];
        dp = new Integer[30][30];
    }

    public static void main(String[] args) {
        System.out.println("Subset sum Recursion");
        System.out.println("15 -> " + subsetSum(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSum(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSum(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSum(new int[]{2,3,7,8,10}, 5, 12));

        System.out.println("Subset sum Memoized");
        clearCache();
        System.out.println("15 -> " + subsetSumMem(new int[]{2,3,7,8,10}, 5, 15));
        clearCache();
        System.out.println("24 -> " + subsetSumMem(new int[]{2,3,7,8,10}, 5, 24));
        clearCache();
        System.out.println("16 -> " + subsetSumMem(new int[]{2,3,7,8,10}, 5, 16));
        clearCache();
        System.out.println("12 -> " + subsetSumMem(new int[]{2,3,7,8,10}, 5, 12));

        System.out.println("Subset sum Tab");
        System.out.println("15 -> " + subsetSumTab(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumTab(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumTab(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumTab(new int[]{2,3,7,8,10}, 5, 12));

        System.out.println("Subset sum Count");
        System.out.println("15 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 15));
        System.out.println("24 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 24));
        System.out.println("16 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 16));
        System.out.println("12 -> " + subsetSumCount(new int[]{2,3,7,8,10}, 5, 12));

        System.out.println("Subset sum mem Count");
        clearCache();
        System.out.println("15 -> " + subsetSumCountMem(new int[]{2,3,7,8,10}, 5, 15));
        clearCache();
        System.out.println("24 -> " + subsetSumCountMem(new int[]{2,3,7,8,10}, 5, 24));
        clearCache();
        System.out.println("16 -> " + subsetSumCountMem(new int[]{2,3,7,8,10}, 5, 16));
        clearCache();
        System.out.println("12 -> " + subsetSumCountMem(new int[]{2,3,7,8,10}, 5, 12));
        clearCache();
        System.out.println(equalSumPartition(new int[]{1, 5, 11, 5}));
        clearCache();
        System.out.println(equalSumPartition(new int[]{1, 3, 5, 11}));

        System.out.println("Subset sum tab Count");
        clearCache();
        System.out.println("15 -> " + subsetSumCountTab(new int[]{2,3,7,8,10}, 5, 15));
        clearCache();
        System.out.println("24 -> " + subsetSumCountTab(new int[]{2,3,7,8,10}, 5, 24));
        clearCache();
        System.out.println("16 -> " + subsetSumCountTab(new int[]{2,3,7,8,10}, 5, 16));
        clearCache();
        System.out.println("12 -> " + subsetSumCountTab(new int[]{2,3,7,8,10}, 5, 12));
        clearCache();

        System.out.println("reduceSum 5 -> " + reduceSum(new int[]{1,1,4,2,3}, 5, 0, 4, 0));
    }

    /*
    * 1. Subset sum
    * 2. Subset sum count
    * 3. Subset sum subsets
    *
    * * Recursion
    * * Memoization
    * * Tabulation
    *
    * */

    public static int reduceSumMem(int[] nums, int sum, int l, int r, int count, Map<String, Integer> cache) {
        if(sum == 0) return count;
        if(sum < 0 || l > r) return Integer.MAX_VALUE;

        String key = l + "-" + r + "-" + sum;
        if(cache.containsKey(key)) return cache.get(key);

        int left = reduceSum(nums, sum - nums[l], l + 1, r, count + 1);
        int right = reduceSum(nums, sum - nums[r], l, r - 1, count + 1);
        int min = Math.min(left, right);
        cache.put(key, min);
        return cache.get(key);
    }

    public static int reduceSum(int[] nums, int sum, int l, int r, int count) {
        if(sum == 0) return count;
        if(sum < 0 || l > r) return Integer.MAX_VALUE;

        int left = reduceSum(nums, sum - nums[l], l + 1, r, count + 1);
        int right = reduceSum(nums, sum - nums[r], l, r - 1, count + 1);
        return Math.min(left, right);
    }

    public static boolean equalSumPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 != 0)
            return false;

        return subsetSumTab(nums, nums.length, sum / 2);
    }

    public static int subsetSumCountTab(int[] nums, int n, int sum) {
        for(int i = 0; i <= nums.length; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if(i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(j >= nums[i - 1]) {
                    int include = dp[i - 1][j - nums[i - 1]];
                    int exclude = dp[i - 1][j];
                    dp[i][j] = include + exclude;
                } else {
                    int exclude = dp[i - 1][j];
                    dp[i][j] = exclude;
                }
            }
        }
        return dp[n][sum];
    }

    public static int subsetSumCountMem(int[] nums, int n, int sum) {
        if(sum == 0) return 1;
        if(sum < 0 || (sum > 0 && n == 0)) return 0;

        if(dp[n][sum] != null) return dp[n][sum];

        if(sum >= nums[n - 1]) {
            int include = subsetSumCountMem(nums, n - 1, sum - nums[n - 1]);
            int exclude = subsetSumCountMem(nums, n - 1, sum);
            dp[n][sum] = include + exclude;
        } else {
            dp[n][sum] = subsetSumCountMem(nums, n - 1, sum);
        }

        return dp[n][sum];
    }

    public static int subsetSumCount(int[] nums, int n, int sum) {
        if(sum == 0) return 1;
        if(sum < 0 || (sum > 0 && n == 0))
            return 0;

        if(sum >= nums[n - 1]) {
            int include = subsetSumCount(nums, n - 1, sum - nums[n - 1]);
            int exclude = subsetSumCount(nums, n - 1, sum);
            return include + exclude;
        } else {
            return subsetSumCount(nums, n - 1, sum);
        }
    }

    public static boolean subsetSumTab(int[] nums, int n, int sum) {
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j == 0) {
                    cache[i][j] = true;
                    continue;
                }
                if(i == 0) {
                    cache[i][j] = false;
                    continue;
                }
                cache[i][j] = j >= nums[i - 1] ? cache[i - 1][j - nums[i - 1]] || cache[i - 1][j]
                        : cache[i - 1][j];
            }
        }
        return cache[n][sum];
    }

    public static boolean subsetSumMem(int[] nums, int n, int sum) {
        if(sum == 0)
            return true;
        if(sum < 0 || (sum > 0 && n == 0))
            return false;

        if(cache[n][sum] != null)
            return cache[n][sum];

        if(sum >= nums[n - 1]) {
            boolean include = subsetSumMem(nums, n - 1, sum - nums[n - 1]);
            boolean exclude = subsetSumMem(nums, n - 1, sum);
            cache[n][sum] = include || exclude;
        } else {
            cache[n][sum] = subsetSumMem(nums, n - 1, sum);
        }

        return cache[n][sum];
    }

    public static boolean subsetSum(int[] nums, int n, int sum) {
        if(sum == 0)
            return true;
        if(sum < 0 || (sum > 0 && n == 0))
            return false;

        if(sum >= nums[n - 1]) {
            boolean include = subsetSum(nums, n - 1, sum - nums[n - 1]);
            boolean exclude = subsetSum(nums, n - 1, sum);
            return include || exclude;
        } else {
            return subsetSum(nums, n - 1, sum);
        }
    }
}
