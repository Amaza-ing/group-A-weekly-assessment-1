package com.ironhack.generator;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

// 	Method to generate a random character.
	public static Character generateRandomCharacter(List<Character> currentCharacters, int id) {

//		Names available for the random generator
		String[] nameRandom = {"Legolas", "Darth Vader", "Mark Zuckerberg", "Harry Potter", "Voldemort", "Víctor Cardozo", "Arnoldo Sicilia", "Xabier García", "Steve Jobs", "Thanos", "Donald Trump", "Spider-Man", "Varian Wrynn", "Sylvanas", "Putin", "Kim Jong-un"};
		Character character;

// 		All the stats are randomly generated within the proper boundaries.
		if (randomNumber(0, 1) == 0) {
			character = new Warrior(id, nameRandom[randomNumber(0, 15)], randomNumber(100, 200), randomNumber(10, 50), randomNumber(1, 10));
		} else {
			character = new Wizard(id, nameRandom[randomNumber(0, 15)], randomNumber(50, 100), randomNumber(10, 50), randomNumber(1, 50));
		}

// 		If the character already exists, the name gets a 'Jr.' at the end.
		for (Character i : currentCharacters) {
			if (character.getName().equals(i == null ? null : i.getName())) {
				character.setName(character.getName() + " Jr");
				break;
			}
		}
		return character;
	}

//	Generates a group of random characters of a certain size
	public static List<Character> generateGroup(int quantity, int index) {
		List<Character> characters = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			characters.add(RandomGenerator.generateRandomCharacter(characters, i + index));
		}
		return characters;
	}

// 	Random number generator within min and max limits.
	public static int randomNumber(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1));
	}
}
