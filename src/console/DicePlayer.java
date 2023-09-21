package console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public record DicePlayer(String name) implements Player {
	static int [] diceValue = new int[5];
	boolean rollAll() {
		Random r = new Random();
		diceValue = r.ints(5,1,7).toArray();
		System.out.println("Current Dice value: "+ Arrays.toString(diceValue));
		return false;
	}
	
	boolean rollSelected(String selectedValue) {

		String[] values = selectedValue.split(" "); 
		Set<Integer> selectedValueIndex = new HashSet<>();
		Random r = new Random();
		for (String v : values) {
			for (int i =0 ; i< diceValue.length; i++) {	
				if(diceValue[i] == Integer.parseInt(v)) {
					selectedValueIndex.add(i);
					break;
				}
			}	
		}
		System.out.println("Keeping: ");
		for(int i : selectedValueIndex) {
			System.out.print(diceValue[i]+" ");
		}
		System.out.println();
		for (int i = 0; i< diceValue.length; i++) {
			if(!selectedValueIndex.contains(i)) {
				diceValue[i] = r.nextInt(1,7);
			}
		}
		System.out.println("Current Dice value: "+ Arrays.toString(diceValue));
		return false;
	}
}
