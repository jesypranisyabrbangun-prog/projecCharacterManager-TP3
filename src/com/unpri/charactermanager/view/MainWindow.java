
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

public class MainWindow extends JFrame {

    private CharacterManager manager;
    private JPanel characterPanel;
    private JLabel statisticsLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP14");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Judul aplikasi
        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(35, 55, 90));
        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        // Form input karakter
        CharacterFormPanel formPanel = new CharacterFormPanel(
                manager,
                character -> {
                    if (manager.addCharacter(character)) {
                        refreshCharacterList();
                    }
                }
        );

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Panel daftar karakter
        characterPanel = new JPanel();
        characterPanel.setLayout(
                new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );

        characterPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        JScrollPane scrollPane = new JScrollPane(characterPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Statistik karakter
        statisticsLabel = new JLabel(
                "Rata-rata Level: 0",
                SwingConstants.CENTER
        );

        statisticsLabel.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        statisticsLabel.setForeground(new Color(20, 110, 70));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15)
        );

        bottomPanel.add(statisticsLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshCharacterList();
    }

    // Memperbarui daftar karakter
    public void refreshCharacterList() {

        characterPanel.removeAll();

        for (Character character : manager.getAllCharacters()) {

            JLabel characterLabel = new JLabel(
                    character.getName()
                    + " | " + character.getType()
                    + " | Level " + character.getLevel()
                    + " | ATK " + character.getAttackPower()
            );

            characterLabel.setFont(
                    new Font("Arial", Font.PLAIN, 15)
            );

            characterLabel.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                            BorderFactory.createEmptyBorder(12, 12, 12, 12)
                    )
            );

            characterLabel.setAlignmentX(LEFT_ALIGNMENT);
            characterPanel.add(characterLabel);
        }

        statisticsLabel.setText(
                String.format(
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()
                )
        );

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}
