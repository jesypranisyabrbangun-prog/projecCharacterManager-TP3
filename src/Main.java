import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.1 ===");

        // Membuat 3 object Character menggunakan constructor pertama
        Character hero = new Character("Arjuna", CharacterType.WARRIOR, 5);
        Character wizard = new Character("Merlin", CharacterType.MAGE, 3);
        Character scout = new Character("Athena", CharacterType.ARCHER, 4);

        // Menampilkan ketiga karakter menggunakan toString()
        System.out.println(hero);
        System.out.println(wizard);
        System.out.println(scout);

        System.out.println();
        System.out.println(">> " + hero.getName() + " level up!");
        hero.setLevel(hero.getLevel() + 1);
        System.out.println("After level up: " + hero);

        System.out.println();
        System.out.println(">> Nama karakter: " + hero.getName());
        System.out.println(">> Jenis karakter: " + hero.getType());
        System.out.println(">> HP saat ini: " + hero.getHitPoints());

        // ===== BAGIAN B: MINI-CHALLENGE =====
        System.out.println();
        System.out.println("=== Mini-Challenge: Mana & Status ===");

        // Membuat satu karakter baru
        Character newCharacter = new Character(
            "Gandalf",
            CharacterType.MAGE,
            5
        );

        // Mengatur mana karakter
        newCharacter.setMana(100);

        // Menampilkan nilai mana
        System.out.println("Mana: " + newCharacter.getMana());

        // Memeriksa apakah karakter masih hidup
        System.out.println("Is alive: " + newCharacter.isAlive());

        // Menampilkan informasi lengkap karakter, termasuk mana
        System.out.println(newCharacter);
    }
}