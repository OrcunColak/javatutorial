package com.colak.reflection.typetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {
    private final Type type;

    protected TypeReference() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        // capture generic type information
        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

}
