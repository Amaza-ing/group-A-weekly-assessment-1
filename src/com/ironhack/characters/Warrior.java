package com.ironhack.characters;

import com.ironhack.interfaces.Attacker;

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
			System.out.println(getName() + "Made a Heavy attack to " + character.getName() + ".");
			System.out.println(character.getName() + " loses " + (this.strength) + " hp.");
			character.setHp(character.getHp() - this.strength);
			setStamina(this.stamina - 5);
		}
		else {
			System.out.println(getName() + "Made a Weak attack to " + character.getName() + ".");
			System.out.println(character.getName() + " loses " + (this.strength / 2) + " hp.");
			character.setHp(character.getHp() - (this.strength / 2));
			setStamina(this.stamina + 1);
		}
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
