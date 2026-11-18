
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final Consumer<Character> onAdd;

    private JTextField nameField;
    private JComboBox<CharacterType> roleComboBox;
    private JSpinner levelSpinner;
    private JLabel errorLabel;

    private Border originalBorder;

    public CharacterFormPanel(
            CharacterManager manager,
            Consumer<Character> onAdd) {

        this.manager = manager;
        this.onAdd = onAdd;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Form Tambah Karakter"));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        nameField = new JTextField(15);
        originalBorder = nameField.getBorder();

        roleComboBox = new JComboBox<>(CharacterType.values());

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        inputPanel.add(new JLabel("Nama Karakter:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Role:"));
        inputPanel.add(roleComboBox);

        inputPanel.add(new JLabel("Level:"));
        inputPanel.add(levelSpinner);

        add(inputPanel, BorderLayout.CENTER);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);

        JButton addButton = new JButton("Tambah Karakter");
        addButton.addActionListener(this::handleAdd);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(errorLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Validasi nama: kelengkapan, panjang, format, keunikan
    public String validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter wajib diisi.";
        }

        String trimmedName = name.trim();

        if (trimmedName.length() < 3 || trimmedName.length() > 20) {
            return "Nama harus terdiri dari 3 sampai 20 karakter.";
        }

        if (!trimmedName.matches("[\\p{L}][\\p{L} '-]*")) {
            return "Nama hanya boleh berisi huruf, spasi, tanda hubung, atau apostrof.";
        }

        if (manager.isNameTaken(trimmedName)) {
            return "Nama karakter sudah digunakan.";
        }

        return null;
    }

    private void handleAdd(ActionEvent event) {

        String name = nameField.getText().trim();

        String validationMessage = validateName(name);

        if (validationMessage != null) {
            showError(validationMessage);
            nameField.requestFocusInWindow();
            return;
        }

        try {
            levelSpinner.commitEdit();

            String roleText = roleComboBox.getSelectedItem().toString();
            CharacterType role = CharacterType.valueOf(roleText);

            int level = (Integer) levelSpinner.getValue();

            Character character = new Character(name, role, level);

            onAdd.accept(character);

            nameField.setText("");
            roleComboBox.setSelectedIndex(0);
            levelSpinner.setValue(1);

            clearError();

        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            showError("Input tidak valid. Periksa kembali data.");
        }
    }

    public void showError(String message) {
        errorLabel.setText(message);
        nameField.setBorder(
                BorderFactory.createLineBorder(Color.RED, 2)
        );
    }

    public void clearError() {
        errorLabel.setText(" ");
        nameField.setBorder(originalBorder);
    }
}
