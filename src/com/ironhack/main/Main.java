package com.ironhack.main;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;
import com.ironhack.generator.Checker;
import com.ironhack.generator.RandomGenerator;
import com.ironhack.input.Input;
import com.ironhack.styles.ConsoleColors;
import com.ironhack.styles.Start;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static final int MAX_NUM_OF_FIGHTERS = 10;
    static ArrayList<Character> firstParty;
    static ArrayList<Character> originalFirstParty;
    static ArrayList<Character> secondParty;
    static ArrayList<Character> originalSecondParty;
    static ArrayList<Character> graveyard = new ArrayList<>();

    public static void main(String[] args) {
        int option, numFighters;
        boolean automaticBattle = false;

        Start.printStart();
        while (true) {
            //se vacían las listas para cada partida
            if (graveyard.size() > 0) {
                firstParty.clear();
                secondParty.clear();
                graveyard.clear();
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
                    firstParty = new ArrayList<>(createParty());
                    secondParty = new ArrayList<>(createParty());
                    break;
                case 2:
                    System.out.println("Type the name of the first file (e.g. 'characters.csv')");
                    Scanner sc = new Scanner(System.in);
                    String firstFile = sc.nextLine();
                    try {
                        firstParty = importParty(firstFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Type the name of the second file (e.g. 'myTeam.csv')");
                    String secondFile = sc.nextLine();
                    try {
                        secondParty = importParty(secondFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    // Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
                    System.out.println("How many characters will be fighting for each party? Max number = 10\n");
                    numFighters = Input.getInputNumber(1, MAX_NUM_OF_FIGHTERS);
                    firstParty = new ArrayList<>(generateGroup(numFighters, 1));
                    secondParty = new ArrayList<>(generateGroup(numFighters, 1));
                    break;
                case 4:
                    numFighters = RandomGenerator.randomNumber(1, MAX_NUM_OF_FIGHTERS);
                    System.out.println("There are: " + numFighters + " characters in each party.");
                    firstParty = new ArrayList<>(generateGroup(numFighters, 1));
                    secondParty = new ArrayList<>(generateGroup(numFighters, 1));
                    automaticBattle = true;
                    break;
                case 5:
                    System.out.println("Thanks for playing..See you soon! :)");
                    System.exit(1);
                    break;
                default:
                    break;
            }
            if (firstParty == null || secondParty == null) {
                System.err.println("Something went wrong creating parties...");
            } else {
                System.out.println("Parties created. Starting battle!");
                //The "originals" save the parties in case the user decides to save the winner team into a .csv file
                originalFirstParty = new ArrayList<Character>(firstParty) ;
                originalSecondParty = new ArrayList<Character>(secondParty);

                if (!automaticBattle) {
                    battle(firstParty, secondParty);
                } else {
                    automaticBattle(firstParty, secondParty);
                }
                System.out.println("Battle has ended!");
                result();
                System.out.println("Wanna see the graveyard? Type 1[Yes] / 2[No]");
                option = Input.getInputNumber(1, 2);
                if (option == 1) {
                    graveyard();
                }
                //If one team has won, user can export the winning team to a .csv file
                if (firstParty.size() != 0 || secondParty.size() != 0) {
                    System.out.println("Wanna export the winner team to a file? Type 1[Yes] / 2[No]");
                    option = Input.getInputNumber(1, 2);
                    if (option == 1)
                        savePartyToFile();
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

    public static void battle(List<Character> firstParty, List<Character> secondParty) {
        while (firstParty.size() > 0 && secondParty.size() > 0) {
            int IdChar1 = 0;
            int IdChar2 = 0;
            int size1 = firstParty.size();
            int size2 = secondParty.size();
            boolean selectOk = false;

            //The user can pick one opponent of each party using the IDs
            do {
                System.out.println("Select first opponent by ID: ");
                for (Character ch : firstParty) {
                    System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
                }

                int char1 = Input.getInputNumber(1, originalFirstParty.size());
                for (Character ch : firstParty) {
                    if (ch.getId() == char1) {
                        IdChar1 = ch.getId();
                        selectOk = true;
                    }
                }
                if (!selectOk) {
                    System.err.println("Wrong ID!!!");
                }
            } while (!selectOk);

            //Se busca el personaje y se muestra con su avatar
            int firstChar = IdChar1;
            Character opponent1 = firstParty.stream().
                    filter(x -> x.getId() == firstChar).
                    findFirst().get();
            System.out.println(opponent1.printAvatar()); // Se añade el print avatar cuando selecciona el personaje


            selectOk = false;
            do {
                System.out.println("Select second opponent by ID: ");
                for (Character ch : secondParty) {
                    System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
                }

                int char2 = Input.getInputNumber(1, originalSecondParty.size());
                for (Character ch : secondParty) {
                    if (ch.getId() == char2) {
                        IdChar2 = ch.getId();
                        selectOk = true;
                    }
                }
                if (!selectOk) {
                    System.err.println("Wrong ID!!!");
                }
            } while (!selectOk);

            int secondChar = IdChar2;
            Character opponent2 = secondParty.stream().
                    filter(x -> x.getId() == secondChar).
                    findFirst().get();
            System.out.println(opponent2.printAvatar()); // Se añade el print avatar cuando selecciona el personaje

            //The two opponents get in combat until one or both are dead
            combat(opponent1, opponent2);

            //This continues until one or both parties have no more characters.
        }
    }

    public static void automaticBattle(List<Character> firstParty, List<Character> secondParty) {
        while (firstParty.size() > 0 && secondParty.size() > 0) {
            for (Character ch : firstParty) {
                System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
            }
            int char1 = RandomGenerator.randomNumber(0, firstParty.size() - 1);
            System.out.println("Character chosen from first party: " + firstParty.get(char1).getName());
            Character opponent1 = firstParty.get(char1);
            System.out.println(opponent1.printAvatar()); // Se añade el print avatar cuando selecciona el personaje

            for (Character ch : secondParty) {
                System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + getType(ch) + "]");
            }
            int char2 = RandomGenerator.randomNumber(0, secondParty.size() - 1);
            System.out.println("Character chosen from second party: " + secondParty.get(char2).getName());
            Character opponent2 = secondParty.get(char2);
            System.out.println(opponent2.printAvatar()); // Se añade el print avatar cuando selecciona el personaje

            //The two opponents get in combat until one or both are dead
            combat(opponent1, opponent2);
        }
    }

    public static void combat(Character c1, Character c2) {
        while (c1.isAlive() && c2.isAlive()) {
            c1.attack(c2);
            c2.attack(c1);
            System.out.println(c1.getName() + " has: " + c1.getHp() + " hp.");
            System.out.println(c2.getName() + " has: " + c2.getHp() + " hp.\n");
        }
        if (!c1.isAlive() && !c2.isAlive()) {
            System.out.println("Both of them are dead. It's a tie!\n");
            graveyard.add(c1);
            graveyard.add(c2);
            firstParty.remove(c1);
            secondParty.remove(c2);
        } else if (c1.isAlive()) {
            System.out.println(c1.printWinner() + c2.getName() + " is dead. " + c1.getName() + " wins!\n");
            graveyard.add(c2);
            secondParty.remove(c2);

        } else {
            System.out.println(c2.printWinner() + c1.getName() + " is dead. " + c2.getName() + " wins!\n");
            graveyard.add(c1);
            firstParty.remove(c1);
        }
    }

    public static void result() {
        if (firstParty.size() == 0 && secondParty.size() == 0) {
            System.out.println("\nParty crasher!! Everyone's dead. Nobody wins.\n");
        } else if (firstParty.size() == 0) {
            System.out.println("\nParty 2 wins!\n");
        } else {
            System.out.println("\nParty 1 wins!\n");
        }
    }

    public static void graveyard() {
        System.out.println("Rest in peace:\n");
        for (Character i : graveyard) {
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

    //Returns the class name of a Character as a String
    private static String getType(Character character) {
        String type;
        if (character.getClass() == Warrior.class)
            type = "Warrior";
        else
            type = "Wizard";
        return type;
    }

    //Import a party passing as a parameter the name of the CSV file, e.g. "myBestTeam.csv"
    //The file must be located in the "Resources" folder.
    public static ArrayList<Character> importParty(String filename) throws IOException {
        ArrayList<Character> party = new ArrayList<>();
        String filePath = "src/com/ironhack/resources/" + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("File not found, check the spelling");
            return null;
        }
        Scanner sc = new Scanner(file);
        sc.nextLine();
        ArrayList<String[]> characterValues = new ArrayList<>();
        while (sc.hasNextLine()) {
            characterValues.add(sc.nextLine().split(","));
        }
        for (String[] characterValue : characterValues) {
            if (characterValue[0].equalsIgnoreCase("warrior")) {
                Warrior warrior = new Warrior(
                        Integer.parseInt(characterValue[1].trim()),
                        characterValue[2],
                        Integer.parseInt(characterValue[3].trim()),
                        Integer.parseInt(characterValue[4].trim()),
                        Integer.parseInt(characterValue[5].trim())
                );
                party.add(warrior);
            } else {
                Wizard wizard = new Wizard(
                        Integer.parseInt(characterValue[1].trim()),
                        characterValue[2],
                        Integer.parseInt(characterValue[3].trim()),
                        Integer.parseInt(characterValue[4].trim()),
                        Integer.parseInt(characterValue[5].trim())
                );
                party.add(wizard);
            }
        }
        sc.close();
        return party;
    }

    //Export the party to an importable CSV file. The file gets stored in the "Resources" folder
    public static void savePartyToFile() {
        ArrayList<Character> winners = firstParty.size() == 0 ? originalSecondParty : originalFirstParty;

        String fileName = Input.getFileName();
        String filePath = "src/com/ironhack/resources/" + fileName + ".csv";
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("\"Type\", \"id\", \"Name\", \"HP\", \"Stamina/Mana\", \"Strength/Intelligence\"\n");
            for (int i = 0; i < winners.size(); i++) {
                if (getType(winners.get(i)).equals("Warrior")) {
                    Warrior winner = (Warrior) winners.get(i);
                    fw.write("\"" + getType(winners.get(i)) + "\"" + ", "
                            + winner.getId() + ", "
                            + winner.getName() + ", "
                            + winner.getHp() + ", "
                            + winner.getStamina() + ", "
                            + winner.getStrength() + "\n");
                } else {
                    Wizard winner = (Wizard) winners.get(i);
                    fw.write("\"" + getType(winners.get(i)) + "\"" + ", "
                            + winner.getId() + ", "
                            + winner.getName() + ", "
                            + winner.getHp() + ", "
                            + winner.getMana() + ", "
                            + winner.getIntelligence() + "\n");
                }
            }
            fw.close();
            System.out.println("Team saved in 'Resources' folder as '" + fileName + ".csv'");
        } catch (IOException e) {
            System.err.println("Sorry, couldn't create the file. Contact Admin.");
        }
    }
}
