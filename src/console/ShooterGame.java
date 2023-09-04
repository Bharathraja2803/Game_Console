package console;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShooterGame extends Game<Shooter>{

	public ShooterGame(String name) {
		super(name);
	}

	@Override
	public Shooter createNewPlayer(String name) {
		return new Shooter(name);
	}

	@Override
	public Map<Character, GameAction> getGameActions(int playerIndex) {
		
		Map<Character, GameAction> map = new LinkedHashMap<>(
				Map.of(
						'F', new GameAction('F',"Find Prize", this::findPrize),
						'S', new GameAction('S',"Use a gun",this::useWeapon)
						)
				);
		map.putAll(getStandardActions());
		return map;
		
	}
	
	public boolean findPrize(int playerIndex) {
		return getPlayer(playerIndex).findPrize();
	}
	
	public boolean useWeapon(int playerIndex) {
		return getPlayer(playerIndex).useWeapon("AK47");
	}
}
