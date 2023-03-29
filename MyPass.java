package com.virus.MyPass;

/*
 *
 * MyPass (v4.2.0) - By SecVirus (c) 4Ever
 *
 *
 * This file contains the main class.
 *
 *  > ICONS
 * -> https://jiconfont.github.io/fontawesome
 *  > Graphics icons
 *  -> https://graphics.keenthemes.com/
 *  > UI
 *  -> https://mvnrepository.com/artifact/com.formdev/flatlaf
 *
 * BackGround(hex=46494b, r=70,g=73,b=75)
 *
 */
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.icons.FlatSearchWithHistoryIcon;
import com.virus.MyPass.history.history_vars;
import com.virus.MyPass.ui.Converter;
import com.virus.MyPass.ui.Entity;
import com.virus.MyPass.ui.KeyBoard;
import com.virus.MyPass.ui.MGenerator;
import com.virus.MyPass.ui.MPasswordDialog;
import com.virus.MyPass.ui.ui_vars;
import com.virus.MyPass.util.AES.Decryption;
import com.virus.MyPass.util.AES.Encryption;
import com.virus.MyPass.util.ClipBoard.Clear;
import com.virus.MyPass.util.ClipBoard.Copy;
import com.virus.MyPass.util.ClipBoard.Get;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EventObject;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPass {

    static JFrame window;
    static JMenuBar menubar; // LEAF
    static JMenu file_menu,
            edit_menu,
            edit_copy_entity_menu,
            appearance_menu,
            appearance_theme_menu,
            tool_menu,
            window_menu,
            help_menu,
            about_menu,
            direction_window_menu; // This one is for Manager Table.
    static JMenuItem NewFile_menu, OpenFile_menu, SaveFile_menu,
            NewEntity_menu, EditEntity_menu, DeleteEntity_menu,
            edit_copy_entity_entity_menu, edit_copy_entity_username_menu, edit_copy_entity_url_menu, edit_copy_entity_password_menu, edit_copy_entity_note_menu,
            edit_change_password_menu,
            generator_tool_menu, converter_tool_menu, clear_clipboard_tool_menu,
            help_about_software, help_report_bug;
    static JRadioButtonMenuItem menu_light_theme, menu_dark_theme, manager_direction_rtl, manager_direction_ltr;
    static JCheckBoxMenuItem menu_manager_dragable, appearance_animate, topmost_window_menu, resizeable_window_menu, auto_clear_clipboard_tool_menu, window_toolbar_dragable_menu;
    static JPanel panel, manager_frame;
    static JToolBar toolbar, subToolbar;
    static JTextField search_field;
    static JButton searchHistory_btn,
            NewFile, OpenFile, SaveFile,
            NewEntity, EditEntity, DeleteEntity,
            GeneratePassword,
            CopyEntity, CopyUsername, CopyUrl, CopyPassword, CopyNote, ClearClipboard,
//            LogToolbarButton,
            ExitToolbarButton;
    static FlatButton EntityCounter, tutorial;
    static JTable table;
    static JScrollPane manager_scrollPane;
    static DefaultTableModel table_model;
    static DefaultTableCellRenderer table_row_center;
    static ButtonGroup themes_groub, manager_direction_group;
    static TableRowSorter<TableModel> manager_sorter;
    static JSONObject json_data = new JSONObject();
    static String session_filename, session_filepath, current_title, encryption_key;
    static boolean saved_changes, isNew;

    public static void main(String[] args) {        
        saved_changes = true;
        IconFontSwing.register(FontAwesome.getIconFont());

        window = new JFrame(ui_vars.tool_title.toString());
        window.getRootPane().putClientProperty("JRootPane.titleBarBackground", ui_vars.color(25));
        

        UIManager.put("Panel.arc", 0);

//        UIManager.put("PasswordField.revealIconColor", ui_vars.color(255));
        UIManager.put("PasswordField.capsLockIconColor", ui_vars.color(139));
        UIManager.put("TextField.selectionBackground", ui_vars.color(100));
        
        UIManager.put("ScrollPane.smoothScrolling", true);
        UIManager.put("showButtons", true);
                
        UIManager.put("ToolBar.dockingBackground", ui_vars.color(190));
        UIManager.put("ToolBar.floatingBackground", ui_vars.color(70));
        UIManager.put("ToolBar.gripColor", ui_vars.color(255));

        UIManager.put("Button.background", ui_vars.color(50));
        UIManager.put("Button.disabledBackground", ui_vars.color(20));
        UIManager.put("Button.default.background", ui_vars.color(50));
        UIManager.put("Button.hoverBackground", ui_vars.color(35));
        UIManager.put("Button.pressedBackground", ui_vars.color(50));
//        UIManager.put("Button.selectedBackground", ui_vars.color(30));

        UIManager.put("Button.toolbar.hoverBackground", ui_vars.color(25));
        UIManager.put("Button.toolbar.pressedBackground", ui_vars.color(50));
        
//        UIManager.put("CheckBox.icon.selectedBackground", ui_vars.color(150));
//        UIManager.put("CheckBox.icon.checkmarkColor", ui_vars.color(0));

//        UIManager.put("TitlePane.unifiedBackground", true);
//        UIManager.put("TitlePane.background", ui_vars.color(50));
//        UIManager.put("ToolBar.background", ui_vars.color(50));
//        UIManager.put("TableHeader.background", ui_vars.color(30));
//        UIManager.put("Table.background", ui_vars.color(30));
//        UIManager.put("TableHeader.separatorColor", ui_vars.color(150));
        UIManager.put("TableHeader.bottomSeparatorColor", ui_vars.color(150));
        UIManager.put("Table.selectionBackground", ui_vars.color(150));
//        UIManager.put("Table.gridColor", ui_vars.color(150));
        UIManager.put("Table.sortIconColor", ui_vars.color(255));
        UIManager.put("TableHeader.sortIconPosition", "left");
        UIManager.put("Table.showHorizontalLines", true);
//        UIManager.put("Table.showVerticalLines", true);

        UIManager.put("TitlePane.foreground", ui_vars.color(200));
        UIManager.put("TitlePane.background", ui_vars.color(30)); // shows as #673d01
//        UIManager.put("TitlePane.inactiveBackground", Color.decode("#090700"));

        UIManager.put("TitlePane.borderColor", ui_vars.color(255));

        UIManager.put("Spinner.buttonSeparatorColor", ui_vars.color(100));
        UIManager.put("Component.arc", 999); // roundness of some elements

        UIManager.put("TextComponent.arc", 10); // roundness of some elements
        UIManager.put("JButton.buttonType", "roundRect"); // roundness of some elements

        UIManager.put("TitlePane.closeHoverBackground", ui_vars.color(150));
        UIManager.put("TitlePane.closePressedBackground", ui_vars.color(200));

//        UIManager.put("MenuBar.foreground", Color.lightGray);
        UIManager.put("MenuBar.hoverBackground", ui_vars.color(75));
//        UIManager.put("MenuBar.selectionForeground", Color.decode("#ffffff"));
        UIManager.put("MenuBar.selectionBackground", ui_vars.color(175));
        UIManager.put("MenuBar.underlineSelectionColor", ui_vars.color(175));
        UIManager.put("MenuItem.underlineSelectionColor", ui_vars.color(175));
        UIManager.put("MenuItem.underlineSelectionBackground", ui_vars.color(35));
        UIManager.put("MenuItem.underlineSelectionCheckBackground", ui_vars.color(75));
        UIManager.put("MenuItem.selectionBackground", ui_vars.color(200));
        UIManager.put("CheckBoxMenuItem.icon.checkmarkColor", ui_vars.color(255));
        UIManager.put("Menu.icon.arrowColor", ui_vars.color(255));

        UIManager.put("MenuBar.borderColor", ui_vars.color(175));
        UIManager.put("MenuItem.selectionType", "underline");
        UIManager.put("MenuBar.underlineSelectionBackground", ui_vars.color(35));

        UIManager.put("Component.arrowType", "chevron"); // triangle

        UIManager.put("TitlePane.centerTitle", true); // center window title 

        UIManager.put("Button.borderColor", ui_vars.color(50));
        UIManager.put("Button.hoverBorderColor", ui_vars.color(100));
        UIManager.put("Button.toolbar.focusColor", ui_vars.color(150));

        UIManager.put("Component.borderColor", ui_vars.color(50));
        UIManager.put("Component.focusedBorderColor", ui_vars.color(255));

//        UIManager.put("Component.focusColor", new Color(254, 152, 1, 50));
//        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(new Color(254, 152, 1, 100)));
//        UIManager.put("ToolBar.separatorColor", new Color(254, 152, 1, 50));
//        window.setIconImage(new ImageIcon("mypass.png").getImage());
        Theme.toggle_theme(window, ui_vars.default_theme);

        toolbar = new JToolBar();
        toolbar.setFloatable(false);

        panel = new JPanel(new BorderLayout());
        manager_frame = new JPanel(new BorderLayout());

        menubar = new JMenuBar();

        // =====================================================================
        file_menu = new JMenu("File");

        NewFile_menu = new JMenuItem("New");
        NewFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE, ui_vars.icons_size, ui_vars.color));
        NewFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        NewFile_menu.addActionListener(e -> {
            Data.new_file();
        });
        NewFile_menu.setToolTipText("New Session");

        OpenFile_menu = new JMenuItem("Open");
        OpenFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, ui_vars.icons_size, ui_vars.color));
        OpenFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        OpenFile_menu.addActionListener(e -> {
            Data.open_file();
        });
        OpenFile_menu.setToolTipText("Open Existing Session");

        SaveFile_menu = new JMenuItem("Save");
        SaveFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, ui_vars.icons_size, ui_vars.color));
        SaveFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        SaveFile_menu.setEnabled(false);
        SaveFile_menu.addActionListener(e -> {
            Data.save_file();
        });
        SaveFile_menu.setToolTipText("Save Current Session");

        edit_menu = new JMenu("Edit");

        NewEntity_menu = new JMenuItem("New");
        NewEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.USER_PLUS, ui_vars.icons_size, ui_vars.color));
        NewEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        NewEntity_menu.setEnabled(false);
        NewEntity_menu.addActionListener(e -> {
            Data.new_entity();
        });
        NewEntity_menu.setToolTipText("New Entity");

        EditEntity_menu = new JMenuItem("Edit");
        EditEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, ui_vars.icons_size, ui_vars.color));
        EditEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        EditEntity_menu.setEnabled(false);
        EditEntity_menu.addActionListener(e -> {
            Data.edit_entity();
        });
        EditEntity_menu.setToolTipText("Edit Entity");

        DeleteEntity_menu = new JMenuItem("Delete");
        DeleteEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, ui_vars.icons_size, ui_vars.color));
        DeleteEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        DeleteEntity_menu.setEnabled(false);
        DeleteEntity_menu.addActionListener(e -> {
            Data.delete_entity();
        });
        DeleteEntity_menu.setToolTipText("Delete Entity");

        // --------------
        edit_copy_entity_menu = new JMenu("Copy");
        edit_copy_entity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));

        edit_copy_entity_entity_menu = new JMenuItem("Entity");
        edit_copy_entity_entity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.CUBE, ui_vars.icons_size, ui_vars.color));
        edit_copy_entity_entity_menu.addActionListener(e -> {
            Table.content.entity();
        });
        edit_copy_entity_entity_menu.setToolTipText("Copy Entity");
        edit_copy_entity_entity_menu.setEnabled(false);

        edit_copy_entity_username_menu = new JMenuItem("Username");
        edit_copy_entity_username_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.USER, ui_vars.icons_size, ui_vars.color));
        edit_copy_entity_username_menu.addActionListener(e -> {
            Table.content.copy("username");
        });
        edit_copy_entity_username_menu.setToolTipText("Copy Username for selected entity");
        edit_copy_entity_username_menu.setEnabled(false);

        edit_copy_entity_url_menu = new JMenuItem("Url");
        edit_copy_entity_url_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.LINK, ui_vars.icons_size, ui_vars.color));
        edit_copy_entity_url_menu.addActionListener(e -> {
            Table.content.copy("url");
        });
        edit_copy_entity_url_menu.setToolTipText("Copy Url for selected entity");
        edit_copy_entity_url_menu.setEnabled(false);

        edit_copy_entity_password_menu = new JMenuItem("Password");
        edit_copy_entity_password_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.KEY, ui_vars.icons_size, ui_vars.color));
        edit_copy_entity_password_menu.addActionListener(e -> {
            Table.content.copy("password");
        });
        edit_copy_entity_password_menu.setToolTipText("Copy Password for selected entity");
        edit_copy_entity_password_menu.setEnabled(false);

        edit_copy_entity_note_menu = new JMenuItem("Note");
        edit_copy_entity_note_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.STICKY_NOTE, ui_vars.icons_size, ui_vars.color));
        edit_copy_entity_note_menu.addActionListener(e -> {
            Table.content.copy("note");
        });
        edit_copy_entity_note_menu.setToolTipText("Copy Note for selected entity");
        edit_copy_entity_note_menu.setEnabled(false);

        edit_change_password_menu = new JMenuItem("Change Password");
        edit_change_password_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.MAGIC, ui_vars.icons_size, ui_vars.color));
        edit_change_password_menu.addActionListener(e -> {
            JSONObject submit_key_dialog = MPasswordDialog.createDialog(window, "(password verfiction)");
            int submit_answer = Integer.parseInt(submit_key_dialog.get("answer").toString());
            String submit_key = submit_key_dialog.get("password").toString();
            if (submit_answer == 0) {
                if (submit_key.length() > 0) {
                    if (submit_key.equals(encryption_key)) {
                        JSONObject new_key_dialog = MPasswordDialog.createDialog(window, "(new password)");
                        int new_answer = Integer.parseInt(new_key_dialog.get("answer").toString());
                        String new_key = new_key_dialog.get("password").toString();

                        if (new_answer == 0) { // if pressed submit/ok/done in the password dialog
                            if (new_key.length() > 0) {
                                int answer = JOptionPane.showOptionDialog(window, "The session '" + session_filename + "' password will be changed.\n\nAre you sure?", "Change password?!", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                                if (answer == 0) {
                                    encryption_key = new_key;
                                    unsaved();

                                    JOptionPane.showMessageDialog(window, "Password changed successfully!", "Password Changed!", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(window, "Couldn't verify password to change it.", "Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        edit_change_password_menu.setToolTipText("Change Session password");
        edit_change_password_menu.setEnabled(false);

        // Seperator ------------
        menu_manager_dragable = new JCheckBoxMenuItem("Dragable");
        menu_manager_dragable.setToolTipText("Enable/Disable abillity to drag text from manager table");
        menu_manager_dragable.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROWS, ui_vars.icons_size, ui_vars.color));

        appearance_menu = new JMenu("Appearance");

        appearance_theme_menu = new JMenu("Theme");
        appearance_theme_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PAINT_BRUSH, ui_vars.icons_size, ui_vars.color));

        themes_groub = new ButtonGroup();
        menu_light_theme = new JRadioButtonMenuItem("Light Theme");
//        menu_light_theme.setMnemonic(KeyEvent.VK_L);
        menu_light_theme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        menu_light_theme.setToolTipText("Toggle light theme");

        menu_dark_theme = new JRadioButtonMenuItem("Dark Theme", true);
        menu_dark_theme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        menu_dark_theme.setToolTipText("Toggle dark theme");

        appearance_animate = new JCheckBoxMenuItem("Animate", true);
        appearance_animate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        appearance_animate.setToolTipText("Animate theme changes");

        tool_menu = new JMenu("Tool");

        generator_tool_menu = new JMenuItem("Generator", IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size, ui_vars.color));
        generator_tool_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generator_tool_menu.setToolTipText("Password Generator");
        generator_tool_menu.addActionListener(e -> {
            MGenerator.run(window, null);
        });

        converter_tool_menu = new JMenuItem("Converter", IconFontSwing.buildIcon(FontAwesome.RECYCLE, ui_vars.icons_size, ui_vars.color));
        converter_tool_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        converter_tool_menu.setToolTipText("Convert MyPass passwords file <3.2.0v to 4.2.0v");
        converter_tool_menu.addActionListener(e -> {
            Converter.run(window);
        });

        clear_clipboard_tool_menu = new JMenuItem("Clear Clipboard", IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, ui_vars.color));
        clear_clipboard_tool_menu.setSelected(false);
        clear_clipboard_tool_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        clear_clipboard_tool_menu.setToolTipText("Clear Clipboard (For Security Reasons)");
        clear_clipboard_tool_menu.addActionListener(e -> {
            Clear.content();
        });

        auto_clear_clipboard_tool_menu = new JCheckBoxMenuItem("Auto Clear Clipboard");
        auto_clear_clipboard_tool_menu.setSelected(false);
        auto_clear_clipboard_tool_menu.setToolTipText("Auto Clear Clipboard (For Security Reasons)");

        window_menu = new JMenu("Window");

        topmost_window_menu = new JCheckBoxMenuItem("Topmost");
        topmost_window_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        topmost_window_menu.setSelected(false);
        topmost_window_menu.setToolTipText("On top of all other windows");

        topmost_window_menu.addActionListener(twe -> {
            window.setAlwaysOnTop(topmost_window_menu.isSelected());
        });

        resizeable_window_menu = new JCheckBoxMenuItem("Resizeable");
        resizeable_window_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        resizeable_window_menu.setSelected(true);

        resizeable_window_menu.addActionListener(twe -> {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }
            window.setResizable(resizeable_window_menu.isSelected());
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        });
        
        window_toolbar_dragable_menu = new JCheckBoxMenuItem("Toolbar dragable");
        window_toolbar_dragable_menu.setSelected(false);
        window_toolbar_dragable_menu.addActionListener(e->{toolbar.setFloatable(window_toolbar_dragable_menu.isSelected());});

        help_menu = new JMenu("Help");

        about_menu = new JMenu("About");
        about_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, ui_vars.icons_size, ui_vars.color));

        help_about_software = new JMenuItem("Software");
        help_about_software.setIcon(IconFontSwing.buildIcon(FontAwesome.DESKTOP, ui_vars.icons_size, ui_vars.color));

        help_report_bug = new JMenuItem("Report a Bug");
        help_report_bug.setIcon(IconFontSwing.buildIcon(FontAwesome.BUG, ui_vars.icons_size, ui_vars.color));
        help_report_bug.addActionListener(e->{visit("https://github.com/isecvirus/MyPass/issues/new");});

        tutorial = new FlatButton();
        tutorial.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOK, ui_vars.icons_size, ui_vars.color));
//        tutorial.setEnabled(false);
        tutorial.setToolTipText("Tutorial");
        tutorial.setButtonType(FlatButton.ButtonType.toolBarButton);
        tutorial.setFocusable(false);
        tutorial.addActionListener(e->{visit("https://github.com/isecvirus/MyPass/blob/main/README.md#tutorial");});

        search_field = new JTextField();
        search_field.setLayout(new BorderLayout());
        KeyBoard.show(window, search_field);

//        search_manager.setMargin(new Insets(5, 5, 5, 5));
//        search_manager.setBorder(new EmptyBorder(10, 10, 10, 10));
        search_field.setToolTipText("Search.. (RegEx supported)");

        JToolBar PrefixSearchToolbar = new JToolBar();
        PrefixSearchToolbar.addSeparator();

        JToolBar SufixSearchToolbar = new JToolBar();

        FlatButton results_count_label = new FlatButton();
        results_count_label.setVisible(false);

        searchHistory_btn = new JButton((Icon) new FlatSearchWithHistoryIcon(true));
        searchHistory_btn.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem clear_history = new JMenuItem("Clear History", IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, ui_vars.color));
            clear_history.setToolTipText("Clear Search History");
            clear_history.addActionListener(ev -> {
                history_vars.SearchHistory.clear();
            });
            clear_history.setEnabled((!history_vars.SearchHistory.isEmpty()));
            popupMenu.add(clear_history);
            if (!history_vars.SearchHistory.isEmpty()) {
                popupMenu.addSeparator();
            }

            for (int sr = 0; sr < history_vars.SearchHistory.size(); sr++) { // sr=search result
                String query = history_vars.SearchHistory.get(sr);
                JMenuItem this_item = new JMenuItem(query);
                this_item.addActionListener(item_al -> {
                    search_field.setText(query);
                });

                popupMenu.add(this_item);
            }
            popupMenu.show(searchHistory_btn, 0, searchHistory_btn.getHeight());
        });
//        PrefixSearchToolbar.add(SearchQuery);

        SufixSearchToolbar.addSeparator();
        SufixSearchToolbar.add(results_count_label);
        SufixSearchToolbar.add(searchHistory_btn);

//        search_field.putClientProperty("JTextField.leadingIcon", IconFontSwing.buildIcon(FontAwesome.SEARCH, ui_vars.icons_size, ui_vars.icons_color));
//        search_field.putClientProperty("JTextField.leadingComponent", PrefixSearchToolbar);
        search_field.putClientProperty("JTextField.trailingComponent", SufixSearchToolbar);
        search_field.putClientProperty("JTextField.placeholderText", "Search..");
        search_field.putClientProperty("JTextField.showClearButton", true);
//        search_field.putClientProperty("JComponent.roundRect", Boolean.valueOf(true));        

        table_model = new DefaultTableModel(0, ui_vars.manager_columns.length);
        table_model.setColumnIdentifiers(ui_vars.manager_columns);
        table_row_center = new DefaultTableCellRenderer();

        table = new JTable(table_model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        ;
        };
//        DefaultTableModel mtm = (DefaultTableModel) table.getModel(); // manager table model
        table.getTableHeader().setReorderingAllowed(false);

        Manager.CenterTableRows(table, table_row_center, ui_vars.manager_columns);
//        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(0); // 0=single selection
//        table.setAutoCreateRowSorter(true);

        manager_sorter = new TableRowSorter<>(table_model);
        table.setRowSorter(manager_sorter);

        // make columns unsortable in the passwords ..
        // .. manager table (avoiding passwords arrangment problems).
        for (int i = 0; i < ui_vars.manager_columns.length; i++) {
            manager_sorter.setSortable(i, false);
        }
//        manager_sorter.setSortsOnUpdates(true);
        search_field.getDocument().addDocumentListener(new DocumentListener() {
            public void search(String query) {
                if (query.length() > 0) {
                    try {
                        manager_sorter.setRowFilter(RowFilter.regexFilter(query));
                        search_field.setForeground(Color.decode(ui_vars.valid_search_color));
                    } catch (Exception error) { // PatternSyntaxException, NumberFormatException
                        search_field.setForeground(Color.decode(ui_vars.invalid_search_color));
                    }

                    if (!history_vars.SearchHistory.contains(query)) {
                        history_vars.SearchHistory.add(query);
                    }
                } else {
                    manager_sorter.setRowFilter(null);
                    search_field.setForeground(Color.decode(ui_vars.valid_search_color));
                }

                int results_count = table.getRowCount();
                if (search_field.getText().length() > 0) {
                    results_count_label.setVisible(true);
                    results_count_label.setText(String.valueOf(results_count));
                } else {
                    results_count_label.setText("");
                    results_count_label.setVisible(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(search_field.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(search_field.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                search(search_field.getText());
            }
        });

        direction_window_menu = new JMenu("Direction");
        direction_window_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_CENTER, ui_vars.icons_size, ui_vars.color));

        manager_direction_group = new ButtonGroup();
        manager_direction_rtl = new JRadioButtonMenuItem("RTL");
        manager_direction_rtl.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_RIGHT, ui_vars.icons_size, ui_vars.color));

        manager_direction_ltr = new JRadioButtonMenuItem("LTR", true);
        manager_direction_ltr.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_LEFT, ui_vars.icons_size, ui_vars.color));

        manager_direction_group.add(manager_direction_rtl);
        manager_direction_group.add(manager_direction_ltr);
        direction_window_menu.add(manager_direction_rtl);
        direction_window_menu.add(manager_direction_ltr);

        manager_direction_rtl.addActionListener((ActionEvent e) -> {
            Ui.RTL();
        });
        manager_direction_ltr.addActionListener((ActionEvent e) -> {
            Ui.LTR();
        });

        table.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        table.setFocusable(false);
//        table.setColumnSelectionAllowed(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        manager_scrollPane = new JScrollPane(table);

        menu_manager_dragable.addActionListener((ActionEvent e) -> {
            Manager.draggable(table);
        });
        menu_light_theme.addActionListener((ActionEvent e) -> {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            Theme.toggle_theme(window, ui_vars.light_theme);

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        });
        menu_dark_theme.addActionListener((ActionEvent e) -> {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            Theme.toggle_theme(window, ui_vars.dark_theme);

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        });
        help_about_software.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window, "MyPass is a password manager.\nUse it to secure, store and manage passwords.\n\nJava 18.0.1.1 2022-04-22 (build 18.0.1.1+2-6)", "About MyPass", JOptionPane.INFORMATION_MESSAGE);
        });

//        toolbar.add(Box.createHorizontalStrut(5), 0);
        NewFile = new JButton(IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE, ui_vars.icons_size + 5, ui_vars.color));
        NewFile.setToolTipText("New File");

        OpenFile = new JButton(IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, ui_vars.icons_size + 5, ui_vars.color));
        OpenFile.setToolTipText("Open File");

        SaveFile = new JButton(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, ui_vars.icons_size + 5, ui_vars.color));
        SaveFile.setToolTipText("Save File");
        SaveFile.setEnabled(false);

        // ~~~~~~~~~~~~~~~~~
        NewEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.USER_PLUS, ui_vars.icons_size + 5, ui_vars.color));
        NewEntity.setToolTipText("New Entity");
        NewEntity.setEnabled(false);

        EditEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.PENCIL, ui_vars.icons_size + 5, ui_vars.color));
        EditEntity.setToolTipText("Edit Entity");
        EditEntity.setEnabled(false);

        DeleteEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.TRASH, ui_vars.icons_size + 5, ui_vars.color));
        DeleteEntity.setToolTipText("Delete Entity");
        DeleteEntity.setEnabled(false);

        // ~~~~~~~~~~~~~~~~~
        CopyEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.CUBE, ui_vars.icons_size + 5, ui_vars.color));
        CopyEntity.setToolTipText("Copy Entity");
        CopyEntity.setEnabled(false);

        CopyUsername = new JButton(IconFontSwing.buildIcon(FontAwesome.USER, ui_vars.icons_size + 5, ui_vars.color));
        CopyUsername.setToolTipText("Copy Username");
        CopyUsername.setEnabled(false);

        CopyUrl = new JButton(IconFontSwing.buildIcon(FontAwesome.LINK, ui_vars.icons_size + 5, ui_vars.color));
        CopyUrl.setToolTipText("Copy Url");
        CopyUrl.setEnabled(false);

        CopyPassword = new JButton(IconFontSwing.buildIcon(FontAwesome.KEY, ui_vars.icons_size + 5, ui_vars.color));
        CopyPassword.setToolTipText("Copy Password");
        CopyPassword.setEnabled(false);

        CopyNote = new JButton(IconFontSwing.buildIcon(FontAwesome.STICKY_NOTE, ui_vars.icons_size + 5, ui_vars.color));
        CopyNote.setToolTipText("Copy Note");
        CopyNote.setEnabled(false);

        ClearClipboard = new JButton(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size + 5, ui_vars.color));
        ClearClipboard.setToolTipText("Clear Clipboard");
        ClearClipboard.setEnabled(false);

        check_clipboard_status();

        // ~~~~~~~~~~~~~~~~~
        GeneratePassword = new JButton(IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size + 5, ui_vars.color));
        GeneratePassword.setToolTipText("Generate Password");

        // ~~~~~~~~~~~~~~~~~
//        LogToolbarButton = new JButton(IconFontSwing.buildIcon(FontAwesome.FILE_TEXT, ui_vars.icons_size + 5, ui_vars.color));
//        LogToolbarButton.setToolTipText("Log");
        
        ExitToolbarButton = new JButton(IconFontSwing.buildIcon(FontAwesome.POWER_OFF, ui_vars.icons_size + 5, ui_vars.color));
        ExitToolbarButton.setToolTipText("Exit");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mpe) {
                Point point = mpe.getPoint();
                int row = table.rowAtPoint(point);

                Table.selectionChange();
                // if left button and double clicks
                if (mpe.getClickCount() == 2 & mpe.getButton() == 1) {
                    Data.edit_entity();
                }
                Table.selectionChange();
            }
        });

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            private void handleSelectionEvent(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {
                    return;
                }
                Table.selectionChange();
            }

            public void valueChanged(ListSelectionEvent lse) {
                handleSelectionEvent(lse);
            }
        });

        NewFile.addActionListener(e -> {
            if (!saved_changes) {
                // yes=0
                // no=1
                // cancel=2
                // closed=-1
                int answer = JOptionPane.showConfirmDialog(window, "Unsaved changes will be lost.\nDo you want to save?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (answer == 0) {
                    Data.save_file();
                } else if (answer == 1) {
                    Data.new_file();
                }
            } else {
                Data.new_file();
            }
        });
        OpenFile.addActionListener(e -> {
            Data.open_file();
        });
        SaveFile.addActionListener(e -> {
            Data.save_file();
        });

        NewEntity.addActionListener(e -> {
            Data.new_entity();
        });
        EditEntity.addActionListener(e -> {
            Data.edit_entity();
        });
        DeleteEntity.addActionListener(e -> {
            Data.delete_entity();
        });
        ClearClipboard.addActionListener(e -> {
            Clear.content();
        });
        GeneratePassword.addActionListener(e -> {
            MGenerator.run(window, null);
        });
        ExitToolbarButton.addActionListener(e -> {
            if (!saved_changes) {
                int answer = JOptionPane.showOptionDialog(window, "There is unsaved data, make sure to save it before exit.\n\nAre you sure?", "Exit?!", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                if (answer == 0) {
                    window.dispose();
                }
            } else {
                window.dispose();
            }
        });
        CopyEntity.addActionListener(e -> {
            Table.content.entity();
        });
        CopyUsername.addActionListener(e -> {
            Table.content.copy("username");
        });
        CopyUrl.addActionListener(e -> {
            Table.content.copy("url");
        });
        CopyPassword.addActionListener(e -> {
            Table.content.copy("password");
        });
        CopyNote.addActionListener(e -> {
            Table.content.copy("note");
        });

        Clipboard sys_clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        sys_clipboard.addFlavorListener((FlavorEvent e) -> {
            if (auto_clear_clipboard_tool_menu.isSelected()) {
                sys_clipboard.setContents(new StringSelection(""), null);
            }
            ClearClipboard.setEnabled((Get.asString().length() > 0));
            clear_clipboard_tool_menu.setEnabled((Get.asString().length() > 0));
        });

//        window.addFocusListener(new FocusListener() {
//            private void status() {
//                clear_clipboard_tool_menu.setEnabled((Get.asString().length() > 0));
//                ClearClipboard.setEnabled((Get.asString().length() > 0));
//            }
//            
//            @Override
//            public void focusGained(FocusEvent e) {status();}
//
//            @Override
//            public void focusLost(FocusEvent e) {status();}
//        
//        });
        toolbar.add(NewFile);
        toolbar.add(OpenFile);
        toolbar.add(SaveFile);
        toolbar.addSeparator();
        toolbar.add(NewEntity);
        toolbar.add(EditEntity);
        toolbar.add(DeleteEntity);
        toolbar.addSeparator();
        toolbar.add(CopyEntity);
        toolbar.add(CopyUsername);
        toolbar.add(CopyUrl);
        toolbar.add(CopyPassword);
        toolbar.add(CopyNote);
        toolbar.addSeparator();
        toolbar.add(GeneratePassword);
        toolbar.add(ClearClipboard);
        toolbar.addSeparator();
        toolbar.add(ExitToolbarButton);

        subToolbar = new JToolBar();

        EntityCounter = new FlatButton();
        EntityCounter.setText("Entity: 0");
        
        subToolbar.add(EntityCounter);
        subToolbar.addSeparator();
        subToolbar.add(search_field);
//        subToolbar.add(Box.createGlue());
        

//        search_manager.setVisible(true);
        manager_frame.add(toolbar, BorderLayout.BEFORE_FIRST_LINE);
        manager_frame.add(manager_scrollPane);

        panel.add(manager_frame);
        panel.add(subToolbar, BorderLayout.AFTER_LAST_LINE);

        menubar.add(file_menu);
        menubar.add(edit_menu);
        menubar.add(tool_menu);
        menubar.add(window_menu);
        menubar.add(appearance_menu);
        menubar.add(help_menu);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(tutorial);

        file_menu.add(NewFile_menu);
        file_menu.addSeparator();
        file_menu.add(OpenFile_menu);
        file_menu.add(SaveFile_menu);

        edit_menu.add(NewEntity_menu);
        edit_menu.add(EditEntity_menu);
        edit_menu.add(DeleteEntity_menu);
        edit_menu.addSeparator();
        edit_menu.add(edit_copy_entity_menu);
        edit_menu.addSeparator();
        edit_menu.add(edit_change_password_menu);

        edit_copy_entity_menu.add(edit_copy_entity_entity_menu);
        edit_copy_entity_menu.add(edit_copy_entity_username_menu);
        edit_copy_entity_menu.add(edit_copy_entity_url_menu);
        edit_copy_entity_menu.add(edit_copy_entity_password_menu);
        edit_copy_entity_menu.add(edit_copy_entity_note_menu);

        appearance_menu.add(appearance_theme_menu);
        appearance_menu.add(appearance_animate);
        appearance_theme_menu.add(menu_light_theme);
        appearance_theme_menu.add(menu_dark_theme);
        themes_groub.add(menu_light_theme);
        themes_groub.add(menu_dark_theme);

        tool_menu.add(generator_tool_menu);
        tool_menu.add(converter_tool_menu);
        tool_menu.addSeparator();
        tool_menu.add(clear_clipboard_tool_menu);
        tool_menu.add(auto_clear_clipboard_tool_menu);
//        tool_menu.add();

        window_menu.add(topmost_window_menu);
        window_menu.add(resizeable_window_menu);
        window_menu.add(direction_window_menu);
        window_menu.addSeparator();
        window_menu.add(window_toolbar_dragable_menu);
        
        
        help_menu.add(help_report_bug);
        help_menu.addSeparator();
        help_menu.add(about_menu);
        
        about_menu.add(help_about_software);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.add(menubar);
        window.setJMenuBar(menubar);
        window.setContentPane(panel);

//        window.addWindowFocusListener(new WindowAdapter() {
//            @Override
//            public void windowGainedFocus(WindowEvent e) {}
//        });
        window.setPreferredSize(new Dimension(ui_vars.screen_width / 2, ui_vars.screen_height / 2));
        panel.setVisible(true);

        window.setAlwaysOnTop(topmost_window_menu.isSelected());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private static void visit(String url) {
        try {
                Desktop.getDesktop().browse(new URL(url).toURI());
            } catch (URISyntaxException | IOException url_error) {}
    }

    private static void unsaved() {
        saved_changes = false;
        window.setTitle("* " + current_title);
        SaveFile.setEnabled(true);

        SaveFile_menu.setEnabled(true);
    }

    private static void saved() {
        saved_changes = true;
        window.setTitle(current_title);
        SaveFile.setEnabled(false);

        SaveFile_menu.setEnabled(false);
    }

    private static void check_clipboard_status() {
        clear_clipboard_tool_menu.setEnabled((Get.asString().length() > 0));
        ClearClipboard.setEnabled((Get.asString().length() > 0));
    }

    private static class Table {

        private static boolean delete_selected() {
            int selected = table.getSelectedRow();
            if (selected >= 0 && table.getRowCount() > 0) {
                // closed_window=-1
                // yes=0
                // no=1
                String message, entity;

                entity = table.getValueAt(table.getSelectedRow(), 0).toString();

                message = String.format("Delete '%s'", entity);
                int answer = JOptionPane.showConfirmDialog(window, "Are you sure?", message, JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    table_model.removeRow(selected);
                    json_data.remove(entity);
                    SwingUtilities.updateComponentTreeUI(window);
                    unfocused();

                    unsaved();

                    return true;
                }
            }
            return false;
        }

        private static void unfocused() {
            CopyEntity.setEnabled(false);
            CopyUsername.setEnabled(false);
            CopyUrl.setEnabled(false);
            CopyPassword.setEnabled(false);
            CopyNote.setEnabled(false);

            EditEntity.setEnabled(false);
            DeleteEntity.setEnabled(false);
        }

        private static void selectionChange() {
            if (table.getSelectedRow() >= 0 & table.getRowCount() > 0) {
                String entity = table.getValueAt(table.getSelectedRow(), 0).toString();
                JSONObject data = new JSONObject(json_data.get(entity).toString());

                CopyEntity.setEnabled((entity.length() > 0));
                CopyUsername.setEnabled((data.get("username").toString().length() > 0));
                CopyUrl.setEnabled((data.get("url").toString().length() > 0));
                CopyPassword.setEnabled((data.get("password").toString().length() > 0));
                CopyNote.setEnabled((data.get("note").toString().length() > 0));

                edit_copy_entity_entity_menu.setEnabled(true);
                edit_copy_entity_username_menu.setEnabled(true);
                edit_copy_entity_url_menu.setEnabled(true);
                edit_copy_entity_password_menu.setEnabled(true);
                edit_copy_entity_note_menu.setEnabled(true);

                EditEntity.setEnabled(true);
                DeleteEntity.setEnabled(true);

                EditEntity_menu.setEnabled(true);
                DeleteEntity_menu.setEnabled(true);
            }
        }

        class content {

            private static String returnEntity() {
                int selected = table.getSelectedRow();

                if (selected >= 0 && table.getRowCount() > 0) {
                    return table.getValueAt(selected, 0).toString();
                }
                return "";
            }

            private static void entity() {
                Copy.string(returnEntity());
            }

            private static void copy(String key) {
                String entity = returnEntity();
                if (entity.length() > 0) {
                    String content = new JSONObject(json_data.get(entity).toString()).get(key).toString();
                    Copy.string(content);
                }
            }
        }
    }

    private static class Ui {

        private static void RTL() {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            table.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            window.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            SwingUtilities.updateComponentTreeUI(window);

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        }

        private static void LTR() {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            table.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            window.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            SwingUtilities.updateComponentTreeUI(window);

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        }

        private static void put_session_filename(File file) {
            session_filename = file.getName();
            session_filepath = file.getAbsolutePath();

            if (!session_filename.endsWith(".mp")) {
                session_filename += ".mp";
            }
            if (!session_filepath.endsWith(".mp")) {
                session_filepath += ".mp";
            }
        }
    }

    private static class Data {

        private static void clear_table() {
            for (int i = 0; i < table_model.getRowCount(); i++) {
                table_model.removeRow(i);
            }
            table_model.setRowCount(0);
            table.clearSelection();
            EntityCounter.setText("Entity: " + String.valueOf(json_data.length()));
        }

        private static void new_file() {
            JFileChooser filedialog = new JFileChooser(Paths.get("").toAbsolutePath().toString());
            filedialog.setDialogTitle("New File");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("MyPass (passwords file)", "mp", "mypass");
            filedialog.setFileFilter(filter);

            int returnVal = filedialog.showDialog(window, "Confirm");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = filedialog.getSelectedFile().toString();

                if (filename.length() > 0) {
                    Ui.put_session_filename(filedialog.getSelectedFile());

                    current_title = session_filename + " - " + ui_vars.tool_title;
                    window.setTitle(current_title);
                    NewEntity.setEnabled(true);
                    edit_change_password_menu.setEnabled(false);
                    isNew = true;

                    clear_table();
                    json_data.clear();
                } else {
                    JOptionPane.showMessageDialog(window, "Can't leave this field empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private static void save_data_to_file(String key) {
            try {

                byte[] encrypted_data = Encryption.encrypt(json_data.toString(), key);
                FileOutputStream file = new FileOutputStream(session_filepath);
                file.write(encrypted_data);

                isNew = false;
                saved();
            } catch (Exception error) {
                JOptionPane.showMessageDialog(window,
                        "Couldn't save data to '" + session_filepath + "', Try Again.\n\nError: " + error.getMessage(),
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

        private static void save_file() {
            if (!saved_changes) {
                if (isNew) {

                    JSONObject password_dialog = MPasswordDialog.createDialog(window, null);

                    String key = password_dialog.getString("password");
                    int answer = password_dialog.getInt("answer");

                    if (answer == 0 & key.length() > 0) {
                        save_data_to_file(key);

                        encryption_key = key;
                        edit_change_password_menu.setEnabled(true);
                    }
                } else {
                    save_data_to_file(encryption_key);
                }
            }
        }

        private static void open_file() {
            JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
            fc.setDialogTitle("Open File");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("MyPass (passwords file)", "mp", "mypass");
            fc.setFileFilter(filter);
            int returnVal = fc.showDialog(window, "Open");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filename, key;

                filename = fc.getSelectedFile().getAbsolutePath();

                JSONObject password_dialog = MPasswordDialog.createDialog(window, null);

                key = password_dialog.getString("password");
                int answer = password_dialog.getInt("answer");
                if (answer == 0 & key.length() > 0) {
                    try {

                        byte[] encrypted_data = Files.readAllBytes(Paths.get(filename));
                        String loaded_data = Decryption.decrypt(encrypted_data, key);

                        new JSONObject(loaded_data); // trying to parse json data
                        // if an error occured then data will not be erased from the table (:
                        // and method execution will be stopped.

                        clear_table(); // should be right here to reset the table.

                        json_data = new JSONObject(loaded_data);
                        json_data.keySet().forEach(entity -> {
                            JSONObject entity_data = new JSONObject(json_data.get(entity).toString());
                            String creation, modification;

                            creation = entity_data.getString("created");
                            modification = entity_data.getString("modified");

                            table_model.addRow(new String[]{entity, creation, modification});
                        });

                        Ui.put_session_filename(fc.getSelectedFile());

                        current_title = session_filename + " - " + ui_vars.tool_title;
                        window.setTitle(current_title);
                        NewEntity.setEnabled(true);
                        isNew = false;

                        encryption_key = key;
                        edit_change_password_menu.setEnabled(true);
                        EntityCounter.setText("Entity: " + String.valueOf(json_data.length()));
                        search_field.setText("");
                    } catch (JSONException json_malformed) {
                        JOptionPane.showMessageDialog(window, "Data looks malformed", "Error!", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException incorrect_key) {
                        JOptionPane.showMessageDialog(null, "Incorrect password", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(window, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        private static void new_entity() {
            String temp = json_data.toString(); // copy current json data to check for changes

            new Entity().EntityWindow(window, "New Entity", table_model, json_data, -1, false);

            boolean isAddedOrEdited = !json_data.toString().equals(temp); // if old data not equals to current data..
            // then it's been modified(either added new entity or modified one)

            if (isAddedOrEdited) {
                EntityCounter.setText("Entity: " + String.valueOf(json_data.length()));
                unsaved();
                search_field.setText("");
            }
        }

        private static void edit_entity() {
            String temp = json_data.toString(); // copy current json data to check for changes

            int row = table.getSelectedRow();
            new Entity().EntityWindow(window, "Edit Entity", table_model, json_data, row, true);
            table.updateUI();

            boolean isAddedOrEdited = !json_data.toString().equals(temp); // if old data not equals to current data..
            // then it's been modified(either added new entity or modified one)

            if (isAddedOrEdited == true) {
                unsaved();
                search_field.setText("");
            }
        }

        private static void delete_entity() {
            boolean isDeleted = Table.delete_selected();

            if (isDeleted) {
                unsaved();
                EntityCounter.setText("Entity: " + String.valueOf(json_data.length()));
                search_field.setText("");
            }
        }

    }
}
