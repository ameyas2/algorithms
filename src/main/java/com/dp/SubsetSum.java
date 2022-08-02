package com.dp;

public class SubsetSum {
    public static void main(String[] args) {
        System.out.println(subsetSum(11, new int[]{2,3,7,8,10}));
    }

    public static boolean subsetSum(int sum, int[] num) {
        // Row size = length of number choices
        // Column size length = total sum
        Boolean[][] arr = new Boolean[num.length + 1][sum + 1];

        for(int i = 0; i < arr[0].length; i++)
            arr[0][i] = false;

        for(int i = 0; i < arr.length; i++)
            arr[i][0] = true;

        CoinChange.print(arr);

        for(int r = 1; r <= num.length; r++) {
            for(int c = 1; c <= sum; c++) {
                // If the column to be processed is less than the number chosen from the numbers array (row'th element of the num array)
                // OR the upper row value for the same column is true | 1 then copy the upper value
                if(c < num[r - 1] || arr[r - 1][c])
                    arr[r][c] = arr[r - 1][c];
                else {
                    // if the column value is greater than the
                    arr[r][c] = arr[r - 1][c - num[r - 1]];
                }
            }
        }

        CoinChange.print(arr);
        

        return arr[arr.length - 1][arr.length - 1];
    }
}
