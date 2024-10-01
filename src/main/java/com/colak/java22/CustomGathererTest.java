package com.colak.java22;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;


// See https://levelup.gitconnected.com/java-22-explaining-new-features-with-examples-7fb2571188c8"
@Slf4j
class CustomGathererTest {

    record LargestInt(int limit) implements Gatherer<Integer, List<Integer>, Integer> {
        @Override
        public Supplier<List<Integer>> initializer() {
            return () -> new ArrayList<>(1);
        }

        @Override
        public Integrator<List<Integer>, Integer, Integer> integrator() {
            return Integrator.of((max, element, downstream) -> {
                if (max.isEmpty()) max.addFirst(element);
                else if (element > max.getFirst()) max.set(0, element);
                if (element >= limit) {
                    downstream.push(element);
                    return false;
                }
                return true;
            });
        }

        @Override
        public BinaryOperator<List<Integer>> combiner() {
            return (leftMax, rightMax) -> {
                if (leftMax.isEmpty()) return rightMax;
                if (rightMax.isEmpty()) return leftMax;
                int leftVal = leftMax.getFirst();
                int rightVal = rightMax.getFirst();
                return (leftVal > rightVal) ? leftMax : rightMax;
            };
        }

        @Override
        public BiConsumer<List<Integer>, Downstream<? super Integer>> finisher() {
            return (max, downstream) -> {
                if (!max.isEmpty()) downstream.push(max.getFirst());
            };
        }
    }

    public static void main() {
        fold();

    }

    // fold is a stateful many-to-one gatherer which constructs an aggregate incrementally and emits that aggregate when no more input elements exist.
    private static void fold() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> list = numbers.stream()
                .gather(new LargestInt(10))
                .toList();
        // 5
        log.info("List : {}", list);
    }

}
