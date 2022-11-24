package com.virus.MyPass;

/*
 * @author SecVirus
 *
 *
 * This file contains the main class.
 * PublicKey: Encrypt
 * PrivateKey: Decrypt
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//import org.json.;
public class MyPass {

    public static void main(String[] args) {
        final JFrame window = new JFrame(Vars.tool_title.toString());
        Theme.toggle_theme(window, Vars.default_theme);

        JMenuBar menubar;
        JMenu file_menu, file_menu_import, file_menu_export, edit_menu, appearance_menu, appearance_theme_menu, manager_menu, generator_menu;
        JMenuItem menu_manager_new_password, file_import_passwords, file_export_passwords, file_menu_generate_rsa, file_menu_encrypt, file_menu_decrypt, menu_appearance_theme;
        JRadioButtonMenuItem menu_light_theme, menu_dark_theme;
        JPanel panel, manager_frame, generator_frame, generator_result_frame;
        JTextField generated_password;
        JSpinner password_length;
        SpinnerModel pl_settings; // pl=password length
        JButton generate_btn, copy_password;
        JCheckBoxMenuItem PasswordContain_upper_letters, PasswordContain_lower_letters, PasswordContain_numbers, PasswordContain_punctuation, menu_manager_dragable;
        JTable manager_table;
        JScrollPane manager_scrollPane;
        DefaultTableModel manager_table_model;
        DefaultTableCellRenderer manager_table_row_center;
        ButtonGroup themes_groub;

        menubar = new JMenuBar();

        file_menu = new JMenu("File");
        edit_menu = new JMenu("Edit");
        appearance_menu = new JMenu("Appearance");
        appearance_theme_menu = new JMenu("Theme");

        manager_menu = new JMenu("Manager");
        generator_menu = new JMenu("Generator");

        file_menu_import = new JMenu("Import");
        file_menu_import.setToolTipText("Import encrypted passwords from json file");
        file_menu_export = new JMenu("Export");

        menu_manager_new_password = new JMenuItem("New");
        menu_manager_new_password.setToolTipText("Add new password");
        file_import_passwords = new JMenuItem("Passwords");
        file_import_passwords.setToolTipText("Import passwords from *.json file");

        file_menu_encrypt = new JMenuItem("Encrypt");
        file_menu_encrypt.setToolTipText("Encrypt passwords");
        file_menu_decrypt = new JMenuItem("Decrypt");
        file_menu_decrypt.setToolTipText("Decrypt imported passwords");

        file_export_passwords = new JMenuItem("Passwords");
        file_export_passwords.setToolTipText("Save encrypted passwords to *.json file");

        file_menu_generate_rsa = new JMenuItem("New RSA");
        file_menu_generate_rsa.setToolTipText("Generate new Encryption RSA Pubilc & Private key");

        // Seperator ------------
        menu_manager_dragable = new JCheckBoxMenuItem("Dragable");
        menu_manager_dragable.setToolTipText("Enable/Disable abillity to drag text from manager table");

        themes_groub = new ButtonGroup();
        menu_light_theme = new JRadioButtonMenuItem("Light Theme");
        menu_dark_theme = new JRadioButtonMenuItem("Dark Theme", true);
        menu_light_theme.setToolTipText("Toggle light theme");
        menu_dark_theme.setToolTipText("Toggle dark theme");

        panel = new JPanel(new BorderLayout());

        manager_frame = new JPanel(new BorderLayout());
        generator_frame = new JPanel(new BorderLayout());
        generator_result_frame = new JPanel();
        generator_result_frame.setAlignmentY(JComponent.CENTER_ALIGNMENT);

        generator_frame.setLayout(new BoxLayout(generator_frame, BoxLayout.X_AXIS));

        manager_table_model = new DefaultTableModel(0, Vars.manager_columns.length);
        manager_table_model.setColumnIdentifiers(Vars.manager_columns);
        manager_table_row_center = new DefaultTableCellRenderer();

        manager_table = new JTable(manager_table_model);
        DefaultTableModel mtm = (DefaultTableModel) manager_table.getModel(); // manager table model

        Manager.CenterTableRows(manager_table, manager_table_row_center, Vars.manager_columns);
//        manager_table.setDefaultEditor(Object.class, null);
        manager_table.setSelectionMode(0); // single selection
        manager_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        manager_table.setFocusable(false);
        manager_table.setColumnSelectionAllowed(true);
        manager_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        manager_scrollPane = new JScrollPane(manager_table);
        manager_scrollPane.setBackground(Color.red);

        generated_password = new JTextField();
        generated_password.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        generated_password.setFocusable(false);
        generated_password.setPreferredSize(new Dimension(Vars.screen_width / 3, 30)); // x, y, width, height

        copy_password = new JButton("Copy");
        copy_password.setPreferredSize(new Dimension(70, 30));
        copy_password.setVisible(true);
        copy_password.setLayout(null);

        PasswordContain_upper_letters = new JCheckBoxMenuItem("Uppers");
        PasswordContain_upper_letters.setSelected(true);
        PasswordContain_upper_letters.setToolTipText("Upper letters [A-z]");

        PasswordContain_lower_letters = new JCheckBoxMenuItem("Lowers");
        PasswordContain_lower_letters.setSelected(true);
        PasswordContain_lower_letters.setToolTipText("Lower letters [A-z]");

        PasswordContain_numbers = new JCheckBoxMenuItem("Numbers");
        PasswordContain_numbers.setSelected(true);
        PasswordContain_numbers.setToolTipText("Numbers [0-9]");

        PasswordContain_punctuation = new JCheckBoxMenuItem("Punctuations");
        PasswordContain_punctuation.setSelected(true);
        PasswordContain_punctuation.setToolTipText("Punctuations");

        pl_settings = new SpinnerNumberModel(Vars.initial_len, Vars.min_len, Vars.max_len, Vars.steps);
        // 8: initial value
        // 0: min value
        // 2500: max value
        // 1: step
        password_length = new JSpinner(pl_settings);
        password_length.setPreferredSize(new Dimension(70, 30));
        ((DefaultEditor) password_length.getEditor()).getTextField().setEditable(false);

//        password_length.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        password_length.setToolTipText("Length of generated password");

        generate_btn = new JButton("Generate");
        generate_btn.setPreferredSize(new Dimension(120, 30));
        generate_btn.setLayout(null);
        generate_btn.setFocusable(true);
        generate_btn.setBounds(50, 100, 95, 30);
        generate_btn.setVisible(true);

        file_export_passwords.addActionListener((ActionEvent e) -> {
            if (mtm.getRowCount() > 0) {
                FileDialog export_data_to = new FileDialog(window, "Export data to:", FileDialog.SAVE);
                export_data_to.setAlwaysOnTop(true);
                export_data_to.setVisible(true);

                if (export_data_to.getFile() != null) {
                    Hashtable<String, String> data = new Hashtable<String, String>();

                    for (int r = 0; r < mtm.getRowCount(); r++) {
                        String name = mtm.getValueAt(r, 1).toString();
                        String password = mtm.getValueAt(r, 2).toString();
                        data.put(name, password);
                    }
                    String export_data_to_path = new File(export_data_to.getDirectory() + export_data_to.getFile()).toPath().toString();
                    Export.passwords(export_data_to_path, data);
                }
            }
        });

        file_menu_encrypt.addActionListener((ActionEvent e) -> {
            if (mtm.getRowCount() > 0) {
                FileDialog import_publickey_from = new FileDialog(window, "Import (RSA public) key:", FileDialog.LOAD);
                import_publickey_from.setAlwaysOnTop(true);
                import_publickey_from.setVisible(true);

                if (import_publickey_from.getFile() != null) {
                    File import_publickey_from_file = new File(import_publickey_from.getDirectory() + import_publickey_from.getFile());
                    if (import_publickey_from_file.exists()) {
                        Path publickey_file_path = import_publickey_from_file.toPath();

                        Cipher encryptCipher = RSA.read_PublicFile(publickey_file_path);

                        if (encryptCipher != null) {
                            for (int row = 0; row < mtm.getRowCount(); row++) {
                                try {
                                    String password = mtm.getValueAt(row, 2).toString();
                                    byte[] secretStringBytes = password.getBytes(StandardCharsets.UTF_8);
                                    byte[] encryptSecretString = encryptCipher.doFinal(secretStringBytes);
                                    String base64Secret = Base64.getEncoder().encodeToString(encryptSecretString);
                                    mtm.setValueAt(base64Secret, row, 2);
                                } catch (Exception error) { // BadPaddingException | IllegalBlockSizeException | IllegalArgumentException
                                }
                            }
                        }
                    }
                }
            }
        });

        file_menu_decrypt.addActionListener((ActionEvent e) -> {
            if (mtm.getRowCount() > 0) {
                FileDialog key_file = new FileDialog(window, "Import (RSA private) key:", FileDialog.LOAD);
                key_file.setAlwaysOnTop(true);
                key_file.setVisible(true);

                if (key_file.getFile() != null) {
                    File import_privatekey_from_file = new File(key_file.getDirectory() + key_file.getFile());
                    if (import_privatekey_from_file.exists()) {
                        Path privatekey_file_path = import_privatekey_from_file.toPath();
                        try {
                            Cipher decryptCipher = RSA.read_PrivateFile(privatekey_file_path);

                            if (decryptCipher != null) {
                                for (int row = 0; row < mtm.getRowCount(); row++) {
                                    String b64password = mtm.getValueAt(row, 2).toString();
                                    byte[] passwordbytes = Base64.getDecoder().decode(b64password.getBytes());

                                    byte[] decryptSecretBytes = decryptCipher.doFinal(passwordbytes);
                                    String decryptedSecretString = new String(decryptSecretBytes, StandardCharsets.UTF_8);
                                    // decryptedSecretString = password (as plaintext)
                                    mtm.setValueAt(decryptedSecretString, row, 2);
                                }
                            }
                        } catch (Exception error) { // BadPaddingException | IllegalBlockSizeException | IllegalArgumentException
                        }
                    }
                }
            }
        });

        menu_manager_new_password.addActionListener((ActionEvent e) -> {
            int index = mtm.getRowCount() + 1;
            Object[] a = {index, "", ""};
            mtm.addRow(a);
        });
        file_import_passwords.addActionListener((ActionEvent e) -> {
            FileDialog import_passwords_from = new FileDialog(window, "Import passwords from:", FileDialog.LOAD);
            import_passwords_from.setAlwaysOnTop(true);
            import_passwords_from.setVisible(true);
            File import_passwords_from_file = new File(import_passwords_from.getDirectory() + import_passwords_from.getFile());

            if (import_passwords_from_file.exists()) {
                Path passwords_file_path = import_passwords_from_file.toPath();
                Import.passwords(passwords_file_path, mtm);
            }
        });
        file_menu_generate_rsa.addActionListener((ActionEvent e) -> {
            FileDialog save_public_to = new FileDialog(window, "Save RSA public key to:", FileDialog.SAVE);

            save_public_to.setFile(Vars.default_public_name);
            save_public_to.setAlwaysOnTop(true);
            save_public_to.setVisible(true);

            if (save_public_to.getFile() != null) {

                FileDialog save_private_to = new FileDialog(window, "Save RSA private key to:", FileDialog.SAVE);

                save_private_to.setFile(Vars.default_private_name);
                save_private_to.setAlwaysOnTop(true);
                save_private_to.setVisible(true);

                String public_file = save_public_to.getDirectory() + save_public_to.getFile();
                String private_file = save_private_to.getDirectory() + save_private_to.getFile();

                if (save_private_to.getFile() != null) {
                    RSA.generate(public_file, private_file);
                }
            }
        });
        menu_manager_dragable.addActionListener((ActionEvent e) -> {
            boolean isDragable = manager_table.getDragEnabled();
            if (isDragable) {
                manager_table.setDragEnabled(false);
            } else {
                manager_table.setDragEnabled(true);
            }
        });
        menu_light_theme.addActionListener((ActionEvent e) -> {
            Theme.toggle_theme(window, Vars.light_theme);
        });
        menu_dark_theme.addActionListener((ActionEvent e) -> {
            Theme.toggle_theme(window, Vars.dark_theme);
        });
        generate_btn.addActionListener((ActionEvent ae) -> {
            String password = "";
            int length = (int) password_length.getValue();
            String all = "";

            if (PasswordContain_upper_letters.isSelected()) {
                all += Vars.upper_letters;
            }
            if (PasswordContain_lower_letters.isSelected()) {
                all += Vars.lower_letters;
            }
            if (PasswordContain_numbers.isSelected()) {
                all += Vars.numbers;
            }
            if (PasswordContain_punctuation.isSelected()) {
                all += Vars.punctuations;
            }

            if (all.length() > 0) {
                for (int i = 0; i < length; i++) {
                    int random = (int) (Math.random() * all.length());
                    password += all.charAt(random);
                }
                generated_password.setText(password);
            }
        });
        copy_password.addActionListener((ActionEvent ae) -> {
            String password = generated_password.getText();
            Copy.string(password);
        });

        manager_frame.add(manager_scrollPane);

        generator_result_frame.add(copy_password);
        generator_result_frame.add(generated_password);
        generator_result_frame.add(password_length);
        generator_result_frame.add(generate_btn);

        generator_frame.add(generator_result_frame);

        panel.add(manager_frame);
        panel.add(generator_frame, BorderLayout.AFTER_LAST_LINE);

        menubar.add(file_menu);
        menubar.add(edit_menu);
        menubar.add(appearance_menu);

        file_menu.add(file_menu_import);
        file_menu_import.add(file_import_passwords);
        file_menu.add(file_menu_export);
        file_menu_export.add(file_export_passwords);
        file_menu.addSeparator();
        file_menu.add(file_menu_generate_rsa);

        edit_menu.add(manager_menu);
        edit_menu.add(generator_menu);

        manager_menu.add(menu_manager_new_password);
        manager_menu.addSeparator();
        manager_menu.add(file_menu_encrypt);
        manager_menu.add(file_menu_decrypt);
        manager_menu.addSeparator();
        manager_menu.add(menu_manager_dragable);

        generator_menu.add(PasswordContain_upper_letters);
        generator_menu.add(PasswordContain_lower_letters);
        generator_menu.add(PasswordContain_numbers);
        generator_menu.add(PasswordContain_punctuation);

        appearance_menu.add(appearance_theme_menu);
        appearance_theme_menu.add(menu_light_theme);
        appearance_theme_menu.add(menu_dark_theme);
        themes_groub.add(menu_light_theme);
        themes_groub.add(menu_dark_theme);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
        window.add(menubar);
        window.setJMenuBar(menubar);
        window.setContentPane(panel);

        window.setPreferredSize(new Dimension(Vars.screen_width / 2, Vars.screen_height / 2));
        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
    }
}
