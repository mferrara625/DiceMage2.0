package com.company;

import java.util.List;
import java.util.Scanner;

public class Game {

    public static boolean playDiceMage(Action gamePlay, List<Player> playerList, Display display, boolean isGameActive, Scanner scan) {
        Player activePlayer;
        boolean isTurnOver;
        for (Player player : playerList) {
            activePlayer = player;
            activePlayer.hasAttacked = false;
            if(activePlayer.health <= 0){
                System.out.println("\n" + activePlayer.name + " Was DEFEATED!!!\n");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                for (Player winner : playerList){
                    if(winner != activePlayer){
                        System.out.println("\t" + winner.name + " is the SUPERIOR WIZARD!!!");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                    }
                }
                isGameActive = false;
                break;
            }
            isTurnOver = false;
            display.displayMana(activePlayer, display);
            while (!isTurnOver) {
                display.displayActions(activePlayer);
                String input = scan.nextLine();
                if (input.equals("1"))
                    isTurnOver = true;
                else if (input.equals("2")) {
                    display.showField(playerList);
                }
                else if (input.equals("3")) {
                    gamePlay.powerUp(activePlayer);
                }
                else if (input.equals("4")) {
                    if(activePlayer.mana >= 6)
                    isTurnOver = gamePlay.summonMonster(scan, activePlayer);
                }
                else if (input.equals("5") && !activePlayer.hasAttacked && activePlayer.monsterDen.size() > 0) {
                    gamePlay.combat(playerList, activePlayer);
                }
                else if (input.equals("6") && activePlayer.health < 3) {
                    gamePlay.heal(activePlayer);
                }
                else if (input.equals("7") && activePlayer.mana > (activePlayer.luckDisplayed + 1) && activePlayer.actualLuckAmount > 3){
                    gamePlay.increaseLuck(activePlayer);
                }
            }
        }
        return isGameActive;
    }

    public static void setup(Action gamePlay, List<Player> playerList, Scanner scan) {
        while (playerList.size() < 2)
            gamePlay.addPlayer(playerList, scan);
        for (Player player : playerList) {
            for (int i = 0; i < 6; i++){
                player.manaDice.add(new Die());
            }
        }
    }
}