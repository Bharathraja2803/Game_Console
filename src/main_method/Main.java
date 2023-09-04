package main_method;

import console.GameConsole;
import console.ShooterGame;
import pirate_game.PirateGame;

public class Main {
	public static void main(String [] args) {
//		var console = new GameConsole<>(new ShooterGame("The Shooting Game"));
//		
//		int playerIndex = console.addPlayer();
//		console.playGame(playerIndex);
		
		var console = new GameConsole<>(new PirateGame("The Sailer Man"));
		int playerIndex = console.addPlayer();
		console.playGame(playerIndex);
	}
}
