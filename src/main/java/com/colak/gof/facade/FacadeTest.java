package com.colak.gof.facade;

import lombok.experimental.UtilityClass;

// See https://medium.com/@nagarjun_nagesh/facade-design-pattern-in-java-592c58a4735f
@UtilityClass
class FacadeTest {

    public static void main() {
        HomeAutomationFacade homeAutomation = new HomeAutomationFacade();
        homeAutomation.arriveHome();
        homeAutomation.leaveHome();
    }
}
