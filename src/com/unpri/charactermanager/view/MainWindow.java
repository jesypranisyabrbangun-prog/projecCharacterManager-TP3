
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private static final int HIGH_LEVEL_THRESHOLD = 5;

    private final CharacterManager manager;

    private JPanel characterPanel;
    private JPanel legendPanel;

    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel highLevelLabel;
    private JLabel roleLabel;
    private JLabel statusLabel;

    public MainWindow(CharacterManager manager, ActionListener addAction) {

        this.manager = manager;

        setTitle("Character Manager - TP12 v0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(950, 620));

        // Panel utama dengan empat wilayah BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(AppTheme.WINDOW_BORDER);

        // =========================
        // NORTH: HEADER DAN STATISTIK
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout(5, 10));
        headerPanel.setBackground(AppTheme.BACKGROUND);
        headerPanel.setOpaque(true);

        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER DASHBOARD",
                SwingConstants.CENTER
        );
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.PRIMARY);
        titleLabel.setBorder(AppTheme.TITLE_BORDER);

        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel tambahan menggunakan GridLayout 2 x 2
        JPanel statisticsPanel = new JPanel(new GridLayout(2, 2, 10, 8));
        statisticsPanel.setBackground(AppTheme.BACKGROUND);
        statisticsPanel.setOpaque(true);

        totalLabel = createStatisticLabel();
        averageLabel = createStatisticLabel();
        highLevelLabel = createStatisticLabel();
        roleLabel = createStatisticLabel();

        statisticsPanel.add(totalLabel);
        statisticsPanel.add(averageLabel);
        statisticsPanel.add(highLevelLabel);
        statisticsPanel.add(roleLabel);

        headerPanel.add(statisticsPanel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =========================
        // CENTER: DAFTAR KARAKTER
        // =========================

        characterPanel = new JPanel();
        characterPanel.setLayout(
                new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );
        characterPanel.setBackground(AppTheme.BACKGROUND);
        characterPanel.setOpaque(true);
        characterPanel.setBorder(AppTheme.PANEL_BORDER);

        // Panel pembungkus agar daftar berada di bagian NORTH
        JPanel characterWrapper = new JPanel(new BorderLayout());
        characterWrapper.setBackground(AppTheme.BACKGROUND);
        characterWrapper.setOpaque(true);
        characterWrapper.add(characterPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(characterWrapper);
        scrollPane.setPreferredSize(new Dimension(580, 350));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =========================
        // EAST: LEGENDA ROLE
        // =========================

        legendPanel = new JPanel();
        legendPanel.setLayout(
                new BoxLayout(legendPanel, BoxLayout.Y_AXIS)
        );
        legendPanel.setBackground(AppTheme.SURFACE);
        legendPanel.setOpaque(true);
        legendPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppTheme.PRIMARY),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)
                )
        );

        JLabel legendTitle = new JLabel("LEGENDA ROLE");
        legendTitle.setFont(AppTheme.BOLD_FONT);
        legendTitle.setForeground(AppTheme.PRIMARY);
        legendTitle.setAlignmentX(LEFT_ALIGNMENT);

        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(15));

        // Legenda dibuat otomatis berdasarkan enum CharacterType
        for (CharacterType type : CharacterType.values()) {

            JLabel roleLabel = new JLabel("● " + type.name());
            roleLabel.setFont(AppTheme.NORMAL_FONT);
            roleLabel.setForeground(AppTheme.colorFor(type));
            roleLabel.setAlignmentX(LEFT_ALIGNMENT);

            legendPanel.add(roleLabel);
            legendPanel.add(Box.createVerticalStrut(12));
        }

        legendPanel.add(Box.createVerticalGlue());

        mainPanel.add(legendPanel, BorderLayout.EAST);

        // =========================
        // SOUTH: STATUS BAR
        // =========================

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(AppTheme.PRIMARY);
        statusPanel.setOpaque(true);
        statusPanel.setBorder(AppTheme.PANEL_BORDER);

        statusLabel = new JLabel("Menyiapkan data karakter...");
        statusLabel.setFont(AppTheme.BOLD_FONT);
        statusLabel.setForeground(AppTheme.SURFACE);

        JLabel versionLabel = new JLabel("v0.5");
        versionLabel.setFont(AppTheme.BOLD_FONT);
        versionLabel.setForeground(AppTheme.SURFACE);

        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);

        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        // Tombol tambah karakter diletakkan di header bawah statistik
        JButton addButton = new JButton("Tambah 2 Karakter");
        addButton.setFont(AppTheme.BOLD_FONT);
        addButton.addActionListener(addAction);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(AppTheme.BACKGROUND);
        buttonPanel.setOpaque(true);
        buttonPanel.add(addButton);

        headerPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        refreshCharacterList();

        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createStatisticLabel() {

        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setFont(AppTheme.STATISTICS_FONT);
        label.setForeground(AppTheme.STATISTICS);
        label.setBackground(AppTheme.SURFACE);
        label.setOpaque(true);

        return label;
    }

    public void refreshCharacterList() {

        characterPanel.removeAll();

        for (Character character : manager.getAllCharacters()) {

            JPanel card = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 15, 10)
            );

            card.setBackground(
                    AppTheme.colorFor(character.getType())
            );
            card.setOpaque(true);
            card.setBorder(
                    AppTheme.borderFor(character.getType())
            );

            JLabel informationLabel = new JLabel(
                    character.getName()
                    + " | " + character.getType()
                    + " | HP: " + character.getHitPoints()
                    + " | ATK: " + character.getAttackPower()
            );

            informationLabel.setFont(AppTheme.NORMAL_FONT);
            informationLabel.setForeground(AppTheme.SURFACE);

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

            card.add(informationLabel);
            card.add(levelLabel);

            card.setAlignmentX(LEFT_ALIGNMENT);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

            characterPanel.add(card);
            characterPanel.add(Box.createVerticalStrut(8));
        }

        updateStatistics();

        characterPanel.revalidate();
        characterPanel.repaint();
    }

    private void updateStatistics() {

        int total = manager.getTotalCharacters();
        double average = manager.getAverageLevel();

        int highestLevel = 0;

        for (Character character : manager.getAllCharacters()) {
            if (character.getLevel() > highestLevel) {
                highestLevel = character.getLevel();
            }
        }

        totalLabel.setText("Total Karakter: " + total);
        averageLabel.setText(
                String.format("Rata-rata Level: %.2f", average)
        );
        highLevelLabel.setText("Level Tertinggi: " + highestLevel);
        roleLabel.setText(
                "Jenis Role: " + CharacterType.values().length
        );

        statusLabel.setText(
                "Total karakter: " + total
                + " | Rata-rata level: "
                + String.format("%.2f", average)
        );
    }
}
