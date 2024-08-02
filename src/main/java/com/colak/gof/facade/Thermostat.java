package com.colak.gof.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Thermostat {
    public void setTemperature(int temperature) {
        log.info("Setting temperature to {} degrees...", temperature);
    }
}
