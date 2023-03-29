package com.virus.MyPass.ui;

import com.formdev.flatlaf.icons.FlatMenuArrowIcon;
import com.virus.MyPass.ui.ui_vars;
import com.virus.MyPass.util.ClipBoard.Copy;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 * MyPass Generator
 *
 * @author SecVirus
 */
public class MGenerator {

    public static void run(JFrame parent, JPasswordField... password_fields) {
        IconFontSwing.register(FontAwesome.getIconFont());

        JDialog frame = new JDialog(parent);
        frame.setTitle("Generator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", ui_vars.color(25));

        GroupLayout layout;

        JCheckBox isAlphabetUpper, isAlphabetLower, isNumbers, isPunctuation, isCustom, isAutoGenerate;
        JTextField AlphabetUpper, AlphabetLower, Numbers, Punctuation, Custom;
        JSpinner PasswordLength;
        JPasswordField ResultPassword;
        JButton GenerateBtn, CopyBtn, ClearBtn, ResetBtn, ApplyBtn, ResultHistoryBtn, RefillAlphabetUpper, RefillAlphabetLower, RefillNumbers, RefillPunctuation, ClearPasswordsHistoryBtn, CopyAlphabetUpper, CopyAlphabetLower, CopyNumbers, CopyPunctuation, CopyCustom;
        JSeparator separator;
        List<String> ResultHistoryList;
        ActionListener run_generator, apply_listener;
        DocumentListener password_listener;
        JToolBar ResultPasswordToolBar, AlphabetUpperToolbar, AlphabetLowerToolbar, NumbersToolbar, PunctuationToolbar, CustomToolbar;

        // -----------------------------------
        ResultHistoryList = new ArrayList<>();

        ResultPasswordToolBar = new JToolBar();
        AlphabetUpperToolbar = new JToolBar();
        AlphabetLowerToolbar = new JToolBar();
        NumbersToolbar = new JToolBar();
        PunctuationToolbar = new JToolBar();
        CustomToolbar = new JToolBar();

        isAlphabetUpper = new JCheckBox("Alphabet (Upper Case)");
        isAlphabetUpper.setSelected(true);
        isAlphabetUpper.setToolTipText("Include upper letters in the password?");

        isAlphabetLower = new JCheckBox("Alphabet (Lower Case)");
        isAlphabetLower.setSelected(true);
        isAlphabetLower.setToolTipText("Include lower letters in the password?");

        isNumbers = new JCheckBox("Number");
        isNumbers.setSelected(true);
        isNumbers.setToolTipText("Included numbers in the password?");

        isPunctuation = new JCheckBox("Punctuation");
        isPunctuation.setSelected(true);
        isPunctuation.setToolTipText("Include punctuations in the password?");

        isCustom = new JCheckBox("Custom");
        isCustom.setSelected(false);
        isCustom.setToolTipText("Include custom characters in the password?");

        isAutoGenerate = new JCheckBox("Auto Generate");
        isAutoGenerate.setSelected(false);
        isAutoGenerate.setToolTipText("Auto Generate password with settings changes");

        AlphabetUpper = new JTextField();
        AlphabetUpper.setText(gen_vars.upper_letters);
        AlphabetUpper.setEnabled(true);
        AlphabetUpper.putClientProperty("JTextField.showClearButton", true);
        AlphabetUpper.putClientProperty("JTextField.placeholderText", "Alphabet Upper");
        AlphabetUpper.setToolTipText("Included upper letters");
        KeyBoard.show(frame, AlphabetUpper);

        AlphabetLower = new JTextField();
        AlphabetLower.setText(gen_vars.lower_letters);
        AlphabetLower.setEnabled(true);
        AlphabetLower.putClientProperty("JTextField.showClearButton", true);
        AlphabetLower.putClientProperty("JTextField.placeholderText", "Alphabet Lower");
        AlphabetLower.setToolTipText("Included lower letters");
        KeyBoard.show(frame, AlphabetLower);

        Numbers = new JTextField();
        Numbers.setText(gen_vars.numbers);
        Numbers.setEnabled(true);
        Numbers.putClientProperty("JTextField.showClearButton", true);
        Numbers.putClientProperty("JTextField.placeholderText", "Number");
        Numbers.setToolTipText("Included numbers");
        KeyBoard.show(frame, Numbers);
        

        Punctuation = new JTextField();
        Punctuation.setText(gen_vars.punctuations);
        Punctuation.setEnabled(true);
        Punctuation.putClientProperty("JTextField.showClearButton", true);
        Punctuation.putClientProperty("JTextField.placeholderText", "Punctuation");
        Punctuation.setToolTipText("Included punctuations");
        KeyBoard.show(frame, Punctuation);

        Custom = new JTextField();
        Custom.setEnabled(false);
        Custom.putClientProperty("JTextField.showClearButton", true);
        Custom.putClientProperty("JTextField.placeholderText", "Custom");
        Custom.setToolTipText("Custom characters");
        JButton CustomKeyboardButton = KeyBoard.show(frame, Custom);
        CustomKeyboardButton.setEnabled(false);

        PasswordLength = new JSpinner();
        PasswordLength.setModel(new SpinnerNumberModel(gen_vars.initial_len, gen_vars.min_len, gen_vars.max_len, gen_vars.steps));
        PasswordLength.setToolTipText("Password Length");
        ((DefaultEditor) PasswordLength.getEditor()).getTextField().setEditable(false);

        ResultPassword = new JPasswordField();
        ResultPassword.setEditable(false);
        ResultPassword.setEchoChar('\u2022');
        ResultPassword.putClientProperty("FlatLaf.style", "showRevealButton: true");

        GenerateBtn = new JButton("Generate");
        GenerateBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.RANDOM, ui_vars.icons_size, ui_vars.color));
        GenerateBtn.setEnabled(true);

        CopyBtn = new JButton("Copy");
        CopyBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyBtn.setEnabled(false);

        ClearBtn = new JButton("Clear");
        ClearBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, ui_vars.color));
        ClearBtn.setEnabled(false);

        ResetBtn = new JButton("Reset");
        ResetBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.RETWEET, ui_vars.icons_size, ui_vars.color));
        ResetBtn.setEnabled(true);

        // --------------------
        RefillAlphabetUpper = new JButton();
        RefillAlphabetUpper.setIcon(IconFontSwing.buildIcon(FontAwesome.RETWEET, ui_vars.icons_size, ui_vars.color));
        RefillAlphabetUpper.setToolTipText("Refill");
        RefillAlphabetUpper.setEnabled(true);

        CopyAlphabetUpper = new JButton();
        CopyAlphabetUpper.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyAlphabetUpper.setToolTipText("Copy Alphabet Upper");
        CopyAlphabetUpper.setEnabled(true);
        CopyAlphabetUpper.addActionListener(e -> {
            Copy.string(AlphabetUpper.getText());
        });

        AlphabetUpperToolbar.addSeparator();
        AlphabetUpperToolbar.add(RefillAlphabetUpper);
        AlphabetUpperToolbar.add(CopyAlphabetUpper);
        AlphabetUpper.putClientProperty("JTextField.trailingComponent", AlphabetUpperToolbar);

        // --------------------
        RefillAlphabetLower = new JButton();
        RefillAlphabetLower.setIcon(IconFontSwing.buildIcon(FontAwesome.RETWEET, ui_vars.icons_size, ui_vars.color));
        RefillAlphabetLower.setToolTipText("Refill");
        RefillAlphabetLower.setEnabled(true);

        CopyAlphabetLower = new JButton();
        CopyAlphabetLower.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyAlphabetLower.setToolTipText("Copy Alphabet Lower");
        CopyAlphabetLower.setEnabled(true);
        CopyAlphabetLower.addActionListener(e -> {
            Copy.string(AlphabetLower.getText());
        });

        AlphabetLowerToolbar.addSeparator();
        AlphabetLowerToolbar.add(RefillAlphabetLower);
        AlphabetLowerToolbar.add(CopyAlphabetLower);
        AlphabetLower.putClientProperty("JTextField.trailingComponent", AlphabetLowerToolbar);

        // --------------------
        RefillNumbers = new JButton();
        RefillNumbers.setIcon(IconFontSwing.buildIcon(FontAwesome.RETWEET, ui_vars.icons_size, ui_vars.color));
        RefillNumbers.setToolTipText("Refill");
        RefillNumbers.setEnabled(true);

        CopyNumbers = new JButton();
        CopyNumbers.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyNumbers.setToolTipText("Copy Numbers");
        CopyNumbers.setEnabled(true);
        CopyNumbers.addActionListener(e -> {
            Copy.string(Numbers.getText());
        });

        NumbersToolbar.addSeparator();
        NumbersToolbar.add(RefillNumbers);
        NumbersToolbar.add(CopyNumbers);
        Numbers.putClientProperty("JTextField.trailingComponent", NumbersToolbar);

        // --------------------
        RefillPunctuation = new JButton();
        RefillPunctuation.setIcon(IconFontSwing.buildIcon(FontAwesome.RETWEET, ui_vars.icons_size, ui_vars.color));
        RefillPunctuation.setToolTipText("Refill");
        RefillPunctuation.setEnabled(true);

        CopyPunctuation = new JButton();
        CopyPunctuation.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyPunctuation.setToolTipText("Copy Punctuations");
        CopyPunctuation.setEnabled(true);
        CopyPunctuation.addActionListener(e -> {
            Copy.string(Punctuation.getText());
        });

        PunctuationToolbar.addSeparator();
        PunctuationToolbar.add(RefillPunctuation);
        PunctuationToolbar.add(CopyPunctuation);
        Punctuation.putClientProperty("JTextField.trailingComponent", PunctuationToolbar);

        // --------------------
        CopyCustom = new JButton();
        CopyCustom.setIcon(IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, ui_vars.icons_size, ui_vars.color));
        CopyCustom.setToolTipText("Copy Custom Characters");
        CopyCustom.setEnabled(false);
        CopyCustom.addActionListener(e -> {
            Copy.string(Custom.getText());
        });

        CustomToolbar.addSeparator();
        CustomToolbar.add(CopyCustom);
        Custom.putClientProperty("JTextField.trailingComponent", CustomToolbar);

        // --------------------
        ApplyBtn = new JButton("Apply");
        ApplyBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.CHECK, ui_vars.icons_size, ui_vars.color));
        ApplyBtn.setEnabled(false);
        if (password_fields == null) {
            ApplyBtn.setText("Exit");
            ApplyBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.POWER_OFF, ui_vars.icons_size, ui_vars.color));
        }

        ResultHistoryBtn = new JButton((Icon) new FlatMenuArrowIcon());
        ResultHistoryBtn.setToolTipText("History");
        ResultHistoryBtn.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();

            for (int sr = 0; sr < ResultHistoryList.size(); sr++) { // sr=search result
                String password = ResultHistoryList.get(sr);
                JMenuItem this_item = new JMenuItem(password);
                this_item.addActionListener(item_al -> {
                    ResultPassword.setText(password);
                });

                popupMenu.add(this_item);
            }
            popupMenu.show(ResultHistoryBtn, ResultHistoryBtn.getWidth(), 0);
        });

        ClearPasswordsHistoryBtn = new JButton();
        ClearPasswordsHistoryBtn.setIcon(IconFontSwing.buildIcon(FontAwesome.ERASER, ui_vars.icons_size, ui_vars.color));
        ClearPasswordsHistoryBtn.setEnabled(false);
        ClearPasswordsHistoryBtn.setToolTipText("Clear History");

        ClearPasswordsHistoryBtn.addActionListener(e -> {
            ResultHistoryList.clear();
            ClearPasswordsHistoryBtn.setEnabled(false);
        });

        ResultPasswordToolBar.add(ResultHistoryBtn);
        ResultPasswordToolBar.addSeparator();
        ResultPasswordToolBar.add(ClearPasswordsHistoryBtn);

        ResultPassword.putClientProperty("JTextField.trailingComponent", ResultPasswordToolBar);

        separator = new JSeparator();

        RefillAlphabetUpper.addActionListener(e -> {
            AlphabetUpper.setText(gen_vars.upper_letters);
        });
        RefillAlphabetLower.addActionListener(e -> {
            AlphabetLower.setText(gen_vars.lower_letters);
        });
        RefillNumbers.addActionListener(e -> {
            Numbers.setText(gen_vars.numbers);
        });
        RefillPunctuation.addActionListener(e -> {
            Punctuation.setText(gen_vars.punctuations);
        });

        run_generator = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String password = generate();

                ResultPassword.setText(password);
                ClearBtn.setEnabled(true);
                CopyBtn.setEnabled(true);
                if (!ResultHistoryList.contains(password)) {
                    ResultHistoryList.add(password);
                }
                if (!ResultHistoryList.isEmpty()) {
                    ClearPasswordsHistoryBtn.setEnabled(true);
                } else {
                    ClearPasswordsHistoryBtn.setEnabled(false);
                }
            }

            public String generate() {
                String password = "";
                String characters = chars();

                try {
                    for (int i = 0; i < (int) PasswordLength.getValue(); i++) {
                        int random_index = (int) (Math.random() * characters.length());
                        password += characters.charAt(random_index);
                    }
                } catch (Exception error) {
                }

                return password;
            }

            private String chars() {
                String charsSeq = ""; // characters sequence
                if (isAlphabetUpper.isSelected()) {
                    charsSeq += AlphabetUpper.getText();
                }
                if (isAlphabetLower.isSelected()) {
                    charsSeq += AlphabetLower.getText();
                }
                if (isNumbers.isSelected()) {
                    charsSeq += Numbers.getText();
                }
                if (isPunctuation.isSelected()) {
                    charsSeq += Punctuation.getText();
                }
                if (isCustom.isSelected()) {
                    charsSeq += Custom.getText();
                }

                return charsSeq;
            }
        };

        ResultHistoryBtn.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();

            for (int sr = 0; sr < ResultHistoryList.size(); sr++) { // sr=search result
                popupMenu.add(ResultHistoryList.get(sr));
            }
            popupMenu.show(ResultHistoryBtn, 0, ResultHistoryBtn.getHeight());
        });

        isAlphabetUpper.addActionListener((e) -> {
            boolean status = isAlphabetUpper.isSelected();
            AlphabetUpper.setEnabled(status);
            RefillAlphabetUpper.setEnabled(status);
            CopyAlphabetUpper.setEnabled(status);

            if (isAutoGenerate.isSelected()) {
                run_generator.actionPerformed(null);
            }
        });

        isAlphabetLower.addActionListener((e) -> {
            boolean status = isAlphabetLower.isSelected();
            AlphabetLower.setEnabled(status);
            RefillAlphabetLower.setEnabled(status);
            CopyAlphabetLower.setEnabled(status);

            if (isAutoGenerate.isSelected()) {
                run_generator.actionPerformed(null);
            }
        });

        isNumbers.addActionListener((e) -> {
            boolean status = isNumbers.isSelected();
            Numbers.setEnabled(status);
            RefillNumbers.setEnabled(status);
            CopyNumbers.setEnabled(status);

            if (isAutoGenerate.isSelected()) {
                run_generator.actionPerformed(null);
            }
        });

        isPunctuation.addActionListener((e) -> {
            boolean status = isPunctuation.isSelected();
            Punctuation.setEnabled(status);
            RefillPunctuation.setEnabled(status);
            CopyPunctuation.setEnabled(status);

            if (isAutoGenerate.isSelected()) {
                run_generator.actionPerformed(null);
            }
        });

        isCustom.addActionListener((e) -> {
            boolean status = isCustom.isSelected();
            CustomKeyboardButton.setEnabled(status);
            Custom.setEnabled(status);
            CopyCustom.setEnabled(status);

            if (isAutoGenerate.isSelected()) {
                run_generator.actionPerformed(null);
            }
        });

        PasswordLength.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                if (isAutoGenerate.isSelected()) {
                    run_generator.actionPerformed(null);
                }
            }
        });

        password_listener = new DocumentListener() {
            private void check_password() {
                if (String.valueOf(ResultPassword.getPassword()).length() > 0) {
                    ApplyBtn.setEnabled(true);
                } else {
                    ApplyBtn.setEnabled(false);
                }
            }

            public void insertUpdate(DocumentEvent de) {
                check_password();
            }

            public void removeUpdate(DocumentEvent de) {
                check_password();
            }

            public void changedUpdate(DocumentEvent de) {
                check_password();
            }
        };
        ResultPassword.getDocument().addDocumentListener(password_listener);

        GenerateBtn.addActionListener(run_generator);

        CopyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Copy.string(String.valueOf(ResultPassword.getPassword()));
            }
        });

        ClearBtn.addActionListener(e -> {
            ResultPassword.setText("");
            ClearBtn.setEnabled(false);
            CopyBtn.setEnabled(false);
        });

        ResetBtn.addActionListener(e -> {
            isAlphabetUpper.setSelected(true);
            AlphabetUpper.setText(gen_vars.upper_letters);
            AlphabetUpper.setEnabled(true);
            CopyAlphabetUpper.setEnabled(true);
            RefillAlphabetUpper.setEnabled(true);

            isAlphabetLower.setSelected(true);
            AlphabetLower.setText(gen_vars.lower_letters);
            AlphabetLower.setEnabled(true);
            CopyAlphabetLower.setEnabled(true);
            RefillAlphabetLower.setEnabled(true);

            isNumbers.setSelected(true);
            Numbers.setText(gen_vars.numbers);
            Numbers.setEnabled(true);
            CopyNumbers.setEnabled(true);
            RefillNumbers.setEnabled(true);

            isPunctuation.setSelected(true);
            Punctuation.setText(gen_vars.punctuations);
            Punctuation.setEnabled(true);
            CopyPunctuation.setEnabled(true);
            RefillPunctuation.setEnabled(true);

            isCustom.setSelected(false);
            Custom.setText("");
            CustomKeyboardButton.setEnabled(false);
            Custom.setEnabled(false);
            CopyCustom.setEnabled(false);

            isAutoGenerate.setSelected(false);

            PasswordLength.setValue(Integer.valueOf(gen_vars.initial_len));
            ResultPassword.setText("");

            GenerateBtn.setEnabled(true);
            CopyBtn.setEnabled(false);
            ClearBtn.setEnabled(false);
        });

        isAutoGenerate.addActionListener((e) -> {
            if (isAutoGenerate.isSelected()) {
                GenerateBtn.setEnabled(false);
            } else {
                GenerateBtn.setEnabled(true);
            }
        });

        DocumentListener doc_listener = new DocumentListener() {
            private void isEmpty(JCheckBox is, JTextField field) {
                if (!is.isSelected()) {
                    is.setSelected(false);
                    field.setEnabled(false);
                }
            }

            private void toggle() {
                isEmpty(isAlphabetUpper, AlphabetUpper);
                isEmpty(isAlphabetLower, AlphabetLower);
                isEmpty(isNumbers, Numbers);
                isEmpty(isPunctuation, Punctuation);
                isEmpty(isCustom, Custom);

                if (isAutoGenerate.isSelected()) {
                    run_generator.actionPerformed(null);
                }
            }

            public void insertUpdate(DocumentEvent e) {
                toggle();
            }

            public void removeUpdate(DocumentEvent e) {
                toggle();
            }

            public void changedUpdate(DocumentEvent e) {
                toggle();
            }
        };

        AlphabetUpper.getDocument().addDocumentListener(doc_listener);
        AlphabetLower.getDocument().addDocumentListener(doc_listener);
        Numbers.getDocument().addDocumentListener(doc_listener);
        Punctuation.getDocument().addDocumentListener(doc_listener);
        Custom.getDocument().addDocumentListener(doc_listener);
        

        layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(separator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Punctuation)
                                        .addComponent(Numbers)
                                        .addComponent(AlphabetLower)
                                        .addComponent(AlphabetUpper)
                                        .addComponent(Custom)
                                        .addComponent(ResultPassword)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(GenerateBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(CopyBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ClearBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(isPunctuation)
                                                        .addComponent(isNumbers)
                                                        .addComponent(isAlphabetLower)
                                                        .addComponent(isAlphabetUpper)
                                                        .addComponent(isCustom))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(ResetBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ApplyBtn))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(isAutoGenerate)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(PasswordLength, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(isAlphabetUpper)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlphabetUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isAlphabetLower)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlphabetLower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isNumbers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Numbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isPunctuation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Punctuation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isCustom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Custom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(PasswordLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isAutoGenerate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(ResultPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CopyBtn)
                                        .addComponent(ClearBtn)
                                        .addComponent(GenerateBtn))
                                .addGap(26, 26, 26)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ApplyBtn)
                                        .addComponent(ResetBtn)
                                )
                                .addContainerGap())
        );

        apply_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (password_fields != null) {
                    String password = String.valueOf(ResultPassword.getPassword());

                    if (password.length() > 0) {
                        for (int pf = 0; pf < password_fields.length; pf++) {
                            password_fields[pf].setText(password);
                        }
                    }
                }
                frame.dispose();
            }
        };

        ApplyBtn.addActionListener(apply_listener);

        // ---------------------------------------------------------------------
        ComponentOrientation orientation = parent.getComponentOrientation();

        frame.applyComponentOrientation(orientation);

        isAlphabetUpper.applyComponentOrientation(orientation);
        isAlphabetLower.applyComponentOrientation(orientation);
        isNumbers.applyComponentOrientation(orientation);
        isPunctuation.applyComponentOrientation(orientation);
        isCustom.applyComponentOrientation(orientation);
        isAutoGenerate.applyComponentOrientation(orientation);

        AlphabetUpper.applyComponentOrientation(orientation);
        AlphabetLower.applyComponentOrientation(orientation);
        Numbers.applyComponentOrientation(orientation);
        Punctuation.applyComponentOrientation(orientation);
        Custom.applyComponentOrientation(orientation);
        ResultPassword.applyComponentOrientation(orientation);

        PasswordLength.applyComponentOrientation(orientation);

        GenerateBtn.applyComponentOrientation(orientation);
        CopyBtn.applyComponentOrientation(orientation);
        ClearBtn.applyComponentOrientation(orientation);
        ResetBtn.applyComponentOrientation(orientation);
        ApplyBtn.applyComponentOrientation(orientation);

        // ---------------------------------------------------------------------
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setModal(true);
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
    }
}
