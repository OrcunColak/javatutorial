package com.colak.classloader;

import lombok.experimental.UtilityClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

@UtilityClass
public class CreateJarUtil {

    static void createJarFile(List<String> classNames, List<byte[]> compiledBytesList, String jarFilePath) throws IOException {
        try (FileOutputStream out = new FileOutputStream(jarFilePath);
             JarOutputStream jarOutputStream = new JarOutputStream(out)) {
            for (int i = 0; i < classNames.size(); i++) {
                String className = classNames.get(i);
                String jarEntryName = convertClassNameToPath(className);
                // Create a JAR entry for each compiled class
                JarEntry jarEntry = new JarEntry(jarEntryName);
                jarOutputStream.putNextEntry(jarEntry);

                // Write the compiled class bytes to the JAR
                jarOutputStream.write(compiledBytesList.get(i));

                // Close the JAR entry
                jarOutputStream.closeEntry();
            }
        }
    }

    private static String convertClassNameToPath(String className) {
        // Replace dots with slashes and add ".class" extension
        return className.replace('.', '/') + ".class";
    }
}