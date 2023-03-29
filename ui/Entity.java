package com.virus.MyPass.ui;

import com.virus.MyPass.util.ClipBoard.Copy;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public class Entity {

    private static JScrollPane Note_Pane;
    private static JTextField Entity_Field, Username_Field, Url_Field;
    private static JPasswordField Password_Field;
    private static JTextArea Note_Area;
    private static JButton GenerateButton, ClearButton, CopyPassword_Button, ConfirmButton;
    private static GroupLayout layout;
    private static JLabel Note_Label;
    static boolean isAddedOrEdited = false;

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

    public static boolean EntityWindow(JFrame parent, String title, DefaultTableModel table_model, JSONObject json_data, int entity_index, boolean isEditing) {
        IconFontSwing.register(FontAwesome.getIconFont());
        
        JDialog frame = new JDialog();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", ui_vars.color(25));

        Entity_Field = MTextField(null, "Entity", null, true);
        KeyBoard.show(frame, Entity_Field);

        Username_Field = MTextField(null, "Username", null, true);
        Username_Field.setEnabled(false);
        KeyBoard.show(frame, Username_Field);

        Url_Field = MTextField(null, "Url", null, true);
        Url_Field.setEnabled(false);
        KeyBoard.show(frame, Url_Field);

        Password_Field = new JPasswordField(null, 10);
        Password_Field.putClientProperty("JTextField.placeholderText", "Password");
        Password_Field.putClientProperty("PasswordField.showCapsLock", true);
        Password_Field.putClientProperty("FlatLaf.style", "showRevealButton: true");
        Password_Field.setEchoChar('\u2022');
        Password_Field.setEnabled(false);
        KeyBoard.show(frame, Password_Field);

        GenerateButton = new JButton("Generate");
        GenerateButton.setIcon(IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size, ui_vars.color));
        GenerateButton.setEnabled(false);
        GenerateButton.setToolTipText("Generate Password");

        ClearButton = new JButton("Clear");
        ClearButton.setIcon(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, ui_vars.color));
        ClearButton.setEnabled(false);
        ClearButton.setToolTipText("Clear All Fields");

        CopyPassword_Button = new JButton("Copy");
        CopyPassword_Button.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyPassword_Button.setEnabled(false);
        CopyPassword_Button.setToolTipText("Copy Password");

        ConfirmButton = new JButton("Confirm");
        ConfirmButton.setEnabled(false);
        ConfirmButton.setIcon(IconFontSwing.buildIcon(FontAwesome.CHECK, ui_vars.icons_size, ui_vars.color));
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

        layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Password_Field)
                                        .addComponent(Url_Field)
                                        .addComponent(Username_Field)
                                        .addComponent(Entity_Field)
                                        .addComponent(Note_Label)
                                        .addComponent(Note_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
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
                                .addGap(15, 15, 15)
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

            public void actionPerformed(ActionEvent ae) {
                final LocalDateTime now = LocalDateTime.now();
                final String time = dtf.format(now);

                JSONObject inner_json_data = new JSONObject()
                        .put("username", Username_Field.getText())
                        .put("url", Url_Field.getText())
                        .put("password", String.valueOf(Password_Field.getPassword()))
                        .put("note", Note_Area.getText())
                        .put("created", time) // the current date & time, default for new entity operation
                        .put("modified", ""); // no modification date.. default for new entity operation

                if (isEditing) {
                    String entity, new_entity_name, username, url, password, note;

                    entity = table_model.getValueAt(entity_index, 0).toString();
                    new_entity_name = Entity_Field.getText();

                    JSONObject data = new JSONObject(json_data.get(entity).toString());
                    username = data.getString("username");
                    url = data.getString("url");
                    password = data.getString("password");
                    note = data.getString("note");

                    inner_json_data.put("created", data.get("created")); // don't change the creation date

                    table_model.setValueAt(new_entity_name, entity_index, 0); // modified column

                    // check if any field is modified
                    if (!compare(new_entity_name, entity) || !compare(Username_Field.getText(), username) || !compare(Url_Field.getText(), url) || !compare(String.valueOf(Password_Field.getPassword()), password) || !compare(Note_Area.getText(), note)) {
                        // then change the text in the table with the new modified date
                        table_model.setValueAt(dtf.format(LocalDateTime.now()), entity_index, 2); // modified column
                        // and update the date in the json data
                        inner_json_data.put("modified", time);
                        isAddedOrEdited = true;
                    } else {
                        // otherwise insert the same existing date
                        inner_json_data.put("modified", data.get("modified"));
                        // the above line should be applied even if nothing modified..
                        // since that the "modified" key have a default value of ""..
                        // for the new entity operation, so if it's editing mode ..
                        // and some fields edited or not, the "modified" value should..
                        // be initialized as the entity value of "modified" key.
                    }
                    if (!compare(new_entity_name, entity)) { // insert if the entity name changed
                        json_data.remove(entity); // remove the key
                        json_data.put(new_entity_name, inner_json_data); // put the new key(entity name) with the new data
                        isAddedOrEdited = true;
                    } else { // entity name not changed
                        json_data.put(entity, inner_json_data); // insert data in with the same key
                        isAddedOrEdited = false;
                    }

                    frame.dispose();
                } else {
                    if (!json_data.keySet().contains(Entity_Field.getText())) {
                        String entity = Entity_Field.getText();

                        String[] table_data = {entity, time, ""};
                        table_model.addRow(table_data);
                        json_data.put(entity, inner_json_data);
                        
                        isAddedOrEdited = true;
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "This entity is already exists", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
//                System.out.println(json_data);
            }
        });
        
        DocumentListener doc_listener = new DocumentListener() {
            private void update(JSONObject json_data) {
                boolean status;

                if (isEditing == false) { // adding mode
                    status = (notEmpty(Entity_Field) // check for entity field is filled.
                            & notEmpty(Password_Field) // check for password field is filled.

                            // if the entity name not in the list then add it.
                            & !json_data.keySet().contains(Entity_Field.getText()));
                } else { // editing mode
                    String entity, new_entity;
                    
                    entity = table_model.getValueAt(entity_index, 0).toString();
                    new_entity = Entity_Field.getText();

                    status = (notEmpty(Entity_Field) // check for entity field is filled.
                            & notEmpty(Password_Field) // check for password field is filled.

                            // check if the entity in the list and the entity is the same one (the entity name haven't been modified).
                            & ((json_data.keySet().contains(new_entity) & new_entity.equals(entity))
                            // or the entity name is not in the list (available).
                            || !json_data.keySet().contains(new_entity)));
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
            String entity, username, url, password, note;
            
            entity = table_model.getValueAt(entity_index, 0).toString();

            JSONObject data = new JSONObject(json_data.get(entity).toString());
            username = data.getString("username");
            url = data.getString("url");
            password = data.getString("password");
            note = data.getString("note");

            Entity_Field.setText(entity);
            Username_Field.setText(username);
            Url_Field.setText(url);
            Password_Field.setText(password);
            Note_Area.setText(note);
        }

        // ---------------------------------------------------------------------
        ComponentOrientation orientation = parent.getComponentOrientation();

        frame.applyComponentOrientation(orientation);

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
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setModal(true);
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);

        return isAddedOrEdited;
    }
}
