package com.ironhack.characters;

public class Warrior extends Character {
	private int	stamina;
	private int	strength;

	public Warrior(int id, String name, int hp, int stamina, int strength) {
		super(id, name, hp);
		setStamina(stamina);
		setStrength(strength);
	}

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
