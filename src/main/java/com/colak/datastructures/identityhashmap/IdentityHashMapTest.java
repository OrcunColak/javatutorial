package com.colak.datastructures.identityhashmap;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * See <a href="https://medium.com/@humbleCoder007/understanding-javas-identityhashmap-and-hashmap-a-deep-dive-into-object-equality-840c7d30c21c">...</a>
 */
@Slf4j
public class IdentityHashMapTest {

    public static void main(String[] args) {
        testHashMap();
        testIdentityHashMap();
    }

    private static void testIdentityHashMap() {
        IdentityHashMap<Object, Object> identityHashMap = new IdentityHashMap<>();
        Integer i1 = 10_000;
        Integer i2 = 10_000;

        // even though i1 and i2 have the same content, they reference different memory locations.
        // As a result, both key-value pairs are retained in the IdentityHashMap,
        identityHashMap.put(i1, "sachin");
        identityHashMap.put(i2, "messi");

        // Integers are pooled
        // IdentityHashMap : {10000=sachin, 10000=messi}
        log.info("IdentityHashMap : {}", identityHashMap);
    }

    private static void testHashMap() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Integer i1 = 10_000;
        Integer i2 = 10_000;

        //  i1 and i2 have the same content (both are 10), so when adding them to the HashMap, the equals() method is
        //  used to determine equality.
        //  As a result, the second key-value pair will overwrite the first one
        hashMap.put(i1, "sachin");
        hashMap.put(i2, "messi");

        // HashMap : {10=messi}
        log.info("HashMap : {}", hashMap);
    }
}
