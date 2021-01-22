package com.ironhack.generator;

import com.ironhack.input.Input;

//All the methods in this class help to check the values typed by the user when creating a new character.
public class Checker {
	//In this class we have included all the check-methods we have used to verify that the data for each character is
	//set correctly. These are pretty straight-forward to understand as they build using only comparisons and loops.
	//Each check-method verifies if the data has been set as it was presumed in the exercise's statement.
	public static int checkHP(String fighterType, int hp) {
		if (fighterType.equals("Warrior")) {
			while (hp < 100 || hp > 200) {
				System.err.println("The Warriors' HP must be between 100 and 200. Set it again, please.");
				hp = Input.getInputNumber(100, 200);
			}
		} else {
			while (hp < 50 || hp > 100) {
				System.err.println("The Wizards' HP must be between 50 and 100. Set it again, please.");
				hp = Input.getInputNumber(50, 100);
			}
		}
		return hp;
	}

	public static int checkStamina(int stamina) {
		while (stamina < 10 || stamina > 50) {
			System.err.println("Stamina must be between 10 and 50. Set it again, please.");
			stamina = Input.getInputNumber(10, 50);
		}
		return stamina;
	}

	public static int checkStrength(int strength) {
		while (strength < 1 || strength > 10) {
			System.err.println("Strength must be between 1 and 10. Set it again, please.");
			strength = Input.getInputNumber(1, 10);
		}
		return strength;
	}

	public static int checkMana(int mana) {
		while (mana < 10 || mana > 50) {
			System.err.println("Mana must be between 10 and 50. Set it again, please.");
			mana = Input.getInputNumber(10, 50);
		}
		return mana;
	}

	public static int checkIntelligence(int intel) {
		while (intel < 1 || intel > 50) {
			System.err.println("Intelligence must be between 1 and 10. Set it again, please.");
			intel = Input.getInputNumber(1, 50);
		}
		return intel;
	}
}
