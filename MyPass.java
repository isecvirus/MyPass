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
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class MyPass {

    public static void main(String[] args) {
        final JFrame window = new JFrame(Vars.tool_title.toString());

        Theme.toggle_theme(window, Vars.default_theme);

        JMenuBar menubar;
        JMenu file_menu, file_menu_import, file_menu_export, edit_menu, appearance_menu, appearance_theme_menu, manager_menu, menu_manager_new, generator_menu, manager_direction_menu;
        JMenuItem manager_menu_new_password, manager_menu_new_RsaKey, file_import_passwords, file_export_passwords, file_menu_encrypt, file_menu_decrypt, menu_appearance_theme, manager_delete_menu, manager_direction_arrange;
        JRadioButtonMenuItem menu_light_theme, menu_dark_theme, manager_direction_rtl, manager_direction_ltr;
        JPanel panel, manager_frame, generator_frame, generator_result_frame;
        JTextField search_manager, generated_password;
        JSpinner password_length;
        SpinnerModel pl_settings; // pl=password length
        JButton generate_btn, copy_password;
        JCheckBoxMenuItem PasswordContain_upper_letters, PasswordContain_lower_letters, PasswordContain_numbers, PasswordContain_punctuation, menu_manager_dragable;
        JTable manager_table;
        JScrollPane manager_scrollPane;
        DefaultTableModel manager_table_model;
        DefaultTableCellRenderer manager_table_row_center;
        ButtonGroup themes_groub, manager_direction_group;
        JPopupMenu manager_popup;
        TableRowSorter<TableModel> manager_sorter;

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

        menu_manager_new = new JMenu("New");
        manager_menu_new_password = new JMenuItem("Password");
        manager_menu_new_RsaKey = new JMenuItem("RSA Key");
        manager_menu_new_RsaKey.setToolTipText("Generate new Encryption RSA Pubilc & Private key");

        manager_menu_new_password.setToolTipText("Add new password");
        file_import_passwords = new JMenuItem("Passwords");
        file_import_passwords.setToolTipText("Import passwords from *.json file");

        file_menu_encrypt = new JMenuItem("Encrypt");
        file_menu_encrypt.setToolTipText("Encrypt passwords");
        file_menu_decrypt = new JMenuItem("Decrypt");
        file_menu_decrypt.setToolTipText("Decrypt imported passwords");

        file_export_passwords = new JMenuItem("Passwords");
        file_export_passwords.setToolTipText("Save encrypted passwords to *.json file");

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

        search_manager = new JTextField();
        search_manager.setLayout(new BorderLayout());
        search_manager.setMargin(new Insets(5, 5, 5, 5));
        search_manager.setBorder(BorderFactory.createCompoundBorder(BorderFactory.creat‌​eEmptyBorder(5, 5, 5, 5), search_manager.getBorder()));
        search_manager.setToolTipText("Search.. (RegEx supported)");

        manager_table_model = new DefaultTableModel(0, Vars.manager_columns.length);
        manager_table_model.setColumnIdentifiers(Vars.manager_columns);
        manager_table_row_center = new DefaultTableCellRenderer();

        manager_table = new JTable(manager_table_model);
        DefaultTableModel mtm = (DefaultTableModel) manager_table.getModel(); // manager table model

        Manager.CenterTableRows(manager_table, manager_table_row_center, Vars.manager_columns);
//        manager_table.setDefaultEditor(Object.class, null);
        manager_table.setSelectionMode(0); // single selection
//        manager_table.setAutoCreateRowSorter(true);

        manager_sorter = new TableRowSorter<>(mtm);
        manager_table.setRowSorter(manager_sorter);
        for (int i = 0; i < Vars.manager_columns.length; i++) {
            manager_sorter.setSortable(i, false);
        }

//        manager_sorter.setSortsOnUpdates(true);
        search_manager.getDocument().addDocumentListener(new DocumentListener() {
            public void search(String query) {
                if (query.length() > 0) {
                    try {
                        manager_sorter.setRowFilter(RowFilter.regexFilter(query));
                        search_manager.setForeground(Color.decode(Vars.valid_search_color));
                        Manager.ReArrange(manager_table);
                    } catch (Exception error) {
                        error.printStackTrace();
                        search_manager.setForeground(Color.decode(Vars.invalid_search_color));
                    }
                } else {
                    manager_sorter.setRowFilter(null);
                    search_manager.setForeground(Color.decode(Vars.valid_search_color));
                }
            }

            public void changedUpdate(DocumentEvent e) {
                search(search_manager.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                search(search_manager.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                search(search_manager.getText());
            }
        });

        manager_popup = new JPopupMenu();
        manager_direction_arrange = new JMenuItem("Arrange");
        manager_direction_arrange.setToolTipText("Fix manager arrangement (1,2,3..)");
        manager_delete_menu = new JMenuItem("Delete");
        manager_direction_menu = new JMenu("Direction");
        manager_direction_group = new ButtonGroup();
        manager_direction_rtl = new JRadioButtonMenuItem("RTL");
        manager_direction_ltr = new JRadioButtonMenuItem("LTR", true);

        manager_direction_group.add(manager_direction_rtl);
        manager_direction_group.add(manager_direction_ltr);
        manager_direction_menu.add(manager_direction_rtl);
        manager_direction_menu.add(manager_direction_ltr);

        manager_delete_menu.addActionListener((ActionEvent e) -> {
            int selected = manager_table.getSelectedRow();
            if (selected >= 0 && manager_table.getRowCount() > 0) {

                // closed_window=-1
                // yes=0
                // no=1
                String message = String.format("Delete %s", selected + 1);
                int answer = JOptionPane.showConfirmDialog(window, "Are you sure?", message, JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    mtm.removeRow(selected);
                    Manager.ReArrange(manager_table);
                    SwingUtilities.updateComponentTreeUI(window);
                }
            }
        });
        manager_direction_rtl.addActionListener((ActionEvent e) -> {
            manager_table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            SwingUtilities.updateComponentTreeUI(window);
        });
        manager_direction_ltr.addActionListener((ActionEvent e) -> {
            manager_table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            SwingUtilities.updateComponentTreeUI(window);
        });
        manager_direction_arrange.addActionListener((ActionEvent e) -> {
            Manager.ReArrange(manager_table);
            SwingUtilities.updateComponentTreeUI(window);
        });

        manager_popup.add(manager_direction_arrange);
        manager_popup.add(new JSeparator());
        manager_popup.add(manager_direction_menu);
        manager_popup.add(new JSeparator());
        manager_popup.add(manager_delete_menu);

        manager_table.setComponentPopupMenu(manager_popup);
        manager_table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

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
        PasswordContain_upper_letters.setToolTipText("Upper letters [A-Z]");

        PasswordContain_lower_letters = new JCheckBoxMenuItem("Lowers");
        PasswordContain_lower_letters.setSelected(true);
        PasswordContain_lower_letters.setToolTipText("Lower letters [a-z]");

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
            Export.passwords(mtm);
        });
        file_menu_encrypt.addActionListener((ActionEvent e) -> {
            Encryption.encrypt(mtm);
        });
        file_menu_decrypt.addActionListener((ActionEvent e) -> {
            Decryption.decrypt(mtm);
        });
        manager_menu_new_password.addActionListener((ActionEvent e) -> {
            New.password(mtm);
        });
        file_import_passwords.addActionListener((ActionEvent e) -> {
            Import.passwords(mtm);
        });
        manager_menu_new_RsaKey.addActionListener((ActionEvent e) -> {
            RSA.generate();
        });
        menu_manager_dragable.addActionListener((ActionEvent e) -> {
            Manager.draggable(manager_table);
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

        search_manager.setVisible(true);
        manager_frame.add(search_manager, BorderLayout.BEFORE_FIRST_LINE);
        manager_frame.add(manager_scrollPane);
        generator_frame.add(generator_result_frame);

        generator_result_frame.add(copy_password);
        generator_result_frame.add(generated_password);
        generator_result_frame.add(password_length);
        generator_result_frame.add(generate_btn);

        panel.add(manager_frame);
        panel.add(generator_frame, BorderLayout.AFTER_LAST_LINE);

        menubar.add(file_menu);
        menubar.add(edit_menu);
        menubar.add(appearance_menu);

        file_menu.add(file_menu_import);
        file_menu_import.add(file_import_passwords);
        file_menu.add(file_menu_export);
        file_menu_export.add(file_export_passwords);

        edit_menu.add(manager_menu);
        edit_menu.add(generator_menu);

        manager_menu.add(menu_manager_new);
        menu_manager_new.add(manager_menu_new_password);
        menu_manager_new.add(manager_menu_new_RsaKey);
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
