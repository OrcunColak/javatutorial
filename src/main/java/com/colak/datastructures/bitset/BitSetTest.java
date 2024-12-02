package com.colak.datastructures.bitset;

import java.util.BitSet;

class BitSetTest {

    public static void main() {
        // Create a BitSet of size 10
        BitSet bitSet = new BitSet(10);

        // Set bits at specific positions
        bitSet.set(0);
        bitSet.set(3);
        bitSet.set(7);

        // Check if specific bits are set
        System.out.println("Bit at position 0: " + bitSet.get(0)); // true
        System.out.println("Bit at position 1: " + bitSet.get(1)); // false

        // Print the BitSet
        System.out.println("BitSet: " + bitSet); // {0, 3, 7}

        // Clear a bit
        bitSet.clear(3);
        System.out.println("After clearing position 3: " + bitSet); // {0, 7}

        // Flip a bit
        bitSet.flip(7);
        System.out.println("After flipping position 7: " + bitSet); // {0}

        // Get the size of the BitSet
        System.out.println("Size of BitSet: " + bitSet.size()); // 64

        // Number of set bits
        System.out.println("Cardinality (number of true bits): " + bitSet.cardinality()); // 1
    }
}
