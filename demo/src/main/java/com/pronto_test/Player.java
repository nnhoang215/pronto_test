package com.pronto_test;

public class Player {
  String name;
  int balance;
  private int position = 0;
  private BankruptcyListener listener;
  
  public Player(String name, int cash, int position, BankruptcyListener listener) {
    this.name = name;
    this.balance = cash;
    this.position = position;
    this.listener = listener;
  }
  public void pay(int amount) {
    //TODO: validate amount
    balance -= amount;
    System.out.println(Player.this.name + " paid " + amount + " in rent.");
    if (balance < 0) {
      listener.onBankruptcy(this);
    }
  }

  public int getBalance() {
    return balance;
  }

  public void receiveMoney(int amount) {
    Util.log(name + " received " + amount + " in rent.");
    balance += amount;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }
  
  public String toString() {
    return "Player: " + name + ", Balance: " + balance + ", Position: " + position;
  }
}
