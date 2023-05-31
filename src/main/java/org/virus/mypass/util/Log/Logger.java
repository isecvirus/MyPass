package org.virus.mypass.util.Log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.json.JSONObject;
import org.virus.mypass.ui.Theme;
import org.virus.mypass.ui.ui_vars;

/**
 *
 * @author SecVirus
 */
public class Logger extends JTable {

    public static final int CRITICAL = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int SUCCESS = 3;
    public static final int VALIDATION = 4;
    public static final int DEBUG = 5;
    public static final int INFORMATION = 6;
    public static final int NOTIFICATION = 7;
    public static final int MESSAGE = 8;
    public static final int PROGRESS = 9;
    public static final int STATUS = 10;
    public static final int HELP = 11;

    public static final String[] LOG_LEVELS = {
        "CRITICAL",
        "ERROR",
        "WARNING",
        "SUCCESS",
        "VALIDATION",
        "DEBUG",
        "INFORMATION",
        "NOTIFICATION",
        "MESSAGE",
        "PROGRESS",
        "STATUS",
        "HELP"
    };

    private static final String TIME_KEY = "time";
    private static final String MESSAGE_KEY = "message";
    private static final String TYPE_KEY = "type";
    private static final String FOREGROUND_KEY = "foreground";
    private static final String BACKGROUND_KEY = "background";

    private static final Color[] BACKGROUNDS = {
        Color.decode("#FF0000"), // CRITICAL     - red
        Color.decode("#FF4500"), // ERROR        - orange
        Color.decode("#FFFF00"), // WARNING      - yellow
        Color.decode("#32CD32"), // SUCCESS      - green
        Color.decode("#00BFFF"), // VALIDATION   - blue
        Color.decode("#9400D3"), // DEBUG        - purple
        Color.decode("#FFFFFF"), // INFORMATION  - white
        Color.decode("#00FFFF"), // NOTIFICATION - cyan
        Color.decode("#808080"), // MESSAGE      - gray
        Color.decode("#FF00FF"), // PROGRESS     - magenta
        Color.decode("#FFFFFF"), // STATUS       - white
        Color.decode("#ADD8E6") // HELP         - light blue
    };

    private static final Color[] FOREGROUNDS = {
        Color.decode("#000000"), // CRITICAL     - black
        Color.decode("#000000"), // ERROR        - black
        Color.decode("#000000"), // WARNING      - black
        Color.decode("#000000"), // SUCCESS      - black
        Color.decode("#000000"), // VALIDATION   - black
        Color.decode("#000000"), // DEBUG        - black
        Color.decode("#000000"), // INFORMATION  - black
        Color.decode("#000000"), // NOTIFICATION - black
        Color.decode("#000000"), // MESSAGE      - black
        Color.decode("#000000"), // PROGRESS     - black
        Color.decode("#000000"), // STATUS       - black
        Color.decode("#000000") // HELP         - black
    };

    private static String current_time() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    private final JSONObject log_history;
    public Logger(JSONObject log_history) {
        this.log_history = log_history;

        setColumnModel(new DefaultTableColumnModel()); // Add this line
        String[] columns = new String[]{"#", "Time", "Message", "Type"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (String id : log_history.keySet()) {
            JSONObject logEntry = log_history.getJSONObject(id);
            String time = logEntry.getString(TIME_KEY);
            String message = logEntry.getString(MESSAGE_KEY);
            String type = LOG_LEVELS[logEntry.getInt(TYPE_KEY)];
            model.addRow(new Object[]{id, time, message, type});
        }

        setModel(model);

        setOpaque(false);
        setRowHeight(getRowHeight() + 25);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // enable single selection

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Center the text for the "Time" and "Type" columns
                if (column == 1 || column == 3) {
                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);
                }

                // Set the selection background default_accent to the row background default_accent with an alpha of 112
                Color foreground = (Color) log_history.getJSONObject(getValueAt(row, 0).toString()).get(FOREGROUND_KEY);
                Color background = (Color) log_history.getJSONObject(getValueAt(row, 0).toString()).get(BACKGROUND_KEY);

                if (isSelected) {
                    c.setBackground(new Color(0, 0, 0, 99));
                    c.setForeground(new Color(
                            Math.min(background.getRed() + 3, 255),
                            Math.min(background.getGreen() + 3, 255),
                            Math.min(background.getBlue() + 3, 255)
                    ));
                    Font font = c.getFont();
                    c.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
                } else {
                    c.setForeground(foreground);
                    c.setBackground(background);
                }

                // Set the tooltip text of the "Message" column to show the message
                if (column == 2) {
                    ((JLabel) c).setToolTipText(value.toString());
                }

                return c;
            }
        });

        TableRowSorter manager_sorter = new TableRowSorter<>(model);
        setRowSorter(manager_sorter);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        getTableHeader().setOpaque(false);
        getTableHeader().setPreferredSize(new Dimension(100, 30));
        setAutoCreateRowSorter(true);

        TableColumnModel columnModel = getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(400);
        columnModel.getColumn(3).setPreferredWidth(80);
    }

    public void add_log(int type, String msg) {
        String time = current_time();

        JSONObject inner = new JSONObject();
        inner.put(TIME_KEY, time);
        inner.put(MESSAGE_KEY, msg);
        inner.put(TYPE_KEY, type);
        inner.put(FOREGROUND_KEY, FOREGROUNDS[type]);
        inner.put(BACKGROUND_KEY, BACKGROUNDS[type]);

        String id = UUID.randomUUID().toString();

        DefaultTableModel model = (DefaultTableModel) getModel();
        String rowCount = String.valueOf(model.getRowCount() + 1);
        log_history.put(rowCount, inner);

        // Redraw the table with the new log message
        model.addRow(new Object[]{rowCount, time, msg, LOG_LEVELS[type]});
        this.updateUI();
    }

    public JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    public void show(JFrame parent, JSONObject log_history) {
        // create a JDialog to hold the log table
        JDialog dialog = new JDialog(parent, "Logger", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // set the size of the dialog to half of the screen width and height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = screenSize.width / 3;
        int dialogHeight = screenSize.height / 3;
        dialog.setSize(dialogWidth, dialogHeight);

        // center the dialog on the screen
        dialog.setLocationRelativeTo(null);

        // create an instance of the Logger class
        Logger logger = new Logger(log_history);
        JScrollPane scrollPane = logger.getScrollPane();

        JTextField search = new JTextField();
        search.putClientProperty("JTextField.placeholderText", "Search..");
        search.putClientProperty("JTextField.showClearButton", true);
        search.setPreferredSize(new Dimension(search.getPreferredSize().width, search.getPreferredSize().height + 25));
        search.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < ui_vars.manager_columns.length; i++) {
            ((TableRowSorter) logger.getRowSorter()).setSortable(i, false);
        }

        search.getDocument().addDocumentListener(new DocumentListener() {
            public void search(String query) {
                TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) logger.getRowSorter();
                if (query.length() > 0) {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(query));
                        search.setForeground(Color.decode(ui_vars.valid_search_color));
                    } catch (Exception error) {
                        // PatternSyntaxException, NumberFormatException
                    }
                } else {
                    sorter.setRowFilter(null);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(search.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(search.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                search(search.getText());
            }
        });

        // create a panel to hold the log table and add it to the dialog
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(search, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(scrollPane, BorderLayout.CENTER);
        dialog.setContentPane(panel);

        // show the dialog
        dialog.applyComponentOrientation(parent.getComponentOrientation());
        dialog.setLocationRelativeTo(parent);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
    }
}
