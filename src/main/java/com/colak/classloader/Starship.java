package com.colak.classloader;

public class Starship {

    public void hello() {
        System.out.println("Starship original: " + this.getClass().getClassLoader());
    }
}
