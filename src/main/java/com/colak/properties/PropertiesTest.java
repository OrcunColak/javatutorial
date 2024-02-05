package com.colak.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * See <a href="https://medium.com/@JavaFusion/what-is-a-properties-file-in-java-e955c3adc92f">...</a>
 */
@Slf4j
public class PropertiesTest {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("test.properties")) {
            properties.load(inputStream);
        }

        String key = "server.port";
        String property = properties.getProperty(key);
        log.info("{} : {}", key, property);
    }
}
