package org.virus.mypass.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.virus.mypass.util.Strength.Shannon.passwordStrength;
import org.virus.mypass.util.Misc.red2green;

/**
 *
 * @author SecVirus
 */
public class Passwords {

    public static String ask(Component comp, String title) {
        JPanel handlePanel = new JPanel();
        String[] options = new String[]{"Submit", "Cancel"};
        JPasswordField passwordInput = AdvancedPasswordField();

        passwordInput.setColumns(20);

        handlePanel.add(passwordInput);

        int answer;
        answer = JOptionPane.showOptionDialog(comp, handlePanel, title, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, IconFontSwing.buildIcon(FontAwesome.LOCK, 50, Accent.AlphaSetGet(255)), options, options[0]);

        if (answer == 0) {
            return String.valueOf(passwordInput.getPassword());
        }
        return null;
    }

    public static class StrengthProgressbar extends JProgressBar {

        private JProgressBar progressbar = new JProgressBar();
        private int strength = 0;
        private boolean focusable = false;
        private boolean paintString = false;
        private int min = 0;
        private int max = 100;

        public StrengthProgressbar() {
            progressbar.setMinimum(min);
            progressbar.setMaximum(max);
            progressbar.setValue(strength);
            progressbar.setFocusable(focusable);
            progressbar.setStringPainted(paintString); // text on progressbar
            progressbar.setToolTipText("0%");
        }

        public int getStrength() {
            return strength;
        }
    }

    public static class PasswordStrengthIndicator extends JDialog {

        private JPanel panel = new JPanel();
        private StrengthProgressbar progressbar;
        JPasswordField password_field;
        GroupLayout layout;
        private final String message = "Password Strength Meter";

        public PasswordStrengthIndicator(JFrame parent) {
            super(parent, true);

            setTitle(message);
            setLocationRelativeTo(parent);

            IconFontSwing.register(FontAwesome.getIconFont());

            layout = new GroupLayout(getContentPane());

            String placeholder = "Password";
            String tooltip = "";

            password_field = new JPasswordField();
            password_field.putClientProperty("JTextField.placeholderText", placeholder);
            password_field.putClientProperty("PasswordField.showCapsLock", true);
            password_field.putClientProperty("FlatLaf.style", "showRevealButton: true");
            password_field.putClientProperty("JTextField.showClearButton", true);
            password_field.setToolTipText(tooltip);
            password_field.setEchoChar('\u2022');

            KeyBoard.give(null, password_field);
            progressbar = new StrengthProgressbar();
            progressbar.setStringPainted(true);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(progressbar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(password_field))
                                    .addGap(25, 25, 25))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(progressbar, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(31, Short.MAX_VALUE))
            );

            panel.setLayout(layout);
            getContentPane().setLayout(layout);
            setSize(300, 100);
            setResizable(false);

            password_field.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update();
                }

            });

            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }

        public String getPassword() {
            return String.valueOf(password_field.getPassword());
        }

        public int getStrength() {
            return passwordStrength(getPassword());
        }

        private void update() {
            int strength = passwordStrength(getPassword());
            progressbar.setValue(strength);
            progressbar.setToolTipText(String.format("%d%%", strength));

            int index = Math.max(0, strength - 1);
            progressbar.setForeground(Color.decode((String) red2green.Red2Green().get(index)));
        }
    }

    public static JPasswordField AdvancedPasswordField() {

        JPasswordField password_field;
        String placeholder = "Password";
        String tooltip = "";

        password_field = new JPasswordField();
        password_field.putClientProperty("JTextField.placeholderText", placeholder);
        password_field.putClientProperty("PasswordField.showCapsLock", true);
        password_field.putClientProperty("FlatLaf.style", "showRevealButton: true");
        password_field.putClientProperty("JTextField.showClearButton", true);
        password_field.setToolTipText(tooltip);
        password_field.setEchoChar('\u2022');

        KeyBoard.give(null, password_field);

        return password_field;
    }

    public static void Reporter(JFrame parent, Object data) {
        JDialog dialog = new JDialog(parent, "Passwords Report", true);
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Entity", "Strength"});
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setOpaque(false);
        table.getTableHeader().setOpaque(false);

        table.getColumnModel().getColumn(1).setCellRenderer(new ProgressBarRenderer());
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        if (data instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) data;
            for (String entity : jsonObject.keySet()) {
                tableModel.addRow(new Object[]{entity, jsonObject.getJSONObject(entity).getString("password")});
            }
        } else if (data instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) data;
            for (Object obj : jsonArray.toList()) {
                String password = (String) obj;
                tableModel.addRow(new Object[]{password, password});
            }
        }

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);

        JTextField searchField = new JTextField();
        searchField.setBorder(new CompoundBorder(
                searchField.getBorder(),
                new EmptyBorder(3, 3, 3, 3)
        ));
        searchField.putClientProperty("JTextField.placeholderText", "Search..");
        searchField.putClientProperty("JTextField.showClearButton", true);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(7, 7, 7, 7));
        searchPanel.add(searchField, BorderLayout.CENTER);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void search(String query) {
                if (query.length() > 0) {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter(query));
                        searchField.setForeground(Color.decode(ui_vars.valid_search_color));
                    } catch (Exception error) {
                        // PatternSyntaxException, NumberFormatException
                    }
                } else {
                    rowSorter.setRowFilter(null);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(panel);

        dialog.setModalityType(ModalityType.APPLICATION_MODAL);

        int width = screen_size.width;
        int height = screen_size.height;
        dialog.setPreferredSize(new Dimension(width / 3, height / 3));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private static class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {

        public ProgressBarRenderer() {
            super(0, 100);
            setStringPainted(true);
            Color foreground_color = Color.decode("#222222");
            setForeground(foreground_color);
            setFont(new Font(getFont().getName(), Font.BOLD, getFont().getSize() + 1)); // Set the font size to 14
            setUI(new BasicProgressBarUI() {
                protected Color getSelectionForeground() {
                    return foreground_color;
                }

                protected Color getSelectionBackground() {
                    return foreground_color;
                }

                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    super.paint(g2d, c);
                }
            });
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            int strength = passwordStrength(value.toString());

            setBackground(ui_vars.color(33));
            setValue(strength);
            int index = Math.max(0, strength - 1);
            setForeground(Color.decode((String) red2green.Red2Green().get(index)));
            return this;
        }
    }
}
