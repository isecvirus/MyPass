package com.virus.MyPass;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public class Export {
    
    public static String table2json(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            Hashtable<String, String> data = new Hashtable<String, String>();

            for (int r = 0; r < table_model.getRowCount(); r++) {
                String name = table_model.getValueAt(r, 1).toString();
                String password = table_model.getValueAt(r, 2).toString();
                data.put(name, password);
            }
            JSONObject json_data = new JSONObject(data);
            
            return json_data.toString();
        }
        
        // if table is empty; then return empty json array as a string.
        Hashtable<String, String> empty_json = new Hashtable<String, String>();
        return new JSONObject(empty_json).toString(); // {}
    }

    public static void passwords_to_file(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            FileDialog export_data_to = new FileDialog(new JFrame(), "Export data to:", FileDialog.SAVE);
            export_data_to.setAlwaysOnTop(true);
            export_data_to.setVisible(true);

            if (export_data_to.getFile() != null) { // if selected file; then
                String export_data_to_path = new File(export_data_to.getDirectory() + export_data_to.getFile()).toPath().toString();
                String json_data = table2json(table_model);
                
                try {
                    PrintWriter output_file = new PrintWriter(new FileWriter(export_data_to_path));
                    output_file.write(json_data);
                    output_file.close();
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(null, error.toString(), "Error!", JOptionPane.OK_OPTION);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Manager table is empty, add at least one password.", "Error!", JOptionPane.OK_OPTION);
        }
    }
    
    public static void passwords_to_clipboard(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            String json_data = table2json(table_model);
            Copy.string(json_data);
        } else {
            JOptionPane.showMessageDialog(null, "Manager table is empty, add at least one password.", "Error!", JOptionPane.OK_OPTION);
        }
    }
}
