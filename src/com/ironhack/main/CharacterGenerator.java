package com.ironhack.main;

import com.ironhack.classes.Character;
import com.ironhack.classes.Warrior;
import com.ironhack.classes.Wizard;

public class CharacterGenerator {

    public CharacterGenerator() {
    }

    public static Character[] generateGroup(int quantity, int index) {
        Character[] characters = new Character[quantity];
        for (int i = 0; i < quantity; i++) {
            characters[i] = generateRandom(characters, i+index);
        }
        return characters;
    }

    public static Character generateRandom(Character[] personajesActuales, int id) {

        String[] nameRandom = {"Legolas", "Darth Vader", "Mark Zuckerberg", "Harry Potter", "Voldemort", "Víctor Cardozo", "Arnoldo Sicilia", "Xabier García", "Steve Jobs", "Thanos", "Donald Trump", "Spider-Man", "Varian Wrynn", "Sylvanas", "Putin", "Kim Jong-un"};
        Character character;

        if (randomNumber(0, 1) == 0) {
            character = new Warrior(id, nameRandom[randomNumber(0, 15)], randomNumber(100, 200), randomNumber(10, 50), randomNumber(1, 10));
        } else {
            character = new Wizard(id, nameRandom[randomNumber(0, 15)], randomNumber(50, 100), randomNumber(10, 50), randomNumber(1, 50));
        }

        for (int i = 0; i < personajesActuales.length; i++) {
            if (character.getName().equals(personajesActuales[i] == null ? null : personajesActuales[i].getName())) {
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
