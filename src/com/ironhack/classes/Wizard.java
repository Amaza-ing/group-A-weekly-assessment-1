package com.ironhack.classes;

import com.ironhack.interfaces.Attacker;
import com.ironhack.styles.ConsoleColors;

public class Wizard extends Character implements Attacker {
    private int mana;
    private int intelligence;

    public Wizard(int id, String name, int hp, int mana, int intelligence) {
        super(id, name, hp);
        setMana(mana);
        setIntelligence(intelligence);
        this.used = false;
    }

    //restoreHealth restores 30% of the player's current health and consumes 7 points of mana.
    //If the healing is over the 100hp limit for Wizards, HP will be set to the maximum 100hp.
    //RestoreHealth can only be used ONCE.
    public void restoreHealth() {
        if (this.mana >= 7) {
            int health = getHp();
            if ((int) (getHp()*1.3) <= 100) {
                setHp((int) (getHp()*1.3));
            } else setHp(100);
            System.out.print(getName() + " used Restore Health. ");
            System.out.println(getName() + " self-healed " + (getHp()-health) + " hp.");
            setMana(this.mana - 7);
            System.out.println(getName() + " uses 7 mana and now has: " + getMana() + " left.");
            setUsed(true);
        } else {
            System.out.println("The Wizard " + getName() + " does not have enough mana to cast Restore Health.");
        }
    }

    @Override
    public void attack(Character character) {
        printAttack();
        if (!this.used) {
            int randomNum = (int) (Math.random() * (20));
            if (randomNum == 20) {
                restoreHealth();
            }
        }
        if (this.mana >= 5) {
            System.out.print(getName() + " casted a fireball to " + character.getName() + ". ");
            System.out.println(character.getName() + " loses " + (this.intelligence) + " hp.");
            character.setHp(character.getHp() - this.intelligence);
            setMana(this.mana - 5);
            System.out.println(getName() + " uses 5 mana and now has: " + getMana() + " left.");
        } else {
            System.out.print(getName() + " did a staff hit to " + character.getName() + ". ");
            System.out.println(character.getName() + " loses 2 hp.");
            character.setHp(character.getHp() - 2);
            setMana(this.mana + 1);
            System.out.println(getName() + " regenerates 1 mana and now has: " + getMana() + ".");
        }
    }

    //	Se hace un override del método printAvatar de la clase abstracta character
    @Override
    public String printAvatar() {
        System.out.println(ConsoleColors.PURPLE_BOLD);
        for (int i = 0; i < 5; i++) {
            for (int j = 5 - i; j > 1; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("=========");
        System.out.println(" WIZZARD " + ConsoleColors.WHITE_BOLD);
        return getName().toUpperCase() + "\n\n";
    }


    // Getters and Setters

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
