package com.colak.algorithms.getfirst;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFirstExecutor {

    public static <R> SupplierExecution<R> get(final Supplier<Optional<R>> supplier) {
        return new SupplierExecution<R>(Optional.empty()).or(supplier);
    }

    public record SupplierExecution<R>(Optional<R> value) {
            public SupplierExecution<R> or(final Supplier<Optional<R>> supplier) {
                return value.isEmpty() ? new SupplierExecution<>(supplier.get()) : this;
            }

            public R orElse(final R defaultValue) {
                return value.orElse(defaultValue);
            }

            public R orElseGet(final Supplier<R> defaultSupplier) {
                return value.orElseGet(defaultSupplier);
            }
        }
}
