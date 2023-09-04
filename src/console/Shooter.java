package console;

public record Shooter(String name) implements Player {
	
	boolean findPrize() {
		System.out.println("You found your prize, score should be adjusted");
		return false;
	}
	
	boolean useWeapon(String weapon) {
		System.out.println("Shot! using %s".formatted(weapon));
		return false;
	}
}
