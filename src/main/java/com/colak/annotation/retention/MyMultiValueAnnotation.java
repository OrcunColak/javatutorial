package com.colak.annotation.retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be accessed by reflection
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyMultiValueAnnotation {
    int getValue() default 2;

    String getMessage() default "hello";

    double getDoubleValue() default 5.5;
}
