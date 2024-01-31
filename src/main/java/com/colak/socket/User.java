package com.colak.socket;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public class User implements Serializable {
    private String name;
}
