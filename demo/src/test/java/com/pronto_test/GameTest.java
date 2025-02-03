package com.pronto_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
  Player player;
  Game newGame;
  Game newGame2;
  Board board;

  @BeforeEach
  void setup() {
    String[] playerNames = {"Peter", "Billy", "Charlotte", "Sweedal"};
    board = new Board("demo/board.json");
    Board board2 = new Board("demo/board.json");
    newGame = new Game(playerNames, board, "demo/rolls_1.json");
    newGame2 = new Game(playerNames, board2, "demo/rolls_2.json");
  }

  @Test
  void testEndGame() {
    newGame.endGame();
    assertTrue(newGame.isGameOver());
  }

  @Test
  void testGetCurrentPlayer() {
    // The first player loaded
    Player firstPlayer = newGame.getPlayerByIndex(0);

    assertEquals(firstPlayer, newGame.getCurrentPlayer());
  }

  @Test
  void testNextTurnWrap() {
    Player oldPlayer = newGame.getCurrentPlayer();

    for (int i = 0; i < newGame.getPlayers().size(); i++) {
      newGame.nextTurn();
    }
    Player newPlayer = newGame.getCurrentPlayer();

    assertEquals(oldPlayer, newPlayer);
  }

  @Test
  void testPlay() {
    newGame.play();
    assertEquals(newGame.richestPlayers.get(0).getName(), "Peter");
    newGame2.play();
    assertEquals(newGame2.richestPlayers.get(0).getName(), "Charlotte");
  }
}
