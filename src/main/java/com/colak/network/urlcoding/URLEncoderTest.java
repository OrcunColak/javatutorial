package com.colak.network.urlcoding;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * See <a href="https://medium.com/@snyksec/secure-java-url-encoding-and-decoding-1e8b3eac669b">...</a>
 */
@Slf4j
public class URLEncoderTest {

    public static void main(String[] args) {
        String pathParameters = "Hellö Wörld@Java";
        String encodedParameters = encodeValue(pathParameters);

        String url = "https://example.com/search?q=" + encodedParameters;
        // https://example.com/search?q=Hell%C3%B6+W%C3%B6rld%40Java
        log.info("Encoded url : {}", url);

        String string = decodeValue(url);
        log.info("Decoded url : {}", string);

        URI uri = forwardRequestToAnotherService("my-key", "my-value");
        log.info("URI is : {}", uri);
    }

    private static String encodeValue(String value) {
        // There are three overloads of the encode() method
        // encode(String s, String enc) : allows you to explicitly set the encoding scheme, but it throws UnsupportedEncodingException
        // encode(String s, Charset charset)  available since Java 10 and is the best overload so far
        // encode(String s) is the oldest overload and is marked as deprecated in OpenJDK 17
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public static String decodeValue(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private static URI forwardRequestToAnotherService(String key, String user) {
        String newUrl = "https://my.internal.api.com/info?key=" +
                        URLEncoder.encode(key, StandardCharsets.UTF_8) +
                        "&user=" +
                        URLEncoder.encode(user, StandardCharsets.UTF_8);
        return URI.create(newUrl);
    }
}
