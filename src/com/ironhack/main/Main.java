package com.ironhack.main;

import com.ironhack.characters.Character;
import com.ironhack.characters.Warrior;
import com.ironhack.characters.Wizard;

public class Main {
	public static void main(String[] args) {
		Character warrior1 = new Warrior(1, "Dummy guy", 100, 20, 10);
		Character wizard1 = new Wizard(2, "Magic dude", 50, 30, 40);
		battle(warrior1, wizard1);
	}

	public static void battle(Character c1, Character c2) {
		c1.attack(c2);
		c2.attack(c1);
	}
}
