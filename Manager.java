package com.virus.MyPass;

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
        table.getColumnModel().getColumn(Vars.no_index).setWidth(15);
        table.getColumnModel().getColumn(Vars.no_index).setMinWidth(10);
        table.getColumnModel().getColumn(Vars.no_index).setMaxWidth(50);
        
        table.getColumnModel().getColumn(Vars.id_index).setWidth(50);
        table.getColumnModel().getColumn(Vars.id_index).setMinWidth(25);
        table.getColumnModel().getColumn(Vars.id_index).setMaxWidth(200);
        
//        table.getColumnModel().getColumn(Vars.password_index).setWidth(150);
        table.getColumnModel().getColumn(Vars.password_index).setMinWidth(50);
//        table.getColumnModel().getColumn(Vars.password_index).setMaxWidth(400);
//        
        table.getColumnModel().getColumn(Vars.password_length_index).setWidth(50);
        table.getColumnModel().getColumn(Vars.password_length_index).setMinWidth(25);
        table.getColumnModel().getColumn(Vars.password_length_index).setMaxWidth(50);
    }

    public static void draggable(JTable manager_table) {
        boolean isDragable = manager_table.getDragEnabled();
        if (isDragable) {
            manager_table.setDragEnabled(false);
        } else {
            manager_table.setDragEnabled(true);
        }
    }
    
    public static void ReArrange(JTable manager_table) {
        int index = 0;
        for (int i=0;i<manager_table.getRowCount();i++) {
            index += 1;
            manager_table.setValueAt(index, i, Vars.no_index);
        }
        
        
    }
}
