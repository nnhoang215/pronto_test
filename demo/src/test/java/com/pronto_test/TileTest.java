package com.pronto_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {
  Player player1;
  Player player2;  
  Game newGame;

  @BeforeEach
  void setup() {
    String[] playerNames = {"Peter", "Annie"};
    Board board = new Board("demo/board.json");
    newGame = new Game(playerNames, board, "demo/rolls_1.json");
    List<Player> players = newGame.getPlayers();
    player1 = players.get(0);
    player2 = players.get(1);
  }

  @Test
  void testOnArriveAndPassGoTile() {
    GoTile tile = new GoTile("GoTile");
    
    tile.onArrive(player1, newGame.getBoard());
    GoTile.passGo(player1);

    assertEquals(player1.getBalance(), 17);
  }

  @Test
  void testOnArrivePropertyTileDifferentOwner() {
    PropertyTile tile = new PropertyTile("Maccas", 3, "Blue");
    
    tile.setOwner(player2);
    tile.onArrive(player1, newGame.getBoard());
    
    assertEquals(player1.getBalance(), 13);
    assertEquals(player2.getBalance(), 19);
  }

  @Test
  void testOnArrivePropertyTileSameOwner() {
    PropertyTile tile = new PropertyTile("Maccas", 3, "Blue");
    
    tile.setOwner(player1);
    tile.onArrive(player1, newGame.getBoard());
    
    assertEquals(player1.getBalance(), 16);
  }

  @Test
  void testOnArrivePropertyTileNoOwner() {
    PropertyTile tile = new PropertyTile("Maccas", 3, "Blue");
    
    tile.onArrive(player1, newGame.getBoard());
    
    assertEquals(tile.getOwner(), player1);
  }

  @Test
  void testGetColour() {
    PropertyTile tile = new PropertyTile("Maccas", 3, "Blue");
    assertEquals(tile.getColour(), "Blue");
  }
}
