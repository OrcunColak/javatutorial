package com.colak.gof.mediator;

// See https://medium.com/@nagarjun_nagesh/mediator-design-pattern-in-java-21-9f953cba0ded
public class TrafficControlDemo {

    public static void main(String[] args) {
        TrafficMediator mediator = new TrafficMediator();

        TrafficLight northSouth = new TrafficLight(mediator, "North-South");
        TrafficLight eastWest = new TrafficLight(mediator, "East-West");

        mediator.setNorthSouth(northSouth);
        mediator.setEastWest(eastWest);

        northSouth.setGreen();
        eastWest.setGreen();
    }
}
