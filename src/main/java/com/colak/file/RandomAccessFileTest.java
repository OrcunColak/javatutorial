package com.colak.file;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

@Slf4j
@UtilityClass
public class RandomAccessFileTest {

    public static void main() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test.bat");
        assert resource != null;
        File file = new File(resource.getFile());
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            while (true) {
                String message = randomAccessFile.readLine();
                if (message == null) {
                    break;
                }
                log.info(message);
            }
        }
    }
}
