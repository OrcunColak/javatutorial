package com.colak.java21.structuredtaskscope;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

import static java.lang.StringTemplate.STR;

/**
 * See <a href="https://medium.com/@phoenixrising_93140/what-is-structured-concurrency-java-21-6134374696be">...</a>
 */
@Slf4j
public class StructuredTaskScopeTest {

    public static void main(String[] args) throws Exception {
        StructuredTaskScopeTest structuredTaskScopeTest = new StructuredTaskScopeTest();
        log.info(structuredTaskScopeTest.returnAny());
        log.info(structuredTaskScopeTest.failFast());
    }

    private String failFast() throws InterruptedException, ExecutionException {
        // ShutdownOnFailure policy means that if any of the subtasks fail within the scope ,
        // then all the subtasks are cancelled and control returns to the parent.
        // You can think of it like a short circuit operation policy.
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<String> respA = scope.fork(this::taskThatErrorsOut);
            StructuredTaskScope.Subtask<String> respB = scope.fork(this::justAnotherLongRunningTask);
            scope.join().throwIfFailed();
            return STR."\{ respA.get() } \{ respB.get() }" ;
        }
    }

    private String returnAny() throws InterruptedException, ExecutionException {
        // ShutdownOnSuccess policy is the counterbalance to the ShutdownOnFailure policy, meaning that if any of the
        // subtasks succeed then all subtasks are cancelled and control returns to the parent.
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
            // Even though the taskThatErrorsOut is still failing
            // the parent task is satisfied that any of the subtasks has succeeded, and it processes that result.
            scope.fork(this::taskThatErrorsOut);
            scope.fork(this::justAnotherLongRunningTask);
            return STR."\{ scope.join().result() }" ;
        }
    }

    private String taskThatErrorsOut() throws InterruptedException {
        Thread.sleep(5000);
        throw new RuntimeException("task fails and we do not know why!");
    }


    private String justAnotherLongRunningTask() throws InterruptedException {
        int i = 0;
        while (i < 10) {
            Thread.sleep(2000);
            System.out.println("Doing fancy stuff  ...... ");
            i++;
        }
        System.out.println("Where is mama?");
        return "Task completed successfully , I win";
    }
}
