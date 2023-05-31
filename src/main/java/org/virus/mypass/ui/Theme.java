package org.virus.mypass.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * @author SecVirus
 *
 * This file will help MyPass GUI to change theme {DARK..LIGHT};
 */
public class Theme {

    public static void toggle_theme(Component window, String theme) {
        String dark = ui_vars.dark_theme;
        String light = ui_vars.light_theme;

        if (theme.equals(light)) {
            apply_laf(window, new FlatLightLaf());

        } else if (theme.equals(dark)) {
            apply_laf(window, new FlatDarkLaf());
        }
    }

    public static void apply_laf(Component comp, LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);

            /* update the window to submit the new theme */
            SwingUtilities.updateComponentTreeUI(comp);
        } catch (UnsupportedLookAndFeelException error) {
        }
    }

    public static void apply_configurations() {
        UIManager.put("Panel.arc", 0);

//        UIManager.put("PasswordField.revealIconColor", ui_vars.default_accent(255));
        UIManager.put("PasswordField.capsLockIconColor", Accent.AlphaSetGet(139));
        UIManager.put("TextField.selectionBackground", Accent.AlphaSetGet(100));

        UIManager.put("ScrollPane.smoothScrolling", true);
        UIManager.put("showButtons", true);

        UIManager.put("ToolBar.dockingBackground", Accent.AlphaSetGet(190));
        UIManager.put("ToolBar.floatingBackground", Accent.AlphaSetGet(70));
        UIManager.put("ToolBar.gripColor", Accent.AlphaSetGet(255));

        UIManager.put("Button.background", Accent.AlphaSetGet(50));
        UIManager.put("Button.disabledBackground", Accent.AlphaSetGet(20));
        UIManager.put("Button.default.background", Accent.AlphaSetGet(50));
        UIManager.put("Button.hoverBackground", Accent.AlphaSetGet(35));
        UIManager.put("Button.pressedBackground", Accent.AlphaSetGet(50));
//        UIManager.put("Button.selectedBackground", ui_vars.default_accent(30));

        UIManager.put("Button.toolbar.hoverBackground", Accent.AlphaSetGet(25));
        UIManager.put("Button.toolbar.pressedBackground", Accent.AlphaSetGet(50));

//        UIManager.put("CheckBox.icon.selectedBackground", ui_vars.default_accent(150));
//        UIManager.put("CheckBox.icon.checkmarkColor", ui_vars.default_accent(0));
        UIManager.put("TitlePane.unifiedBackground", true);
//        UIManager.put("TitlePane.background", ui_vars.default_accent(50));
//        UIManager.put("ToolBar.background", ui_vars.default_accent(50));
        UIManager.put("TableHeader.background", Accent.AlphaSetGet(67));
        UIManager.put("Table.background", Accent.AlphaSetGet(30));
        UIManager.put("TableHeader.separatorColor", Accent.AlphaSetGet(150));
        UIManager.put("TableHeader.bottomSeparatorColor", Accent.AlphaSetGet(150));
        UIManager.put("Table.focusCellForeground", Accent.AlphaSetGet(150));
        UIManager.put("Table.gridColor", Accent.AlphaSetGet(150));
        UIManager.put("Table.sortIconColor", Accent.AlphaSetGet(255));
        UIManager.put("TableHeader.sortIconPosition", "left");
        UIManager.put("Table.showHorizontalLines", true);
//        UIManager.put("Table.showVerticalLines", true);

        UIManager.put("TitlePane.foreground", Accent.AlphaSetGet(200));
        UIManager.put("TitlePane.background", Accent.AlphaSetGet(30)); // shows as #673d01
//        UIManager.put("TitlePane.inactiveBackground", Color.decode("#090700"));

        UIManager.put("TitlePane.borderColor", Accent.AlphaSetGet(255));

        UIManager.put("Spinner.buttonSeparatorColor", Accent.AlphaSetGet(100));
        UIManager.put("Component.arc", 999); // roundness of some elements

        UIManager.put("TextComponent.arc", 10); // roundness of some elements
        UIManager.put("JButton.buttonType", "roundRect");

        UIManager.put("TitlePane.closeHoverBackground", Accent.AlphaSetGet(150));
        UIManager.put("TitlePane.closePressedBackground", Accent.AlphaSetGet(200));

//        UIManager.put("MenuBar.foreground", Color.lightGray);
        UIManager.put("MenuBar.hoverBackground", Accent.AlphaSetGet(75));
//        UIManager.put("MenuBar.selectionForeground", Color.decode("#ffffff"));
        UIManager.put("MenuBar.selectionBackground", Accent.AlphaSetGet(175));
        UIManager.put("MenuBar.underlineSelectionColor", Accent.AlphaSetGet(175));
        UIManager.put("MenuItem.underlineSelectionColor", Accent.AlphaSetGet(175));
        UIManager.put("MenuItem.underlineSelectionBackground", Accent.AlphaSetGet(35));
        UIManager.put("MenuItem.underlineSelectionCheckBackground", Accent.AlphaSetGet(75));
        UIManager.put("MenuItem.selectionBackground", Accent.AlphaSetGet(200));
        UIManager.put("CheckBoxMenuItem.icon.checkmarkColor", Accent.AlphaSetGet(255));
        UIManager.put("Menu.icon.arrowColor", Accent.AlphaSetGet(255));

        UIManager.put("MenuBar.borderColor", Accent.AlphaSetGet(175));
        UIManager.put("MenuItem.selectionType", "underline");
        UIManager.put("MenuBar.underlineSelectionBackground", Accent.AlphaSetGet(35));

        UIManager.put("Component.arrowType", "chevron"); // triangle

        UIManager.put("TitlePane.centerTitle", true); // center window title 

        UIManager.put("Button.borderColor", Accent.AlphaSetGet(50));
        UIManager.put("Button.hoverBorderColor", Accent.AlphaSetGet(100));
        UIManager.put("Button.toolbar.focusColor", Accent.AlphaSetGet(150));

        UIManager.put("Button.startBorderColor", Accent.AlphaSetGet(90));
        UIManager.put("Button.hoverBorderColor", Accent.AlphaSetGet(190));
        UIManager.put("Button.focusedBorderColor", Accent.AlphaSetGet(150));
        UIManager.put("Button.default.borderColor", Accent.AlphaSetGet(50));
        UIManager.put("Button.default.focusColor", Accent.AlphaSetGet(50));
        UIManager.put("Component.borderColor", Accent.AlphaSetGet(50));
        UIManager.put("Component.focusedBorderColor", Accent.AlphaSetGet(255));

        UIManager.put("Component.focusColor", Accent.AlphaSetGet(50));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Accent.AlphaSetGet(150)));
        UIManager.put("ToolTip.background", Accent.AlphaSetGet(200));
//        UIManager.put("ToolTip.foreground", Accent.AlphaSetGet(255).brighter().brighter().brighter().brighter().brighter());
        UIManager.put("ToolBar.separatorColor", Accent.AlphaSetGet(77));
    }
}
