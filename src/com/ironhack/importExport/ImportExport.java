package com.ironhack.importExport;

import com.ironhack.battle.Battle;
import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;
import com.ironhack.input.Input;
import com.ironhack.main.Init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportExport {
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
		ArrayList<Character> winners = Init.firstParty.size() == 0 ? Init.originalSecondParty : Init.originalFirstParty;

		String fileName = Input.getFileName();
		String filePath = "src/com/ironhack/resources/" + fileName + ".csv";
		try {
			FileWriter fw = new FileWriter(filePath, false);
			fw.write("\"Type\", \"id\", \"Name\", \"HP\", \"Stamina/Mana\", \"Strength/Intelligence\"\n");
			for (int i = 0; i < winners.size(); i++) {
				if (Battle.getType(winners.get(i)).equals("Warrior")) {
					Warrior winner = (Warrior) winners.get(i);
					fw.write("\"" + Battle.getType(winners.get(i)) + "\"" + ", "
							+ winner.getId() + ", "
							+ winner.getName() + ", "
							+ winner.getHp() + ", "
							+ winner.getStamina() + ", "
							+ winner.getStrength() + "\n");
				} else {
					Wizard winner = (Wizard) winners.get(i);
					fw.write("\"" + Battle.getType(winners.get(i)) + "\"" + ", "
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
