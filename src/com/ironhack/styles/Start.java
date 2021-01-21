package com.ironhack.styles;

public class Start {
	public static void printStart(){
		int titleLength = 32;

		System.out.println(ConsoleColors.YELLOW_BOLD);
		for(int i=0;i<titleLength;i++){
			System.out.print("*");
		}
		System.out.println("\n\n\tWIZARDS VERSUS WARRIORS\n");
		for(int i=0;i<titleLength;i++){
			System.out.print("*");
		}
		System.out.println(ConsoleColors.WHITE_BOLD);
	}
}
