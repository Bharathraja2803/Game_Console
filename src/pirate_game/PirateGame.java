package pirate_game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.ValueExp;

import console.Game;
import console.GameAction;

public class PirateGame extends Game<Pirate>{

	private static final List<List<String>> levelMap;
	
	// Static initializer
	static {
		levelMap = new ArrayList<>();
		System.out.println("Loading data...");
		loadData();
		
		if(levelMap.size() ==0) {
			throw new RuntimeException("Could not load data");
		}
		System.out.println("Finish loading data");
	}
	
	public PirateGame(String name) {
		super(name);	
	}
	
	
	private static void loadData() {
		// Level 1 town
		
		levelMap.add(new ArrayList<>(
				List.of(
						"Bridgetown, Barbados",
		                "Fitts Village, Barbados",
		                "Holetown, Barbados"
						)
				));
		
		//Level 2 town
		
		levelMap.add(new ArrayList<>(
				List.of(
						 "Fort-de-France, Martinique",
			              "Sainte-Anne, Martinique",
			              "Le Vauclin, Martinique"
						)
				));
			
	}


	@Override
	public Pirate createNewPlayer(String name) {		
		return new Pirate(name);
	}


	@Override
	public Map<Character, GameAction> getGameActions(int playerIndex) {
		Pirate pirate = getPlayer(playerIndex);
		System.out.println(pirate);
		List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));
		
		Map<Character, GameAction> map = new LinkedHashMap<>();
		
		for (Weapon w: weapons) {
			char key = w.name().charAt(0);
			map.put(key, new GameAction(key, "Use "+w.name() , this::useWeapon));
			
		}
		map.putAll(getStandardActions());
		return map;
	}
	
	public static List<String> getTowns(int level){
		if(level <= levelMap.size() -1 ) {
			return levelMap.get(level);
		}
		return null;
	}
	
	private boolean useWeapon(int playerIndex) {
		return getPlayer(playerIndex).useWeapon();
	}


	@Override
	public boolean exeucteGameAction(int player, GameAction action) {
		getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
		return super.exeucteGameAction(player, action);
	}
	
	
}
