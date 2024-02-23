package com.colak.dynamiccompile;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class MemoryJavaCompilerTest {

    public static void main(String[] args) {
        try {
            // List of class names to compile
            List<String> fileNameList = List.of("AlienInvasion.java", "Starship.java");

            String code1 = """
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
                    public class Starship {
                           public void hello() {
                               System.out.println("Starship: " + this.getClass().getClassLoader());
                           }
                       }
                    """;

            List<String> codeList = List.of(code1, code2);

            // Compile code snippets in memory
            Method mainMethod = new MemoryJavaCompiler().compileStaticMethod("main", "AlienInvasion", fileNameList, codeList);
            mainMethod.invoke(null, (Object) null);

            log.info("Test passed");

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
