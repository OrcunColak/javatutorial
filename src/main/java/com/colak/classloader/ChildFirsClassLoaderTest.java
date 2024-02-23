package com.colak.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class ChildFirsClassLoaderTest {

    public static void main(String[] args) throws Exception {

        List<String> list = List.of("com/colak/classloader/TestA.class");
        JarUtil.createJarFile(
                "target/classes/",
                list,
                "test.jar"
        );


        // Specify the path to the JAR file
        String jarFilePath = "test.jar";

        // Create a URL for the JAR file
        URL url1 = new URL("jar:file:" + jarFilePath + "!/");

        URL[] urls = new URL[]{url1};

        ChildFirstClassLoader classLoader = new ChildFirstClassLoader(urls, Thread.currentThread().getContextClassLoader());

        Class<?> testAClass = classLoader.loadClass("com.colak.classloader.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, new Object[]{args});


    }
}
