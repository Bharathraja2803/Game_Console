package main_method;

import console.GameConsole;
import console.ShooterGame;

public class Main {
	public static void main(String [] args) {
		var console = new GameConsole<>(new ShooterGame("The Shooting Game"));
		
		int playerIndex = console.addPlayer();
		console.playGame(playerIndex);
	}
}
