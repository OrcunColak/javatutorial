package com.colak.java17.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public sealed class Rectangle extends Shape permits FilledRectangle, SealedSquare, TransparentRectangle {

    @Override
    public void draw() {
        log.info("Rectangle");
    }
}
