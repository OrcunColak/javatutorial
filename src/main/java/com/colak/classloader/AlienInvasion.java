package com.colak.classloader;

public class AlienInvasion {
    public static void main(String[] args) {
        AlienInvasion alienInvasion = new AlienInvasion();
        alienInvasion.hello();
    }

    private void hello() {
        System.out.println("AlienInvasion original: " + this.getClass().getClassLoader());
        Starship starship = new Starship();
        starship.hello();
    }
}
