package com.ironhack.characters;

import com.ironhack.interfaces.Attacker;

public class Wizard extends Character implements Attacker {
	private int	mana;
	private int	intelligence;

	/*
	**	Constructor
	 */

	public Wizard(int id, String name, int hp, int mana, int intelligence) {
		super(id, name, hp);
		setMana(mana);
		setIntelligence(intelligence);
	}

	/*
	 **	Methods
	 */

	@Override
	public void attack(Character character) {
		if (this.mana >= 5) {
			System.out.print(getName() + " casted a fireball to " + character.getName() + ". ");
			System.out.println(character.getName() + " loses " + (this.intelligence) + " hp.");
			character.setHp(character.getHp() - this.intelligence);
			setMana(this.mana - 5);
			System.out.println(getName() + " uses 5 mana and now has: " + getMana() + " left.");
		}
		else {
			System.out.print(getName() + " did a staff hit to " + character.getName() + ". ");
			System.out.println(character.getName() + " loses 2 hp.");
			character.setHp(character.getHp() - 2);
			setMana(this.mana + 1);
			System.out.println(getName() + " regenerates 1 mana and now has: " + getMana() + ".");
		}
	}

	/*
	 **	Getters and Setters
	 */

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
