package com.ironhack.main;

import com.ironhack.classes.Character;

import java.util.ArrayList;

public class Init {
	public static ArrayList<Character> firstParty;
	public static ArrayList<Character> originalFirstParty;
	public static ArrayList<Character> secondParty;
	public static ArrayList<Character> originalSecondParty;
	public static ArrayList<Character> graveyard = new ArrayList<>();

	public static void clear() {
		firstParty.clear();
		originalFirstParty.clear();
		secondParty.clear();
		originalSecondParty.clear();
		graveyard.clear();
	}

	public static void graveyard() {
		System.out.println("Rest in peace:\n");
		for (Character i : Init.graveyard) {
			System.out.println(i.getName() + ".");
		}
	}

	public static ArrayList<Character> getFirstParty() {
		return firstParty;
	}

	public static ArrayList<Character> getOriginalFirstParty() {
		return originalFirstParty;
	}

	public static ArrayList<Character> getSecondParty() {
		return secondParty;
	}

	public static ArrayList<Character> getOriginalSecondParty() {
		return originalSecondParty;
	}

	public static ArrayList<Character> getGraveyard() {
		return graveyard;
	}

	public static void setFirstParty(ArrayList<Character> firstParty) {
		Init.firstParty = firstParty;
	}

	public static void setOriginalFirstParty(ArrayList<Character> originalFirstParty) {
		Init.originalFirstParty = originalFirstParty;
	}

	public static void setSecondParty(ArrayList<Character> secondParty) {
		Init.secondParty = secondParty;
	}

	public static void setOriginalSecondParty(ArrayList<Character> originalSecondParty) {
		Init.originalSecondParty = originalSecondParty;
	}

	public static void setGraveyard(ArrayList<Character> graveyard) {
		Init.graveyard = graveyard;
	}
}
