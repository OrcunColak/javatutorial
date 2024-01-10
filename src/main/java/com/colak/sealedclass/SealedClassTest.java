package com.colak.sealedclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SealedClassTest {

    public static void main(String[] args) {
        Shape shape = new Shape();
        shape.draw();

        Shape circle = new Circle();
        circle.draw();

        Shape filledRectangle = new FilledRectangle();
        filledRectangle.draw();

        Shape nonSealedShape1 = new NonSealedShape1();
        nonSealedShape1.draw();
    }

    static class NonSealedShape1 extends NonSealedShape {

        @Override
        public void draw() {
            log.info("NonSealedShape1");
        }
    }
}
