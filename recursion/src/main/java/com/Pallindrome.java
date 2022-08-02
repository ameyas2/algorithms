package com;

public class Pallindrome {
    public static boolean isPallindrome(String input) {
        // Base Case
        if(input.isEmpty() || input.length() == 1)
            return true;

        // Shrink Problem
        if(input.charAt(0) == input.charAt(input.length() - 1))
            return isPallindrome(input.substring(1, input.length() - 1));

        // Fallback case
        return false;
    }

    public static boolean palindrome(String str) {
        System.out.println("String : " + str);
        if(str.length() <= 1)
            return true;
        if(str.charAt(0) != str.charAt(str.length() - 1))
            return false;

        return palindrome(str.substring(1, str.length() - 1));
    }
}
