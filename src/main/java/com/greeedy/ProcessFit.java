package com.greeedy;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Iterator;

@Log4j2
public class ProcessFit {
    public static void main(String[] args) {
        int[] blockSize   = {100, 500, 200, 300, 600};
        int[] processSize = {212, 417, 112, 426};
        bestFit(blockSize, processSize);
    }

    public static void firstFit(int[] blockSize, int[] processSize) {
        for(int p = 0; p < processSize.length; p++) {
            if(processSize[p] == -1)
                continue;
            for(int b = 0; b < blockSize.length; b++) {
                if (blockSize[b] == -1)
                    continue;
                if(processSize[p] <= blockSize[b]) {
                    log.info("process " + processSize[p] + " block " + blockSize[b]);
                    processSize[p] = -1;
                    blockSize[b] = -1;
                    break;
                }
            }

            if(processSize[p] != -1) {
                log.info(processSize[p] + " not allocatecd");
            }
        }
    }

    public static void bestFit(int[] blockSize, int[] processSize) {
        Arrays.sort(blockSize);
        for(int p = 0; p < processSize.length; p++) {
            if(processSize[p] == -1)
                continue;
            for(int b = 0; b < blockSize.length; b++) {
                if (blockSize[b] == -1)
                    continue;
                if(processSize[p] <= blockSize[b]) {
                    log.info("process " + processSize[p] + " block " + blockSize[b]);
                    processSize[p] = -1;
                    blockSize[b] = -1;
                    break;
                }
            }

            if(processSize[p] != -1) {
                log.info(processSize[p] + " not allocatecd");
            }
        }
    }
}
