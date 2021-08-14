package com.company;

import java.util.List;
import java.util.Scanner;

public class Action {


    public static void addPlayer(List<Player> playerList, Scanner scan) {
        System.out.println("Enter Player Name");
        String playerName = scan.nextLine();
        playerList.add(new Player(playerName));
    }



    public static void combat(List<Player> playerList, Player activePlayer) {
        for (Player player1 : playerList) {
            if (player1 != activePlayer) {
                if (player1.monsterDen.size() == 0) {
                    int randNum = (int) ((Math.random() * 3) + 1);
                    if (randNum == 1){
                        System.out.println(player1.name + " dodged " + activePlayer.name + "'s monsters attack!");
                        activePlayer.hasAttacked = true;
                        break;
                    } else if (randNum != 1){
                        player1.health--;
                        System.out.println(activePlayer.name + " hit " + player1.name + " directly!");
                        activePlayer.hasAttacked = true;
                        break;
                    }

                } else {
                    int strongestAttack = 0;
                    int strongestDefense = 0;
                    Player attackWinner = null;
                        for (Monster monster : activePlayer.monsterDen) {
                            monster.attack();
                            System.out.println(activePlayer.name + "'s Monster Attacked for " + monster.faceUpValue + " Strength!");
                            if (monster.faceUpValue > strongestAttack) {
                                strongestAttack = monster.faceUpValue;
                            }

                        }
                        for (Player defender : playerList){
                            if(defender != activePlayer){
                                for (Monster monster : defender.monsterDen){
                                    monster.attack();
                                    if (monster.faceUpValue > strongestDefense) {
                                        strongestDefense = monster.faceUpValue;
                                    }

                                    if(strongestDefense > strongestAttack){
                                        System.out.println(defender.name + " defended!    Defense Strength: " + strongestDefense);
                                        attackWinner = defender;
                                    } else if (strongestDefense == strongestAttack){
                                        System.out.println("Equal Strength   " + defender.name + "'s Defense Strength: " + strongestDefense);
                                        attackWinner = playerList.get((int) (Math.random() * playerList.size()));
                                    } else if (strongestDefense < strongestAttack){
                                        System.out.println(defender.name + "'s defense was too weak!    Defense Strength: " + strongestDefense);
                                        attackWinner = activePlayer;
                                    }
                                }
                            }
                        }
                    assert attackWinner != null;
                    System.out.println(attackWinner.name + " won the Battle!");
                    activePlayer.hasAttacked = true;
                    for (Player player2 : playerList) {
                        if (player2 != attackWinner) {
                            player2.monsterDen.remove(player2.monsterDen.size() - 1);
                            System.out.println(player2.name + "'s Monster was killed");
                        }
                    }
                }
            }
        }
    }

    public static boolean summonMonster(Scanner scan, Player activePlayer) {
        boolean isTurnOver = false;
        if (activePlayer.mana >= 13)
            System.out.println("Choose Monster Strength 3-10");
        if (activePlayer.mana >= 6 && activePlayer.mana < 13)
            System.out.println("Choose Monster Strength 3-" + (activePlayer.mana - 3));
        int input1 = scan.nextInt();
        scan.nextLine();
        if(input1 <= (activePlayer.mana - 3) && input1 <= 10 && input1 >= 3){
            activePlayer.monsterDen.add(new Monster(input1));
            activePlayer.mana -= (input1 + 3);
            System.out.println("Monster added to " + activePlayer.name + "'s Den\n");
            isTurnOver = true;
        }
        return isTurnOver;
    }

    public static void powerUp(Player activePlayer) {
        if (activePlayer.mana >= (4 + activePlayer.numTimesPoweredUp)) {
            activePlayer.mana -= (4 + activePlayer.numTimesPoweredUp);
            activePlayer.power++;
            activePlayer.numTimesPoweredUp++;
            activePlayer.manaDice.add(new Die());
            System.out.println(activePlayer.name + " Powered Up!");
        }
    }
}