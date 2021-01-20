package com.ironhack.classes;

import com.ironhack.interfaces.Attacker;
import com.ironhack.styles.ConsoleColors;

public class Warrior extends Character implements Attacker {
    private int stamina;
    private int strength;

    public Warrior(int id, String name, int hp, int stamina, int strength) {
        super(id, name, hp);
        setStamina(stamina);
        setStrength(strength);
        this.used = false;
    }

    //An enhanced attack consumes 7 points of stamina to deal 30% more damage on hit. It works the same way the heavy
    //attack does, it just deals more damage, consumes more stamina, and can only be casted ONCE!
    public void enhancedAttack(Character character) {
        if (isUsed()) {
            System.out.println(getName() + " has already used the Enhanced Attack ability.");
        } else if (this.stamina >= 7) {
            System.out.print(getName() + " made an Enhanced Attack to " + character.getName() + ". ");
            System.out.println(character.getName() + " loses " + (int) (this.strength*1.3) + " hp.");
            character.setHp(character.getHp()-(int) (this.strength*1.3));
            setUsed(true);
            setStamina(this.stamina - 7);
            System.out.println(getName() + " uses 7 stamina and now has: " + getStamina() + " left.");
        } else {
            System.out.println("The Warrior " + getName() + " does not have enough stamina to use an Enhanced Attack.");
        }
    }

    @Override
    public void attack(Character character) {
        printAttack();
        if (this.stamina >= 5) {
            System.out.print(getName() + " made a Heavy attack to " + character.getName() + ". ");
            System.out.println(character.getName() + " loses " + (this.strength) + " hp.");
            character.setHp(character.getHp() - this.strength);
            setStamina(this.stamina - 5);
            System.out.println(getName() + " uses 5 stamina and now has: " + getStamina() + " left.");
        } else {
            System.out.print(getName() + " made a Weak attack to " + character.getName() + ". ");
            System.out.println(character.getName() + " loses " + (this.strength / 2) + " hp.");
            character.setHp(character.getHp() - (this.strength / 2));
            setStamina(this.stamina + 1);
            System.out.println(getName() + " regenerates 1 stamina and now has: " + getStamina() + ".");
        }
    }

    //	Se hace un override del método printAvatar de la clase abstracta character
    @Override
    public String printAvatar() {
        System.out.println(ConsoleColors.RED_BOLD);
        for (int i = 1; i <= 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int k = 1; k < 2 * (i - 1); k++) {
                System.out.print(" ");
            }
            if (i == 1) {
                System.out.println("");
            } else {
                System.out.println("*");
            }
        }
        for (int i = 5 - 1; i >= 1; i--) {
            for (int j = 5; j > i; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int k = 1; k < 2 * (i - 1); k++) {
                System.out.print(" ");
            }
            if (i == 1)
                System.out.println("");
            else
                System.out.println("*");
        }
        System.out.println("=========");
        System.out.println(" WARRIOR " + ConsoleColors.WHITE_BOLD);
        return getName().toUpperCase() + "\n\n";

    }

    //Getters and Setters

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
