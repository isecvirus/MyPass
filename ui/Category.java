package org.virus.mypass.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.json.JSONObject;
import org.virus.mypass.ui.Accent;
import org.virus.mypass.ui.categories;

/**
 *
 * @author SecVirus
 */
public class Category {

    static String selected_icon_name, selected_icon_code;
    static Color selected_icon_color;
    static JDialog window;
    static JScrollPane scrollPane;
    static JPanel icons_panel;

    public void pick(Component comp, Color color) {
        IconFontSwing.register(FontAwesome.getIconFont());
        
        if (color == null) {
            selected_icon_color = Accent.AlphaSetGet(255);
        } else {
            selected_icon_color = color;
        }

        icons_panel = new JPanel(new GridLayout(categories.icons.length() / 8, 8));
        window = new JDialog();
        scrollPane = new JScrollPane(icons_panel);
        scrollPane.putClientProperty("JScrollBar.showButtons", true);

        map("");

        JToolBar top_toolbar = new JToolBar();

        JButton colorBtn = new JButton("   ");
        colorBtn.setBackground(selected_icon_color);

        JTextField searchField = new JTextField();
        
        colorBtn.addActionListener(l -> {
            Color selectedColor = JColorChooser.showDialog(window, "Choose a color", selected_icon_color);
            if (selectedColor != null) {
                colorBtn.setBackground(selectedColor);
                selected_icon_color = selectedColor;
                map(searchField.getText());
            }
        });
        
        searchField.putClientProperty("JTextField.placeholderText", "Search");
        searchField.putClientProperty("JTextField.showClearButton", true);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                map(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                map(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        top_toolbar.add(searchField);
        top_toolbar.add(colorBtn);

        scrollPane.setColumnHeaderView(top_toolbar);

        window.setTitle("Icons");

        window.setPreferredSize(new Dimension(440, 500));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.add(scrollPane);

        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(comp);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setVisible(true);
    }

    private static void map(String query) {
        icons_panel.removeAll();
        JSONObject icons = categories.icons;
        int found = 0;
        for (String cat : icons.keySet()) {
            if (cat.toLowerCase().contains(query)) {
                String icon_name = icons.getString(cat);
                Icon icon = IconFontSwing.buildIcon(FontAwesome.valueOf(icon_name), 25, selected_icon_color);
                JButton button = new JButton(icon);
                button.setPreferredSize(new Dimension(50, 50));

                button.setToolTipText(cat);
                button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setVerticalAlignment(SwingConstants.TOP);
                button.setHorizontalAlignment(SwingConstants.CENTER);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                            window.dispose();
                            selected_icon_name = cat;
                            selected_icon_code = icon_name;
                        }
                    }
                });

                icons_panel.add(button);
                found += 1;
            }
        }
        icons_panel.setLayout(new GridLayout(found / 8, 8));
        icons_panel.updateUI();
        icons_panel.repaint();
    }

    public String getName() {
        return selected_icon_name;
    }

    public String getIcon() {
        return selected_icon_code;
    }

    public String getHexColor() {
        return Accent.ColorToHex(selected_icon_color);
    }
    
    public Color getColor() {
        return selected_icon_color;
    }

}
