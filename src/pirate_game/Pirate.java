package pirate_game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import console.Player;

public class Pirate implements Player{

	private final String name;
	private final Map<String,Integer> gameData;
	private final List<String> townsVisited = new LinkedList<>();
	private Weapon currentWeapon;
	
	
	
	public Pirate(String name) {
		this.name = name;
	}
	
	//Instance Initializer
	{
		gameData = new HashMap<>(
				Map.of(
						"health",100,
						"score",0,
						"level",0,
						"townIndex",0
						)
				);
		
		visitTown();
	}
	
	
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}


	void setCurrentWeapon(Weapon currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	int value(String type) {
		return this.gameData.get(type);
	}

	private void setValue(String type, int value) {
		gameData.put(type, value);
	}
	
	private void adjustValue(String type, int adjValue) {
		gameData.compute(type, (k,v) -> v += adjValue);
	}
	
	private void adjustHealth(int adjValue) {
		int health = gameData.get("health");
		health += adjValue;
		health = (health<=0) ? 0 : (health>=100)? 100: health;
		setValue("health", health);
	}
	
	boolean useWeapon() {
		System.out.println("Used the "+ currentWeapon);
		return visitNextTown();
	}
	
	boolean visitTown() {
		List<String> levelTowns = PirateGame.getTowns(value("level"));
		
		if(levelTowns == null) return true;
		String town = levelTowns.get(value("townIndex"));
		if(town != null) {
			townsVisited.add(town);
			return false;
		}
		
		return true;
	}
	
	
	
	@Override
	public String toString() {
		var current = ((LinkedList<String>) townsVisited).getLast();
		String [] simpleNamesOfTown = new String[townsVisited.size()];
		Arrays.setAll(simpleNamesOfTown,i -> townsVisited.get(i).split(",")[0]);
		
		return """
				--> current: %s
				Pirate: %s -> %s
				\tTowns Visited: %s
				""".formatted(current, name, gameData, Arrays.toString(simpleNamesOfTown));
	}


	@Override
	public String name() {
		return name;
	}
	
	
	private boolean visitNextTown() {
		int  townIndex = value("townIndex");
		List<String> towns = PirateGame.getTowns(value("level"));
		if(towns == null) return true;
		if(townIndex >= towns.size() -1) {
			System.out.println("Leveling up! Bonus: 500 points");
			adjustValue("level", 1);
			adjustValue("score", 500);
			setValue("townIndex", 0);
		}else {
			System.out.println("Sailing to next town! Bonus: 50 points");
			adjustValue("townIndex", 1);
			adjustValue("score", 50);
		}
		return visitTown();
	}
	
}
