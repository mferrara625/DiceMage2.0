package com.company;

public class Monster extends Die{
    public int numberOfSides;
    public int faceUpValue;

    public Monster () {
        numberOfSides = 6;
    }

    public Monster (int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public void attack() {
        faceUpValue = (int) ((Math.random() * numberOfSides) + 1);
    }

}
