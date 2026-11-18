
import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat pengelola karakter
            CharacterManager manager = new CharacterManager();

            // Menambahkan karakter awal
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

            // Membuat dan menampilkan jendela aplikasi
            MainWindow window = new MainWindow(manager);

            window.setVisible(true);
        });
    }
}
