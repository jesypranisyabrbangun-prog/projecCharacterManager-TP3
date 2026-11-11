
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {

    private AppTheme() {
    }

    // WARNA APLIKASI
    public static final Color BACKGROUND =
            new Color(245, 247, 250);

    public static final Color SURFACE =
            Color.WHITE;

    public static final Color PRIMARY =
            new Color(35, 55, 90);

    public static final Color TEXT =
            new Color(45, 50, 60);

    public static final Color STATISTICS =
            new Color(20, 110, 70);

    public static final Color GOLD =
            new Color(190, 135, 0);

    // FONT APLIKASI
    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 24);

    public static final Font NORMAL_FONT =
            new Font("SansSerif", Font.PLAIN, 15);

    public static final Font BOLD_FONT =
            new Font("SansSerif", Font.BOLD, 15);

    public static final Font STATISTICS_FONT =
            new Font("SansSerif", Font.BOLD, 17);

    // BORDER APLIKASI
    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(10, 20, 10, 20);

    public static final Border WINDOW_BORDER =
            BorderFactory.createEmptyBorder(15, 15, 15, 15);

    public static final Border TITLE_BORDER =
            BorderFactory.createEmptyBorder(10, 10, 15, 10);

    // WARNA BERDASARKAN ROLE
    public static Color colorFor(CharacterType type) {

        switch (type) {
            case WARRIOR:
                return new Color(220, 90, 90);

            case MAGE:
                return new Color(120, 100, 210);

            case ARCHER:
                return new Color(40, 150, 100);

            case HEALER:
                return new Color(50, 150, 190);

            default:
                return TEXT;
        }
    }

    // BORDER BERDASARKAN ROLE
    public static Border borderFor(CharacterType type) {

        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorFor(type), 2),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );
    }
}
