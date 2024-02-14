package com.colak.java9.stackwalker;

import lombok.extern.slf4j.Slf4j;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

@Slf4j
public class StackWalkerTest {

    public static void main(String[] args) {
        new StackWalkerTest().methodOne();
    }

    private void methodOne() {
        methodTwo();
    }

    private void methodTwo() {
        methodThree();
    }

    private void methodThree() {
        // get the caller class.
        StackWalker stackWalker = StackWalker.getInstance(RETAIN_CLASS_REFERENCE);
        // The methods forEach and walk will pass StackFrame instances in the stream or to the consumer callback.
        stackWalker.forEach(frame ->
                log.info("Class: {}, Method: {}, Line: {}",
                        frame.getClassName(), frame.getMethodName(), frame.getLineNumber()));
    }
}
