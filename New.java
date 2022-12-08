package com.virus.MyPass;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SecVirus
 */
public class New {

    public static void password(DefaultTableModel table_model) {
        int index = table_model.getRowCount() + 1;
        Object[] a = {index, "", "", Vars.unknwon_length};
        table_model.addRow(a);
    }
}
