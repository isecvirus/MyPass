package org.virus.mypass.ui;

import com.formdev.flatlaf.icons.FlatMenuArrowIcon;
import org.virus.mypass.util.ClipBoard.Copy;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.virus.mypass.ui.ui_vars.entity_column_index;
import static org.virus.mypass.ui.ui_vars.modified_column_index;

/**
 *
 * @author SecVirus
 */
public class Entity {

    private static JScrollPane Note_Pane;
    private static JTextField Entity_Field, Username_Field, Url_Field;
    private static JPasswordField Password_Field;
    private static JTextArea Note_Area;
    private static JButton entity_cat_btn, GenerateButton, ClearButton, CopyPassword_Button, ConfirmButton;
    private static GroupLayout layout;
    private static JLabel Note_Label;
    static boolean isAddedOrEdited = false;
    static int icon_size = 75;

    private static JTextField MTextField(String DefaultValue, String placeholder, String ToolTipText, boolean clearButton) {
        JTextField textfield = new JTextField(null, 10);
        if (DefaultValue != null) {
            textfield.setText(DefaultValue);
        }
        if (placeholder != null) {
            textfield.putClientProperty("JTextField.placeholderText", placeholder);
        }
        if (ToolTipText != null) {
            textfield.setToolTipText(ToolTipText);
        }
        if (clearButton == true) {
            textfield.putClientProperty("JTextField.showClearButton", clearButton);
        }

//        textfield.putClientProperty("JTextField.trailingComponent", ...);
        return textfield;
    }

    private static boolean notEmpty(JTextField field) {
        /*
                Check if the given JTextField is not empty
         */

        return field.getText().length() > 0;
    }

    private static void clearBtn_Status() {
        if (notEmpty(Entity_Field) || notEmpty(Username_Field) || notEmpty(Url_Field) || notEmpty(Password_Field) || (Note_Area.getText().length() > 0)) {
            ClearButton.setEnabled(true);
        } else {
            ClearButton.setEnabled(false);
        }
    }

    private static void change_status(boolean new_status) {
        Username_Field.setEnabled(new_status);
        Url_Field.setEnabled(new_status);
        Password_Field.setEnabled(new_status);
        GenerateButton.setEnabled(new_status);
        Note_Area.setEnabled(new_status);
        clearBtn_Status();
    }

    private static JSONArray passwords_list = new JSONArray();
    private static String no_category_msg = "No Category";
    private static String selecetd_icon_name = "";
    private static Color selecetd_color = Accent.AlphaSetGet(255);

    public static boolean EntityWindow(JFrame parent, String title, JTable table, JSONObject json_data, boolean isEditing) {
        IconFontSwing.register(FontAwesome.getIconFont());

        JDialog window = new JDialog();
        window.setTitle(title);
        window.getRootPane().putClientProperty("JRootPane.titleBarBackground", ui_vars.color(25));

        entity_cat_btn = new JButton(no_category_msg);
        entity_cat_btn.setForeground(Color.darkGray.darker());
        entity_cat_btn.setBackground(Accent.AlphaSetGet(50));
        entity_cat_btn.setFont(new Font(entity_cat_btn.getFont().getName(), Font.ITALIC, entity_cat_btn.getFont().getSize()));
        entity_cat_btn.setPreferredSize(new Dimension(100, 100));
        entity_cat_btn.setHorizontalAlignment(SwingConstants.CENTER);
        entity_cat_btn.setVerticalAlignment(SwingConstants.CENTER);
        entity_cat_btn.setFocusable(false);
        entity_cat_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        entity_cat_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        entity_cat_btn.setIconTextGap(3);
        entity_cat_btn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Accent.AlphaSetGet(155)));

        entity_cat_btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    Category cat_pick = new Category();
                    cat_pick.pick(window, selecetd_color);

                    if (cat_pick.getName() != null && cat_pick.getIcon() != null) {
                        selecetd_icon_name = cat_pick.getName();
                        selecetd_color = cat_pick.getColor();

                        entity_cat_btn.setIcon(IconFontSwing.buildIcon(FontAwesome.valueOf(categories.icons.getString(selecetd_icon_name)), icon_size, selecetd_color));
                        entity_cat_btn.setText(cat_pick.getName());
                        entity_cat_btn.setForeground(Color.lightGray);
                        entity_cat_btn.setFont(new Font(entity_cat_btn.getFont().getName(), Font.PLAIN, entity_cat_btn.getFont().getSize()));
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
                    entity_cat_btn.setIcon(null);
                    entity_cat_btn.setText(no_category_msg);
                    entity_cat_btn.setForeground(Color.darkGray.darker());

                    selecetd_icon_name = "";
                    selecetd_color = Accent.AlphaSetGet(255);
                }
            }
        });

        Entity_Field = MTextField(null, "Entity", null, true);
        KeyBoard.give(window, Entity_Field);

        Username_Field = MTextField(null, "Username", null, true);
        Username_Field.setEnabled(false);
        KeyBoard.give(window, Username_Field);

        Url_Field = MTextField(null, "Url", null, true);
        Url_Field.setEnabled(false);
        KeyBoard.give(window, Url_Field);

        JToolBar SufixPasswordToolbar = new JToolBar();

        JButton PasswordHistoryMenu = new JButton((Icon) new FlatMenuArrowIcon());
        PasswordHistoryMenu.setEnabled(table.getSelectedRow() >= 0);
        PasswordHistoryMenu.setToolTipText("Password history");
        SufixPasswordToolbar.add(PasswordHistoryMenu);
        PasswordHistoryMenu.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();
            String target_entity = table.getModel().getValueAt(table.getSelectedRow(), entity_column_index).toString();

            if (json_data.has(target_entity)) {
                if (json_data.getJSONObject(target_entity).has("passwordHistory")) {
                    passwords_list = json_data.getJSONObject(target_entity).getJSONArray("passwordHistory");
                }
            }

            // Remove all existing items from the menu
            popupMenu.removeAll();

            JMenuItem Report_PasswordsStrengthHistory = new JMenuItem("Strength Report", IconFontSwing.buildIcon(FontAwesome.NEWSPAPER_O, ui_vars.icons_size, Accent.AlphaSetGet(255)));
            Report_PasswordsStrengthHistory.setToolTipText("Get a report about ");
            popupMenu.add(Report_PasswordsStrengthHistory);
            Report_PasswordsStrengthHistory.setEnabled(!passwords_list.isEmpty());
            Report_PasswordsStrengthHistory.addActionListener(ev -> {
                Passwords.Reporter(parent, passwords_list);
            });

            JMenuItem clear_history = new JMenuItem("Clear History", IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
            clear_history.setToolTipText("Clear Search History");
            clear_history.addActionListener(ev -> {
                if (json_data.getJSONObject(target_entity).has("passwordHistory")) {
                    json_data.getJSONObject(target_entity).getJSONArray("passwordHistory").clear();
                }
            });
            clear_history.setEnabled(!passwords_list.isEmpty());
            popupMenu.add(clear_history);
            if (!passwords_list.isEmpty()) {
                popupMenu.addSeparator();
            }

            for (int sr = 0; sr < passwords_list.length(); sr++) { // sr=search result
                String pass = passwords_list.getString(sr);
                JMenuItem this_item = new JMenuItem(pass);
                this_item.addActionListener(event -> {
                    Password_Field.setText(pass);
                });

                popupMenu.add(this_item);
            }
            popupMenu.show(PasswordHistoryMenu, 0, PasswordHistoryMenu.getHeight());
        });

        Password_Field = new JPasswordField(null, 10);
        Password_Field.putClientProperty("JTextField.placeholderText", "Password");
        Password_Field.putClientProperty("PasswordField.showCapsLock", true);
        Password_Field.putClientProperty("FlatLaf.style", "showRevealButton: true");
        Password_Field.putClientProperty("JTextField.trailingComponent", SufixPasswordToolbar);
        Password_Field.setEchoChar('\u2022');
        Password_Field.setEnabled(false);
        KeyBoard.give(window, Password_Field);

        GenerateButton = new JButton("Generate");
        GenerateButton.setIcon(IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        GenerateButton.setEnabled(false);
        GenerateButton.setToolTipText("Generate Password");

        ClearButton = new JButton("Clear");
        ClearButton.setIcon(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        ClearButton.setEnabled(false);
        ClearButton.setToolTipText("Clear All Fields");

        CopyPassword_Button = new JButton("Copy");
        CopyPassword_Button.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        CopyPassword_Button.setEnabled(false);
        CopyPassword_Button.setToolTipText("Copy Password");

        ConfirmButton = new JButton("Confirm");
        ConfirmButton.setEnabled(false);
        ConfirmButton.setIcon(IconFontSwing.buildIcon(FontAwesome.CHECK, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        ConfirmButton.setToolTipText("Confirm Adding Entity");

        Note_Area = new JTextArea();
        Note_Area.setLineWrap(true);
        Note_Area.setWrapStyleWord(true);
        Note_Area.setColumns(30);
        Note_Area.setRows(5);
        Note_Area.setTabSize(4);
        Note_Area.setEnabled(false);
        Note_Area.setToolTipText("Write a note for this entity");

        CopyPassword_Button.addActionListener(e -> {
            Copy.string(String.valueOf(Password_Field.getPassword()));
        });

        GenerateButton.addActionListener(e -> {
            new MGenerator().run(parent, Password_Field);
        });

        ClearButton.addActionListener(e -> {
            Entity_Field.setText("");
            Username_Field.setText("");
            Url_Field.setText("");
            Password_Field.setText("");
            Note_Area.setText("");
        });

        Note_Pane = new JScrollPane();
        Note_Pane.putClientProperty("JScrollBar.showButtons", true);
        Note_Pane.putClientProperty("JScrollPane.smoothScrolling", true);

        Note_Pane.setViewportView(Note_Area);

        Note_Label = new JLabel("Note:");

        layout = new GroupLayout(window.getContentPane());
        window.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Password_Field)
                                        .addComponent(Url_Field)
                                        .addComponent(Username_Field)
                                        .addComponent(Entity_Field)
                                        .addComponent(entity_cat_btn, 100, 100, 100)
                                        .addComponent(Note_Label)
                                        .addComponent(Note_Pane, DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(GenerateButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(CopyPassword_Button, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ClearButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(ConfirmButton)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(entity_cat_btn, 100, 100, 100)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Entity_Field, DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Username_Field, DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Url_Field, DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Password_Field, DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(GenerateButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CopyPassword_Button, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ClearButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addGap(23, 23, 23)
                                .addComponent(Note_Label, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Note_Pane, DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ConfirmButton)
                                .addContainerGap())
        );

        ConfirmButton.addActionListener(new ActionListener() {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            private boolean compare(String s1, String s2) {
                return s1.equals(s2);
            }

            private JSONObject temp_copy(JSONObject json, String... remove) {
                JSONObject temp = new JSONObject(json);

                for (String rm : remove) {
                    temp.remove(rm);
                }
                return temp;
            }

            public void actionPerformed(ActionEvent ae) {
                final String time = dtf.format(LocalDateTime.now());

                if (isEditing) {
                    int selectedRow = table.getSelectedRow();
                    String targetEntity = table.getValueAt(table.getSelectedRow(), entity_column_index).toString();
                    JSONObject entityObject = json_data.getJSONObject(targetEntity);

                    String defaultPassword = entityObject.optString("password", "");
                    String defaultCreationDate = entityObject.optString("created", "");
                    JSONArray defaultPasswordHistory = entityObject.optJSONArray("passwordHistory");

                    defaultPasswordHistory = (defaultPasswordHistory == null) ? new JSONArray() : defaultPasswordHistory;

                    String newEntityName = Entity_Field.getText();
                    String newUsername = Username_Field.getText();
                    String newUrl = Url_Field.getText();
                    String newPassword = String.valueOf(Password_Field.getPassword());
                    String newNote = Note_Area.getText();

                    JSONObject category = new JSONObject()
                            .put("icon", selecetd_icon_name)
                            .put("color", Accent.ColorToHex(selecetd_color));

                    JSONObject innerJsonData = new JSONObject()
                            .put("username", newUsername)
                            .put("url", newUrl)
                            .put("password", newPassword)
                            .put("note", newNote)
                            .put("created", entityObject.optString("created", ""))
                            .put("modified", time)
                            .put("category", category)
                            .put("passwordHistory", defaultPasswordHistory);

                    table.setValueAt(newEntityName, selectedRow, entity_column_index);

                    if (!compare(newEntityName, targetEntity) || !temp_copy(innerJsonData, "passwordHistory").equals(entityObject)) {
                        table.setValueAt(time, selectedRow, modified_column_index);
                        innerJsonData.put("modified", time);
                        json_data.remove(targetEntity);
                        isAddedOrEdited = true;
                    } else {
                        innerJsonData.put("modified", entityObject.optString("modified", ""));
                    }

                    if (!defaultPasswordHistory.toList().contains(newPassword)) {
                        JSONArray passwordHistory = innerJsonData.optJSONArray("passwordHistory");
                        if (passwordHistory != null) {
                            passwordHistory.put(newPassword);
                        }
                    }

                    json_data.put(newEntityName, innerJsonData);
                    isAddedOrEdited = true;
                } else { // add
                    String entityName = Entity_Field.getText();
                    String username = Username_Field.getText();
                    String url = Url_Field.getText();
                    String password = String.valueOf(Password_Field.getPassword());
                    String note = Note_Area.getText();

                    JSONArray passwordHistory = new JSONArray();
                    passwordHistory.put(password);

                    JSONObject category = new JSONObject()
                            .put("icon", selecetd_icon_name)
                            .put("color", Accent.ColorToHex(selecetd_color));

                    JSONObject newInnerJsonData = new JSONObject()
                            .put("username", username)
                            .put("url", url)
                            .put("password", password)
                            .put("note", note)
                            .put("created", time)
                            .put("modified", "")
                            .put("category", category)
                            .put("passwordHistory", passwordHistory);

                    if (!json_data.has(entityName)) {
                        String[] tableData = {"", entityName, time, ""};
                        ((DefaultTableModel) table.getModel()).addRow(tableData);
                        json_data.put(entityName, newInnerJsonData);
                        isAddedOrEdited = true;
                    } else {
                        JOptionPane.showMessageDialog(window, "This entity already exists", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                window.dispose();
            }
        });

        DocumentListener doc_listener = new DocumentListener() {
            private void update(JSONObject json_data) {
                boolean status;

                if (isEditing) { // adding mode                    
                    String entity, new_entity;

                    entity = table.getValueAt(table.getSelectedRow(), entity_column_index).toString();
                    new_entity = Entity_Field.getText();

                    status = (notEmpty(Entity_Field) // check for entity field is filled.
                            && notEmpty(Password_Field) // check for password field is filled.

                            // check if the entity in the list and the entity is the same one (the entity name haven't been modified).
                            && ((json_data.keySet().contains(new_entity) && new_entity.equals(entity))
                            // or the entity name is not in the list (available).
                            || !json_data.keySet().contains(new_entity)));
                } else { // editing mode
                    status = (notEmpty(Entity_Field) // check for entity field is filled.
                            && notEmpty(Password_Field) // check for password field is filled.

                            // if the entity name not in the list then add it.
                            && !json_data.keySet().contains(Entity_Field.getText()));
                }

                if (table.getSelectedRow() >= 0) {
                    String entity = table.getModel().getValueAt(table.getSelectedRow(), entity_column_index).toString();
                    String password = String.valueOf(Password_Field.getPassword());
                    JSONObject entity_object = json_data.getJSONObject(entity);

                    if (entity_object.has("passwordHistory")) {
                        if (entity_object.getJSONArray("passwordHistory").toList().contains(password)) {
                            Password_Field.setForeground(Color.decode("#ffff00"));
                        } else {
                            Password_Field.setForeground(Color.lightGray);
                        }
                    }
                }

                ConfirmButton.setEnabled(status);
                change_status(notEmpty(Entity_Field));
                CopyPassword_Button.setEnabled(notEmpty(Password_Field));

                try {
                    new URL(Url_Field.getText());
                    Url_Field.setForeground(Color.lightGray);
                } catch (MalformedURLException mue) {
                    Url_Field.setForeground(Color.red);
                }
            }

            public void changedUpdate(DocumentEvent ce) {
                update(json_data);
            }

            public void removeUpdate(DocumentEvent re) {
                update(json_data);
            }

            public void insertUpdate(DocumentEvent ie) {
                update(json_data);
            }
        };

        Entity_Field.getDocument().addDocumentListener(doc_listener);
        Username_Field.getDocument().addDocumentListener(doc_listener);
        Url_Field.getDocument().addDocumentListener(doc_listener);
        Password_Field.getDocument().addDocumentListener(doc_listener);
        Note_Area.getDocument().addDocumentListener(doc_listener);

        if (isEditing == true) {
            String icon, icon_color, entity, username, url, password, note;
            entity = table.getValueAt(table.getSelectedRow(), entity_column_index).toString();

            JSONObject data = json_data.getJSONObject(entity);
            JSONObject category_obj = data.getJSONObject("category");

            icon = category_obj.getString("icon");
            icon_color = category_obj.getString("color");

            username = data.getString("username");
            url = data.getString("url");
            password = data.getString("password");
            note = data.getString("note");

            if (!icon_color.isEmpty()) {
                selecetd_color = Color.decode(icon_color);
            }

            if (!icon.isEmpty()) {
                selecetd_icon_name = icon;
                entity_cat_btn.setIcon(IconFontSwing.buildIcon(FontAwesome.valueOf(categories.icons.getString(icon)), icon_size, selecetd_color));
                entity_cat_btn.setText(selecetd_icon_name);
            } else {
                entity_cat_btn.setIcon(null);
                entity_cat_btn.setText(no_category_msg);
                entity_cat_btn.setForeground(Color.darkGray.darker());
            }

            Entity_Field.setText(entity);
            Username_Field.setText(username);
            Url_Field.setText(url);
            Password_Field.setText(password);
            Note_Area.setText(note);
        }

        // ---------------------------------------------------------------------
        ComponentOrientation orientation = parent.getComponentOrientation();

        window.applyComponentOrientation(orientation);

        Entity_Field.setComponentOrientation(orientation);
        Username_Field.setComponentOrientation(orientation);
        Url_Field.setComponentOrientation(orientation);
        Password_Field.setComponentOrientation(orientation);

        GenerateButton.setComponentOrientation(orientation);
        CopyPassword_Button.setComponentOrientation(orientation);
        ClearButton.setComponentOrientation(orientation);
        Note_Pane.setComponentOrientation(orientation);
        ConfirmButton.setComponentOrientation(orientation);

        // ---------------------------------------------------------------------
        window.pack();
        window.setAlwaysOnTop(parent.isAlwaysOnTop());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setResizable(false);
        window.setModal(true);
        window.setLocationRelativeTo(parent);
        window.setVisible(true);

        return isAddedOrEdited;
    }
}
