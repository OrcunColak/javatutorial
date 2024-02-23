package com.colak.java9.flow.withprocessor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

@Slf4j
class MySubscriber implements Flow.Subscriber<String> {
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // Request items when ready
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        // Process the item
        log.info("Received: " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        // Handle errors
        log.error("Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        // Notify completion
        log.info("Processing completed");
    }
}

