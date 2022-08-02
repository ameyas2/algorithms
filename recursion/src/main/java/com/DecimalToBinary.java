package com;

public class DecimalToBinary {
    public static String toBinary(String result, int num) {
        // Base Case
        if(num == 0)
            return result;
        result = num % 2 + result;
        // Sub problem
        return toBinary(result, num / 2);
    }

    public static String decToBin(Integer num, String bin) {
        if(num == 0)
            return bin + num;
        return decToBin(num / 2, (num % 2)  + bin);
    }
}
