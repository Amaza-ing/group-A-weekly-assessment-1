package com.ironhack.battle;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.input.Input;

import java.util.List;

public class Battle {
	/*
	public static void battle(List<Character> firstParty, List<Character> secondParty) {
		while (firstParty.size() > 0 && secondParty.size() > 0) {
			int IdChar1 = 0;
			int IdChar2 = 0;
			int	size1 = firstParty.size();
			int size2 = secondParty.size();
			boolean selectOk = false;

			//The user can pick one opponent of each party using the IDs
			do {
				System.out.println("Select first opponent by ID: ");
				for (Character ch : firstParty) {
					System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
				}

				int char1 = Input.getInputNumber(1, size1);
				for (Character ch : firstParty) {
					if (ch.getId() == char1) {
						IdChar1 = ch.getId();
						selectOk = true;
					}
				}
				if (!selectOk) {
					System.err.println("Wrong ID!!!");
				}
			} while (!selectOk);

			//Se busca el personaje y se muestra con su avatar
			int firstChar = IdChar1;
			Character opponent1 = firstParty.stream().
					filter(x -> x.getId() == firstChar).
					findFirst().get();
			System.out.println(opponent1.printAvatar()); // Se añade el print avatar cuando selecciona el personaje


			selectOk = false;
			do {
				System.out.println("Select second opponent by ID: ");
				for (Character ch : secondParty) {
					System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
				}

				int char2 = Input.getInputNumber(1, size2);
				for (Character ch : secondParty) {
					if (ch.getId() == char2) {
						IdChar2 = ch.getId();
						selectOk = true;
					}
				}
				if (!selectOk) {
					System.err.println("Wrong ID!!!");
				}
			} while (!selectOk);

			int secondChar = IdChar2;
			Character opponent2 = secondParty.stream().
					filter(x -> x.getId() == secondChar).
					findFirst().get();
			System.out.println(opponent2.printAvatar()); // Se añade el print avatar cuando selecciona el personaje

			//The two opponents get in combat until one or both are dead
			combat(opponent1, opponent2);

			//This continues until one or both parties have no more characters.
		}
	}


	public static String getType(Character character) {
		String type;
		if (character.getClass() == Warrior.class)
			type = "Warrior";
		else
			type = "Wizard";
		return type;
	}

	 */
}