## Woven coding test

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

# How to run
* Requirement: Java17
* clone the project from "https://github.com/nnhoang215/pronto_test.git"
* change directory to '/demo/target'
* run either "rolls_1.jar" or "rolls_2.jar" with command "java -jar rolls_1.jar" or "java -jar rolls_2.jar"
* if you want to run with a different rolls.json, you can change /demo/target/demo/rolls_1.json content and then run 'rolls_1.json'
