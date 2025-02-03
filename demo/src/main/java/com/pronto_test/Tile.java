package com.pronto_test;
import java.util.ArrayList;
import java.util.List;
abstract class Tile {
  String name;

  abstract void onArrive(Player player, Board board);
}

class GoTile extends Tile {
  
  public String toString() {
    return "GoTile: " + name;
  }
  
  GoTile(String name) {
    this.name = name;
  }

  public void onArrive(Player player, Board board) {
    // NOTE: for later expansion
  }

  public static void passGo(Player player) {
    player.receiveMoney(1);
  }
}

class PropertyTile extends Tile {
  private boolean isDoubleRent = false;
  private int rent;
  private String colour;
  Player owner;

  public String toString() {
    return "PropertyName: " + name + ", Rent: " + rent + ", Colour: " + colour;
  }

  public String getColour() {
    return colour;
  }

  PropertyTile(String name, int rent, String colour) {
    this.name = name;
    this.rent = rent;
    this.colour = colour;
  }

  public void onArrive(Player player, Board board) {
    if (player != owner) {
      // TODO: implement comparable to compare players
      if (owner != null) {
        takeRent(player);
      } else {
        handlePurchase(player, board);
      }
    }
  }

  private void handlePurchase(Player player, Board board) {
    player.pay(this.rent);
    owner = player;
    // TODO: optimize this | consider splitting method
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

    if (haveSameOwner) {
      for (PropertyTile tile : sameColourTiles) {
        tile.isDoubleRent = true;
      }
    }
  }

  private void takeRent(Player player) {
    // NOTE: if the player cannot afford the rent, he pays his remaining bank balance only
    int amount = isDoubleRent ? rent * 2 : rent;
      int currentBalance = player.getBalance();
      player.pay(amount);
      owner.receiveMoney(Math.min(amount, currentBalance));
  }
}