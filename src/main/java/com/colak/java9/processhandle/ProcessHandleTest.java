package com.colak.java9.processhandle;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://rathod-ajay.medium.com/a-comprehensive-journey-from-java-8-to-java-21-with-code-examples-of-essential-api-enhancements-6817d2ab3ba8">...</a>
 */
@Slf4j
public class ProcessHandleTest {

    public static void main(String[] args) {
        ProcessHandle currentProcess = ProcessHandle.current();
        log.info("Process ID: " + currentProcess.pid());
        log.info("Is alive? " + currentProcess.isAlive());
    }
}
