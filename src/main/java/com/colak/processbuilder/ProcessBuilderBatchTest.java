package com.colak.processbuilder;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
class ProcessBuilderBatchTest {

    public static void main(String[] args) {
        try {
            URL resourceUrl = ProcessBuilderBatchTest.class.getClassLoader().getResource("test.bat");
            assert resourceUrl != null;
            String resourcePath = resourceUrl.getPath();
            if (resourcePath.startsWith("/")) {
                resourcePath = resourcePath.substring(1);
            }

            log.info(resourcePath);
            // Create ProcessBuilder with the command to run the batch file
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", resourcePath)
                    .directory(new File("."))
                    .inheritIO();

            // Start the process
            Process process = builder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Print the exit code
            log.info("Batch file execution completed with exit code: " + exitCode);
        } catch (IOException exception) {
            log.error("Exception ", exception);
        } catch (InterruptedException exception) {
            log.error("Exception ", exception);
            Thread.currentThread().interrupt();
        }
    }
}
