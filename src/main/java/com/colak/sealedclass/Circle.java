package com.colak.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Circle extends Shape {

    @Override
    public void draw() {
        log.info("Circle");
    }
}
