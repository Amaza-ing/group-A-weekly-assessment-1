package com.ironhack.characters;

import com.ironhack.interfaces.Attacker;

public abstract class Character implements Attacker {
	private final int	id;
	private String		name;
	private int			hp;
	private boolean		isAlive;

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
		setAlive();
	}

	public void setAlive() {
		isAlive = this.hp > 0;
	}
}
