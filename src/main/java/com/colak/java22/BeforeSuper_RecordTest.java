package com.colak.java22;

import java.util.Objects;

/**
 * See <a href="https://medium.com/@benweidig/looking-at-java-22-statements-before-super-298bc9ee4d89">...</a>
 */
public class BeforeSuper_RecordTest {

    public static void main(String[] args) {
        new Email(null);
    }
    public record Email(String local, String domain) {

        public Email(String fqda) {
            Objects.requireNonNull(fqda);
            var parts = fqda.split("@");
            if (parts.length != 2) {
                throw new IllegalArgumentException("...");
            }
            this(parts[0], parts[1]);
        }
    }
}
