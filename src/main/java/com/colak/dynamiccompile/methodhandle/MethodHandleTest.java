package com.colak.dynamiccompile.methodhandle;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

/// General usage to load class bytes
/// ```
/// MethodHandles.Lookup lookup = MethodHandles.lookup();
/// MethodHandles.Lookup hidden = lookup.defineHiddenClass(classBytes, true);
/// Class<?> cls = hidden.lookupClass();
///```
public class MethodHandleTest {

    static void main() throws Throwable {
        // Step 1: Generate Java Source Code
        String className = "HiddenHello";
        String sourceCode = """
                package com.colak.dynamiccompile.methodhandle;
                public class HiddenHello {
                    public String greet() {
                        return "Hello from hidden class!";
                    }
                }
                """;

        // Step 2: Compile Java Code and Get Bytecode
        byte[] classBytes = compileJavaClass(className, sourceCode);

        // Step 3: Define Hidden Class
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup hiddenLookup = lookup.defineHiddenClass(classBytes, true);

        // Step 4: Get Hidden Class
        Class<?> hiddenClass = hiddenLookup.lookupClass();

        // Step 5: Create an Instance
        Object instance = hiddenClass.getDeclaredConstructor().newInstance();

        // Step 6: Invoke `greet` Method Using Reflection
        Method greetMethod = hiddenClass.getMethod("greet");
        String message = (String) greetMethod.invoke(instance);

        System.out.println(message);  // Output: Hello from hidden class!
    }

    // Helper method: Compiles Java source code and returns bytecode
    private static byte[] compileJavaClass(String className, String sourceCode) throws Exception {
        Path tempDir = Files.createTempDirectory("hidden_class");
        Path sourceFile = tempDir.resolve(className + ".java");
        Files.writeString(sourceFile, sourceCode);

        // Compile the Java file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler.run(null, null, null, sourceFile.toString()) != 0) {
            throw new RuntimeException("Compilation failed");
        }

        // Read the compiled .class file as byte array
        Path classFile = tempDir.resolve(className + ".class");
        return Files.readAllBytes(classFile);
    }
}
