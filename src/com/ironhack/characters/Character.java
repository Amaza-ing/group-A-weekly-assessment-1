package com.ironhack.characters;

public class Character {
	private int		id;
	private String	name;
	private int		hp;
	private boolean	isAlive;

	public Character(int id, String name, int hp) {
		this.id = id;
		setName(name);
		setHp(hp);
		setAlive();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAlive() {
		if (getHp() > 0) {
			isAlive = true;
		}
		else {
			isAlive = false;
		}
	}
}
