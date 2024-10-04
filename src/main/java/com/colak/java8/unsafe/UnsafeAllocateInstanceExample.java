package com.colak.java8.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j
class UnsafeAllocateInstanceExample {

    public static void main() throws Exception {
        // Get the Unsafe instance
        Unsafe unsafe = getUnsafe();

        // Create an instance of the class without invoking the constructor
        MyClass myClassInstance = (MyClass) unsafe.allocateInstance(MyClass.class);

        // Display the object's field value (notice the constructor was never called)
        log.info("MyClass field value: {}", myClassInstance.getValue()); // Should be 0, not 42

        // Now we can manually set the field value if needed
        myClassInstance.setValue(99);
        log.info("Updated MyClass field value: {}", myClassInstance.getValue());
    }

    // Helper method to access the Unsafe instance via reflection
    private static Unsafe getUnsafe() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        return (Unsafe) unsafeField.get(null);
    }

    static class MyClass {
        private int value;

        public MyClass() {
            // Constructor should set the value to 42, but it won't be called in this example
            log.info("Constructor called");
            this.value = 42;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

