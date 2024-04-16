package com.colak.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
class NewSetFromMapTest {

    public static void main(String[] args) {
        // Create a ConcurrentHashMap
        Map<String, Boolean> map = new ConcurrentHashMap<>();

        // Create a set backed by the map
        Set<String> set = Collections.newSetFromMap(map);

        // Add elements to the set
        set.add("element1");
        set.add("element2");

        // Changes to the set are reflected in the map
        log.info("map : {}", map); // Output: {element1=true, element2=true}

        // Changes to the map are reflected in the set
        map.put("element3", true);
        log.info("set : {}", set); // Output: [element1, element2, element3]
    }
}
