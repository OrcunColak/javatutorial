package com.colak.nio.files;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
class FilesWriteStringTest {

    public static void main() throws IOException {
        touch();
    }

    private static void touch() throws IOException {
        Path path = Paths.get("example.txt");
        String content = "value";
        // Same as Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.writeString(path, content);
    }
}
