package com.ironhack.classes;

import com.ironhack.interfaces.Attacker;
import com.ironhack.styles.ConsoleColors;

public abstract class Character implements Attacker {
    private final int id;
    private String name;
    private int hp;
    private boolean isAlive;
    protected boolean used;

    public Character(int id, String name, int hp) {
        this.id = id;
        setName(name);
        setHp(hp);
        setAlive();
    }

//  The print avatar method is defined in the child classes Warrior and Wizard.
    public abstract String printAvatar();

//  The following method simulates a loading process for attacks. It forces the computer thread to sleep between for prints.
    public void printAttack()  {
        String[] attackLoad = {"Preparing next attack: ","=","=","=","=","=","=",">", " Ready!\n"};
        System.out.println(ConsoleColors.YELLOW_BOLD);
        for(int i=0; i<attackLoad.length;i++){
            System.out.print(attackLoad[i]);
            try{Thread.sleep(30);
            }catch (InterruptedException e){
                System.out.print("Loading...");
            }
        }
        System.out.println(ConsoleColors.WHITE_BOLD);
    }

//  The following method prints a header and uses the getName method to print the winners name:
    public String printWinner() {
        System.out.println(ConsoleColors.YELLOW_BOLD+"========================");
        System.out.println("*** WE HAVE A WINNER ***");
        System.out.println("========================");
        return "  ***" + getName().toUpperCase() + "***" + ConsoleColors.WHITE_BOLD + "\n";
    }


//  Getters and setters
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

    public boolean isUsed() {
        return used;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else this.hp = hp;
        setAlive();
    }

    public void setAlive() {
        isAlive = this.hp > 0;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
