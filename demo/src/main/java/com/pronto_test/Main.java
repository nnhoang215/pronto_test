package com.pronto_test;

// import java.util.*;

public class Main {
  public static void main(String[] args) {
    String[] playerNames = {"Peter", "Billy", "Charlotte", "Sweedal"};
    Board board = new Board( "demo/board.json");
    Game game = new Game(playerNames, board, "demo/rolls_1.json");
  
    try {
      board.loadBoard();
    } catch (Exception e) {
      System.out.println("Error loading board: " + e);
    }

    game.play();
  }
}
