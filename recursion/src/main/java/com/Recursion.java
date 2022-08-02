package com;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Recursion {
    public static void main(String[] args) {
        //printN(10);
        Stack<Integer> s = new Stack<>();
        s.push(6);
        s.push(2);
        s.push(1);
        s.push(3);
        s.push(5);
        s.push(0);
        //sortStack(s);
        //stackRev(s);
        //stackInsertAtBottom(s, 7);
        /*while (!s.isEmpty()) {
            System.out.println(s.pop());
        }*/
        //tower("S", "D", "H", 3);
        List<String> list = new LinkedList<>();
        //subset("abc", list);
        System.out.println(list);
    }

    /*public static void subset(String str, List<String> list, StringBuilder) {
        if(str.isEmpty())
            return;

        //list.add();
        subset(str.substring(1), sb.append(str.charAt(0)));
        subset(str.substring(1), sb);
    }*/

    public static void tower(String s, String d, String h, int n) {
        if(n == 1) {
            System.out.println("Moving " + n + " from " + s + " to " + d);
            return;
        }


        tower(s, h, d, n - 1);
        System.out.println("Moving " + n + " from " + s + " to " + d);
        tower(h, d, s, n - 1);

    }

    public static void stackRev(Stack<Integer> stack) {
        if(stack.isEmpty())
            return;

        int temp = stack.pop();
        stackRev(stack);
        //stack.push(temp);
        stackInsertAtBottom(stack, temp);
    }

    public static void stackInsertAtBottom(Stack<Integer> stack, int val) {
        if(stack.isEmpty()) {
            stack.push(val);
            return;
        }

        int temp = stack.pop();
        stackInsertAtBottom(stack, val);
        stack.push(temp);
    }

    public static void printN(int n) {
        if(n == 0)
            return;

        System.out.println(n); // descending
        printN(n - 1);
        System.out.println(n); // ascending
    }

    public static void sortStack(Stack<Integer> stack) {
        if(stack.isEmpty())
            return;

        int a = stack.pop();
        sortStack(stack);
        if(stack.isEmpty() || stack.peek() > a)
            stack.push(a);
        else {
            insertStack(stack, a);
        }
    }

    public static void insertStack(Stack<Integer> stack, int a) {
        if(stack.isEmpty() || stack.peek() > a) {
            stack.push(a);
            return;
        }
        int temp = stack.pop();
        insertStack(stack, a);
        stack.push(temp);
    }
}
