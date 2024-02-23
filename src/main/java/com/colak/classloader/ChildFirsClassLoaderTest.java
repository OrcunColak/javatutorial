package com.colak.classloader;

import com.colak.dynamiccompile.MemoryJavaCompiler;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * For the test see <a href="https://www.alibabacloud.com/blog/implementing-java-class-isolation-loading_597343">...</a>
 * For the class loader see <a href="https://medium.com/@isuru89/java-a-child-first-class-loader-cbd9c3d0305">...</a>
 */
@Slf4j
public class ChildFirsClassLoaderTest {

    public static void main(String[] args) throws Exception {

        // List of class names to compile
        List<String> fileNameList = List.of("AlienInvasion.java", "Starship.java");

        String code1 = """
                package com.colak.classloader;
                                    
                public class AlienInvasion {
                      public static void main(String[] args) {
                          AlienInvasion alienInvasion = new AlienInvasion();
                          alienInvasion.hello();
                      }
                      private void hello() {
                          System.out.println("AlienInvasion: " + this.getClass().getClassLoader());
                          Starship starship = new Starship();
                          starship.hello();
                      }
                  }
                """;
        String code2 = """
                package com.colak.classloader;
                                    
                public class Starship {
                       public void hello() {
                           System.out.println("Starship: " + this.getClass().getClassLoader());
                       }
                   }
                """;

        List<String> codeList = List.of(code1, code2);

        // Compile code snippets in memory
        MemoryJavaCompiler memoryJavaCompiler = new MemoryJavaCompiler();
        Map<String, byte[]> compiled = memoryJavaCompiler.compile(fileNameList, codeList);

        String jarFilePath = "test.jar";
        List<String> keys = new ArrayList<>(compiled.keySet());
        List<byte[]> values = new ArrayList<>(compiled.values());

        CreateJarUtil.createJarFile(keys, values, jarFilePath);

        log.info("-------------- Starting test of class in classpath --------------");
        // Load original files before jar
        AlienInvasion.main(null);


        log.info("-------------- Starting test of ChildFirstClassLoader --------------");
        // Create a URL for the JAR file
        URL url1 = new URL("jar:file:" + jarFilePath + "!/");

        URL[] urls = new URL[]{url1};

        ChildFirstClassLoader classLoader = new ChildFirstClassLoader(urls, Thread.currentThread().getContextClassLoader());

        Class<?> testAClass = classLoader.loadClass("com.colak.classloader.AlienInvasion");
        Method mainMethod = testAClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, (Object) null);
    }

    private static void createJarFile(String jarFilePath) throws IOException {
        List<String> classNameList = List.of(
                "com/colak/classloader/TestA.class",
                "com/colak/classloader/Testb.class"
        );
        try (FileOutputStream out = new FileOutputStream(jarFilePath);
             JarOutputStream jarOutputStream = new JarOutputStream(out)) {

            for (String className : classNameList) {
                // Create a JAR entry for each class
                JarEntry jarEntry = new JarEntry(className);
                jarOutputStream.putNextEntry(jarEntry);
                try (FileInputStream fileInputStream = new FileInputStream("target/classes/" + className)) {
                    fileInputStream.transferTo(jarOutputStream);
                }
                jarOutputStream.closeEntry();
            }
        }
    }

}
