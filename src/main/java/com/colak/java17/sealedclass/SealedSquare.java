package com.colak.java17.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SealedSquare extends Rectangle {
    @Override
    public void draw() {
        log.info("SealedSquare");
    }
}
