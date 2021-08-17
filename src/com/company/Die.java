package com.company;

public class Die {
    public int numberOfSides;
    public int faceUpValue;

    public Die() {
        numberOfSides = 6;
    }

    public void roll() {
        faceUpValue = (int) ((Math.random() * numberOfSides) + 1);
    }

}