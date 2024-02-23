package com.colak.java9.flow.withprocessor;

import java.util.concurrent.Flow;

class MyProcessor implements Flow.Processor<Integer, String> {
    private Flow.Subscriber<? super String> subscriber;
    private Flow.Subscription subscription;

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        // Request items when ready
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(Integer item) {
        // Process the item (e.g., convert to String) and send it downstream
        subscriber.onNext("Processed: " + item.toString());

        // Request the next item
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        // Handle errors
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        // Notify completion
        subscriber.onComplete();
    }
}