package com.colak.gof.mediator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrafficLight {

    protected final TrafficMediator mediator;
    private final String direction;

    public TrafficLight(TrafficMediator mediator, String direction) {
        this.mediator = mediator;
        this.direction = direction;
    }

    public void setGreen() {
        log.info("Traffic Light {} : GREEN", direction);
        mediator.notify(this, "GREEN");
    }

    public void setRed() {
        log.info("Traffic Light {} : RED", direction);
    }
}
