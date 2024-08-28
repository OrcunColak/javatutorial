package com.colak.gof.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Radio implements Device {
    public void turnOn() {
        log.info("Turning on the Radio");
    }

    public void turnOff() {
        log.info("Turning off the Radio");
    }

    public void setChannel(int channel) {
        log.info("Setting Radio channel to : {}", channel);
    }
}
