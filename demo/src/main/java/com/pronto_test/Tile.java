package com.pronto_test;

import java.util.ArrayList;
import java.util.List;

// Abstract class representing a Tile on the board
abstract class Tile {
  String name;

  // Abstract method to be implemented by subclasses, defining behavior when a player arrives on the tile
  abstract void onArrive(Player player, Board board);
}

// Class representing the "Go" tile
class GoTile extends Tile {
  
  // Constructor to initialize the GoTile with a name
  GoTile(String name) {
    this.name = name;
  }

  // Override toString method to provide a string representation of the GoTile
  public String toString() {
    return "GoTile: " + name;
  }

  // Method called when a player arrives on the GoTile
  public void onArrive(Player player, Board board) {
    Util.log("Player " + player.getName() + " has landed on " + name);
  }

  // Static method to handle passing the Go tile
  public static void passGo(Player player) {
    player.receiveBankMoney(1);
  }
}

// Class representing a property tile
class PropertyTile extends Tile {
  private boolean isDoubleRent = false;
  private int rent;
  private String colour;
  Player owner;

  // Constructor to initialize the PropertyTile with a name, rent, and colour
  PropertyTile(String name, int rent, String colour) {
    this.name = name;
    this.rent = rent;
    this.colour = colour;
  }

  // Override toString method to provide a string representation of the PropertyTile
  public String toString() {
    return "PropertyName: " + name + ", Rent: " + rent + ", Colour: " + colour;
  }

  // Getter method for the colour of the property
  public String getColour() {
    return colour;
  }

  // Method called when a player arrives on the PropertyTile
  public void onArrive(Player player, Board board) {
    Util.log("Player " + player.getName() + " has landed on " + name + (owner == null ? " (Bank's)" : " (" + owner.getName() + "'s)"));
    if (player != owner) {
      // If the property has an owner, the player pays rent
      if (owner != null) {
        takeRent(player);
      } else {
        // If the property is not owned, the player must purchase it
        handlePurchase(player, board);
      }
    }
  }

  // Method to handle the purchase of the property by a player
  private void handlePurchase(Player player, Board board) {
    // EDGE CASE: if the player cannot pay in full for the property, he goes bankrupt
    Util.log("Player " + player.getName() + " is buying " + name);
    player.pay(this.rent);
    owner = player;

    // Check if all properties of the same colour have the same owner
    List<PropertyTile> sameColourTiles = new ArrayList<PropertyTile>();
    for (Tile tile : board.getTiles()) {
      if (tile instanceof PropertyTile) {
        PropertyTile pTile = (PropertyTile) tile;
        if (pTile.getColour().equals(this.colour)) {
          sameColourTiles.add(pTile);
        }
      }
    }
    
    boolean haveSameOwner = true;
    for (PropertyTile tile : sameColourTiles) {
      haveSameOwner = haveSameOwner && (tile.owner == this.owner);      
    }

    // If all properties of the same colour have the same owner, set double rent
    if (haveSameOwner) {
      for (PropertyTile tile : sameColourTiles) {
        tile.isDoubleRent = true;
      }
    }
  }

  // Method to handle the payment of rent by a player
  private void takeRent(Player player) {
    // NOTE: if the player cannot afford the rent, he pays his remaining bank balance only
    int amount = isDoubleRent ? rent * 2 : rent;
    int currentBalance = player.getBalance();
    player.pay(amount);
    owner.receiveRent(Math.min(amount, currentBalance));
  }
}