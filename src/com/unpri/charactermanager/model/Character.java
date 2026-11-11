
package com.unpri.charactermanager.model;

import java.util.Locale;
import java.util.Objects;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menggunakan Encapsulation dan Object Reference.
 */
public class Character {

    // ===== ATRIBUT =====
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // Menghitung jumlah object Character yang dibuat
    private static int totalCharacterCreated = 0;

    // ===== CONSTRUCTOR 1 =====
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;

        setLevel(level);
        this.mana = 0;

        totalCharacterCreated++;
    }

    // ===== CONSTRUCTOR 2 =====
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;

        totalCharacterCreated++;
    }

    // ===== HELPER PRIVATE =====
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // ===== GETTER =====
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    // ===== SETTER =====
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException(
                    "Level minimal adalah 1");
        }

        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                    "Mana tidak boleh negatif");
        }

        this.mana = mana;
    }

    // ===== CEK STATUS HIDUP =====
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== LEVEL UP =====
    public void levelUp() {
        setLevel(this.level + 1);
    }

    // ===== EQUALS =====
    /**
     * Membandingkan dua karakter berdasarkan nama.
     * Tidak membedakan huruf besar dan kecil.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Character)) {
            return false;
        }

        Character other = (Character) obj;

        return name != null
                && other.name != null
                && name.equalsIgnoreCase(other.name);
    }

    // ===== HASHCODE =====
    @Override
    public int hashCode() {
        return Objects.hash(
                name == null
                        ? null
                        : name.toLowerCase(Locale.ROOT));
    }

    // ===== TOSTRING =====
    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hp=" + hitPoints +
                ", atk=" + attackPower +
                ", mana=" + mana +
                '}';
    }
}
