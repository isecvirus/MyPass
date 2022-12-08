package com.virus.MyPass;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public class Export {

    public static void passwords(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            FileDialog export_data_to = new FileDialog(new JFrame(), "Export data to:", FileDialog.SAVE);
            export_data_to.setAlwaysOnTop(true);
            export_data_to.setVisible(true);

            if (export_data_to.getFile() != null) {
                Hashtable<String, String> data = new Hashtable<String, String>();

                for (int r = 0; r < table_model.getRowCount(); r++) {
                    String name = table_model.getValueAt(r, 1).toString();
                    String password = table_model.getValueAt(r, 2).toString();
                    data.put(name, password);
                }
                String export_data_to_path = new File(export_data_to.getDirectory() + export_data_to.getFile()).toPath().toString();
                JSONObject json_data = new JSONObject(data);
                try {
                    PrintWriter output_file = new PrintWriter(new FileWriter(export_data_to_path));
                    output_file.write(json_data.toString());
                    output_file.close();
                } catch (IOException error) {
                }
            }
        }
    }
}
