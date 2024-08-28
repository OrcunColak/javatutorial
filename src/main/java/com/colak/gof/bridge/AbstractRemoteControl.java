package com.colak.gof.bridge;

abstract class AbstractRemoteControl {

    protected Device device;

    protected AbstractRemoteControl(Device device) {
        this.device = device;
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void setChannel(int channel);
}
