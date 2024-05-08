package com.colak.convariant.array;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CovariantAssignmentTest {

    public static void main() {
        Integer[] integers = {100, 200};
        // Treat Integer[] as Number[]
        Number[] nums = integers;
        double sum = sum(nums);
        log.info("Integer sum : {}", sum);

        Float[] floats = {3.2F, 5.2F};
        // Treat Float[] as Number[]
        nums = floats;
        double sum2 = sum(nums);
        log.info("Float sum : {}", sum2);
    }

    private static double sum(Number[] nums) {
        double result = 0;
        for (Number num : nums) {
            result += num.doubleValue();
        }
        return result;
    }
}
