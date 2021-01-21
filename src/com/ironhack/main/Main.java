package com.ironhack.main;

import com.ironhack.battle.Battle;
import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;
import com.ironhack.generator.Checker;
import com.ironhack.generator.RandomGenerator;
import com.ironhack.importExport.ImportExport;
import com.ironhack.input.Input;
import com.ironhack.styles.ConsoleColors;
import com.ironhack.styles.Start;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static final int MAX_NUM_OF_FIGHTERS = 10;

    public static void main(String[] args) {
        int option, numFighters;
        boolean automaticBattle = false;

//      The printStart method prints a header for the game before launching the initial menu.
        Start.printStart();
        while (true) {
            //se vacían las listas para cada partida
            if (Init.getGraveyard().size() > 0) {
                Init.clear();
            }
            System.out.println("What do you want to do? Insert the number to choose an option.\n" +
                    "\t1: Create your own parties and pick the character for each round.\n" +
                    "\t2: Load parties from files and pick the character for each round.\n" +
                    "\t3: Generate parties randomly and pick the character for each round.\n" +
                    "\t4: Generate parties randomly and simulate the whole party fight.\n" +
                    "\t5: Exit the game.\n");

            option = Input.getInputNumber(1, 5);

            switch (option) {
                case 1:
                    Init.firstParty = new ArrayList<>(createParty());
                    Init.secondParty = new ArrayList<>(createParty());
                    break;
                case 2:
                    System.out.println("Type the name of the first file (e.g. 'characters.csv')");
                    Scanner sc = new Scanner(System.in);
                    String firstFile = sc.nextLine();
                    try {
                        Init.firstParty = ImportExport.importParty(firstFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Type the name of the second file (e.g. 'myTeam.csv')");
                    String secondFile = sc.nextLine();
                    try {
                        Init.secondParty = ImportExport.importParty(secondFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
                    System.out.println("How many characters will be fighting for each party? Max number = 10\n");
                    numFighters = Input.getInputNumber(1, MAX_NUM_OF_FIGHTERS);
                    Init.firstParty = new ArrayList<>(generateGroup(numFighters, 1));
                    Init.secondParty = new ArrayList<>(generateGroup(numFighters, 1));
                    break;
                case 4:
                    numFighters = RandomGenerator.randomNumber(1, MAX_NUM_OF_FIGHTERS);
                    System.out.println("There are: " + numFighters + " characters in each party.");
                    Init.firstParty = new ArrayList<>(generateGroup(numFighters, 1));
                    Init.secondParty = new ArrayList<>(generateGroup(numFighters, 1));
                    automaticBattle = true;
                    break;
                case 5:
                    System.out.println("Thanks for playing..See you soon! :)");
                    System.exit(1);
                    break;
                default:
                    break;
            }
            if (Init.firstParty == null || Init.secondParty == null) {
                System.err.println("Something went wrong creating parties...");
            } else {
                System.out.println("Parties created. Starting battle!");
                //The "originals" save the parties in case the user decides to save the winner team into a .csv file
                Init.originalFirstParty = new ArrayList<Character>(Init.firstParty) ;
                Init.originalSecondParty = new ArrayList<Character>(Init.secondParty);

                if (!automaticBattle) {
                    Battle.battle(Init.firstParty, Init.secondParty);
                } else {
                    Battle.automaticBattle(Init.firstParty, Init.secondParty);
                }
                System.out.println("Battle has ended!");
                result();
                System.out.println("Wanna see the graveyard? Type 1[Yes] / 2[No]");
                option = Input.getInputNumber(1, 2);
                if (option == 1) {
                    graveyard();
                }
                //If one team has won, user can export the winning team to a .csv file
                if (Init.firstParty.size() != 0 || Init.secondParty.size() != 0) {
                    System.out.println("Wanna export the winner team to a file? Type 1[Yes] / 2[No]");
                    option = Input.getInputNumber(1, 2);
                    if (option == 1)
                        ImportExport.savePartyToFile();
                }
                System.out.println("\nWanna play again? Type 1[Yes] / 2[No]");
                option = Input.getInputNumber(1, 2);
                if (option == 2) {
                    System.out.println("Thanks for playing..See you soon! :)");
                    System.exit(1);
                }
            }
        }

    }

    public static void result() {
        if (Init.firstParty.size() == 0 && Init.secondParty.size() == 0) {
            System.out.println("\nParty crasher!! Everyone's dead. Nobody wins.\n");
        } else if (Init.firstParty.size() == 0) {
            System.out.println("\nParty 2 wins!\n");
        } else {
            System.out.println("\nParty 1 wins!\n");
        }
    }

    public static void graveyard() {
        System.out.println("Rest in peace:\n");
        for (Character i : Init.graveyard) {
            System.out.println(i.getName() + ".");
        }
    }

    //Creates a party, both manually or with auto-generated characters
    public static ArrayList<Character> createParty() {
        int CharacterNum, option, hp, stamina, strength, mana, intelligence;
        ArrayList<Character> party = new ArrayList<>();
        String fighterName;

        // Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
        System.out.println("How many characters will be fighting for this party?  Max number = 10");
        CharacterNum = Input.getInputNumber(1, 10);
        //Aquí empieza el proceso de creación de los personajes. Primero pedimos si se quiere crear el personaje
        //manualmente, o bien obtener uno generado por el sistema. Ésto dentro de un bucle hasta llegar a la capacidad
        //máxima del grupo.

        for (int i = 0; i < CharacterNum; i++) {
            System.out.println("CHARACTER CREATION MENU\n" + "Do you want to create the next character or get a random one?\n" +
                    "1: Create my character.\n" +
                    "2: Get Random character.\n" +
                    "Party members: (" + (i + 1) + "/" + CharacterNum + ")");
            option = Input.getInputNumber(1, 2);
            String[] fighterType = {"Warrior", "Wizard"};
            if (option == 1) {
                // Preguntamos si va a ser Warrior o Wizard
                // Reutilizamos la variable option para incluir el tipo de personaje.
                System.out.println("Will it be a warrior or a wizard?\n" + "1: Warrior\n" + "2: Wizard\n");
                option = Input.getInputNumber(1, 2);
                // Determinamos el nombre del personaje
                fighterName = Input.getFighterName(fighterType[option - 1]);
                // Establecemos la vida
                System.out.println("Set the health points of your " + fighterType[option - 1] + " .");
                hp = Checker.checkHP(fighterType[option - 1], Input.getInputNumber(50, 200));

                // Ahora, según si es Warrior o Wizards, customizamos el resto de stats
                if (option == 1) {
                    System.out.println("Set the stamina of your Warrior (10 - 50).");
                    stamina = Checker.checkStamina(Input.getInputNumber(10, 50));
                    System.out.println("Set the strength of your Warrior (1 - 10).");
                    strength = Checker.checkStrength(Input.getInputNumber(1, 10));
                    // Añadimos todos los stats al personaje
                    party.add(new Warrior(i + 1, fighterName, hp, stamina, strength));
                    System.out.println("Warrior created!");
                } else {
                    System.out.println("Set the mana of your Wizard (10 - 50).");
                    mana = Checker.checkMana(Input.getInputNumber(10, 50));
                    System.out.println("Set the intelligence of your Wizard (1 - 50).");
                    intelligence = Checker.checkIntelligence(Input.getInputNumber(1, 50));
                    party.add(new Wizard(i + 1, fighterName, hp, mana, intelligence));
                    System.out.println("Wizard created!");
                }
            } else {
                // Randomly generated characters.
                party.add(RandomGenerator.generateRandomCharacter(party, i + 1));
            }
        }
        return party;
    }

    //Generates a group of random characters of a certain size
    public static List<Character> generateGroup(int quantity, int index) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            characters.add(RandomGenerator.generateRandomCharacter(characters, i + index));
        }
        return characters;
    }
}
