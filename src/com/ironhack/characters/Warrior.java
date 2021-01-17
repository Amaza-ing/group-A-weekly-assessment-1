package com.ironhack.characters;

import com.ironhack.interfaces.Attacker;
import com.ironhack.styles.ConsoleColors;

public class Warrior extends Character implements Attacker {
	private int	stamina;
	private int	strength;

	/*
	**	Constructor
	 */

	public Warrior(int id, String name, int hp, int stamina, int strength) {
		super(id, name, hp);
		setStamina(stamina);
		setStrength(strength);
	}

	/*
	**	Methods
	 */

	@Override
	public void attack(Character character) {
		if (this.stamina >= 5) {
			System.out.print(getName() + " made a Heavy attack to " + character.getName() + ". ");
			System.out.println(character.getName() + " loses " + (this.strength) + " hp.");
			character.setHp(character.getHp() - this.strength);
			setStamina(this.stamina - 5);
			System.out.println(getName() + " uses 5 stamina and now has: " + getStamina() + " left.");
		}
		else {
			System.out.print(getName() + " made a Weak attack to " + character.getName() + ". ");
			System.out.println(character.getName() + " loses " + (this.strength / 2) + " hp.");
			character.setHp(character.getHp() - (this.strength / 2));
			setStamina(this.stamina + 1);
			System.out.println(getName() + " regenerates 1 stamina and now has: " + getStamina() + ".");
		}
	}

//	Se hace un override del método printAvatar de la clase abstracta character
	@Override
	public String printAvatar(){

		for (int i=1; i<=5 ; i++) { for (int j = 5; j > i ; j--) {
			System.out.print(" ");
		}
			System.out.print("*");
			for (int k = 1; k < 2*(i -1) ;k++) {
				System.out.print(" "); } if( i==1) {
				System.out.println("");
			}
			else { System.out.println("*");
			}
		}
		for (int i=5-1; i>= 1 ; i--) {
			for (int j = 5; j > i ; j--) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int k = 1; k < 2*(i -1) ;k++) {
				System.out.print(" ");
			}
			if( i==1)
				System.out.println("");
			else
				System.out.println("*");
		}
		System.out.println(ConsoleColors.RED_BACKGROUND + " WARRIOR " + ConsoleColors.RESET);
		System.out.println("=========");
		return getName().toUpperCase() + "\n\n";

	}

	/*
	**	Getters and Setters
	 */

	public int getStamina() {
		return stamina;
	}

	public int getStrength() {
		return strength;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
}
