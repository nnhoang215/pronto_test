## Woven coding test

Your task is to write an application to play the game of Woven Monopoly.

In Woven Monopoly, when the dice rolls are set ahead of time, the game is deterministic.

### Game rules
* There are four players who take turns in the following order:
  * Peter
  * Billy
  * Charlotte
  * Sweedal
* Each player starts with $16
* Everybody starts on GO
* You get $1 when you pass GO (this excludes your starting move)
* If you land on a property, you must buy it
* If you land on an owned property, you must pay rent to the owner
* If the same owner owns all property of the same colour, the rent is doubled
* Once someone is bankrupt, whoever has the most money remaining is the winner
* There are no chance cards, jail or stations
* The board wraps around (i.e. you get to the last space, the next space is the first space)


### Your task
* Load in the board from board.json
* Implement game logic as per the rules
* Load in the given dice rolls files and simulate the game
  * Who would win each game?
  * How much money does everybody end up with?
  * What spaces does everybody finish on?


The specifics and implementation of this code is completely up to you!

### What we are looking for:
* We are a Ruby house, however feel free to pick the language you feel you are strongest in.
* Code that is well thought out and tested
* Clean and readable code
* Extensibility should be considered
* A git commit-history would be preferred, with small changes committed often so we can see your approach

Please include a readme with any additional information you would like to include, including instructions on how to test and execute your code.  You may wish to use it to explain any design decisions.

Despite this being a small command line app, please approach this as you would a production problem using whatever approach to coding and testing you feel appropriate.



# Notes & assumptions
* Edge case: If a player lands on a property and cannot afford the property he goes bankrupt (rule dictates that he MUST buy).
* Edge case: If after paying rent, a player has $0 left, he can still continue and only goes bankrupt when bank balance is negative.
* onArrive() method of GoTile: it is intentionally empty, because in the future when there are more types of Tile and more functionalities on player's arrival, so all Tiles should have an onArrive() method.
* Edge case: In the case when the game ends and some players tie with the highest balance, they're all winners.
* I implemented the BankruptcyListener interface so that Player can trigger bankruptcy and end the game after paying.
* I tried to orchestrate the game logic through Game, however, I still  seperated several methods to be written under their relevant classes to ensure the modularity, loose-couplingness, and readability throughout the program.

# Things I would change in my program
* Clean up some of the logging which are in place to boost user experience. By cleaning them up, I could make my code more easily readable.
* I could optimize the handlePurchase() method to assess double rent furthermore.