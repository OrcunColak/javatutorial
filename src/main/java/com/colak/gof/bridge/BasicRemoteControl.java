package com.colak.gof.bridge;

class BasicRemoteControl extends AbstractRemoteControl {

    public BasicRemoteControl(Device device) {
        super(device);
    }

    public void turnOn() {
        device.turnOn();
    }

    public void turnOff() {
        device.turnOff();
    }

    public void setChannel(int channel) {
        device.setChannel(channel);
    }
}
