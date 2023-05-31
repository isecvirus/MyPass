package org.virus.mypass.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

/**
 * @author SecVirus
 *
 * This file contains resources to configure the manager table.
 */
public class Manager {

    public static void CenterTableRows(JTable table, DefaultTableCellRenderer renderer, String[] columns) {
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columns.length; ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
//            table.getColumnModel().getColumn(i).setMinWidth(columns[i].length() * 20);
            table.getColumnModel().getColumn(i).setResizable(true);
        }
    }

    public static void draggable(JTable manager_table) {
        boolean isDragable = manager_table.getDragEnabled();
        if (isDragable) {
            manager_table.setDragEnabled(false);
        } else {
            manager_table.setDragEnabled(true);
        }
    }
}
