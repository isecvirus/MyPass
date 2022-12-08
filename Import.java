package com.virus.MyPass;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;

/**
 * @author SecVirus
 *
 * This file will import passwords from a *.json file: {[NAME]: [PASSWORD],
 * [NAME]: [PASSWORD], etc..}
 */
public class Import {

    public static void passwords(DefaultTableModel table_model) {
        FileDialog import_passwords_from = new FileDialog(new JFrame(), "Import passwords from:", FileDialog.LOAD);
        import_passwords_from.setAlwaysOnTop(true);
        import_passwords_from.setVisible(true);
        File import_passwords_from_file = new File(import_passwords_from.getDirectory() + import_passwords_from.getFile());

        if (import_passwords_from_file.exists()) {
            Path passwords_file_path = import_passwords_from_file.toPath();
            try {
                String content = Files.readString(passwords_file_path);
                JSONObject parser = new JSONObject(content);
                Iterator<String> unordered_keys = parser.keys();
                List<String> keys = new ArrayList<String>();

                while (unordered_keys.hasNext()) {
                    keys.add(unordered_keys.next());
                }
                Collections.sort(keys);

                for (int r = 0; r < table_model.getRowCount(); r++) {
                    table_model.removeRow(r); // to clear the table
                }
                table_model.setRowCount(0);

                for (int k = 0; k < keys.size(); k++) {
                    int index = table_model.getRowCount() + 1;
                    String id = keys.get(k);
                    String password = parser.getString(id);
                    Object[] a = {index, id, password, Vars.unknwon_length};
                    table_model.addRow(a);
                }
            } catch (IOException | JSONException error) {
            }
        }
    }
}
