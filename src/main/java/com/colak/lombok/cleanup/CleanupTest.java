package com.colak.lombok.cleanup;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * See <a href="https://awstip.com/mastering-lombok-the-ultimate-guide-to-simplifying-java-development-c79e16114986">...</a>
 * Try-with-resources might be a better choice
 */
@Slf4j
public class CleanupTest {

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test.properties");
        File file = new File(resource.getFile());

        @Cleanup
        FileInputStream fileInputStream = new FileInputStream(file);
        int available = fileInputStream.available();
        log.info(String.valueOf(available));
    }
}
