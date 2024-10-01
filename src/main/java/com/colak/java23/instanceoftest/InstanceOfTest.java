package com.colak.java23.instanceoftest;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
class InstanceOfTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        if (stringMap(map)) {
            log.info("Object is Map");
        }
    }

    private static boolean stringMap(Object object) {
        boolean result = false;
        if (object instanceof Map<?, ?> stringMap) {
            result = true;
        } else if (object instanceof Integer integer) {
            result = true;
        }
        return result;
    }
}
