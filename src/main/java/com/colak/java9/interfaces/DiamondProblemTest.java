package com.colak.java9.interfaces;

import lombok.extern.slf4j.Slf4j;

// See https://medium.com/@salvipriya97/multiple-inheritance-and-diamond-problem-java-fab82367cf05
@Slf4j
class DiamondProblemTest {

    interface InterfaceA {
        default void display() {
            log.info("InterfaceA display");
        }
    }

    interface InterfaceB extends InterfaceA {
        @Override
        default void display() {
            log.info("InterfaceB display");
        }
    }

    interface InterfaceC extends InterfaceA {
        @Override
        default void display() {
            log.info("InterfaceC display");
        }
    }

    // Class inheriting from InterfaceB and InterfaceC
    class MyClass implements InterfaceB, InterfaceC {

        // When we try to call the display() method on an instance of MyClass, it leads to ambiguity because both InterfaceB and InterfaceC
        // declare their own implementations of display().
        // This results in a compilation error.
        // Override the default method: to provide a specific implementation, thereby resolving the ambiguity.
        @Override
        public void display() {
            InterfaceB.super.display();
        }
    }


    public void main() {
        MyClass obj = new MyClass();
        obj.display();


    }

}
