package com.ironhack.characters;

public class Wizard extends Character {
	private int	mana;
	private int	intelligence;

	public Wizard(int id, String name, int hp, int mana, int intelligence) {
		super(id, name, hp);
		setMana(mana);
		setIntelligence(intelligence);
	}

	public int getMana() {
		return mana;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
}
