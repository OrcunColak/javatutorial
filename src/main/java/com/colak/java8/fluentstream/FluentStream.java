package com.colak.java8.fluentstream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class FluentStream<T> {
    private final List<T> elements;

    private FluentStream(List<T> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public static <T> FluentStream<T> from(List<T> elements) {
        return new FluentStream<>(elements);
    }

    public FluentStream<T> filter(Predicate<T> predicate) {
        elements.removeIf(predicate.negate());
        return this;
    }

    public <R> FluentStream<R> map(Function<T, R> mapper) {
        List<R> mappedElements = new ArrayList<>();
        for (T element : elements) {
            mappedElements.add(mapper.apply(element));
        }
        return new FluentStream<>(mappedElements);
    }

    public void forEach(Consumer<T> action) {
        elements.forEach(action);
    }
}

