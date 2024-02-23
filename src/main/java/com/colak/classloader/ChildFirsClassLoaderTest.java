package com.colak.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

/**
 * For the test see <a href="https://www.alibabacloud.com/blog/implementing-java-class-isolation-loading_597343">...</a>
 * For the class loader see <a href="https://medium.com/@isuru89/java-a-child-first-class-loader-cbd9c3d0305">...</a>
 */
public class ChildFirsClassLoaderTest {

    public static void main(String[] args) throws Exception {

        createJar();


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

    private static void createJar() {
        List<String> list = List.of(
                "com/colak/classloader/TestA.class",
                "com/colak/classloader/Testb.class"
        );
        JarUtil.createJarFile(
                "target/classes/",
                list,
                "test.jar"
        );
    }
}
