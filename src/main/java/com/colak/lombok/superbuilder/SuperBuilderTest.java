package com.colak.lombok.superbuilder;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

public class SuperBuilderTest {

    @SuperBuilder
    @ToString
    private static abstract class BaseResponse {
        private Boolean success;
        private String message;
    }

    // Subclass extending BaseResponse
    @SuperBuilder
    @ToString(callSuper = true)
    static class SuccessResponse extends BaseResponse {
        private String successDetail;
    }

    public static void main(String[] args) {
        // Using the builder for a subclass
        SuccessResponse successResponse = SuccessResponse.builder()
                .success(Boolean.TRUE)
                .message("Operation completed successfully")
                .successDetail("Additional success info")
                .build();

        System.out.println(successResponse);
    }
}
