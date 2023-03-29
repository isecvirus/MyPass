package com.virus.MyPass;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.virus.MyPass.ui.ui_vars;
import java.awt.Component;
import javax.swing.JFrame;
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
        try {
            String dark = ui_vars.dark_theme;
            String light = ui_vars.light_theme;

            if (theme == light) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                
            } else if (theme == dark) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            }
            /* update the window to submit the new theme */
            SwingUtilities.updateComponentTreeUI(window);
        } catch (UnsupportedLookAndFeelException error) {
            error.printStackTrace();
        }
    }
}