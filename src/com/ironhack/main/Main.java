package com.ironhack.main;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static final int MAX_NUM_OF_FIGHTERS = 10;
    static ArrayList<Character> firstParty;
    static ArrayList<Character> secondParty;
    static ArrayList<Character> graveyard = new ArrayList<>();
    static ArrayList<Character> auxParty = new ArrayList<>();

    public static void main(String[] args) {
        do {
            //se vacían las listas para cada partida
            if(graveyard.size()>0){
                firstParty.clear();
                secondParty.clear();
                graveyard.clear();
                auxParty.clear();
            }

            Scanner scanner = new Scanner(System.in);
            int gameMode = 3;
            boolean checkOption = false;
            while (!checkOption) {
                System.out.println("What do you want to do? Insert the number to choose an option.\n" +
                        "1: Create your own parties.\n" + "2: Simulate a battle with random parties.\n" + "3: Exit the game.\n");
                gameMode = scanner.nextInt();
                if (gameMode > 0 && gameMode < 4) checkOption = true;
                else {
                    System.err.println("You have to pick a valid number.");
                }
            }

            if (gameMode == 3) {
                System.out.println("Thanks for playing..See you soon! :)");
                System.exit(1);
            } else if (gameMode == 1) {
                firstParty = new ArrayList<>(createParty(auxParty));
                auxParty.clear();
                secondParty = new ArrayList<>(createParty(auxParty));

            } else {
                // Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
                System.out.println("How many characters will be fighting for each party?\n");
                Scanner sc = new Scanner(System.in);
                int numFighters = scanner.nextInt();
                firstParty = new ArrayList<>(generateGroup(numFighters));
                secondParty = new ArrayList<>(generateGroup(numFighters));

            }

            System.out.println("Parties created. Starting battle!");
            battle(firstParty, secondParty);

            System.out.println("Battle has ended!");
            result();
            scanner.nextLine();

            System.out.println("Wanna see the graveyard? Type Y/N");
            String graveyardOp = scanner.nextLine();
            if (graveyardOp.equalsIgnoreCase("y")) {
                graveyard();
            }
            System.out.println("\n Wanna play again? Y/N");
            String playAgain = scanner.nextLine();
            if (playAgain.equalsIgnoreCase("n")) {
                System.exit(1);
            }
            //scanner.close();
        } while (true);
    }

    public static void battle(List<Character> firstParty, List<Character> secondParty) {
        Scanner sc = new Scanner(System.in);
        while (firstParty.size() > 0 && secondParty.size() > 0) {
            int IdChar1 = 0;
            int IdChar2 = 0;
            boolean selectOk = false;

            //The user can pick one opponent of each party using the IDs
            do {
                System.out.println("Select first opponent by ID: ");
                for (Character ch : firstParty) {
                    System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + ch.getClass().getName() + "]");
                }

                int char1 = sc.nextInt();
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
                    System.out.println("ID: " + ch.getId() + " - " + ch.getName() + "  [" + ch.getClass().getName() + "]");
                }

                int char2 = sc.nextInt();
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
        //sc.close();
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

    public static ArrayList<Character> createParty(ArrayList<Character> party) {

        // Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
        System.out.println("How many characters will be fighting for this party?");
        Scanner scanner = new Scanner(System.in);
        int numFighters = scanner.nextInt();
        while (numFighters < 1) {
            System.err.println("You can't create an empty party! Please try again.");
            numFighters = scanner.nextInt();
            if (numFighters > MAX_NUM_OF_FIGHTERS) {
                System.err.println("A party can't have more than " + MAX_NUM_OF_FIGHTERS + " fighters. Please try again.");
                numFighters = 0;
            }
        }

        //Aquí empieza el proceso de creación de los personajes. Primero pedimos si se quiere crear el personaje
        //manualmente, o bien obtener uno generado por el sistema. Ésto dentro de un bucle hasta llegar a la capacidad
        //máxima del grupo.
        int hp, stamina, strength, mana, intelligence, creationMenu;

        for (int i = 0; i < numFighters; i++) {
            System.out.println("CHARACTER CREATION MENU\n" + "Do you want to create the next character or get a random one?\n" +
                    "1: Create my character.\n" +
                    "2: Random character.\n" +
                    "Party members: (" + (i + 1) + "/" + numFighters + ")");
            creationMenu = scanner.nextInt();
            while (creationMenu != 1 && creationMenu != 2) {
                System.err.println("Choose a valid option, please.");
                creationMenu = scanner.nextInt();
            }
            String fighterName;
            String[] fighterType = {"Warrior", "Wizard"};
            if (creationMenu == 1) {

                // Preguntamos si va a ser Warrior o Wizard
                // Reutilizamos la variable creationMenu para incluir el tipo de personaje.
                System.out.println("Will it be a warrior or a wizard?\n" + "1: Warrior\n" + "2: Wizard\n");
                creationMenu = scanner.nextInt();
                while (creationMenu != 1 && creationMenu != 2) {
                    System.err.println("Choose either Warrior or Wizard, please.");
                    System.out.println("1: Warrior\n" + "2: Wizard\n");
                    creationMenu = scanner.nextInt();
                }
                scanner.nextLine();

                // Determinamos el nombre del personaje
                do {
                    System.out.println("Write the name for your " + fighterType[creationMenu - 1] + ".");
                    fighterName = scanner.nextLine();
                    if (fighterName.length() < 4 || fighterName.length() > 40)
                        System.err.println("Characters' name must be between 4 and 40 characters long.");
                } while (fighterName.length() < 4 || fighterName.length() > 40);

                // Establecemos la vida

                System.out.println("Set the health points of your " + fighterType[creationMenu - 1] + ".");
                hp = checkHP(fighterType[creationMenu - 1], scanner.nextInt());


                // Ahora, según si es Warrior o Wizards, customizamos el resto de stats
                if (creationMenu == 1) {
                    System.out.println("Set the stamina of your Warrior");
                    stamina = checkStamina(scanner.nextInt());
                    System.out.println("Set the strength of your Warrior");
                    strength = checkStrength(scanner.nextInt());

                    // Añadimos todos los stats al personaje
                    party.add(new Warrior(i + 1, fighterName, hp, stamina, strength));
                    System.out.println("Warrior created!");
                } else {
                    System.out.println("Set the mana of your Wizard");
                    mana = checkMana(scanner.nextInt());
                    System.out.println("Set the intelligence of your Wizard");
                    intelligence = checkIntelligence(scanner.nextInt());
                    party.add(new Wizard(i + 1, fighterName, hp, mana, intelligence));
                    System.out.println("Wizard created!");
                }
            } else {
                // PERSONAJES GENERADOS ALEATORIAMENTE. IMPLEMENTAR EL GENERADOR DE CAROLINA.
                party.add(generateRandomCharacter(party));
            }
        }

        return party;
    }

    public static int checkHP(String fighterType, int hp) {
        Scanner scanner = new Scanner(System.in);
        if (fighterType.equals("Warrior")) {
            while (hp < 100 || hp > 200) {
                System.err.println("The Warriors' HP must be between 100 and 200. Set it again, please.");
                hp = scanner.nextInt();
            }
        } else {
            while (hp < 50 || hp > 100) {
                System.err.println("The Wizards' HP must be between 50 and 100. Set it again, please.");
                hp = scanner.nextInt();
            }
        }
        return hp;
    }

    public static int checkStamina(int stamina) {
        Scanner scanner = new Scanner(System.in);
        while (stamina < 10 || stamina > 50) {
            System.err.println("Stamina must be between 10 and 50. Set it again, please.");
            stamina = scanner.nextInt();
        }
        return stamina;
    }

    public static int checkStrength(int strength) {
        Scanner scanner = new Scanner(System.in);
        while (strength < 1 || strength > 10) {
            System.err.println("Strength must be between 1 and 10. Set it again, please.");
            strength = scanner.nextInt();
        }
        return strength;
    }

    public static int checkMana(int mana) {
        Scanner scanner = new Scanner(System.in);
        while (mana < 10 || mana > 50) {
            System.err.println("Mana must be between 10 and 50. Set it again, please.");
            mana = scanner.nextInt();
        }
        return mana;
    }

    public static int checkIntelligence(int intel) {
        Scanner scanner = new Scanner(System.in);
        while (intel < 1 || intel > 50) {
            System.err.println("Intelligence must be between 1 and 10. Set it again, please.");
            intel = scanner.nextInt();
        }
        return intel;
    }

    public static List<Character> generateGroup(int quantity) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            characters.set(i, generateRandomCharacter(characters));
        }
        return characters;
    }

    public static Character generateRandomCharacter(List<Character> personajesActuales) {

        String[] nameRandom = {"Legolas", "Darth Vader", "Mark Zuckerberg", "Harry Potter", "Voldemort", "Víctor Cardozo", "Arnoldo Sicilia", "Xabier García", "Steve Jobs", "Thanos", "Donald Trump", "Spider-Man", "Varian Wrynn", "Sylvanas", "Putin", "Kim Jong-un"};
        Character character;

        if (randomNumber(0, 1) == 0) {
            character = new Warrior(randomNumber(0, 1000), nameRandom[randomNumber(0, 15)], randomNumber(100, 200), randomNumber(10, 50), randomNumber(1, 10));
        } else {
            character = new Wizard(randomNumber(0, 1000), nameRandom[randomNumber(0, 15)], randomNumber(50, 100), randomNumber(10, 50), randomNumber(1, 50));
        }

        for (int i = 0; i < personajesActuales.size(); i++) {
            if (character.getName().equals(personajesActuales.get(i) == null ? null : personajesActuales.get(i).getName())) {
                character.setName(character.getName() + " Jr");
                break;
            }
        }
        return character;
    }

    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1));
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