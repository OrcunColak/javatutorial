package com.colak.java8.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j
class UnsafeIntTest {

    public static void main() throws Exception {
        // Get the Unsafe instance
        Unsafe unsafe = getUnsafe();

        // Allocate memory for an integer (4 bytes)
        long address = unsafe.allocateMemory(4L);

        try {
            // Set a value at the allocated memory address (12345)
            unsafe.putInt(address, 12345);

            // Get the value from the memory address
            int value = unsafe.getInt(address);

            // Print the value
            log.info("Value: {}", value);

        } finally {
            // Free the allocated memory to avoid memory leaks
            unsafe.freeMemory(address);
        }
    }

    // Helper method to access the Unsafe instance via reflection
    private static Unsafe getUnsafe() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        return (Unsafe) unsafeField.get(null);
    }
}
