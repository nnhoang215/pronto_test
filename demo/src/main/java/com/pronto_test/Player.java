package com.pronto_test;

public class Player {
  private String name; // The name of the player
  private int balance; // The current balance of the player
  private int position; // The current position of the player on the board
  private BankruptcyListener listener; // Listener to handle bankruptcy events
  
  // Constructor to initialize the player with name, initial cash, position, and bankruptcy listener
  public Player(String name, int cash, int position, BankruptcyListener listener) {
    this.name = name;
    this.balance = cash;
    this.position = position;
    this.listener = listener;
  }
  
  // Method to deduct an amount from the player's balance
  public void pay(int amount) {
    balance -= amount;
    Util.log(Player.this.name + " paid " + amount);
    if (balance < 0) {
      listener.onBankruptcy(this); // Notify listener if the player goes bankrupt
    }
  }

  // Method to get the current balance of the player
  public int getBalance() {
    return balance;
  }

  // Method to add rent amount to the player's balance
  public void receiveRent(int amount) {
    Util.log(name + " received " + amount + " in rent.");
    balance += amount;
  }

  // Method to add money received from the bank to the player's balance
  public void receiveBankMoney(int amount) {
    Util.log(name + " received " + amount + " from the bank.");
    balance += amount;
  }

  // Method to get the current position of the player on the board
  public int getPosition() {
    return position;
  }

  // Method to set the current position of the player on the board
  public void setPosition(int position) {
    this.position = position;
  }

  // Method to get the name of the player
  public String getName() {
    return name;
  }
  
  // Method to return a string representation of the player
  public String toString() {
    return "Player: " + name + ", Balance: " + balance + ", Position: " + position;
  }
}
