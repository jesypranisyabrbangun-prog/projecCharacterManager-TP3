
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    // Menyimpan daftar karakter secara private
    private ArrayList<Character> characterList = new ArrayList<>();

    // 1. Menambahkan karakter dan menolak nama duplikat
    public boolean addCharacter(Character character) {
        if (character == null) {
            return false;
        }

        if (findByName(character.getName()) != null) {
            System.out.println("Karakter " + character.getName()
                    + " sudah ada!");
            return false;
        }

        characterList.add(character);
        System.out.println("Karakter " + character.getName()
                + " berhasil ditambahkan.");
        return true;
    }

    // 2. Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        if (name == null) {
            return null;
        }

        for (Character character : characterList) {
            if (character.getName() != null
                    && character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }

        return null;
    }

    // 3. Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character == null) {
            return false;
        }

        characterList.remove(character);
        return true;
    }

    // 4. Menghitung jumlah karakter
    public int getTotalCharacters() {
        return characterList.size();
    }

    // 5. Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // 6. Menghitung rata-rata level
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characterList) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characterList.size();
    }

    // 7. Mencari semua karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Character character : characterList) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    /**
     * Mencari karakter dengan attack power tertinggi.
     * @return karakter terkuat atau null jika daftar kosong.
     */
    public Character findStrongest() {
        if (characterList.isEmpty()) {
            return null;
        }

        Character strongest = characterList.get(0);

        for (Character character : characterList) {
            if (character.getAttackPower()
                    > strongest.getAttackPower()) {
                strongest = character;
            }
        }

        return strongest;
    }
}

