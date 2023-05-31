package org.virus.mypass.util.Converter;

import org.virus.mypass.util.AES.Encryption;
import java.awt.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.virus.mypass.ui.Accent;
import org.virus.mypass.ui.Passwords;
import org.virus.mypass.util.AES.Decryption;
import org.virus.mypass.util.Timer.Timer;

/**
 *
 * @author SecVirus
 */
public class Converter {

    /*
        <3.2.0v
        [UP TO]
        4.2.0v
     */
    public static void $3_2_0(Component comp) {
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

                    JSONObject inner_json = new JSONObject()
                            .put("username", "")
                            .put("url", "")
                            .put("password", password)
                            .put("note", "")
                            .put("created", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()))
                            .put("modified", "");

                    new_data.put(entity, inner_json);
                });

                JFileChooser fc_to = new JFileChooser(Paths.get("").toAbsolutePath().toString());
                int returnVal_to = fc_to.showDialog(comp, "Apply");

                if (returnVal_to == JFileChooser.APPROVE_OPTION) {
                    String password = Passwords.ask(comp, null);

                    if (password != null) {
                        if (password.length() > 0) {
                            try {
                                byte[] encrypted = Encryption.encrypt(new_data.toString(), password);

                                FileOutputStream file = new FileOutputStream(fc_to.getSelectedFile().toString());
                                file.write(encrypted);
                            } catch (Exception error) {
                                JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } catch (JSONException json_malformed) {
                JOptionPane.showMessageDialog(comp, "Excepected reasons:\n\t• Data looks malformed.\n• Not a valid JSON data.\n• Not a MyPass v3.2.0 passwords file.", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
        <4.2.0v
        [TO]
        4.3.1v
     */
    public static void $4_3_1(Component comp) {
        JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
        int returnVal = fc.showDialog(comp, "Choose");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Path filepath = fc.getSelectedFile().toPath();
            try {
                JSONObject all_entities_data, loaded_json, final_json;

                final_json = new JSONObject();

                all_entities_data = new JSONObject();
                String from_password = Passwords.ask(comp, null);

                if (from_password != null && !from_password.isEmpty()) {

                    String loaded_string = Decryption.decrypt(Files.readAllBytes(filepath), from_password);

                    loaded_json = new JSONObject(loaded_string);

                    loaded_json.keySet().forEach(entity -> {
                        JSONObject object = loaded_json.getJSONObject(entity);

                        String username = object.getString("username");
                        String url = object.getString("url");
                        String password = object.getString("password");
                        String note = object.getString("note");
                        String created = object.getString("created");
                        String modified = object.getString("modified");

                        JSONObject category = new JSONObject()
                                .put("icon", "")
                                .put("color", Accent.AlphaSetGetHex(255));
//
                        JSONObject entity_json = new JSONObject()
                                .put("username", username)
                                .put("url", url)
                                .put("password", password)
                                .put("note", note)
                                .put("created", created)
                                .put("modified", modified)
                                .put("category", category)
                                .put("passwordHistory", new JSONArray());
//
                        all_entities_data.put(entity, entity_json);
                    });
                    final_json.put("entities", all_entities_data);

                    JSONObject settings = new JSONObject()
                            .put("autoClearClipboard", false)
                            .put("topmost", false)
                            .put("resizeable", true)
                            .put("orientation", "ltr")
                            .put("dragable-toolbar", false)
                            .put("theme", "dark")
                            .put("animate", true)
                            .put("session_timeout", new Timer().getDefaultSessionTime())
                            .put("timerAutoStart", false)
                            .put("AutoSaveOnTimeout", true);

                    final_json.put("settings", settings);

                    JFileChooser fc_to = new JFileChooser(Paths.get("").toAbsolutePath().toString());
                    int returnVal_to = fc_to.showDialog(comp, "Apply");

                    if (returnVal_to == JFileChooser.APPROVE_OPTION) {
                        String password = Passwords.ask(comp, null);

                        if (password != null && !password.isEmpty()) {
                            try {
                                byte[] encrypted = Encryption.encrypt(final_json.toString(), password);

                                FileOutputStream file = new FileOutputStream(fc_to.getSelectedFile().toString());
                                file.write(encrypted);
                            } catch (Exception error) {
                                JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } catch (JSONException json_malformed) {
                JOptionPane.showMessageDialog(comp, "Excepected reasons:\n\t• Data looks malformed.\n• Not a valid JSON data.\n• Not a MyPass v4.2.0 passwords file.", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException incorrect_key) {
                JOptionPane.showMessageDialog(comp, "Incorrect password!", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(comp, error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
