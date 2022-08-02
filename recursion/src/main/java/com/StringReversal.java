package com;

public class StringReversal {
    public static String reverse(String input) {
        // Base Case
        if(input.equals(""))
            return "";

        // Shrink the problem
        return reverse(input.substring(1)) + input.charAt(0);
    }

    public static String rev(String str) {
        if(str.isEmpty())
            return "";
        System.out.println("original : " + str);
        String rev = rev(str.substring(1)) + str.charAt(0);
        System.out.println("reversed : " + rev);
        return rev;
    }
}
