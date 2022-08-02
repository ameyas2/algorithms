package com.dp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FriendPairing {
    public static void main(String[] args) {
        log.info(friendsPairing(4));
    }

    public static int friendsPairing(int n) {
        int[] arr = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            if(i <= 2)
                arr[i] = i;
            else
                arr[i] = arr[i - 1] + (i - 1) * arr[i - 2];
        }

        return arr[arr.length - 1];
    }
}
