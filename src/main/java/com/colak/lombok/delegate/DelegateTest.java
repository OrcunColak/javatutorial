package com.colak.lombok.delegate;

import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

// In lombok v1.14, @Delegate annotation was introduced. It lets a class use methods from other classes without additional coding.
@Slf4j
class DelegateTest {

    public static class DelegateHelper {
        public String printHello() {
            return "Hello ";
        }
    }

    @Delegate
    private final DelegateHelper delegateHelper = new DelegateHelper();

    public String print(String name) {
        return printHello() + name + ". This is a @Delegate Example";
    }

    public static void main() {
        DelegateTest delegateTest = new DelegateTest();
        log.info(delegateTest.print("World"));
    }
}
