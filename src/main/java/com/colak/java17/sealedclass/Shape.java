package com.colak.java17.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public sealed class Shape permits Circle, Rectangle, NonSealedShape {

    public void draw() {
        log.info("Shape");
    }
}
