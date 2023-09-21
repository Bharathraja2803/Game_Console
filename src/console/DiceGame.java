package console;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DiceGame extends Game<DicePlayer> {

	public DiceGame(String name) {
		super(name);
	}

	@Override
	public DicePlayer createNewPlayer(String name) {
		DicePlayer player1 = new DicePlayer(name);
		return player1;
	}

	@Override
	public Map<Character, GameAction> getGameActions(int playerIndex) {
		Map<Character, GameAction> map = new LinkedHashMap<>(
				Map.of(
						'A', new GameAction('A',"Rolling all Dice", this::rollAll),
						'S', new GameAction('S',"Rolling Selected Numbers", this::rollSelected)
						)
				);
		map.putAll(getStandardActions());
		return map;
		
	}
	
	public boolean rollAll(int playerIndex) {
		return getPlayer(playerIndex).rollAll();
	}
	
	public boolean rollSelected(int playerIndex) {
		System.out.println("Enter the values you need to hold seperated by <spaces>");
		Scanner scanner = new Scanner(System.in);
		String selectedValues = scanner.nextLine();
		return getPlayer(playerIndex).rollSelected(selectedValues);
	}

}
