package com.colak.algorithms.lrucache;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

// See https://medium.com/justeattakeaway-tech/building-an-lru-cache-in-java-a-simple-approach-using-linkedhashmap-7e984b223cdf
@UtilityClass
@Slf4j
class LRUCacheTest {

    private static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > this.capacity;
        }
    }

    public static void main() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);

        // LRU entry is at the beginning
        cache.put(1, 1);
        cache.put(2, 2);
        log.info(cache.toString()); // output {1=1, 2=2}
        cache.get(1);    // accessing 1 moves it to last position
        log.info(cache.toString()); // output {2=2, 1=1}

        cache.put(3, 3); // Remove 2
        log.info(cache.toString()); // output {1=1, 3=3}
        cache.get(2);    // return null

        cache.put(4, 4); // Remove 1
        log.info(cache.toString()); // output {3=3, 4=4}
        cache.get(3);    // accessing 3 moves it to last position

        log.info(cache.toString()); // output {4=4, 3=3}
        cache.get(4);    // accessing 4 moves it to last position
        log.info(cache.toString()); // output {3=3, 4=4}
    }
}
