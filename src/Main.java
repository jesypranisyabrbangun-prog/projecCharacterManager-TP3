
import java.util.ArrayList;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("       CHARACTER MANAGER TP5");
        System.out.println("==================================");

        // =================================
        // 1. MENAMBAHKAN KARAKTER
        // =================================

        CharacterManager manager = new CharacterManager();

        System.out.println("\n=== 1. ADD CHARACTER ===");

        Character arjuna = new Character(
                "Arjuna", CharacterType.WARRIOR, 5);

        Character merlin = new Character(
                "Merlin", CharacterType.MAGE, 3);

        Character athena = new Character(
                "Athena", CharacterType.ARCHER, 4);

        Character bima = new Character(
                "Bima", CharacterType.WARRIOR, 7);

        manager.addCharacter(arjuna);
        manager.addCharacter(merlin);
        manager.addCharacter(athena);
        manager.addCharacter(bima);

        // =================================
        // 2. MENOLAK NAMA DUPLIKAT
        // =================================

        System.out.println("\n=== 2. DUPLICATE CHECK ===");

        Character duplicate = new Character(
                "Arjuna", CharacterType.MAGE, 8);

        boolean duplicateResult = manager.addCharacter(duplicate);

        System.out.println("Duplicate accepted: " + duplicateResult);

        // =================================
        // 3. MENAMPILKAN SEMUA KARAKTER
        // =================================

        System.out.println("\n=== 3. ALL CHARACTERS ===");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        System.out.println("Total characters: "
                + manager.getTotalCharacters());

        // =================================
        // 4. MENCARI KARAKTER
        // =================================

        System.out.println("\n=== 4. FIND CHARACTER ===");

        Character found = manager.findByName("Merlin");

        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Character not found.");
        }

        // =================================
        // 5. MENGHAPUS KARAKTER
        // =================================

        System.out.println("\n=== 5. REMOVE CHARACTER ===");

        boolean removed = manager.removeCharacter("Athena");

        System.out.println("Athena removed: " + removed);
        System.out.println("Total after removal: "
                + manager.getTotalCharacters());

        // =================================
        // 6. AVERAGE LEVEL
        // =================================

        System.out.println("\n=== 6. AVERAGE LEVEL ===");

        System.out.println("Average level: "
                + manager.getAverageLevel());

        CharacterManager emptyManager = new CharacterManager();
         System.out.println("Average level (empty list): "
        + emptyManager.getAverageLevel());

        // =================================
        // 7. FIND BY TYPE
        // =================================

        System.out.println("\n=== 7. FIND BY TYPE ===");

        ArrayList<Character> warriors =
                manager.findByType(CharacterType.WARRIOR);

        for (Character warrior : warriors) {
            System.out.println(warrior);
        }

        // =================================
        // 8. EKSPERIMEN GET ALL CHARACTERS
        // =================================

        System.out.println("\n=== 8. ARRAYLIST COPY EXPERIMENT ===");

        ArrayList<Character> copiedList =
                manager.getAllCharacters();

        copiedList.clear();

        System.out.println("Copied list size: " + copiedList.size());

        System.out.println("Original manager size: "
                + manager.getTotalCharacters());

        // =================================
        // 9. EKSPERIMEN OBJECT REFERENCE
        // =================================

        System.out.println("\n=== 9. OBJECT REFERENCE EXPERIMENT ===");

        Character a = new Character(
                "Gandalf", CharacterType.MAGE, 5);

        Character b = a;

        b.setLevel(8);

        System.out.println("Level of a: " + a.getLevel());
        System.out.println("a == b: " + (a == b));

        // Membuat object baru yang independen
        Character independent = new Character(
                a.getName(), a.getType(), a.getLevel());

        independent.setLevel(10);

        System.out.println("Level of a: " + a.getLevel());
        System.out.println("Level of independent: "
                + independent.getLevel());

        // =================================
        // 10. FIND STRONGEST
        // =================================

        System.out.println("\n=== 10. STRONGEST CHARACTER ===");

        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println("Strongest character: " + strongest);
        }

        // =================================
        // 11. EQUALS EXPERIMENT
        // =================================

        System.out.println("\n=== 11. EQUALS EXPERIMENT ===");

        Character first = new Character(
                "Arjuna", CharacterType.WARRIOR, 5);

        Character second = new Character(
                "ARJUNA", CharacterType.MAGE, 3);

        System.out.println("first.equals(second): "
                + first.equals(second));

        System.out.println("\n=== TP5 TEST FINISHED ===");
        System.out.println("\n=== STATIC COUNTER ===");

        System.out.println("Total objects created: "
        + Character.getTotalCharacterCreated());
    }
}
