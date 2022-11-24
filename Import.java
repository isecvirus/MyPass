package com.virus.MyPass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;

/**
 * @author SecVirus
 * 
 * This file will import passwords from a *.json file:
 * {[NAME]: [PASSWORD], [NAME]: [PASSWORD], etc..}
 */

public class Import {

    public static void passwords(Path filename, DefaultTableModel table_model) {
        try {
            DefaultTableModel mtm = table_model;
            String content = Files.readString(filename);
            JSONObject parser = new JSONObject(content);
            Iterator<String> unordered_keys = parser.keys();
            List<String> keys = new ArrayList<String>();

            while (unordered_keys.hasNext()) {
                keys.add(unordered_keys.next());
            }
            Collections.sort(keys);

            for (int r = 0; r < mtm.getRowCount(); r++) {
                mtm.removeRow(r); // to clear the table
            }
            mtm.setRowCount(0);

            for (int k = 0; k < keys.size(); k++) {
                int index = mtm.getRowCount() + 1;
                String id = keys.get(k);
                String password = parser.getString(id);
                Object[] a = {index, id, password};
                mtm.addRow(a);
            }
        } catch (IOException | JSONException error) {
        }
    }
}
