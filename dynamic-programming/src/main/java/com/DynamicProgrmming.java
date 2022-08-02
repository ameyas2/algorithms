package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DynamicProgrmming {
    public static void main(String[] args) {
        //goldMineProblem();
        //coinChange(7, new int[]{2,3,5});
        //System.out.println(fibonacci(10, new long[101]));
        //recur(10);
        //System.out.println(numberOfArithmeticSlices(new int[]{1,2,3,4}));
        //System.out.println(numTeams(new int[]{2,5,3,4,1}));
        System.out.println(distributeCookies(new int[] {8,15,10,20,8}, 2));
        System.out.println(distributeCookies(new int[] {6,1,3,2,2,4,1,2}, 3));
    }

    public static int distributeCookies(int[] cookies, int k) {
        int sum = 0;
        int max = 0;
        for(int i = 0; i < cookies.length; i++) {
            sum += cookies[i];
        }

        int partition = sum / k;
        int[] split = new int[k];
        for(int i = 0; i < k - 1; i++) {
            split[i] = partition;
        }

        split[k - 1] = sum / k + sum % k;

        return max;
    }

   /* public static int distributeCookies(int[] cookies, int k) {

    }*/

    public static int numTeams(int[] rating) {
        int result = 0;
        for(int j = 0; j < rating.length; j++) {
            int smallLeft = 0, bigLeft = 0, smallRight = 0, bigRight = 0;

            for(int i = j - 1; i >= 0; i--) {
                if(rating[i] > rating[j])
                    bigLeft++;
                else if(rating[i] < rating[j])
                    smallLeft++;
            }

            for(int k = j + 1; k < rating.length; k++) {
                if(rating[k] > rating[j])
                    bigRight++;
                else if(rating[k] < rating[j])
                    smallRight++;
            }

            result += bigLeft * smallRight + bigRight * smallLeft;
        }
        return result;
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        int[] result = new int[nums.length];
        for(int i = 2; i < nums.length; i++) {
            if(nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2])
                result[i] = result[i - 1] + 1;
            else
                result[i] = 0;
        }
        int sum = 0;
        for (int i : result) {
            sum+=i;
        }
        return sum;
    }


    public static long fibonacci(int n, long[] cache) {
        if(n <= 2)
            return 1 ;
        if(cache[n] > 0)
            return cache[n];
        System.out.println("Fib of " + n);
        long fib = fibonacci(n - 1, cache) + fibonacci(n - 2, cache);
        cache[n] = fib;
        return fib;
    }

    public static int recur(int n) {
        if(n <= 0)
            return 0;
        System.out.println("n is " + n);
        int num = recur(n - 2);

        return num;
    }


    /**
     * sum = 7
     * coins = 2, 3, 5
     *
     * 0    1   2   3   4   5   6   7   <- coin value
     * 1    0   1   1   1   2   2   2   <- coin changes - for 7 the combination is 2,2,3 | 2,5
     * */

    public static int coinChange(int sum, int[] coins) {
        int[] val = new int[sum + 1];
        val[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = 1; j < val.length; j++) {
                if(j < coins[i])
                    continue;
                val[j] += val[j - coins[i]];
            }
        }

        return val[sum];
    }

    public static void goldMineProblem() {
        int[][] mine = {{0,1,4,2,8,2},
                        {4,3,6,5,0,4},
                        {1,2,4,1,4,6},
                        {2,0,7,3,2,2},
                        {3,1,5,9,2,4},
                        {2,7,0,8,5,1}};
        int rowLength = mine.length;
        int colLength = mine[0].length;

        int[][] sum = new int[rowLength][colLength];

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                sum[row][col] = mine[row][col];
            }
        }

        for(int col = colLength - 2; col >= 0; col--) {
            for(int row = 0; row < rowLength; row++) {
                int up = -1, right = sum[row][col + 1], down = -1;

                if(row - 1 >= 0)
                    up = sum[row - 1][col + 1];

                if(row + 1 < rowLength)
                    down = sum[row + 1][col + 1];

                int max = max(up, right, down);
                sum[row][col] = sum[row][col] + max;
            }
        }

        int maxGold = 0;
        int index = 0;
        for(int row = 0; row < rowLength; row++) {
            if(sum[row][0] > maxGold) {
                maxGold = sum[row][0];
                index = row;
            }
        }
        List<Integer> path = new LinkedList<>();
        System.out.println("Max gold " + maxGold);

        for(int col = 0; col < colLength; col++) {
            int up = -1, right = sum[index][col + 1], down = -1;

            /*if(index - 1 >= 0 && ) {
                up = sum[index - 1][col + 1];
                index--;
            }*/

            if(index + 1 < rowLength) {
                down = sum[index + 1][col + 1];
                index++;
            }

            //pathsum[index][col];

        }
    }

    public static int max(int a, int b, int c) {
        if(a > b && a > c)
            return a;
        else if(b > a && b > c)
            return b;
        else
            return c;
    }

    public static void printArr(int[][] arr) {
        for(int row = 0; row < arr.length; row++) {
            for(int col = 0; col < arr[0].length; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
    }
}
