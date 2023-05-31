package org.virus.mypass.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.virus.mypass.ui.Theme;

public class HexViewer {

    private static byte[] read_file(String file) {
        try {
//            System.out.println("Reading..");
            return Files.readAllBytes(Paths.get(file));
        } catch (IOException ex) {
            return new byte[0]; // return 0 bytes
        }
    }

    private static String toHex(byte chr) {
        final char[] hex_chars = "0123456789ABCDEF".toCharArray();
        char[] hexChar = new char[2];
        /*
            This line creates a new character array
            hexChar with a length of 2.
            This array will be used to store the two
            hexadecimal characters that represent
            the input byte.
         */

        int v = chr & 0xFF;
        /*
            This line performs a bitwise AND
            operation between the input byte chr
            and the hexadecimal value 0xFF.
            This operation ensures that only the
            least significant 8 bits of chr are used,
            effectively converting the byte to an
            unsigned integer value between 0 and 255.
            The result of this operation is stored
            in the integer variable v.
         */

        hexChar[0] = hex_chars[v >>> 4];
        /*
            This line sets the first element of
            the hexChar array to the hexadecimal
            character that represents the high
            nibble of the input byte. The >>> operator
            performs a logical right shift of
            the integer value v by 4 bits,
            effectively discarding the low nibble
            and shifting the high nibble to the
            least significant position.
            The resulting value is used as
            an index to access the corresponding hexadecimal
            character in the hex_chars array,
            which is then assigned to hexChar[0].
         */

        hexChar[1] = hex_chars[v & 0x0F];
        /*
            This line sets the second element
            of the hexChar array to the hexadecimal
            character that represents the low nibble
            of the input byte. The & operator performs
            a bitwise AND operation between the
            integer value v and the hexadecimal
            value 0x0F, effectively isolating the low nibble.
            The resulting value is used as an index to access
            the corresponding hexadecimal character in the hex_chars
            array, which is then assigned to hexChar[1].
         */

        return new String(hexChar);
    }

    private static String toChar(byte b) {
        if (b >= 32 && b <= 126) {
            return Character.toString((char) b);
        } else {
            return ".";
        }
    }

    private static JToolBar top_toolbar, searchField_Toolbar;
    private static JLabel search_found;
    private static JTextField searchField;
    private static JScrollPane scrollPane;
    private static JComboBox view_type; // char OR hex
    private static JMenuItem char_type, hex_type;
    private static final int max_size = (int) 10L * 1024 * 1024 * 1024 / 4;
    private static final int top = 3, bottom = 3, right = 3, left = 3;

    public static void hex(JFrame parent, String session_filepath) {
        IconFontSwing.register(FontAwesome.getIconFont());

        byte[] bytes = session_filepath.getBytes();
        int bytes_size = bytes.length;

        if (bytes_size > 0 || bytes_size <= max_size) {
            JPanel view_panel = new JPanel(new GridLayout(0, 16));

            // Add each byte to panel as a text field
            for (byte b : bytes) {
                JTextField hexField = new JTextField(toHex(b), 2) {
                    @Override
                    public int getCaretPosition() {
                        return 0;
                    }
                };
                hexField.setHorizontalAlignment(SwingConstants.CENTER);
//                hexField.setEditable(false);
                hexField.setPreferredSize(new Dimension(30, 30));
                hexField.setMaximumSize(new Dimension(30, 30));
                hexField.setMinimumSize(new Dimension(30, 30));
                hexField.setBorder(BorderFactory.createEmptyBorder(top, bottom, right, left));
                hexField.setSelectionColor(new Color(192, 192, 192, 112));
//                hexField.setCaretColor(new Color(0, 0, 0, 0));

                hexField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        hexField.setSelectionStart(0);
                        hexField.setSelectionEnd(hexField.getText().length());
                    }
                });
                view_panel.add(hexField);
            }

            // Create split pane and set divider location
            // Add panel to scroll pane
            scrollPane = new JScrollPane(view_panel);
            scrollPane.putClientProperty("JScrollBar.showButtons", true);
            Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
            int screen_height = screen_size.height;
            JViewport viewport = scrollPane.getViewport();
            Dimension preferredSize = viewport.getPreferredSize();
            preferredSize.height = screen_height / 2;
            viewport.setPreferredSize(preferredSize);

            // Add search field to top of scroll pane
            searchField = new JTextField();
            searchField.putClientProperty("JTextField.placeholderText", "Search");
            searchField.putClientProperty("JTextField.showClearButton", true);

            searchField_Toolbar = new JToolBar();
            searchField_Toolbar.setOrientation(JToolBar.HORIZONTAL);
            searchField_Toolbar.setVisible(false);
            searchField.putClientProperty("JTextField.trailingComponent", searchField_Toolbar);
            searchField_Toolbar.addSeparator();

            search_found = new JLabel();
            searchField_Toolbar.add(search_found);
            searchField_Toolbar.add(Box.createHorizontalStrut(3));

            searchField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    filterTextFields(view_panel, searchField.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    filterTextFields(view_panel, searchField.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    filterTextFields(view_panel, searchField.getText());
                }
            });

            top_toolbar = new JToolBar();

            top_toolbar.add(searchField);
            top_toolbar.add(Box.createHorizontalStrut(3));

            String[] types_option = {"Hex", "Char"};
            view_type = new JComboBox(types_option);
            view_type.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    searchField.setText("");
                    view_type.setEnabled(false);
                    String selected = (String) e.getItem();
                    view_panel.removeAll();
                    for (byte b : bytes) {
                        String text = selected.equals("Char") ? toChar(b) : toHex(b);
                        JTextField field = new JTextField(text, 2);
                        field.setHorizontalAlignment(SwingConstants.CENTER);
                        field.setEditable(false);
                        field.setPreferredSize(new Dimension(30, 30));
                        field.setMaximumSize(new Dimension(30, 30));
                        field.setMinimumSize(new Dimension(30, 30));
                        field.setBorder(BorderFactory.createEmptyBorder(top, bottom, right, left));
                        view_panel.add(field);
                    }
                    SwingUtilities.updateComponentTreeUI(view_panel);
                    view_type.setEnabled(true);
                }
            });

            top_toolbar.add(view_type);
            top_toolbar.add(Box.createHorizontalStrut(3));
            top_toolbar.add(new JLabel(String.format("%s bytes", bytes_size)));

            scrollPane.setColumnHeaderView(top_toolbar);

            // Create main frame and add scroll pane to it
            JDialog window = new JDialog();
            window.setTitle("Hex Viewer");
            
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.add(scrollPane);
            
            window.pack();
            window.setLocationRelativeTo(parent);
            window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            window.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "The file either isn't there or it has no bytes.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Filter text fields based on search text
    private static int current_index = 0;

    private static void filterTextFields(JPanel panel, String searchText) {
        Component[] components = panel.getComponents();
        ArrayList<Component> found = new ArrayList<Component>();
        found.ensureCapacity(max_size); // max bytes are 2.68GBs
        current_index = 0;

        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (!searchField.getText().isEmpty() && textField.getText().contains(searchText)) {
                    textField.setForeground(Color.GREEN);
                    found.add(textField);
                } else {
                    textField.setForeground(new Color(192, 192, 192, 255));
                }
            }
        }

        int found_num = found.size();

        if (found_num > 0) {
            scroll_to((JTextField) found.get(current_index));
        } else {

        }

        searchField_Toolbar.setVisible(!searchField.getText().isEmpty());
        search_found.setText(String.valueOf(found_num));

        if (found_num < 1) {
            searchField.setForeground(Color.red);
        } else {
            searchField.setForeground(Color.lightGray);
        }
    }

    private static void scroll_to(JTextField field) {
        Rectangle bounds = field.getBounds();
        JViewport viewport = scrollPane.getViewport();
        bounds = SwingUtilities.convertRectangle(field.getParent(), bounds, viewport);
        viewport.scrollRectToVisible(bounds);
    }
}
