Project Name: Woven Monopoly
Author: Austin Nguyen

Solution Design

Entities: 
* Player
  - boolean isStartingMove = true
  - String name
  - int balance
  - int position
  - List<Tile> properties

  - getName()
  - setName()

  - getBalance()
  - setBalance()
  
  - getPosition()
  - setPosition()

  - buyProperty()
  - move(int steps)
* Game
  - LinkedList<Player> players
  - play()
  - checkWinner()
  - loadDiceRoll()
  - endGame()
* Board
  - List<Tile> tileList 
  - loadBoard()

* Tile
  - String name
  * GoTile
  * PropertyTile
    - int position
    - int price
    - String color
    - Player owner
    - boolean takeRent()
    - Player getOwner()
    - boolean isAvailable()
    - boolean setOwner()