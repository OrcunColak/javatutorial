package com.colak.gof.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private final String part1;
    private final String part2;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String part1 = "part1";
        private String part2 = "part2";

        public Builder withPart1(String value) {
            this.part1 = value;
            return this;
        }

        public Builder withPart2(String value) {
            this.part2 = value;
            return this;
        }

        public Product build() {
            return new Product(part1, part2);
        }
    }
}
