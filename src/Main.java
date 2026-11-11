
import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        // Menjalankan aplikasi Swing pada Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            // Membuat objek CharacterManager
            CharacterManager manager = new CharacterManager();

            // Menambahkan 5 karakter awal
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
                new Character("Sinta", CharacterType.HEALER, 2)
                );
            // Penanda agar karakter tambahan hanya dimasukkan satu kali
            final boolean[] sudahDitambahkan = {false};

            // Referensi jendela aplikasi
            final MainWindow[] windowRef = new MainWindow[1];

            // Membuat MainWindow
            windowRef[0] = new MainWindow(manager, event -> {

                // Menambahkan 2 karakter ketika tombol ditekan
                if (!sudahDitambahkan[0]) {

                    manager.addCharacter(
                            new Character("Raphael", CharacterType.HEALER, 8));

                    manager.addCharacter(
                            new Character("Diana", CharacterType.ARCHER, 9));

                    sudahDitambahkan[0] = true;
                }

                // Memperbarui tampilan daftar karakter dan rata-rata level
                windowRef[0].refreshCharacterList();
            });

            // Menampilkan jendela aplikasi
            windowRef[0].setVisible(true);
        });
    }
}
