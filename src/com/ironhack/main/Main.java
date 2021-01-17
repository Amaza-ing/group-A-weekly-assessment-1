package com.ironhack.main;

import com.ironhack.characters.Character;
import com.ironhack.characters.Warrior;
import com.ironhack.characters.Wizard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Character> party1 = new ArrayList<>();
    static List<Character> party2 = new ArrayList<>();
    static List<Character> graveyard = new ArrayList<>();

    public static void main(String[] args) {
        int c1, c2, option;
        Scanner scanner = new Scanner(System.in);

        party1.add(new Warrior(1, "Dummy guy", 100, 10, 10));
        party1.add(new Wizard(2, "Mystic guy", 70, 25, 15));
        party2.add(new Wizard(3, "Magic dude", 50, 15, 20));
        party2.add(new Warrior(4, "Punch dude", 130, 20, 70));

        while (party1.size() > 0 && party2.size() > 0) {
            System.out.println("Chose the party-1 member to fight. From [1] to [" + party1.size() + "].");
            c1 = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.println("Chose the party-2 member to fight. From [1] to [" + party2.size() + "].");
            c2 = Integer.parseInt(scanner.nextLine()) - 1;
            battle(party1.get(c1), party2.get(c2));
        }
        result();
        System.out.println("Do you want to visit the graveyard? Yes[0] No[1]");
        option = Integer.parseInt(scanner.nextLine());
        if (option == 0) {
            graveyard();
        }

    }

    public static void battle(Character c1, Character c2) {
        while (c1.isAlive() && c2.isAlive()) {
            c1.attack(c2);
            c2.attack(c1);
            System.out.println(c1.getName() + " has: " + c1.getHp() + " hp.");
            System.out.println(c2.getName() + " has: " + c2.getHp() + " hp.\n");
        }
        if (!c1.isAlive() && !c2.isAlive()) {
            System.out.println("Both of them are dead. It's a tie!");
            graveyard.add(c1);
            graveyard.add(c2);
            party1.remove(c1);
            party2.remove(c2);
        } else if (c1.isAlive()) {
            System.out.println(c2.getName() + " is dead. " + c1.getName() + " wins!");
            graveyard.add(c2);
            party2.remove(c2);
        } else {
            System.out.println(c1.getName() + " is dead. " + c2.getName() + " wins!");
            graveyard.add(c1);
            party1.remove(c1);
        }
    }

    public static void result() {
        if (party1.size() == 0 && party2.size() == 0) {
            System.out.println("\nParty crasher!! Everyone's dead. Nobody wins.");
        } else if (party1.size() == 0) {
            System.out.println("\nParty 2 wins!");
        } else {
            System.out.println("\nParty 1 wins!");
        }
    }

    public static void graveyard() {
        System.out.println("Rest in peace:\n");
        for (Character i : graveyard) {
            System.out.println(i.getName() + ".");
        }
    }

    //Import a party passing as a parameter the name of the CSV file, e.g. "myBestTeam.csv"
    //The file must be located in the "Resources" folder.
    public static List<Character> importParty(String filename) throws IOException {
        List<Character> party = new ArrayList<>();
        String filePath = "com/ironhack/resources/" + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("File not found, check the spelling");
            return null;
        }

        Scanner sc = new Scanner(file);
        sc.nextLine();
        while (sc.hasNextLine()) {
            List<String[]> characterValues = new ArrayList<>();
            characterValues.add(sc.nextLine().split(","));

            if (Arrays.toString(characterValues.get(0)).equalsIgnoreCase("warrior")) {
                Warrior warrior = new Warrior(
                        Integer.parseInt(Arrays.toString(characterValues.get(1))),
                        Arrays.toString(characterValues.get(2)),
                        Integer.parseInt(Arrays.toString(characterValues.get(3))),
                        Integer.parseInt(Arrays.toString(characterValues.get(4))),
                        Integer.parseInt(Arrays.toString(characterValues.get(5)))
                );
                party.add(warrior);
            } else {
                Wizard wizard = new Wizard(
                        Integer.parseInt(Arrays.toString(characterValues.get(1))),
                        Arrays.toString(characterValues.get(2)),
                        Integer.parseInt(Arrays.toString(characterValues.get(3))),
                        Integer.parseInt(Arrays.toString(characterValues.get(4))),
                        Integer.parseInt(Arrays.toString(characterValues.get(5)))
                );
                party.add(wizard);
            }
        }

        sc.close();
        return party;
    }
}
