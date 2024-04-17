package com.colak.streams.groupby;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// See https://medium.com/@ak123aryan/stream-group-by-interview-questions-ac3bc74d4953?source=explore---------19-98--------------------bc581852_01ce_4461_9d68_172de81a1069-------15

@Slf4j
class StreamGroupByTest {

    record Employee(String name, String department, String gender, double salary) {
    }

    private final List<Employee> employees = List.of(
            new Employee("John Doe", "dept1", "M", 1),
            new Employee("John Wick", "dept1", "M", 100),
            new Employee("Jane Smith", "dept2", "F", 2)
    );

    public static void main(String[] args) {
        StreamGroupByTest test = new StreamGroupByTest();
        test.groupByAttribute();
        test.countByAttribute();
        test.sumByAttribute();
        test.reduceByAttribute();
        test.multiLevelGrouping();
    }

    private void groupByAttribute() {
        Map<String, List<Employee>> employeesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::department));
        log.info("groupByAttribute : {}", employeesByDepartment);
    }

    private void countByAttribute() {
        Map<String, Long> countByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                Collectors.counting()));
        log.info("countByAttribute : {}", countByDepartment);
    }

    private void sumByAttribute() {
        Map<String, Double> salarySumByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                Collectors.summingDouble(Employee::salary)));
        log.info("sumByAttribute : {}", salarySumByDepartment);
    }

    private void reduceByAttribute() {
        Map<String, String> namesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                Collectors.reducing("", Employee::name, (s1, s2) -> STR."\{s1}, \{s2}")));
        log.info("reduceByAttribute : {}", namesByDepartment);
    }

    private void multiLevelGrouping() {
        // First group by department then for each department group by gender
        Map<String, Map<String, List<Employee>>> byDeptThenGender =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                Collectors.groupingBy(Employee::gender)));
        log.info("multiLevelGrouping : {}", byDeptThenGender);
    }
}
