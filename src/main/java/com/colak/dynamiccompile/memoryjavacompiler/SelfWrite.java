package com.colak.dynamiccompile.memoryjavacompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.file.Files;

public class SelfWrite {
    static void main() throws Exception {
        String code = "public class Hello { public static void hi() { System.out.println(\"Hi!\"); } }";

        Files.writeString(new File("Hello.java").toPath(), code);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "Hello.java");
        Class<?> cls = Class.forName("Hello");
        cls.getMethod("hi").invoke(null);
    }
}