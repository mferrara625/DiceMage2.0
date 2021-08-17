package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int health = 5;
    int mana = 10;
    int power = 6;
    int luckDisplayed = 0;
    int actualLuckAmount = 10;
    int numTimesPoweredUp = 0;
    boolean hasAttacked;
    List<Die> manaDice = new ArrayList<>();
    List<Monster> monsterDen = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String toString(){
        return "Wizard " + name + "\tHealth: " + health + " Mana: " + mana + " Power: " + power + " Luck: " + luckDisplayed +
                "\nMonsters: " + monsterDen.size() + "\n##########################################";
    }
}
