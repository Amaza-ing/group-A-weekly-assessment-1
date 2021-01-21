package com.ironhack.generator;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;
import com.ironhack.input.Input;

import java.util.ArrayList;

public class Generator {
//	Creates a party, both manually or with auto-generated characters
	public static ArrayList<Character> createParty() {
		int CharacterNum, option, hp, stamina, strength, mana, intelligence;
		ArrayList<Character> party = new ArrayList<>();
		String fighterName;

//		First we ask the user the amount of fighters per party. It has a 10-characters limit.
		System.out.println("How many characters will be fighting for this party?  Max number = 10");
		CharacterNum = Input.getInputNumber(1, 10);
//		Creation process begins here giving the user the option to create them manually or using a random generator.
//		This is done for every character of the party

		for (int i = 0; i < CharacterNum; i++) {
			System.out.println("CHARACTER CREATION MENU\n" + "Do you want to create the next character or get a random one?\n" +
					"1: Create my character.\n" +
					"2: Get Random character.\n" +
					"Party members: (" + (i + 1) + "/" + CharacterNum + ")");
			option = Input.getInputNumber(1, 2);
			String[] fighterType = {"Warrior", "Wizard"};
			if (option == 1) {
// 				User must select if it's gonna be a Warrior or a Wizard
				System.out.println("Will it be a warrior or a wizard?\n" + "1: Warrior\n" + "2: Wizard\n");
				option = Input.getInputNumber(1, 2);
// 				User must type the name of the character
				fighterName = Input.getFighterName(fighterType[option - 1]);
// 				User must type the health value
				System.out.println("Set the health points of your " + fighterType[option - 1] + " .");
				hp = Checker.checkHP(fighterType[option - 1], Input.getInputNumber(50, 200));

// 				Depending on the type of character, user must type the rest of the stats.
				if (option == 1) {
					System.out.println("Set the stamina of your Warrior (10 - 50).");
					stamina = Checker.checkStamina(Input.getInputNumber(10, 50));
					System.out.println("Set the strength of your Warrior (1 - 10).");
					strength = Checker.checkStrength(Input.getInputNumber(1, 10));
// 					These stats are added to put the character to the party
					party.add(new Warrior(i + 1, fighterName, hp, stamina, strength));
					System.out.println("Warrior created!");
				} else {
					System.out.println("Set the mana of your Wizard (10 - 50).");
					mana = Checker.checkMana(Input.getInputNumber(10, 50));
					System.out.println("Set the intelligence of your Wizard (1 - 50).");
					intelligence = Checker.checkIntelligence(Input.getInputNumber(1, 50));
					party.add(new Wizard(i + 1, fighterName, hp, mana, intelligence));
					System.out.println("Wizard created!");
				}
			} else {
// 				Randomly generated characters.
				party.add(RandomGenerator.generateRandomCharacter(party, i + 1));
			}
		}
		return party;
	}
}
