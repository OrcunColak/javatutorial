package com.colak.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TransparentRectangle extends Rectangle {

    @Override
    public void draw() {
        log.info("TransparentRectangle");
    }
}
