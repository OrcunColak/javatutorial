package com.colak.gof.builder.lombok;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Product {
    private String name;
    private double price;
    private String category;
}
