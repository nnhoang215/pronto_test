package com.pronto_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PlayerTest {
  Player player;
  Game newGame;

  @BeforeEach
  void setup() {
    String[] playerNames = {"Peter"};
    Board board = new Board("demo/board.json");
    newGame = new Game(playerNames, board, "demo/rolls_1.json");
    List<Player> players = newGame.getPlayers();
    player = players.get(0);
  }

  @Test
  void testGetDefaultBalance() {
    assertTrue(player.getBalance() == 16);
  }

  @Test
  void testGetDefaultName() {
    assertTrue(player.getName() == "Peter");
  }

  @Test
  void testGetDefaultPosition() {
    assertTrue(player.getPosition() == 0);
  }

  @Test
  void testPay() {
    player.pay(8);
    assertTrue(player.getBalance() == 8);
  }

  @Test
  void testPayGoBankruptGameOver() {
    player.pay(17);
    assertTrue(newGame.isGameOver());
  }

  @Test
  void testReceiveBankMoney() {
    player.receiveBankMoney(2);
    assertEquals(player.getBalance(), 18);
  }

  @Test
  void testReceiveRent() {
    player.receiveRent(2);
    assertEquals(player.getBalance(), 18);
  }

  @Test
  void testSetPosition() {
    player.setPosition(2);
    assertEquals(player.getPosition(), 2);
  }
}
