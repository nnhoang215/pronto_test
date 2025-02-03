package com.pronto_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
  Player player;
  Game newGame;
  Board board;

  @BeforeEach
  void setup() {
    String[] playerNames = {"Peter"};
    board = new Board("demo/board.json");
    newGame = new Game(playerNames, board, "demo/rolls_1.json");
    List<Player> players = newGame.getPlayers();
    player = players.get(0);
  }

  @Test
  void testGetBoardSize() {

    Util.log("size: " + board.getBoardSize());
    int size = board.getBoardSize();
    assertEquals(size, 9);
  }

  @Test
  void testGetTile() {
    PropertyTile tile = new PropertyTile("Maccas", 3, "Blue");
    board.addTile(tile);

    assertEquals(board.getTile(9), tile);
  }
}
