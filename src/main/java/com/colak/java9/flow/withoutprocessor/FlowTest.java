package com.colak.java9.flow.withoutprocessor;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * See https://medium.com/@vikas.taank_40391/java-answer-to-reactive-design-pattern-05a31433bf11
 */
public class FlowTest {

    public static void main(String[] args) throws InterruptedException {
        // Create a publisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // Create a subscriber
        MySubscriber subscriber = new MySubscriber();

        // Subscribe the subscriber to the processor
        publisher.subscribe(subscriber);

        // Publish some items
        publisher.submit(1);
        publisher.submit(2);
        publisher.submit(3);

        // Close the publisher
        publisher.close();

        // Add delay to ensure processing completes
        TimeUnit.SECONDS.sleep(2);
    }
}
