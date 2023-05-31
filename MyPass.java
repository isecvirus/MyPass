package org.virus.mypass;

/*
 *
 * MyPass (v4.3.1) - By SecVirus (c) 4Ever
 *
 *
 * This file contains the main class.
 *
 *  > ICONS
 * -> https://jiconfont.github.io/fontawesome
 *  > Graphics icons
 * -> https://graphics.keenthemes.com/
 *  > UI
 * -> https://mvnrepository.com/artifact/com.formdev/flatlaf
 *
 * BackGround(hex=46494b, r=70,g=73,b=75)
 *
 */
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.components.FlatButton;
import com.formdev.flatlaf.icons.FlatSearchWithHistoryIcon;
import com.formdev.flatlaf.ui.FlatTableUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.virus.mypass.ui.Manager;
import org.virus.mypass.ui.Theme;
import org.virus.mypass.history.history_vars;
import org.virus.mypass.util.Converter.Converter;
import org.virus.mypass.ui.Entity;
import org.virus.mypass.ui.KeyBoard;
import org.virus.mypass.ui.License;
import org.virus.mypass.ui.MGenerator;
import org.virus.mypass.ui.Passwords;
import org.virus.mypass.ui.ui_vars;
import static org.virus.mypass.ui.ui_vars.category_column_index;
import static org.virus.mypass.ui.ui_vars.entity_column_index;
import org.virus.mypass.util.AES.Decryption;
import org.virus.mypass.util.AES.Encryption;
import org.virus.mypass.util.ClipBoard.Clear;
import org.virus.mypass.util.ClipBoard.Copy;
import org.virus.mypass.util.ClipBoard.Get;
import org.virus.mypass.ui.Accent;
import org.virus.mypass.ui.HexViewer;
import org.virus.mypass.ui.categories;
import org.virus.mypass.util.Timer.Timer;

public class MyPass {

    static JFrame window;
    static JMenuBar menubar; // LEAF
    static JMenu file_menu,
            edit_menu,
            edit_copy_entity_menu,
            appearance_menu,
            appearance_theme_menu,
            language_menu,
            tools_menu,
            security_menu,
            window_menu,
            help_menu,
            about_menu,
            direction_window_menu; // This one is for Manager Table.
    static JMenuItem NewFile_menu, OpenFile_menu, SaveFile_menu,
            NewEntity_menu, EditEntity_menu, DeleteEntity_menu,
            edit_copy_entity_entity_menu, edit_copy_entity_username_menu, edit_copy_entity_url_menu, edit_copy_entity_password_menu, edit_copy_entity_note_menu,
            edit_change_session_name_menu, security_change_password_menu,
            generator_tool_menu, PasswordStrengthMeter_menu, PasswordStrengthReport_menu, HexViewer_menu, converter_tool_menu, clear_clipboard_tool_menu,
            help_about_software, help_about_license, help_report_bug, $3_2_0_converter, $4_2_0_converter, $4_3_1_converter;
    static JRadioButtonMenuItem menu_light_theme, menu_dark_theme, direction_rtl, direction_ltr, autoStartStopTimer_onFocus, menu_english_language;
    static JCheckBoxMenuItem menu_manager_dragable, appearance_animate, topmost_window_menu, resizeable_window_menu, timerAutoStart_security_menu, AutoSaveOnTimeout_security_menu, auto_clear_clipboard_tool_menu, window_toolbar_dragable_menu;
    static JPanel panel, manager_frame;
    static JToolBar toolbar, subToolbar, SessionTimerToolBar;
    static JTextField search_field;
    static JButton searchHistory_btn,
            NewFile, OpenFile, SaveFile,
            NewEntity, EditEntity, DeleteEntity,
            GeneratePassword, PasswordStrengthMeter, StrengthReporter, HexViewerButton,
            CopyEntity, CopyUsername, CopyUrl, CopyPassword, CopyNote, ClearClipboard,
            LogToolbarButton,
            ExitToolbarButton,
            StartSessionTimer;
    static JLabel SessionTimer;
    static FlatButton EntityCounter, tutorial, visit_github;
    static JTable table;
    static JScrollPane table_scrollpane;
    static DefaultTableModel table_model;
    static DefaultTableCellRenderer table_row_center;
    static ButtonGroup themes_group, manager_direction_group, languages_menu_group;
    static TableRowSorter<TableModel> manager_sorter;
    static JSONObject json_data = new JSONObject();
    static String session_filename, session_filepath, current_title, encryption_key, window_theme;
    static boolean saved_changes, isNew;
    static JSONObject log_history = new JSONObject();
    static org.virus.mypass.util.Log.Logger logger = new org.virus.mypass.util.Log.Logger(log_history);
    static Timer timer;

    public static void main(String[] args) {
        logger.add_log(logger.PROGRESS, "Starting MyPass..");

        json_data.put("entities", new JSONObject());
        json_data.put("settings", new JSONObject());

        saved_changes = true;
        window_theme = ui_vars.default_theme;
        IconFontSwing.register(FontAwesome.getIconFont());

        window = new JFrame(ui_vars.tool_title.toString());
        window.getRootPane().putClientProperty("JRootPane.titleBarBackground", Accent.AlphaSetGet(25));

        Theme.apply_configurations();
        Theme.toggle_theme(window, window_theme);

        Icon app_icon = IconFontSwing.buildIcon(FontAwesome.MAXCDN, 75, Accent.AlphaSetGet(255));
        window.setIconImage(((ImageIcon) app_icon).getImage());

        toolbar = new JToolBar();
        toolbar.setFloatable(false);

        panel = new JPanel(new BorderLayout());
        manager_frame = new JPanel(new BorderLayout());

        menubar = new JMenuBar();

        // =====================================================================
        file_menu = new JMenu("File");

        NewFile_menu = new JMenuItem("New");
        NewFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        NewFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        NewFile_menu.addActionListener(e -> {
            Data.new_file();
        });
        NewFile_menu.setToolTipText("New Session");

        OpenFile_menu = new JMenuItem("Open");
        OpenFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        OpenFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        OpenFile_menu.addActionListener(e -> {
            Data.open_file();
        });
        OpenFile_menu.setToolTipText("Open Existing Session");

        SaveFile_menu = new JMenuItem("Save");
        SaveFile_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        SaveFile_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        SaveFile_menu.setEnabled(false);
        SaveFile_menu.addActionListener(e -> {
            Data.save_file();
        });
        SaveFile_menu.setToolTipText("Save Current Session");

        edit_menu = new JMenu("Edit");

        NewEntity_menu = new JMenuItem("New");
        NewEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.USER_PLUS, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        NewEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        NewEntity_menu.setEnabled(false);
        NewEntity_menu.addActionListener(e -> {
            Data.new_entity();
        });
        NewEntity_menu.setToolTipText("New Entity");

        EditEntity_menu = new JMenuItem("Edit");
        EditEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        EditEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        EditEntity_menu.setEnabled(false);
        EditEntity_menu.addActionListener(e -> {
            Data.edit_entity();
        });
        EditEntity_menu.setToolTipText("Edit Entity");

        DeleteEntity_menu = new JMenuItem("Delete");
        DeleteEntity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        DeleteEntity_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        DeleteEntity_menu.setEnabled(false);
        DeleteEntity_menu.addActionListener(e -> {
            Data.delete_entity();
        });
        DeleteEntity_menu.setToolTipText("Delete Entity");

        // --------------
        edit_copy_entity_menu = new JMenu("Copy");
        edit_copy_entity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        edit_copy_entity_entity_menu = new JMenuItem("Entity");
        edit_copy_entity_entity_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.CUBE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_copy_entity_entity_menu.addActionListener(e -> {
            Table.content.entity();
        });
        edit_copy_entity_entity_menu.setToolTipText("Copy Entity name for selected entity");
        edit_copy_entity_entity_menu.setEnabled(false);

        edit_copy_entity_username_menu = new JMenuItem("Username");
        edit_copy_entity_username_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.USER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_copy_entity_username_menu.addActionListener(e -> {
            Table.content.copy("username");
        });
        edit_copy_entity_username_menu.setToolTipText("Copy Username for selected entity");
        edit_copy_entity_username_menu.setEnabled(false);

        edit_copy_entity_url_menu = new JMenuItem("Url");
        edit_copy_entity_url_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.LINK, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_copy_entity_url_menu.addActionListener(e -> {
            Table.content.copy("url");
        });
        edit_copy_entity_url_menu.setToolTipText("Copy Url for selected entity");
        edit_copy_entity_url_menu.setEnabled(false);

        edit_copy_entity_password_menu = new JMenuItem("Password");
        edit_copy_entity_password_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.KEY, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_copy_entity_password_menu.addActionListener(e -> {
            Table.content.copy("password");
        });
        edit_copy_entity_password_menu.setToolTipText("Copy Password for selected entity");
        edit_copy_entity_password_menu.setEnabled(false);

        edit_copy_entity_note_menu = new JMenuItem("Note");
        edit_copy_entity_note_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.STICKY_NOTE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_copy_entity_note_menu.addActionListener(e -> {
            Table.content.copy("note");
        });
        edit_copy_entity_note_menu.setToolTipText("Copy Note for selected entity");
        edit_copy_entity_note_menu.setEnabled(false);

        edit_change_session_name_menu = new JMenuItem("Change Session Name");
        edit_change_session_name_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.I_CURSOR, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        edit_change_session_name_menu.addActionListener(l -> {
            String new_name = JOptionPane.showInputDialog(window, "Session name:");
            if (new_name != null) {
                int answer = JOptionPane.showConfirmDialog(window, "Are you sure?", "Changing session name..", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (answer == 0) {
                    Ui.put_session_filename(new File(new_name));
                    unsaved();
                }
            }
        });
        edit_change_session_name_menu.setToolTipText("Change running session name");
        edit_change_session_name_menu.setEnabled(false);

        security_menu = new JMenu("Security");

        security_change_password_menu = new JMenuItem("Change Password");
        security_change_password_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.MAGIC, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        security_change_password_menu.addActionListener(e -> {
            String verify_password = Passwords.ask(window, "Password verfiction");

            if (verify_password != null) {
                if (verify_password.length() > 0) {
                    if (verify_password.equals(encryption_key)) {
                        String new_password = Passwords.ask(window, "New password");

                        if (new_password != null) { // if pressed submit/ok/done in the password dialog
                            if (new_password.length() > 0) {
                                int answer = JOptionPane.showOptionDialog(window, "The session '" + session_filename + "' password will be changed.\n\nAre you sure?", "Change password?!", JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                                if (answer == 0) {
                                    encryption_key = new_password;
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

        security_change_password_menu.setToolTipText("Change Session password");
        security_change_password_menu.setEnabled(false);

        // Seperator ------------
        menu_manager_dragable = new JCheckBoxMenuItem("Dragable");
        menu_manager_dragable.setToolTipText("Enable/Disable abillity to drag text from manager table");
        menu_manager_dragable.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROWS, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        appearance_menu = new JMenu("Appearance");

        appearance_theme_menu = new JMenu("Theme");
        appearance_theme_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.PAINT_BRUSH, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        themes_group = new ButtonGroup();
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
        appearance_animate.addActionListener(l -> {
            unsaved();
        });

        language_menu = new JMenu("Language");
        languages_menu_group = new ButtonGroup();

        menu_english_language = new JRadioButtonMenuItem("English", true);

        menu_english_language.addActionListener(l -> {
//            Locale.setDefault(new Locale("en", "US"));
//            SwingUtilities.updateComponentTreeUI(window);
        });

        tools_menu = new JMenu("Tools");

        generator_tool_menu = new JMenuItem("Generator", IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        generator_tool_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generator_tool_menu.setToolTipText("Password Generator");
        generator_tool_menu.addActionListener(e -> {
            MGenerator.run(window, null);
        });

        PasswordStrengthMeter_menu = new JMenuItem("Strength Meter", IconFontSwing.buildIcon(FontAwesome.TACHOMETER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        PasswordStrengthMeter_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        PasswordStrengthMeter_menu.setToolTipText("Password Strength Meter");
        PasswordStrengthMeter_menu.addActionListener(e -> {
            new Passwords.PasswordStrengthIndicator((JFrame) window).setVisible(true);
        });

        PasswordStrengthReport_menu = new JMenuItem("Strength Report", IconFontSwing.buildIcon(FontAwesome.NEWSPAPER_O, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        PasswordStrengthReport_menu.setEnabled(false);
        PasswordStrengthReport_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        PasswordStrengthReport_menu.setToolTipText("Password Strength Report");
        PasswordStrengthReport_menu.addActionListener(e -> {
            Passwords.Reporter(window, json_data.getJSONObject("entities"));
        });

        HexViewer_menu = new JMenuItem("Hex Viewer", IconFontSwing.buildIcon(FontAwesome.TH, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        HexViewer_menu.setEnabled(false);
        HexViewer_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.SHIFT_MASK + ActionEvent.CTRL_MASK));
        HexViewer_menu.setToolTipText("View session's data as hexadecimal");
        HexViewer_menu.addActionListener(e -> {
            if (!json_data.getJSONObject("entities").isEmpty()) {
                HexViewer.hex(window, session_filepath);
            }
        });

        converter_tool_menu = new JMenu("Converter");
        converter_tool_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.RECYCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        converter_tool_menu.setToolTipText("Convert MyPass password files from version to another");

        $3_2_0_converter = new JMenuItem("v3.2.0", IconFontSwing.buildIcon(FontAwesome.RECYCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        $3_2_0_converter.setToolTipText("v3.2.0 - v4.2.0");
        $3_2_0_converter.addActionListener(e -> {
            Converter.$3_2_0(window);
        });

        $4_2_0_converter = new JMenuItem("v4.2.0", IconFontSwing.buildIcon(FontAwesome.RECYCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        $4_2_0_converter.setToolTipText("v4.2.0 - v4.3.1");
        $4_2_0_converter.addActionListener(e -> {
            Converter.$4_3_1(window);
        });

        $4_3_1_converter = new JMenuItem("v4.3.1", IconFontSwing.buildIcon(FontAwesome.RECYCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        $4_3_1_converter.setToolTipText("v4.3.1 - v?.?.?");
        $4_3_1_converter.addActionListener(e -> {
//            Converter.$?_?_?(window);
        });
        $4_3_1_converter.setEnabled(false);

        clear_clipboard_tool_menu = new JMenuItem("Clear Clipboard", IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        clear_clipboard_tool_menu.setSelected(false);
        clear_clipboard_tool_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        clear_clipboard_tool_menu.setToolTipText("Clear Clipboard (For security)");
        clear_clipboard_tool_menu.addActionListener(e -> {
            Clear.content();
        });

        timerAutoStart_security_menu = new JCheckBoxMenuItem("Auto Start Timer");
        timerAutoStart_security_menu.setSelected(false);
        timerAutoStart_security_menu.setToolTipText("Auto start session timer (For confidentiality)");
        timerAutoStart_security_menu.addActionListener(l -> {
            unsaved();
        });

        AutoSaveOnTimeout_security_menu = new JCheckBoxMenuItem("Auto Save On Timeout");
        AutoSaveOnTimeout_security_menu.setSelected(true);
        AutoSaveOnTimeout_security_menu.setToolTipText("Auto save session data before timeout terminate");
        AutoSaveOnTimeout_security_menu.addActionListener(l -> {
            unsaved();
        });

        auto_clear_clipboard_tool_menu = new JCheckBoxMenuItem("Auto Clear Clipboard");
        auto_clear_clipboard_tool_menu.setSelected(false);
        auto_clear_clipboard_tool_menu.setToolTipText("Auto Clear Clipboard (For security)");
        auto_clear_clipboard_tool_menu.addActionListener(l -> {
            unsaved();
        });

        window_menu = new JMenu("Window");

        topmost_window_menu = new JCheckBoxMenuItem("Topmost");
        topmost_window_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        topmost_window_menu.setSelected(false);
        topmost_window_menu.setToolTipText("On top of all other windows");
        topmost_window_menu.addActionListener(l -> {
            unsaved();
        });

        topmost_window_menu.addActionListener(twe -> {
            window.setAlwaysOnTop(topmost_window_menu.isSelected());
        });

        resizeable_window_menu = new JCheckBoxMenuItem("Resizeable");
        resizeable_window_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK));
        resizeable_window_menu.setSelected(true);
        resizeable_window_menu.addActionListener(l -> {
            unsaved();
        });

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
        window_toolbar_dragable_menu.addActionListener(e -> {
            toolbar.setFloatable(window_toolbar_dragable_menu.isSelected());
            unsaved();
        });

        help_menu = new JMenu("Help");

        about_menu = new JMenu("About");
        about_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        help_about_software = new JMenuItem("Software");
        help_about_software.setIcon(IconFontSwing.buildIcon(FontAwesome.DESKTOP, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        help_about_license = new JMenuItem("License");
        help_about_license.setIcon(IconFontSwing.buildIcon(FontAwesome.STAR, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        help_report_bug = new JMenuItem("Report a Bug");
        help_report_bug.setIcon(IconFontSwing.buildIcon(FontAwesome.BUG, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        help_report_bug.addActionListener(e -> {
            visit("https://github.com/isecvirus/MyPass/issues/new");
        });

        tutorial = new FlatButton();
        tutorial.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOK, ui_vars.icons_size, Accent.AlphaSetGet(255)));
//        tutorial.setEnabled(false);
        tutorial.setToolTipText("Tutorial");
        tutorial.setButtonType(FlatButton.ButtonType.toolBarButton);
        tutorial.setFocusable(false);
        tutorial.addActionListener(e -> {
            visit("https://github.com/isecvirus/MyPass/blob/main/README.md#-tutorial");
        });

        visit_github = new FlatButton();
        visit_github.setIcon(IconFontSwing.buildIcon(FontAwesome.GITHUB, ui_vars.icons_size + 2, Accent.AlphaSetGet(255)));
        visit_github.setToolTipText("iSecVirus@github");
        visit_github.setButtonType(FlatButton.ButtonType.toolBarButton);
        visit_github.setFocusable(false);
        visit_github.addActionListener(a -> {
            visit("https://www.github.com/isecvirus");
        });

        search_field = new JTextField();
        search_field.setLayout(new BorderLayout());
        KeyBoard.give(window, search_field);

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

            JMenuItem clear_history = new JMenuItem("Clear History", IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, Accent.AlphaSetGet(255)));
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
        table.setSelectionBackground(Accent.AlphaSetGet(65));

        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.getTableHeader().setOpaque(false);

//        DefaultTableModel mtm = (DefaultTableModel) table.getModel(); // manager table model
        table.getTableHeader().setReorderingAllowed(false);

        Manager.CenterTableRows(table, table_row_center, ui_vars.manager_columns);
//        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(0); // 0=single selection
//        table.setAutoCreateRowSorter(true);

        manager_sorter = new TableRowSorter<>(table_model);
        table.setRowSorter(manager_sorter);

        Font currentFont = table.getFont(); // Get the current font of the table
        Font newFont = currentFont.deriveFont(currentFont.getSize() + 1.5f).deriveFont(Font.BOLD); // Create a new font object with increased size and bold style
        table.setFont(newFont); // Set the table font to the new font

        table.setRowHeight(50);
        table.getColumnModel().getColumn(category_column_index).setCellRenderer(new TableCategoryRenderer());
        table.getColumnModel().getColumn(category_column_index).setMaxWidth(50);
        table.getColumnModel().getColumn(category_column_index).setResizable(false);

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
        direction_window_menu.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_CENTER, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        manager_direction_group = new ButtonGroup();
        direction_rtl = new JRadioButtonMenuItem("RTL");
        direction_rtl.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_RIGHT, ui_vars.icons_size, Accent.AlphaSetGet(255)));

        direction_ltr = new JRadioButtonMenuItem("LTR", true);
        direction_ltr.setIcon(IconFontSwing.buildIcon(FontAwesome.ALIGN_LEFT, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        direction_ltr.addActionListener(l -> {
            unsaved();
        });

        autoStartStopTimer_onFocus = new JRadioButtonMenuItem("Auto Timer", true);
        autoStartStopTimer_onFocus.setToolTipText("Auto Start/Stop Timer on Focus/Unfocus window");
        autoStartStopTimer_onFocus.setIcon(IconFontSwing.buildIcon(FontAwesome.WINDOW_RESTORE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
        autoStartStopTimer_onFocus.setSelected(false);

        manager_direction_group.add(direction_rtl);
        manager_direction_group.add(direction_ltr);
        direction_window_menu.add(direction_rtl);
        direction_window_menu.add(direction_ltr);

        direction_rtl.addActionListener((ActionEvent e) -> {
            Ui.RTL();
        });
        direction_ltr.addActionListener((ActionEvent e) -> {
            Ui.LTR();
        });

        table.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        table.setFocusable(false);
//        table.setColumnSelectionAllowed(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table_scrollpane = new JScrollPane(table);
        table_scrollpane.setOpaque(false);
        table_scrollpane.getViewport().setOpaque(false);

        menu_manager_dragable.addActionListener((ActionEvent e) -> {
            Manager.draggable(table);
        });
        menu_light_theme.addActionListener((ActionEvent e) -> {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            Theme.toggle_theme(window, ui_vars.light_theme);
            window_theme = "light";

            unsaved();

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        });
        menu_dark_theme.addActionListener((ActionEvent e) -> {
            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.showSnapshot();
            }

            Theme.toggle_theme(window, ui_vars.dark_theme);
            window_theme = "dark";

            unsaved();

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        });
        help_about_software.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window, "MyPass is a password manager.\nUse it to secure, store and manage passwords.\n\nJava 18.0.1.1 2022-04-22 (build 18.0.1.1+2-6)", "About MyPass", JOptionPane.INFORMATION_MESSAGE);
        });
        help_about_license.addActionListener((ActionEvent e) -> {
            new License().display(window);
        });

//        toolbar.add(Box.createHorizontalStrut(5), 0);
        NewFile = new JButton(IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        NewFile.setToolTipText("New File");

        OpenFile = new JButton(IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        OpenFile.setToolTipText("Open File");

        SaveFile = new JButton(IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        SaveFile.setToolTipText("Save File");
        SaveFile.setEnabled(false);

        // ~~~~~~~~~~~~~~~~~
        NewEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.USER_PLUS, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        NewEntity.setToolTipText("New Entity");
        NewEntity.setEnabled(false);

        EditEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.PENCIL, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        EditEntity.setToolTipText("Edit Entity");
        EditEntity.setEnabled(false);

        DeleteEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.TRASH, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        DeleteEntity.setToolTipText("Delete Entity");
        DeleteEntity.setEnabled(false);

        // ~~~~~~~~~~~~~~~~~
        CopyEntity = new JButton(IconFontSwing.buildIcon(FontAwesome.CUBE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        CopyEntity.setToolTipText("Copy Entity");
        CopyEntity.setEnabled(false);

        CopyUsername = new JButton(IconFontSwing.buildIcon(FontAwesome.USER, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        CopyUsername.setToolTipText("Copy Username");
        CopyUsername.setEnabled(false);

        CopyUrl = new JButton(IconFontSwing.buildIcon(FontAwesome.LINK, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        CopyUrl.setToolTipText("Copy Url");
        CopyUrl.setEnabled(false);

        CopyPassword = new JButton(IconFontSwing.buildIcon(FontAwesome.KEY, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        CopyPassword.setToolTipText("Copy Password");
        CopyPassword.setEnabled(false);

        CopyNote = new JButton(IconFontSwing.buildIcon(FontAwesome.STICKY_NOTE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        CopyNote.setToolTipText("Copy Note");
        CopyNote.setEnabled(false);

        ClearClipboard = new JButton(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        ClearClipboard.setToolTipText("Clear Clipboard");
        ClearClipboard.setEnabled(false);

        check_clipboard_status();

        // ~~~~~~~~~~~~~~~~~
        GeneratePassword = new JButton(IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        GeneratePassword.setToolTipText("Generate Password");

        PasswordStrengthMeter = new JButton(IconFontSwing.buildIcon(FontAwesome.TACHOMETER, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        PasswordStrengthMeter.setToolTipText("Password Strength Meter");

        StrengthReporter = new JButton(IconFontSwing.buildIcon(FontAwesome.NEWSPAPER_O, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        StrengthReporter.setToolTipText("Password Strength Report");
        StrengthReporter.setEnabled(false);

        HexViewerButton = new JButton(IconFontSwing.buildIcon(FontAwesome.TH, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        HexViewerButton.setToolTipText("View File Hexadecimal");
        HexViewerButton.setEnabled(false);

        // ~~~~~~~~~~~~~~~~~
        LogToolbarButton = new JButton(IconFontSwing.buildIcon(FontAwesome.FILE_TEXT, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        LogToolbarButton.setToolTipText("Log");

        ExitToolbarButton = new JButton(IconFontSwing.buildIcon(FontAwesome.POWER_OFF, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
        ExitToolbarButton.setToolTipText("Exit");

        timer = new Timer();

        StartSessionTimer = new JButton(IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));

        SessionTimer = new JLabel(timer.formatTime(timer.getDefaultSessionTime()));
        SessionTimer.setToolTipText("Session Ends in");
        SessionTimer.setForeground(Color.decode("#222222"));

        SessionTimer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getButton() == 3) {
                    JPopupMenu popup = new JPopupMenu();

                    JLabel default_label;
                    JMenuItem update_timer, reset_timer;
                    JCheckBoxMenuItem start_timer;

                    default_label = new JLabel(Timer.formatTime(timer.getDefaultSessionTime()));
                    start_timer = new JCheckBoxMenuItem();
                    update_timer = new JMenuItem("Update");
                    reset_timer = new JMenuItem("Reset");

                    if (timer.isRunning()) {
                        start_timer.setText("Stop");
                        start_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
                    } else {
                        start_timer.setText("Start");
                        start_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE, ui_vars.icons_size, Accent.AlphaSetGet(255)));
                    }
                    update_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.CLOCK_O, ui_vars.icons_size, Accent.AlphaSetGet(255)));
                    reset_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.REPEAT, ui_vars.icons_size, Accent.AlphaSetGet(255)));

//                    default_label.setEnabled(false);
                    default_label.setFocusable(false);
                    default_label.setHorizontalAlignment(SwingConstants.CENTER);
                    default_label.setForeground(Accent.AlphaSetGet(255));

                    start_timer.addActionListener(e -> {
                        if (timer.isRunning()) {
                            SessionTimer.setForeground(Color.decode("#222222"));
                            timer.stop_timer();
                            start_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
                            StartSessionTimer.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
                        } else {
                            SessionTimer.setForeground(Accent.AlphaSetGet(255));
                            timer.start_timer();
                            start_timer.setIcon(IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));
                            StartSessionTimer.setIcon(IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));

                            Thread timerThread = new Thread(timer);
                            timerThread.start();
                        }
                    });

                    update_timer.addActionListener(e -> {
                        timer.updater(window);
                    });

                    reset_timer.addActionListener(e -> {
                        timer.setCurrentSessionTime(timer.getDefaultSessionTime());
                        timer.CallBack();
                    });

                    popup.add(default_label);
                    popup.addSeparator();
                    popup.add(start_timer);
                    popup.add(update_timer);
                    popup.add(reset_timer);

                    popup.show(SessionTimer, 0, SessionTimer.getHeight());
                }
            }
        });

        timer.setCallback(new Callable() {
            @Override
            public Object call() throws Exception {
                int current = timer.getCurrentSessionTime();
                SessionTimer.setText(Timer.formatTime(current));

                if (current == timer.getDefaultSessionTime() / 3) {
                    new Thread(() -> {
                        SwingUtilities.invokeLater(() -> {
                            int answer = JOptionPane.showOptionDialog(window, "Session time about to end, wanna add 1min?", "Session timeout!", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, IconFontSwing.buildIcon(FontAwesome.CLOCK_O, ui_vars.icons_size * 3, Accent.AlphaSetGet(255)), new String[]{"yes"}, "yes");
                            if (answer == 0) {
                                timer.setCurrentSessionTime(timer.getCurrentSessionTime() + 60);
                                timer.CallBack();
                            }
                        });
                    }).start();
                }
                return null;
            }
        });

        timer.setDoneCallback(new Callable() {
            @Override
            public Object call() throws Exception {
                window.dispose();
                if (AutoSaveOnTimeout_security_menu.isSelected()) {
                    Data.save_file();
                    saved();
                }
                return null;
            }
        });

        StartSessionTimer.addActionListener(e -> {
            if (timer.isRunning()) {
                SessionTimer.setForeground(Color.decode("#222222"));
                timer.stop_timer();
                StartSessionTimer.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));

            } else {
                SessionTimer.setForeground(Accent.AlphaSetGet(255));
                timer.start_timer();
                StartSessionTimer.setIcon(IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));

                Thread timerThread = new Thread(timer);
                timerThread.start();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mpe) {
                Point point = mpe.getPoint();
                int row = table.rowAtPoint(point);

                Table.selectionChange();
                // left mouse button clicked twice
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
        PasswordStrengthMeter.addActionListener(e -> {
            new Passwords.PasswordStrengthIndicator((JFrame) window).setVisible(true);
        });
        StrengthReporter.addActionListener(e -> {
            if (!json_data.getJSONObject("entities").isEmpty()) {
                Passwords.Reporter(window, json_data.getJSONObject("entities"));
            }
        });
        HexViewerButton.addActionListener(e -> {
            if (!json_data.getJSONObject("entities").isEmpty()) {
                HexViewer.hex(window, json_data.getJSONObject("entities").toString());
            }
        });
        LogToolbarButton.addActionListener(e -> {
            logger.show(window, log_history);
        });
        GeneratePassword.addActionListener(e -> {
            logger.add_log(logger.DEBUG, "Starting password generator..");
            MGenerator.run(window, null);
            logger.add_log(logger.DEBUG, "Password generator closed");
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
                try {
                    sys_clipboard.setContents(new StringSelection(""), null);
                } catch (Exception error) {
                }
            }
            ClearClipboard.setEnabled((Get.asString().length() > 0));
            clear_clipboard_tool_menu.setEnabled((Get.asString().length() > 0));
        });

        window.addFocusListener(new FocusListener() {
            private void status() {
                if (autoStartStopTimer_onFocus.isSelected()) {
                    timer.start_timer();
                } else {
                    timer.stop_timer();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
//                status();
            }

            @Override
            public void focusLost(FocusEvent e) {
//                status();
            }

        });
        SessionTimerToolBar = new JToolBar();
        SessionTimerToolBar.add(StartSessionTimer);
        SessionTimerToolBar.add(Box.createHorizontalStrut(3));
        SessionTimerToolBar.add(SessionTimer);

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
        toolbar.add(PasswordStrengthMeter);
        toolbar.add(StrengthReporter);
        toolbar.add(HexViewerButton);
        toolbar.add(ClearClipboard);
        toolbar.addSeparator();
        toolbar.add(LogToolbarButton);
        toolbar.addSeparator();
        toolbar.add(ExitToolbarButton);
        toolbar.add(Box.createGlue());
        toolbar.addSeparator();
        toolbar.add(SessionTimerToolBar);

        subToolbar = new JToolBar();
        subToolbar.setOpaque(false);

        EntityCounter = new FlatButton();
        setEntityCount(0);

        subToolbar.add(EntityCounter);
        subToolbar.addSeparator();
        subToolbar.add(search_field);
//        subToolbar.add(Box.createGlue());

//        search_manager.setVisible(true);
        manager_frame.add(toolbar, BorderLayout.BEFORE_FIRST_LINE);
        manager_frame.add(table_scrollpane);

        panel.add(manager_frame);
        panel.add(subToolbar, BorderLayout.AFTER_LAST_LINE);

        menubar.add(file_menu);
        menubar.add(edit_menu);
        menubar.add(security_menu);
        menubar.add(tools_menu);
        menubar.add(window_menu);
        menubar.add(help_menu);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(visit_github); // GITHUB, GITHUB_SQUARE
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
        edit_menu.add(edit_change_session_name_menu);

        edit_copy_entity_menu.add(edit_copy_entity_entity_menu);
        edit_copy_entity_menu.add(edit_copy_entity_username_menu);
        edit_copy_entity_menu.add(edit_copy_entity_url_menu);
        edit_copy_entity_menu.add(edit_copy_entity_password_menu);
        edit_copy_entity_menu.add(edit_copy_entity_note_menu);

        appearance_menu.add(appearance_theme_menu);
        themes_group.add(menu_light_theme);
        appearance_theme_menu.add(menu_light_theme);
        appearance_theme_menu.add(menu_dark_theme);

        appearance_menu.add(appearance_animate);

        themes_group.add(menu_dark_theme);

        tools_menu.add(generator_tool_menu);
        tools_menu.add(PasswordStrengthMeter_menu);
        tools_menu.add(PasswordStrengthReport_menu);
        tools_menu.add(HexViewer_menu);
        tools_menu.add(converter_tool_menu);

        security_menu.add(security_change_password_menu);
        security_menu.addSeparator();
        security_menu.add(timerAutoStart_security_menu);
        security_menu.add(AutoSaveOnTimeout_security_menu);
        security_menu.addSeparator();
        security_menu.add(clear_clipboard_tool_menu);
        security_menu.add(auto_clear_clipboard_tool_menu);

        converter_tool_menu.add($3_2_0_converter);
        converter_tool_menu.add($4_2_0_converter);
        converter_tool_menu.add($4_3_1_converter);

        languages_menu_group.add(menu_english_language);

        language_menu.add(menu_english_language);

        window_menu.add(topmost_window_menu);
        window_menu.add(resizeable_window_menu);
        window_menu.add(direction_window_menu);
        window_menu.add(appearance_menu);
        window_menu.add(language_menu);
        window_menu.addSeparator();
        window_menu.add(window_toolbar_dragable_menu);

        help_menu.add(help_report_bug);
        help_menu.addSeparator();
        help_menu.add(about_menu);

        about_menu.add(help_about_software);
        about_menu.add(help_about_license);

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
        logger.add_log(logger.SUCCESS, "MyPass started");
    }

    private static void setEntityCount(int num) {
        EntityCounter.setText("Entity: " + String.valueOf(num));
        if (num > 0) {
            logger.add_log(logger.DEBUG, String.format("New entity number '%s'", num));
        }
    }

    private static void visit(String url) {
        try {
            logger.add_log(logger.PROGRESS, String.format("Visiting '%s'..", url));
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (URISyntaxException | IOException url_error) {
        }
    }

    private static void unsaved() {
        if (session_filename != null && session_filepath != null) {
            saved_changes = false;
            window.setTitle("* " + current_title);

            SaveFile.setEnabled(true);
            SaveFile_menu.setEnabled(true);

            logger.add_log(logger.STATUS, String.format("Unsaved changes in '%s'..", session_filename));
        }
    }

    private static void saved() {
        saved_changes = true;
        window.setTitle(current_title);
        SaveFile.setEnabled(false);

        SaveFile_menu.setEnabled(false);
        logger.add_log(logger.SUCCESS, String.format("Saved '%s' changes", session_filename));
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

                entity = table.getValueAt(table.getSelectedRow(), entity_column_index).toString();

                logger.add_log(logger.PROGRESS, String.format("Deleting '%s'..", entity));

                message = String.format("Delete '%s'", entity);
                int answer = JOptionPane.showConfirmDialog(window, "Are you sure?", message, JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    table_model.removeRow(selected);
                    json_data.getJSONObject("entities").remove(entity);
                    SwingUtilities.updateComponentTreeUI(window);
                    unfocused();

                    unsaved();
                    logger.add_log(logger.PROGRESS, String.format("Deleted '%s'", entity));

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
                String entity = table.getValueAt(table.getSelectedRow(), entity_column_index).toString();
                JSONObject data = new JSONObject(json_data.getJSONObject("entities").get(entity).toString());

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
                    return table.getValueAt(selected, entity_column_index).toString();
                }
                return "";
            }

            private static void entity() {
                Copy.string(returnEntity());
                logger.add_log(logger.DEBUG, String.format("Copied entity '%s' to clipboard", returnEntity()));
            }

            private static void copy(String key) {
                String entity = returnEntity();
                if (entity.length() > 0) {
                    String content = new JSONObject(json_data.getJSONObject("entities").get(entity).toString()).get(key).toString();
                    Copy.string(content);

                    logger.add_log(logger.DEBUG, String.format("Copied '%s' to clipboard", key));
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
            logger.add_log(logger.DEBUG, "Orientation set to 'Right to Left'");

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
            logger.add_log(logger.DEBUG, "Orientation set to 'Left to Right'");

            if (appearance_animate.isSelected()) {
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            }
        }

        private static void put_session_filename(File file) {
            session_filename = file.getName();
            session_filepath = file.getAbsolutePath();
            String session_name = session_filename;

            if (!session_filename.endsWith(".mp")) {
                session_filename += ".mp";
            }
            if (!session_filepath.endsWith(".mp")) {
                session_filepath += ".mp";
            }

            current_title = session_filename + " - " + ui_vars.tool_title;
            window.setTitle(current_title);
            logger.add_log(logger.DEBUG, String.format("Setting session name to '%s'", session_name));
        }
    }

    private static class Data {

        private static void data_loaded() {
            PasswordStrengthReport_menu.setEnabled(!json_data.getJSONObject("entities").isEmpty());
            HexViewer_menu.setEnabled(!json_data.getJSONObject("entities").isEmpty());
            StrengthReporter.setEnabled(!json_data.getJSONObject("entities").isEmpty());
            HexViewerButton.setEnabled(!json_data.getJSONObject("entities").isEmpty());
        }

        private static void clear_table() {
            logger.add_log(logger.PROGRESS, String.format("Clearing '%s' table entity", table_model.getRowCount()));
            for (int i = 0; i < table_model.getRowCount(); i++) {
                table_model.removeRow(i);
            }
            table_model.setRowCount(0);
            table.clearSelection();
            setEntityCount(json_data.getJSONObject("entities").length());
            logger.add_log(logger.SUCCESS, "Table cleared successfully");
        }

        private static void new_file() {
            logger.add_log(logger.NOTIFICATION, "Attempting create new session");
            JFileChooser filedialog = new JFileChooser(Paths.get("").toAbsolutePath().toString());
            filedialog.setDialogTitle("New File");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("MyPass (passwords file)", "mp", "mypass");
            filedialog.setFileFilter(filter);

            int returnVal = filedialog.showDialog(window, "Confirm");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = filedialog.getSelectedFile().toString();

                if (filename.length() > 0) {
                    logger.add_log(logger.PROGRESS, String.format("Attempting to create session '%s'", filename));
                    Ui.put_session_filename(filedialog.getSelectedFile());
                    edit_change_session_name_menu.setEnabled(true);

                    NewEntity.setEnabled(true);
                    security_change_password_menu.setEnabled(false);
                    isNew = true;

                    clear_table();
                    json_data.getJSONObject("entities").clear();

                    json_data.put("entities", new JSONObject());

                    JSONObject settings = new JSONObject()
                            .put("autoClearClipboard", auto_clear_clipboard_tool_menu.isSelected())
                            .put("topmost", topmost_window_menu.isSelected())
                            .put("resizeable", resizeable_window_menu.isSelected())
                            .put("orientation", direction_ltr.isSelected() ? "ltr" : "rtl")
                            .put("dragable-toolbar", menu_manager_dragable.isSelected())
                            .put("theme", window_theme)
                            .put("animate", appearance_animate.isSelected())
                            .put("session_timeout", timer.getDefaultSessionTime())
                            .put("timerAutoStart", timerAutoStart_security_menu.isSelected())
                            .put("AutoSaveOnTimeout", AutoSaveOnTimeout_security_menu.isSelected());

                    json_data.put("settings", settings);

                    logger.add_log(logger.SUCCESS, String.format("Created session '%s'", session_filename));
                } else {
                    logger.add_log(logger.VALIDATION, "Can't leave session filename empty");
                    JOptionPane.showMessageDialog(window, "Can't leave this field empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private static void save_data_to_file(String key) {
            try {
                if (isNew) {
                    File file = new File(session_filepath);
                    if (file.exists()) {
                        // The file exists, so prompt the user
                        int response = JOptionPane.showConfirmDialog(window,
                                "The file '" + session_filename + "' already exists. Do you want to overwrite it?",
                                "Confirm overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (response != JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                }

                logger.add_log(logger.DEBUG, "Encrypting data..");

                JSONObject settings = new JSONObject()
                        .put("autoClearClipboard", auto_clear_clipboard_tool_menu.isSelected())
                        .put("topmost", topmost_window_menu.isSelected())
                        .put("resizeable", resizeable_window_menu.isSelected())
                        .put("orientation", direction_ltr.isSelected() ? "ltr" : "rtl")
                        .put("dragable-toolbar", window_toolbar_dragable_menu.isSelected())
                        .put("theme", window_theme)
                        .put("animate", appearance_animate.isSelected())
                        .put("session_timeout", timer.getDefaultSessionTime())
                        .put("timerAutoStart", timerAutoStart_security_menu.isSelected())
                        .put("AutoSaveOnTimeout", AutoSaveOnTimeout_security_menu.isSelected());

                json_data.put("settings", settings);

                byte[] encrypted_data = Encryption.encrypt(json_data.toString(), key);
                logger.add_log(logger.SUCCESS, "Encrypted data");

                FileOutputStream fileOutput = new FileOutputStream(session_filepath);

                logger.add_log(logger.PROGRESS, String.format("Writing %sbit of data to '%s'..", encrypted_data.length, session_filename));
                fileOutput.write(encrypted_data);
                logger.add_log(logger.SUCCESS, "Data 100% saved");

                saved();
                isNew = false; // Set isNew to false after the data has been saved
            } catch (Exception error) {
                JOptionPane.showMessageDialog(window,
                        "Couldn't save data to '" + session_filepath + "', Try Again.\n\nError: " + error.getMessage(),
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

        private static void save_file() {
            logger.add_log(logger.PROGRESS, "Attempting to save session");
            if (!saved_changes) {
                logger.add_log(logger.PROGRESS, String.format("Attempted session to save is '%s'", session_filename));
                if (isNew) {
                    logger.add_log(logger.MESSAGE, String.format("Setting password for '%s'", session_filename));
                    String password = Passwords.ask(window, "Session Password");

                    if (password != null) {
                        if (password.length() > 0) {
                            save_data_to_file(password);

                            encryption_key = password;
                            security_change_password_menu.setEnabled(true);
                        } else {
                            logger.add_log(logger.VALIDATION, "Attempted password is empty");
                        }
                    } else {
                        logger.add_log(logger.STATUS, "Password verification dialog closed");
                    }
                } else {
                    save_data_to_file(encryption_key);
                }
            }
        }

        private static void open_file() {
            logger.add_log(logger.NOTIFICATION, "Opening a MP file..");
            JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
            fc.setDialogTitle("Open File");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("MyPass (passwords file)", "mp", "mypass");
            fc.setFileFilter(filter);
            int returnVal = fc.showDialog(window, "Open");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filename;

                filename = fc.getSelectedFile().getAbsolutePath();
                logger.add_log(logger.PROGRESS, String.format("Opening '%s'..", filename));

                String password = Passwords.ask(window, "Verify password");

                if (password != null && !password.isEmpty()) {
                    try {
                        byte[] encrypted_data = Files.readAllBytes(Paths.get(filename));
                        String loaded_data = Decryption.decrypt(encrypted_data, password);

                        new JSONObject(loaded_data); // trying to parse json data
                        // if an error occured then data will not be erased from the table (:
                        // and method execution will be stopped.

                        clear_table(); // should be right here to reset the table.

                        json_data = new JSONObject(loaded_data);
                        JSONObject entites = json_data.getJSONObject("entities");

                        JSONObject settings = json_data.getJSONObject("settings");
                        boolean auto_clear = settings.getBoolean("autoClearClipboard");
                        boolean topmost = settings.getBoolean("topmost");
                        boolean resizeable = settings.getBoolean("resizeable");
                        String orientation = settings.getString("orientation");
                        boolean dragable_toolbar = settings.getBoolean("dragable-toolbar");
                        String theme = settings.getString("theme");
                        boolean animate = settings.getBoolean("animate");
                        boolean timerAutoStart = settings.getBoolean("timerAutoStart");
                        int session_timeout = settings.getInt("session_timeout");
                        boolean AutoSaveOnTimeout = settings.getBoolean("AutoSaveOnTimeout");

                        auto_clear_clipboard_tool_menu.setSelected(auto_clear);

                        topmost_window_menu.setSelected(topmost);
                        window.setAlwaysOnTop(topmost);

                        resizeable_window_menu.setSelected(resizeable);
                        window.setResizable(resizeable);

                        if (orientation.equals("ltr")) {
                            Ui.LTR();
                            direction_ltr.setSelected(true);
                        } else if (orientation.equals("rtl")) {
                            Ui.RTL();
                            direction_rtl.setSelected(true);
                        }

                        window_toolbar_dragable_menu.setSelected(dragable_toolbar);
                        toolbar.setFloatable(dragable_toolbar);

                        Theme.toggle_theme(window, theme);
                        window_theme = theme;
                        if (theme.equals("dark")) {
                            menu_dark_theme.setSelected(true);
                        } else if (theme.equals("light")) {
                            menu_light_theme.setSelected(true);
                        }

                        appearance_animate.setSelected(animate);

                        AutoSaveOnTimeout_security_menu.setSelected(AutoSaveOnTimeout);

                        timer.setDefaultSessionTime(session_timeout);
                        int current = timer.getCurrentSessionTime();
                        SessionTimer.setText(Timer.formatTime(current));

                        if (timerAutoStart) {
                            timerAutoStart_security_menu.setSelected(true);

                            SessionTimer.setForeground(Accent.AlphaSetGet(255));
                            timer.start_timer();
                            StartSessionTimer.setIcon(IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE, ui_vars.icons_size + 5, Accent.AlphaSetGet(255)));

                            Thread timerThread = new Thread(timer);
                            timerThread.start();
                        }

                        logger.add_log(logger.INFORMATION, String.format("'%s' contains '%s' entity", filename, json_data.getJSONObject("entities").keySet().size()));
                        entites.keySet().forEach(entity -> {
                            JSONObject entity_data = entites.optJSONObject(entity);
                            String creation, modification, category;

                            if (entity_data.optJSONArray("passwordHistory") == null) {
                                entity_data.put("passwordHistory", new JSONArray());
                            }

                            creation = entity_data.optString("created");
                            modification = entity_data.optString("modified");
                            category = entity_data.optString("category");

                            table_model.addRow(new String[]{"", entity, creation, modification});
                        });

                        Ui.put_session_filename(fc.getSelectedFile());

                        NewEntity.setEnabled(true);
                        NewEntity_menu.setEnabled(true);
                        isNew = false;

                        encryption_key = password;
                        edit_change_session_name_menu.setEnabled(true);
                        security_change_password_menu.setEnabled(true);
                        setEntityCount(json_data.getJSONObject("entities").length());
                        search_field.setText("");
                        logger.add_log(logger.SUCCESS, String.format("Opened '%s'", filename));

                        data_loaded();
                    } catch (JSONException json_malformed) {
                        logger.add_log(logger.ERROR, String.format("Malformed data detected in '%s'", filename));
                        JOptionPane.showMessageDialog(window, "Data looks malformed", "Error!", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException incorrect_key) {

                        logger.add_log(logger.CRITICAL, String.format("Wrong password attempted '%s'", password));
                        JOptionPane.showMessageDialog(null, "Incorrect password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception error) {
                        logger.add_log(logger.ERROR, error.getMessage());
                        JOptionPane.showMessageDialog(window, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    logger.add_log(logger.VALIDATION, "Attempted password is empty");
                }
            } else {
                logger.add_log(logger.STATUS, "Password verification dialog closed");
            }

        }

        private static void new_entity() {
            JSONObject temp = new JSONObject(json_data.getJSONObject("entities").toString()); // copy current json data to check for changes

            logger.add_log(logger.INFORMATION, "Creating new entity..");

            new Entity().EntityWindow(window, "Edit Entity", table, json_data.getJSONObject("entities"), false);

            boolean isAddedOrEdited = !json_data.getJSONObject("entities").toString().equals(temp.toString()); // if old data not equals to current data..
            // then it's been modified(either added new entity or modified one)

            if (isAddedOrEdited) {
                setEntityCount(json_data.getJSONObject("entities").length());
                unsaved();
                search_field.setText("");
                logger.add_log(logger.SUCCESS, "Added new entity");
            }
            data_loaded();
        }

        private static void edit_entity() {
            JSONObject temp = new JSONObject(json_data.getJSONObject("entities").toString()); // copy current json data to check for changes

            String entity = table_model.getValueAt(table.getSelectedRow(), entity_column_index).toString();
            logger.add_log(logger.INFORMATION, String.format("Editing '%s' entity..", entity));

            new Entity().EntityWindow(window, "Edit Entity", table, json_data.getJSONObject("entities"), true);

            table.updateUI();

            boolean isAddedOrEdited = !json_data.getJSONObject("entities").toString().equals(temp.toString()); // if old data not equals to current data..
            // then it's been modified(either added new entity or modified one)

            if (isAddedOrEdited) {
                unsaved();
                search_field.setText("");
                logger.add_log(logger.SUCCESS, String.format("Edited '%s' entity", entity));
            }
            data_loaded();
        }

        private static void delete_entity() {
            String entity = table_model.getValueAt(table.getSelectedRow(), entity_column_index).toString();

            logger.add_log(logger.WARNING, String.format("Deleting '%s' entity..", entity));
            boolean isDeleted = Table.delete_selected();

            if (isDeleted) {
                unsaved();
                setEntityCount(json_data.getJSONObject("entities").length());
                search_field.setText("");
                logger.add_log(logger.SUCCESS, String.format("Deleted '%s' entity", entity));
            }
            data_loaded();
        }
    }
}

class TableCategoryRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Create a JLabel to display the image
        String entity = table.getValueAt(row, 1).toString();
        JSONObject entity_obj = MyPass.json_data.getJSONObject("entities").getJSONObject(entity);
        JSONObject category_obj = entity_obj.getJSONObject("category");
        String category_icon_name = category_obj.getString("icon");
        String category_color = category_obj.getString("color");

        JLabel label;
        Icon icon;

        if (!category_icon_name.isEmpty()) {
            Color cat_color = Color.decode(category_color);
            Color color = isSelected ? Color.decode(category_color) : new Color(cat_color.getRed(), cat_color.getGreen(), cat_color.getBlue(), Math.max(155, cat_color.getAlpha() - 100));
            icon = IconFontSwing.buildIcon(FontAwesome.valueOf(categories.icons.getString(category_icon_name)), 35, color);
            label = new JLabel(icon);
        } else {
            label = new JLabel();
        }

        label.setVerticalAlignment(CENTER);
        label.setHorizontalAlignment(CENTER);
        label.setBackground(isSelected ? Accent.AlphaSetGet(65) : Accent.AlphaSetGet(30));
        label.setOpaque(true);

        // Set the JLabel as the component for the cell
        return label;
    }
}
