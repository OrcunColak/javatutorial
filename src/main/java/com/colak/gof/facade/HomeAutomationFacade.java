package com.colak.gof.facade;

class HomeAutomationFacade {
    private final Light light;
    private final Thermostat thermostat;

    public HomeAutomationFacade() {
        this.light = new Light();
        this.thermostat = new Thermostat();
    }

    public void leaveHome() {
        light.turnOff();
        thermostat.setTemperature(18);
    }

    public void arriveHome() {
        light.turnOn();
        thermostat.setTemperature(22);
    }
}
