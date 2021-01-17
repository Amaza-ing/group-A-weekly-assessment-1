package com.ironhack.main;

import com.ironhack.characters.Warrior;
import com.ironhack.characters.Wizard;
import com.ironhack.characters.Character;

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
            firstParty = new ArrayList<Character>(generateGroup(numFighters));
            secondParty = new ArrayList<>(generateGroup(numFighters));
        }

    }

    //Agregado los print de Juan
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
			firstParty.remove(c1);
			secondParty.remove(c2);
		}
		else if (c1.isAlive()) {
			System.out.println(c2.getName() + " is dead. " + c1.getName() + " wins!");
			graveyard.add(c2);
			secondParty.remove(c2);

        } else {
            System.out.println(c1.getName() + " is dead. " + c2.getName() + " wins!");
            graveyard.add(c1);
            firstParty.remove(c1);
        }
    }

    public static void result() {
        if (firstParty.size() == 0 && secondParty.size() == 0) {
            System.out.println("\nParty crasher!! Everyone's dead. Nobody wins.");
        } else if (firstParty.size() == 0) {
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


	public static ArrayList<Character> createParty(ArrayList<Character> party){

		// Primero determinamos cuantos personajes tendrá la facción, siempre inferior a 10.
		System.out.println("How many characters will be fighting for this party?\n");
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
            while (creationMenu != 1 || creationMenu != 2) {
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
				while (creationMenu != 1 || creationMenu != 2) {
					System.err.println("Choose either Warrior or Wizard, please.");
					System.out.println("1: Warrior\n" + "2: Wizard\n");
					creationMenu = scanner.nextInt();
				}

				// Determinamos el nombre del personaje
				do {
					System.out.println("Write the name of your " + fighterType[creationMenu] + ".");
					fighterName = scanner.next();
					if (fighterName.length() > 40) System.err.println("Characters' name must be less than 40 characters long.");
				} while (fighterName.length() > 40);

				// Establecemos la vida

				System.out.println("Set the health points of your " + fighterType[creationMenu] + ".");
				hp = checkHP(fighterType[creationMenu], scanner.nextInt());


				// Ahora, según si es Warrior o Wizards, customizamos el resto de stats
				if (creationMenu == 1) {
					System.out.println("Set the stamina of your Warrior");
					stamina = checkStamina(scanner.nextInt());
					System.out.println("Set the strength of your Warrior");
					strength = checkStrength(scanner.nextInt());

					// Añadimos todos los stats al personaje
					party.add(new Warrior(i+1, fighterName, hp, stamina, strength));
				} else {
					System.out.println("Set the mana of your Wizard");
					mana = checkMana(scanner.nextInt());
					System.out.println("Set the intelligence of your Wizard");
					intelligence = checkIntelligence(scanner.nextInt());
					party.add(new Wizard(i+1, fighterName, hp, mana, intelligence));
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
}