package com.colak.gof.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TV implements Device {
    public void turnOn() {
        log.info("Turning on the TV");
    }

    public void turnOff() {
        log.info("Turning off the TV");
    }

    public void setChannel(int channel) {
        log.info("Setting TV channel to : {}", channel);
    }
}
