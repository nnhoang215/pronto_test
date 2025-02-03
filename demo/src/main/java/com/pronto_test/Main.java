package com.pronto_test;

public class Main {
  public static void main(String[] args) {
    String[] playerNames = {"Peter", "Billy", "Charlotte", "Sweedal"};
    Board board = new Board( "demo/board.json");
    Game game = new Game(playerNames, board, "demo/rolls_2.json");

    game.play();
  }
}
