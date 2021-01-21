package com.ironhack.input;

import java.util.Scanner;

public class Input {

	//Gets a number within the limits passed as parameters.
	public static int getInputNumber(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		String	str = "";
		int		result = 0;

		while (str.length() < 1) {
			System.out.println("Type a number");
			str = scanner.nextLine();
			try {
				result = Integer.parseInt(str);
				if (result < min || result > max) {
					System.out.println("Number must be between " + min + " and " + max + ".");
					str = "";
				}
			}
			catch (NumberFormatException e) {
				System.out.println("You must type a number.");
				str = "";
			}
			catch (Exception e) {
				System.out.println("Something was wrong.");
				str = "";
			}
		}
		return result;
	}

	//Gets a proper name for a the fighter with hard-coded boundaries
	public static String getFighterName(String fighterType) {
		Scanner scanner = new Scanner(System.in);
		String fighterName;

		do {
			System.out.println("Write the name for your " + fighterType + ".");
			fighterName = scanner.nextLine();
			if (fighterName.length() < 4 || fighterName.length() > 40)
				System.err.println("Characters' name must be between 4 and 40 characters long.");
		} while (fighterName.length() < 4 || fighterName.length() > 40);
		return fighterName;
	}

	//Gets a proper name for a file with hard-coded boundaries
	public static String getFileName() {
		Scanner scanner = new Scanner(System.in);
		String fileName;

		do {
			System.out.println("Write the name for your team.");
			fileName = scanner.nextLine();
			if (fileName.length() < 4 || fileName.length() > 15)
				System.err.println("Files names must be between 4 and 15 characters long.");
		} while (fileName.length() < 4 || fileName.length() > 15);
		return fileName.trim();
	}
}
