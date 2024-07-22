package com.colak.trywithresources;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

@Slf4j
@UtilityClass
public class TryWithResourcesTest {

    class MyBufferedReader extends BufferedReader {
        public MyBufferedReader(Reader in, int sz) {
            super(in, sz);
        }

        @Override
        public void close() throws IOException {
            super.close();
            throw new IOException("test");
        }
    }

    public static void main() {
        readFromMyBufferedReader();
    }

    private static void readFromMyBufferedReader() {
        Reader reader = new StringReader("test");
        try (MyBufferedReader myBufferedReader = new MyBufferedReader(reader, 1024)) {
            int read = myBufferedReader.read();
        } catch (IOException exception) {
            // Prints test
            log.error("Exception", exception);
        }
    }
}
