package com.virus.MyPass.ui;

import com.virus.MyPass.util.AES.Encryption;
import java.awt.Component;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */
public class Converter {

    public static void run(Component comp) {
        JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
        int returnVal = fc.showDialog(comp, "Choose");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().toString();
            try {
                JSONObject new_data, loaded_json;

                new_data = new JSONObject();

                String loaded_string = Files.readString(Paths.get(filename));

                loaded_json = new JSONObject(loaded_string.toString());

                loaded_json.keySet().forEach(entity -> {
                    String password = loaded_json.getString(entity);

                    JSONObject inner_json = new JSONObject();
                    inner_json.put("username", "");
                    inner_json.put("url", "");
                    inner_json.put("password", password);
                    inner_json.put("note", "");
                    inner_json.put("created", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
                    inner_json.put("modified", "");

                    new_data.put(entity, inner_json);
                });

                JFileChooser fc_to = new JFileChooser(Paths.get("").toAbsolutePath().toString());
                int returnVal_to = fc_to.showDialog(comp, "Apply");

                if (returnVal_to == JFileChooser.APPROVE_OPTION) {
                    JSONObject password_dialog = MPasswordDialog.createDialog(comp, null);

                    String key = password_dialog.getString("password");
                    int answer = password_dialog.getInt("answer");

                    if (answer == 0 & key.length() > 0) {
                        try {
                            byte[] encrypted = Encryption.encrypt(new_data.toString(), key);

                            FileOutputStream file = new FileOutputStream(fc_to.getSelectedFile().toString());
                            file.write(encrypted);
                        } catch (Exception error) {
                            JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (JSONException json_malformed) {
                JOptionPane.showMessageDialog(comp, "Data looks malformed or this is not a valid JSON data", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
