package com.virus.MyPass;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
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
    public static void toggle_theme(JFrame window, String theme) {
        try {
            String dark = Vars.dark_theme;
            String light = Vars.light_theme;

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