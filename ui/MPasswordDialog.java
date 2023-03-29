package com.virus.MyPass.ui;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public class MPasswordDialog {

    public static JSONObject createDialog(Component comp, String title) {
        // Submit=0
        // NOT SURE ABOUT ->   no=1
        // cancel=2
        // closed=-1
        
        JPanel handlePanel = new JPanel();
        JPasswordField passwordInput = new JPasswordField(20);
        JButton PasswordKeyboard;
        
        handlePanel.add(passwordInput);
        String[] options = new String[]{"Submit", "Cancel"};

        passwordInput.putClientProperty("JTextField.placeholderText", "Password");
        passwordInput.putClientProperty("PasswordField.showCapsLock", true);
        passwordInput.putClientProperty("FlatLaf.style", "showRevealButton: true");
        passwordInput.setEchoChar('\u2022');
        
        JOptionPane dialog = new JOptionPane();
        
        PasswordKeyboard = new JButton();
        PasswordKeyboard.setIcon(IconFontSwing.buildIcon(FontAwesome.KEYBOARD_O, ui_vars.icons_size, ui_vars.color));
        PasswordKeyboard.setToolTipText("KeyBoard");
        
//        PasswordKeyboard.addActionListener(e -> {
            KeyBoard.show(dialog, passwordInput);
//        });
//        passwordInput.putClientProperty("JTextField.leadingComponent", PasswordKeyboard);
        passwordInput.putClientProperty("JTextField.showClearButton", true);
        
        int answer;
        if (title == null) {
            answer = dialog.showOptionDialog(comp, handlePanel, "Enter Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        } else {
            answer = dialog.showOptionDialog(comp, handlePanel, "Enter Password " + title, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        }
        dialog.putClientProperty("JRootPane.titleBarBackground", ui_vars.color(25));
        dialog.applyComponentOrientation(comp.getComponentOrientation());
        
        
        
        return new JSONObject()
                .put("answer", answer)
                .put("password", String.valueOf(passwordInput.getPassword()));
    }
}
