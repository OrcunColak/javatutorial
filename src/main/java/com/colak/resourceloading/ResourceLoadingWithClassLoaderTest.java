package com.colak.resourceloading;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
class ResourceLoadingWithClassLoaderTest {

    public static void main() throws Exception {
        URL resourceUrl = ResourceLoadingWithClassLoaderTest.class.getClassLoader().getResource("test.bat");
        absolutePath(resourceUrl);
    }

    private static void absolutePath(URL resourceUrl) throws URISyntaxException, IOException {
        URI uri = resourceUrl.toURI();
        Path path = Paths.get(uri);
        String absolutePath = path.toString();
        log.info("absolutePath : {}", absolutePath);

        File file = new File(resourceUrl.getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            String content = new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
            log.info("content : {}", content);

        }
    }
}
