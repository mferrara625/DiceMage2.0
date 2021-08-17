package com.company;

import java.util.Arrays;
import java.util.List;

public class Display {


    public String orderedDisplay(Player activePlayer, Integer[] dieHolder){
        String output = "";

            for (int i = 0; i < activePlayer.manaDice.size(); i++) {
                dieHolder[i] = activePlayer.manaDice.get(i).faceUpValue;
            }
                Arrays.sort(dieHolder);
                for (Integer num : dieHolder)
                    output += num + " ";
                return output;
            }

    public static void displayMana(Player activePlayer, Display display) {
        Integer[] dieHolder;
        System.out.println("Mana is in the Air...");
        for (Die die : activePlayer.manaDice) {
            die.roll();
        }
        dieHolder = new Integer[activePlayer.manaDice.size()];
        for (int i = 0; i < activePlayer.manaDice.size(); i++){
            dieHolder[i] = activePlayer.manaDice.get(i).faceUpValue;
        }
        Arrays.sort(dieHolder);
        System.out.println(display.orderedDisplay(activePlayer, dieHolder));
        display.calcMana(activePlayer, dieHolder);

    }

    public void calcMana(Player activePlayer, Integer[] dieHolder) {

        int manaPulled = 1;
        for (int i = 0; i < dieHolder.length - 2; i++) {
            if (dieHolder[i] == dieHolder[i + 1] && dieHolder[i] == dieHolder[i + 2]) {
                manaPulled++;
            }
        }
        activePlayer.mana += manaPulled;
        System.out.println(activePlayer.name + " pulled " + manaPulled + " mana");
    }

    public static void showField(List<Player> playerList) {
        System.out.println("\n##########################################");
        for (Player player1 : playerList)
            System.out.println(player1);
    }

    public static void displayActions(Player activePlayer) {
        System.out.println("\n" + activePlayer.name + "'s Turn");
        System.out.println(activePlayer);
        System.out.println("-----------------------------");
        System.out.println("What would you like to do?");
        System.out.println("1. End Turn");
        System.out.println("2. View Field");
        if (activePlayer.mana >= (4 + activePlayer.numTimesPoweredUp))
            System.out.println("3. Power Up  (cost " + (4 + activePlayer.numTimesPoweredUp) + " mana)");
        if (activePlayer.mana >= 6)
            System.out.println("4. Summon Monster (and end turn)");
        if(activePlayer.monsterDen.size() > 0 && !activePlayer.hasAttacked)
            System.out.println("5. Attack!");
        if(activePlayer.health < 3 && activePlayer.manaDice.size() > 0)
            System.out.println("6. Heal (cost 1 mana die)");
        if(activePlayer.mana > (activePlayer.luckDisplayed + 1) && activePlayer.luckDisplayed < 7)
            System.out.println("7. Increase Luck (cost " + (activePlayer.luckDisplayed + 1) + " mana)" );
    }

}
