package org.virus.mypass.ui;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.virus.mypass.ui.Accent;

/**
 *
 * @author SecVirus
 */
public class KeyBoard {

    public static JButton give(Component parent, JTextField comp) {
        JButton $0, $1, $2, $3, $4, $5, $6, $7, $8, $9,
                a, b, c, d, e, f, g, h, i, j, k, l, m,
                n, o, p, q, r, s, t, u, v, w, x, y, z,
                and, asterisk, at, back_slash, backspace,
                backtick, colon, comma, dash, dollar, dot,
                double_quote, equal, exclamation, fn,
                forward_slash, hash_tag, left_alt,
                left_arrow, left_circle_bracket, left_ctrl,
                left_curly_bracket, left_shift, left_square_bracket,
                menu$list, percentage, pipe, plus, question,
                right_alt, right_arrow, right_circle_bracket,
                right_ctrl, right_curly_bracket, right_square_bracket,
                semicolon, single_quote, space, tilde, under_score,
                up_arrow, win;
        JButton KeyboardButton;
        JToggleButton caps_lock;
        JPanel panel;

        panel = new JPanel();
        at = new JButton();
        backtick = new JButton();
        exclamation = new JButton();
        percentage = new JButton();
        hash_tag = new JButton();
        dollar = new JButton();
        up_arrow = new JButton();
        and = new JButton();
        asterisk = new JButton();
        right_circle_bracket = new JButton();
        left_circle_bracket = new JButton();
        dash = new JButton();
        equal = new JButton();
        under_score = new JButton();
        plus = new JButton();
        q = new JButton();
        pipe = new JButton();
        back_slash = new JButton();
        right_square_bracket = new JButton();
        right_curly_bracket = new JButton();
        left_curly_bracket = new JButton();
        left_square_bracket = new JButton();
        p = new JButton();
        o = new JButton();
        i = new JButton();
        u = new JButton();
        y = new JButton();
        t = new JButton();
        r = new JButton();
        e = new JButton();
        w = new JButton();
        colon = new JButton();
        s = new JButton();
        k = new JButton();
        single_quote = new JButton();
        j = new JButton();
        d = new JButton();
        semicolon = new JButton();
        l = new JButton();
        h = new JButton();
        g = new JButton();
        double_quote = new JButton();
        f = new JButton();
        tilde = new JButton();
        a = new JButton();
        caps_lock = new JToggleButton();
        backspace = new JButton();
        left_shift = new JButton();
        z = new JButton();
        x = new JButton();
        c = new JButton();
        v = new JButton();
        n = new JButton();
        b = new JButton();
        comma = new JButton();
        left_arrow = new JButton();
        m = new JButton();
        forward_slash = new JButton();
        dot = new JButton();
        right_arrow = new JButton();
        question = new JButton();
        left_ctrl = new JButton();
        win = new JButton();
        left_alt = new JButton();
        space = new JButton();
        right_ctrl = new JButton();
        fn = new JButton();
        menu$list = new JButton();
        right_alt = new JButton();
        $1 = new JButton();
        $2 = new JButton();
        $3 = new JButton();
        $4 = new JButton();
        $5 = new JButton();
        $6 = new JButton();
        $7 = new JButton();
        $8 = new JButton();
        $9 = new JButton();
        $0 = new JButton();

        JDialog frame = new JDialog();

        ActionListener listener;
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String $char = e.getActionCommand();
                if ($char == "Back Space") {
                    try {
                        comp.setText(comp.getText().substring(0, comp.getText().length() - 1));
                    } catch (StringIndexOutOfBoundsException error) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                } else if ($char == "Space") {
                    comp.setText(comp.getText() + " ");
                } else {
                    if (caps_lock.isSelected()) {
                        comp.setText(comp.getText() + $char.toUpperCase());
                    } else {
                        comp.setText(comp.getText() + $char.toLowerCase());
                    }
                }
            }
        };
        $0.addActionListener(listener);
        $1.addActionListener(listener);
        $2.addActionListener(listener);
        $3.addActionListener(listener);
        $4.addActionListener(listener);
        $5.addActionListener(listener);
        $6.addActionListener(listener);
        $7.addActionListener(listener);
        $8.addActionListener(listener);
        $9.addActionListener(listener);

        a.addActionListener(listener);
        b.addActionListener(listener);
        c.addActionListener(listener);
        d.addActionListener(listener);
        e.addActionListener(listener);
        f.addActionListener(listener);
        g.addActionListener(listener);
        h.addActionListener(listener);
        i.addActionListener(listener);
        j.addActionListener(listener);
        k.addActionListener(listener);
        l.addActionListener(listener);
        m.addActionListener(listener);

        n.addActionListener(listener);
        o.addActionListener(listener);
        p.addActionListener(listener);
        q.addActionListener(listener);
        r.addActionListener(listener);
        s.addActionListener(listener);
        t.addActionListener(listener);
        u.addActionListener(listener);
        v.addActionListener(listener);
        w.addActionListener(listener);
        x.addActionListener(listener);
        y.addActionListener(listener);
        z.addActionListener(listener);

        and.addActionListener(listener);
        asterisk.addActionListener(listener);
        at.addActionListener(listener);
        back_slash.addActionListener(listener);
        backspace.addActionListener(listener);

        backtick.addActionListener(listener);
        colon.addActionListener(listener);
        comma.addActionListener(listener);
        dash.addActionListener(listener);
        dollar.addActionListener(listener);
        dot.addActionListener(listener);

        double_quote.addActionListener(listener);
        equal.addActionListener(listener);
        exclamation.addActionListener(listener);
        fn.addActionListener(listener);

        forward_slash.addActionListener(listener);
        hash_tag.addActionListener(listener);
        left_alt.addActionListener(listener);

        left_arrow.addActionListener(listener);
        left_circle_bracket.addActionListener(listener);
        left_ctrl.addActionListener(listener);

        left_curly_bracket.addActionListener(listener);
        left_shift.addActionListener(listener);
        left_square_bracket.addActionListener(listener);

        menu$list.addActionListener(listener);
        percentage.addActionListener(listener);
        pipe.addActionListener(listener);
        plus.addActionListener(listener);
        question.addActionListener(listener);

        right_alt.addActionListener(listener);
        right_arrow.addActionListener(listener);
        right_circle_bracket.addActionListener(listener);

        right_ctrl.addActionListener(listener);
        right_curly_bracket.addActionListener(listener);
        right_square_bracket.addActionListener(listener);

        semicolon.addActionListener(listener);
        single_quote.addActionListener(listener);
        space.addActionListener(listener);
        tilde.addActionListener(listener);
        under_score.addActionListener(listener);

        up_arrow.addActionListener(listener);
        win.addActionListener(listener);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setTitle(
                "KeyBoard");
        frame.setAlwaysOnTop(
                true);
        frame.setResizable(
                false);

        at.setText(
                "@");
        at.setFocusable(
                false);

        backtick.setText(
                "`");
        backtick.setFocusable(
                false);

        exclamation.setText(
                "!");
        exclamation.setFocusable(
                false);

        percentage.setText(
                "%");
        percentage.setFocusable(
                false);

        hash_tag.setText(
                "#");
        hash_tag.setFocusable(
                false);

        dollar.setText(
                "$");
        dollar.setFocusable(
                false);

        up_arrow.setText(
                "^");
        up_arrow.setFocusable(
                false);

        and.setText(
                "&");
        and.setFocusable(
                false);

        asterisk.setText(
                "*");
        asterisk.setFocusable(
                false);

        right_circle_bracket.setText(
                ")");
        right_circle_bracket.setFocusable(
                false);

        left_circle_bracket.setText(
                "(");
        left_circle_bracket.setFocusable(
                false);

        dash.setText(
                "-");
        dash.setFocusable(
                false);

        equal.setText(
                "=");
        equal.setFocusable(
                false);

        under_score.setText(
                "_");
        under_score.setFocusable(
                false);

        plus.setText(
                "+");
        plus.setFocusable(
                false);

        q.setText(
                "Q");
        q.setFocusable(
                false);

        pipe.setText(
                "|");
        pipe.setFocusable(
                false);

        back_slash.setText(
                "\\");
        back_slash.setFocusable(
                false);

        right_square_bracket.setText(
                "]");
        right_square_bracket.setFocusable(
                false);

        right_curly_bracket.setText(
                "}");
        right_curly_bracket.setFocusable(
                false);

        left_curly_bracket.setText(
                "{");
        left_curly_bracket.setFocusable(
                false);

        left_square_bracket.setText(
                "[");
        left_square_bracket.setFocusable(
                false);

        p.setText(
                "P");
        p.setFocusable(
                false);

        o.setText(
                "O");
        o.setFocusable(
                false);

        i.setText(
                "I");
        i.setFocusable(
                false);

        u.setText(
                "U");
        u.setFocusable(
                false);

        y.setText(
                "Y");
        y.setFocusable(
                false);

        t.setText(
                "T");
        t.setFocusable(
                false);

        r.setText(
                "R");
        r.setFocusable(
                false);

        e.setText(
                "E");
        e.setFocusable(
                false);

        w.setText(
                "W");
        w.setFocusable(
                false);

        colon.setText(
                ":");
        colon.setFocusable(
                false);

        s.setText(
                "S");
        s.setFocusable(
                false);

        k.setText(
                "K");
        k.setFocusable(
                false);

        single_quote.setText(
                "'");
        single_quote.setFocusable(
                false);

        j.setText(
                "J");
        j.setFocusable(
                false);

        d.setText(
                "D");
        d.setFocusable(
                false);

        semicolon.setText(
                ";");
        semicolon.setFocusable(
                false);

        l.setText(
                "L");
        l.setFocusable(
                false);

        h.setText(
                "H");
        h.setFocusable(
                false);

        g.setText(
                "G");
        g.setFocusable(
                false);

        double_quote.setText(
                "\"");
        double_quote.setFocusable(
                false);

        f.setText(
                "F");
        f.setFocusable(
                false);

        tilde.setText(
                "~");
        tilde.setFocusable(
                false);

        a.setText(
                "A");
        a.setFocusable(
                false);

        caps_lock.setFont(
                new java.awt.Font("Segoe UI", 1, 12));
        caps_lock.setForeground(
                Accent.AlphaSetGet(255));
        caps_lock.setText(
                "Caps Lock");
        caps_lock.setFocusable(
                false);

        backspace.setFont(
                new java.awt.Font("Segoe UI", 1, 12));
        backspace.setForeground(
                Accent.AlphaSetGet(255));
        backspace.setText(
                "Back Space");
        backspace.setFocusable(
                false);

        left_shift.setEnabled(
                false);
        left_shift.setFocusable(
                false);

        z.setText(
                "Z");
        z.setFocusable(
                false);

        x.setText(
                "X");
        x.setFocusable(
                false);

        c.setText(
                "C");
        c.setFocusable(
                false);

        v.setText(
                "V");
        v.setFocusable(
                false);

        n.setText(
                "N");
        n.setFocusable(
                false);

        b.setText(
                "B");
        b.setFocusable(
                false);

        comma.setText(
                ",");
        comma.setFocusable(
                false);

        left_arrow.setText(
                "<");
        left_arrow.setFocusable(
                false);

        m.setText(
                "M");
        m.setFocusable(
                false);

        forward_slash.setText(
                "/");
        forward_slash.setFocusable(
                false);

        dot.setText(
                ".");
        dot.setFocusable(
                false);

        right_arrow.setText(
                ">");
        right_arrow.setFocusable(
                false);

        question.setText(
                "?");
        question.setFocusable(
                false);

        left_ctrl.setEnabled(
                false);
        left_ctrl.setFocusable(
                false);

        win.setEnabled(
                false);
        win.setFocusable(
                false);

        left_alt.setEnabled(
                false);
        left_alt.setFocusable(
                false);

        space.setFont(
                new java.awt.Font("Segoe UI", 1, 12));
        space.setForeground(
                Accent.AlphaSetGet(255));
        space.setText(
                "Space");
        space.setFocusable(
                false);

        right_ctrl.setEnabled(
                false);
        right_ctrl.setFocusable(
                false);

        fn.setEnabled(
                false);
        fn.setFocusable(
                false);

        menu$list.setEnabled(
                false);
        menu$list.setFocusable(
                false);

        right_alt.setEnabled(
                false);
        right_alt.setFocusable(
                false);

        $1.setText(
                "1");
        $1.setFocusable(
                false);

        $2.setText(
                "2");
        $2.setFocusable(
                false);

        $3.setText(
                "3");
        $3.setFocusable(
                false);

        $4.setText(
                "4");
        $4.setFocusable(
                false);

        $5.setText(
                "5");
        $5.setFocusable(
                false);

        $6.setText(
                "6");
        $6.setFocusable(
                false);

        $7.setText(
                "7");
        $7.setFocusable(
                false);

        $8.setText(
                "8");
        $8.setFocusable(
                false);

        $9.setText(
                "9");
        $9.setFocusable(
                false);

        $0.setText(
                "0");
        $0.setFocusable(
                false);

        GroupLayout panelLayout = new GroupLayout(panel);

        panel.setLayout(panelLayout);

        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup())
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(q, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(w, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(caps_lock, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(e, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(r, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(t, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(y, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(u, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(i, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(o, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(p, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(left_square_bracket, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(left_curly_bracket, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(right_curly_bracket, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(right_square_bracket, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(back_slash, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(a, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(s, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(d, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(f, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(g, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(h, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(j, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(k, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(l, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(semicolon, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(colon, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(single_quote, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(double_quote, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(pipe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(backspace, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(backtick, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tilde, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(exclamation, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(at, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(hash_tag, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(dollar, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(percentage, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(up_arrow, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(and, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(asterisk, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(left_circle_bracket, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(right_circle_bracket, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(dash, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(under_score, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(equal, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(plus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                                .addComponent($1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent($0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(left_shift, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(z, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(x, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(c, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(v, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(b, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(n, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(m, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(comma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(left_arrow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(dot, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                                                .addComponent(left_ctrl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(win, GroupLayout.PREFERRED_SIZE, 58, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(left_alt, GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(space, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(right_alt, GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)))
                                                                .addGap(6, 6, 6)
                                                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(right_arrow, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(forward_slash, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(question, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                                .addComponent(fn, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(menu$list, GroupLayout.PREFERRED_SIZE, 67, Short.MAX_VALUE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(right_ctrl, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)))))))
                                .addGap(12, 12, 12))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent($0, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($9, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($8, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($7, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent($1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(at, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exclamation, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hash_tag, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(percentage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dollar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(up_arrow, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(asterisk, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(and, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(left_circle_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_circle_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(under_score, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(equal, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(plus, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tilde, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backtick, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(q, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(e, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(w, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(r, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(y, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(t, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(u, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(o, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(i, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(p, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(left_curly_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(left_square_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_curly_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pipe, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_square_bracket, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(back_slash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(d, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(s, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(f, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(h, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(g, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(j, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(l, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(k, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(semicolon, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(single_quote, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(colon, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(double_quote, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(backspace, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(caps_lock, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(left_shift, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(z, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(x, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(c, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(v, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(n, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(m, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comma, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(left_arrow, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dot, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_arrow, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(forward_slash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(question, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(left_ctrl, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(win, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(left_alt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(space, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_ctrl, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(menu$list, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right_alt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());

        frame.getContentPane()
                .setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        KeyboardButton = new JButton();
        KeyboardButton.setIcon(IconFontSwing.buildIcon(FontAwesome.KEYBOARD_O, ui_vars.icons_size, ui_vars.default_accent));
        KeyboardButton.setToolTipText("KeyBoard");

        JToolBar toolbar = new JToolBar();

        toolbar.add(KeyboardButton);
        toolbar.addSeparator();

        comp.putClientProperty("JTextField.leadingComponent", toolbar);

        KeyboardButton.addActionListener(ev -> {
            frame.pack();
            frame.setAlwaysOnTop(true);
            frame.setResizable(false);
            frame.setModal(true);
            frame.setLocationRelativeTo(parent);
            frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            frame.setVisible(true);
        });
        return KeyboardButton;
    }
}
