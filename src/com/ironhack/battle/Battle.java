package com.ironhack.battle;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;

public class Battle {


	public static String getType(Character character) {
		String type;
		if (character.getClass() == Warrior.class)
			type = "Warrior";
		else
			type = "Wizard";
		return type;
	}
}
