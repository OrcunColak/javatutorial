package com.colak.concurrent.weakhashmap;

import lombok.extern.slf4j.Slf4j;

import java.util.WeakHashMap;

/**
 * See <a href="https://medium.com/@bubu.tripathy/clean-code-avoid-leaking-references-4bdce8d6c18b">...</a>
 */
@Slf4j
class WeakHashMapTest {
    
    record AdditionalData(String data) {
    }
    
    public static void main(String[] args) {
        // Creating a WeakHashMap with weak keys
        WeakHashMap<Object, AdditionalData> weakHashMap = new WeakHashMap<>();

        // Creating an object with associated data
        Object keyObject = new Object();
        AdditionalData additionalData = new AdditionalData("Some additional data");

        // Adding the object and associated data to the WeakHashMap
        weakHashMap.put(keyObject, additionalData);

        // Retrieving the associated data
        AdditionalData retrievedData = weakHashMap.get(keyObject);

        // Outputting the associated data (it should be available)
        log.info("Associated Data: " + retrievedData.data());

        // Allowing the keyObject to be eligible for garbage collection
        keyObject = null;

        // Simulating a garbage collection cycle
        System.gc();

        // Attempting to retrieve the associated data after keyObject is collected (it should be null)
        retrievedData = weakHashMap.get(keyObject);
        log.info("Associated Data after keyObject is collected: " + retrievedData);
    }
}
