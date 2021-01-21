package com.ironhack.styles;

// The following method prints a header at the beginning of the game.
// A fopr loop makes star lines on top and bottom of the game name.
public class Start {
	public static void printStart() {
		int titleLength = 32;
		System.out.println(ConsoleColors.YELLOW_BOLD);
		for (int i = 0; i < titleLength; i++) {
			System.out.print("*");
		}
		System.out.println("\n\n\tWIZARDS VERSUS WARRIORS\n");
		for (int i = 0; i < titleLength; i++) {
			System.out.print("*");
		}
		System.out.println(ConsoleColors.WHITE_BOLD);
	}
}
