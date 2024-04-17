package com.colak.streams.partitioningby;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// See https://medium.com/@ak123aryan/stream-group-by-interview-questions-ac3bc74d4953?source=explore---------19-98--------------------bc581852_01ce_4461_9d68_172de81a1069-------15
@Slf4j
class StreamPartitioningByTest {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, Integer> partitionedAndSummed = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0,
                        Collectors.summingInt(Integer::intValue)));

        log.info("Sum of even numbers: {} ", partitionedAndSummed.get(true));
        log.info("Sum of odd numbers: {}", partitionedAndSummed.get(false));
    }
}
