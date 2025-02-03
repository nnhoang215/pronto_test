package com.pronto_test;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class Game implements BankruptcyListener {
  private List<Player> players;
  private Player currentPlayer;
  private int currentPlayerIndex;
  private List<Integer> diceRolls;
  private Board board;
  private boolean isGameOver = false;
  public List<Player> richestPlayers; // for testing only
  
  @Override
  public void onBankruptcy(Player player) {
    Util.log(player.getName() + " is bankrupt!");
    endGame();
  }

  // Constructor to create a new game with a list of players
  public Game(String[] playerNames, Board board, String diceRollsPath) {
    this.board = board;
    this.players = new ArrayList<Player>();
    
    // Initialize players with the given names
    for (String n : playerNames) {
      players.add(new Player(n, 16, 0, this));
    }
    currentPlayerIndex = 0;
    currentPlayer = this.getPlayerByIndex(currentPlayerIndex);

    // Load dice rolls from the specified file
    try {
      loadDiceRolls(diceRollsPath);
    } catch (IOException e) {
      System.out.println("Error loading dice rolls: " + e);
    }
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  // Load dice rolls from a file
  private void loadDiceRolls(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String json = Files.readString(Paths.get(path));
      diceRolls = objectMapper.readValue(json, new TypeReference<List<Integer>>() {});
    } catch (IOException e) {
      throw e;
    }
  }

  // Get the current player
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  // Main game loop
  public void play() {
    while (!isGameOver) {
      int roll = getNextRoll();
      movePlayer(this.currentPlayer, roll);
      Util.log("--------------------------------------------");
      nextTurn();
    }

    showResults();
  }

  // End the game
  public void endGame() {
    this.isGameOver = true;
  }

  // Display the results of the game
  private void showResults() {
    Util.log("============================================");
    Util.log("Game Over! Results are below:");
    richestPlayers = new ArrayList<>();
    int highestBalance = Integer.MIN_VALUE;

    // Determine the richest player(s)
    for (Player player : players) {
      if (player.getBalance() > highestBalance) {
        richestPlayers.clear();
        richestPlayers.add(player);
        highestBalance = player.getBalance();
      } else if (player.getBalance() == highestBalance) {
        richestPlayers.add(player);
      }

      Util.log(player.toString());
    }
    Util.log("The winner(s): ");
    for (Player player : richestPlayers) {
      Util.log(player.getName() + " with a balance of $" + player.getBalance());
    }
  }

  // Move the player based on the dice roll
  private void movePlayer(Player player, int roll) {
    // Calculate new position
    int oldPosition = player.getPosition();
    int newPosition = (oldPosition + roll) % board.getBoardSize();
    
    player.setPosition(newPosition);
    Tile currentTile = board.getTile(newPosition);

    // Check if player passed Go (except for the first move)
    if (newPosition < oldPosition) {
      GoTile.passGo(player);
      Util.log(player.getName() + " passed GoTile.");
    }
    
    currentTile.onArrive(player, board);
  }

  // Proceed to the next player's turn
  public void nextTurn() {
    if (currentPlayerIndex == players.size() - 1) {
      currentPlayerIndex = 0;
    } else {
      currentPlayerIndex++;
    }
    currentPlayer = this.getPlayerByIndex(currentPlayerIndex);
  }

  // Get the next dice roll
  private int getNextRoll() {
    int roll = diceRolls.get(0);
    diceRolls.remove(0);
    return roll;
  }

  // Get player by index
  public Player getPlayerByIndex(int index) {
    if (index >= players.size()) {
      index = index % players.size();
    }

    return players.get(index);
  }

  public boolean isGameOver() {
    return isGameOver;
  }

  public Board getBoard() {
    return this.board;
  }
}
