
import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CharacterManager manager = new CharacterManager();

            // 8 karakter awal dengan variasi role dan level
            manager.addCharacter(
                    new Character("Arjuna", CharacterType.WARRIOR, 5));

            manager.addCharacter(
                    new Character("Merlin", CharacterType.MAGE, 3));

            manager.addCharacter(
                    new Character("Athena", CharacterType.ARCHER, 4));

            manager.addCharacter(
                    new Character("Bima", CharacterType.WARRIOR, 7));

            manager.addCharacter(
                    new Character("Luna", CharacterType.MAGE, 6));

            manager.addCharacter(
                    new Character("Sinta", CharacterType.HEALER, 2));

            manager.addCharacter(
                    new Character("Raphael", CharacterType.HEALER, 8));

            manager.addCharacter(
                    new Character("Diana", CharacterType.ARCHER, 9));

            // Tombol hanya menambahkan karakter satu kali
            final boolean[] sudahDitambahkan = {false};

            final MainWindow[] windowRef = new MainWindow[1];

            windowRef[0] = new MainWindow(manager, event -> {

                if (!sudahDitambahkan[0]) {

                    manager.addCharacter(
                            new Character("Kirana", CharacterType.WARRIOR, 10));

                    manager.addCharacter(
                            new Character("Orion", CharacterType.MAGE, 4));

                    sudahDitambahkan[0] = true;
                }

                windowRef[0].refreshCharacterList();
            });

            windowRef[0].setVisible(true);
        });
    }
}
