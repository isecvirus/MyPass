package com.virus.MyPass;

import javax.swing.JOptionPane;

/**
 *
 * @author SecVirus
 */
public class Prompt {
    public static void Error(String message) {
        JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
