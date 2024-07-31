package com.colak.gof.mediator;

import lombok.Setter;

@Setter
public class TrafficMediator {

    private TrafficLight northSouth;
    private TrafficLight eastWest;

    public void notify(TrafficLight sender, String event) {
        if (sender == northSouth && event.equals("GREEN")) {
            eastWest.setRed();
        } else if (sender == eastWest && event.equals("GREEN")) {
            northSouth.setRed();
        }
    }
}
