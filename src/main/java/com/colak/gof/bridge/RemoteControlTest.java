package com.colak.gof.bridge;

import lombok.experimental.UtilityClass;

@UtilityClass
class RemoteControlTest {

    public static void main() {
        // remote control interface that can work with multiple types of devices
        Device tv = new TV();
        BasicRemoteControl remoteControl = new BasicRemoteControl(tv);
        remoteControl.turnOn();
        remoteControl.setChannel(101);
        remoteControl.turnOff();

        Device radio = new Radio();
        BasicRemoteControl radioRemote = new BasicRemoteControl(radio);
        radioRemote.turnOn();
        radioRemote.setChannel(202);
        radioRemote.turnOff();
    }
}
