package com.colak.reflection.typetoken;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;

/**
 * See <a href="https://medium.com/@dnumbpad/super-type-token-in-java-dd921d931080">...</a>
 * <p>
 * Super-Type-Token is implemented by creating an anonymous subclass of a parameterized type.
 * This technique lets us capture and access specific generic type information at runtime.
 */
@Slf4j
public class TypeReferenceTest {

    public static void main(String[] args) {
        // Create an anonymous subclass of TypeReference with the specific type token
        TypeReference<List<String>> typeRef = new TypeReference<>() {
        };

        // Now you can retrieve the type information at runtime
        Type mySuperType = typeRef.getType();

        // java.util.List<java.lang.String>
        log.info(mySuperType.getTypeName());
    }
}
