package com.colak.reflection.classfile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;

/// The new Class-File API gives you structured, efficient access to class bytecode.
public class ClassFileTest {

    static void main() throws IOException {
        String resource = ClassFileTest.class
                                  .getName()
                                  .replace('.', '/') + ".class";

        try (InputStream in =
                     ClassFileTest.class.getClassLoader().getResourceAsStream(resource)) {

            if (in == null) {
                throw new IllegalStateException("Class file not found");
            }

            ClassModel classModel = ClassFile.of().parse(in.readAllBytes());

            classModel.methods()
                    .forEach(m ->
                            System.out.println(m.methodName().stringValue())
                    );
        }
    }
}
