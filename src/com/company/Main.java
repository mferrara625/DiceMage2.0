package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Action gamePlay = new Action();
        Game game = new Game();
        List<Player> playerList = new ArrayList<>();
        Player activePlayer;
        Integer[] dieHolder = new Integer[0];
        Display display = new Display();
        boolean isTurnOver = false;
        boolean isGameActive = true;
        Scanner scan = new Scanner(System.in);
        game.setup(gamePlay, playerList, scan);
        while (isGameActive) {
            isGameActive = game.playDiceMage(gamePlay, playerList, display, isGameActive, scan);
        }
    }
}