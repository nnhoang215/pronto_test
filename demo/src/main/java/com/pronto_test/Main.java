package com.pronto_test;

// import java.util.*;

public class Main {
  public static void main(String[] args) {
    String[] playerNames = {"Peter", "Billy", "Charlotte", "Sweedal"};
    Board board = new Board();
    Game game = new Game(playerNames, board);
  
    try {
      board.loadBoard();
    } catch (Exception e) {
      System.out.println("Error loading board: " + e);
    }

    game.play();
  }
}
