package com.ironhack.characters;

import com.ironhack.interfaces.Attacker;
import com.ironhack.styles.ConsoleColors;

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

//	Se llama un método para imprimir el avatar del personaje.
	public abstract String printAvatar();

//	Se crea un método para imprimir por pantalla el nombre del ganador.
	public String printWinner(){
		System.out.println("========================");
		System.out.println(ConsoleColors.CYAN_BACKGROUND + "*** WE HAVE A WINNER ***" + ConsoleColors.RESET);
		System.out.println("========================");
		return ConsoleColors.CYAN_BACKGROUND + getName() + ConsoleColors.RESET + "\n";
	}
}
