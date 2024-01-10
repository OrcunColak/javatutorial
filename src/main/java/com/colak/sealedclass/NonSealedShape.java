package com.colak.sealedclass;

import lombok.extern.slf4j.Slf4j;

/**
 * Uses non-sealed keyword to allow another class extend this class
 */
@Slf4j
public non-sealed class NonSealedShape extends Shape {

    @Override
    public void draw() {
        log.info("NonSealedShape");
    }
}
