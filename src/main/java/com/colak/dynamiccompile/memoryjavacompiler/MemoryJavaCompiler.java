package com.colak.dynamiccompile.memoryjavacompiler;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryJavaCompiler {

    private final JavaCompiler compiler;

    private final StandardJavaFileManager stdManager;

    public MemoryJavaCompiler() {
        compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        stdManager = compiler.getStandardFileManager(diagnostics, null, StandardCharsets.UTF_8);
    }

    public Method compileStaticMethod(String methodName,
                                      String className,
                                      List<String> fileNameList,
                                      List<String> codeList)
            throws ClassNotFoundException {

        Map<String, byte[]> classBytes = compile(fileNameList, codeList);
        MemoryClassLoader classLoader = new MemoryClassLoader(classBytes);
        Class<?> clazz = classLoader.loadClass(className);

        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (!method.isAccessible()) method.setAccessible(true);
                return method;
            }
        }
        throw new NoSuchMethodError(methodName);
    }

    public Map<String, byte[]> compile(List<String> fileNameList, List<String> codeList) {
        return compile(fileNameList, codeList, new PrintWriter(System.err), null, null);
    }

    private Map<String, byte[]> compile(List<String> fileNameList,
                                        List<String> codeList,
                                        Writer err,
                                        String sourcePath,
                                        String classPath) {
        // create a new memory JavaFileManager
        MemoryJavaFileManager fileManager = new MemoryJavaFileManager(stdManager);

        // prepare the compilation unit
        List<JavaFileObject> javaFileObjects = new ArrayList<>();
        for (int i = 0; i < fileNameList.size(); i++) {
            String fileName = fileNameList.get(i);
            String code = codeList.get(i);
            JavaFileObject javaFileObject = MemoryJavaFileManager.makeStringSource(fileName, code);
            javaFileObjects.add(javaFileObject);
        }

        return compile(javaFileObjects, fileManager, err, sourcePath, classPath);
    }

    private Map<String, byte[]> compile(List<JavaFileObject> javaFileObjects,
                                        MemoryJavaFileManager fileManager,
                                        Writer err,
                                        String sourcePath,
                                        String classPath) {
        // to collect errors, warnings etc.
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // javac options
        List<String> options = new ArrayList<>();
        options.add("-Xlint:all");
        //       options.add("-g:none");
        options.add("-deprecation");
        if (sourcePath != null) {
            options.add("-sourcepath");
            options.add(sourcePath);
        }

        if (classPath != null) {
            options.add("-classpath");
            options.add(classPath);
        }

        // create a compilation task
        JavaCompiler.CompilationTask task = compiler.getTask(err, fileManager, diagnostics,
                options, null, javaFileObjects);

        if (!task.call()) {
            PrintWriter perr = new PrintWriter(err);
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                perr.println(diagnostic);
            }
            perr.flush();
            return null;
        }

        Map<String, byte[]> classBytes = fileManager.getClassBytes();
        try {
            fileManager.close();
        } catch (IOException ignored) {
        }

        return classBytes;
    }
}
