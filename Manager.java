package com.virus.MyPass;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultTreeCellEditor;

/**
 * @author SecVirus
 *
 * This file contains resources to.. configure the manager table.
 */
public class Manager {

    public static void CenterTableRows(JTable table, DefaultTableCellRenderer renderer, String[] columns) {
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columns.length; ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
            table.getColumnModel().getColumn(i).setMinWidth(columns[i].length() * 20);
            table.getColumnModel().getColumn(i).setResizable(true);
        }
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
    }
}
