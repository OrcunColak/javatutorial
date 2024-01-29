package com.colak.jmx.memorymxbean;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

// https://medium.com/@Athula.B/java-heap-memory-candidates-6785c84ae09d
@Slf4j
public class MemoryMBeansTest {

    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        String usedMemory = formatBytes(heapMemoryUsage.getUsed());
        log.info("used memory {} MB", usedMemory);
    }

    private static String formatBytes(long bytes) {
        long megabytes = bytes / 1024 / 1024;
        return megabytes + " MB";
    }
}
