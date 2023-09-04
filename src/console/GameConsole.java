package console;

import java.util.Scanner;

public class GameConsole<T extends Game<? extends Player>> {
	
	private final T game;
	private static final Scanner scanner = new Scanner(System.in);
	
	public GameConsole(T game) {
		this.game = game;
	}
	
	public int addPlayer() {
		System.out.println("Enter your playing name: ");
		String name = scanner.nextLine();
		System.out.printf("Welcome to %s, %s%n",game.getGameName(),name);
		return game.addPlayer(name);
	}
	
	public void playGame(int playerIndex) {
		boolean done = false;
		while(!done) {
			var gameActions = game.getGameActions(playerIndex);
			System.out.println("Select from one of the following actions: ");
			for(char c : gameActions.keySet()) {
				String prompt = gameActions.get(c).prompt();
				System.out.printf("\t%s (%s)%n",prompt,c);
			}
			
			System.out.println("Enter next action: ");
			char nextAction = scanner.nextLine().toUpperCase().charAt(0);
			GameAction gameAction = gameActions.get(nextAction);
			if(gameAction != null) {
				System.out.println("-".repeat(50));
				done = game.exeucteGameAction(playerIndex, gameAction);
				if(!done) {
					System.out.println("-".repeat(50));
				}
			}
		}
		
		
	}
	
}
