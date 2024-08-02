package com.colak.gof.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Light {
    public void turnOn() {
        log.info("Turning on the lights...");
    }

    public void turnOff() {
        log.info("Turning off the lights...");
    }
}
