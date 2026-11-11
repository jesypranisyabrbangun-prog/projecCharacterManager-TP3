
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

public class MainWindow extends JFrame {

    private static final int HIGH_LEVEL_THRESHOLD = 5;

    private final CharacterManager manager;
    private JPanel characterPanel;
    private JLabel statisticsLabel;
    private JButton addButton;

    public MainWindow(CharacterManager manager, ActionListener addAction) {

        this.manager = manager;

        setTitle("Character Manager - TP10 v0.4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(AppTheme.WINDOW_BORDER);

        // Judul aplikasi
        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.PRIMARY);
        titleLabel.setBorder(AppTheme.TITLE_BORDER);

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel daftar karakter
        characterPanel = new JPanel();
        characterPanel.setLayout(
                new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );
        characterPanel.setBackground(AppTheme.BACKGROUND);
        characterPanel.setOpaque(true);
        characterPanel.setBorder(AppTheme.PANEL_BORDER);

        JScrollPane scrollPane = new JScrollPane(characterPanel);
        scrollPane.setBorder(null);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel bagian bawah
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 10));
        bottomPanel.setBackground(AppTheme.BACKGROUND);
        bottomPanel.setOpaque(true);

        statisticsLabel = new JLabel(
                "Rata-rata Level: 0",
                SwingConstants.CENTER
        );
        statisticsLabel.setFont(AppTheme.STATISTICS_FONT);
        statisticsLabel.setForeground(AppTheme.STATISTICS);

        addButton = new JButton("Tambah 2 Karakter");
        addButton.setFont(AppTheme.BOLD_FONT);
        addButton.addActionListener(addAction);

        bottomPanel.add(statisticsLabel, BorderLayout.NORTH);
        bottomPanel.add(addButton, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        refreshCharacterList();

        pack();
        setLocationRelativeTo(null);
    }

    public void refreshCharacterList() {

        characterPanel.removeAll();

        for (Character character : manager.getAllCharacters()) {

            // Kartu karakter berdasarkan role
            JPanel card = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 12, 10)
            );

            card.setBackground(
                    AppTheme.colorFor(character.getType())
            );
            card.setOpaque(true);
            card.setBorder(
                    AppTheme.borderFor(character.getType())
            );

            // Informasi karakter
            JLabel characterLabel = new JLabel(
                    character.getName()
                    + " | " + character.getType()
                    + " | HP " + character.getHitPoints()
                    + " | ATK " + character.getAttackPower()
            );

            characterLabel.setFont(AppTheme.NORMAL_FONT);
            characterLabel.setForeground(AppTheme.SURFACE);

            // Level tinggi berwarna emas dan tebal
            JLabel levelLabel = new JLabel(
                    "LEVEL " + character.getLevel()
            );

            if (character.getLevel() >= HIGH_LEVEL_THRESHOLD) {
                levelLabel.setFont(AppTheme.BOLD_FONT);
                levelLabel.setForeground(AppTheme.GOLD);
            } else {
                levelLabel.setFont(AppTheme.NORMAL_FONT);
                levelLabel.setForeground(AppTheme.SURFACE);
            }

            // Properti tambahan: tooltip
            String tooltip =
                    "Nama: " + character.getName()
                    + " | Role: " + character.getType()
                    + " | HP: " + character.getHitPoints()
                    + " | ATK: " + character.getAttackPower()
                    + " | Level: " + character.getLevel();

            card.setToolTipText(tooltip);
            characterLabel.setToolTipText(tooltip);
            levelLabel.setToolTipText(tooltip);

            card.add(characterLabel);
            card.add(levelLabel);

            card.setAlignmentX(LEFT_ALIGNMENT);
            card.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 65)
            );

            characterPanel.add(card);
            characterPanel.add(new JLabel(" "));
        }

        // Statistik karakter
        statisticsLabel.setText(
                String.format(
                        "Total Karakter: %d | Rata-rata Level: %.2f",
                        manager.getTotalCharacters(),
                        manager.getAverageLevel()
                )
        );

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}
