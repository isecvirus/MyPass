package com.virus.MyPass;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;

/**
 * @author SecVirus
 *
 * This file will import passwords from a *.json file: {[NAME]: [PASSWORD], ...}
 */
public class Import {

    private static boolean confirm_import(DefaultTableModel table_model) { // in case Manager table had some passwords.
        if (table_model.getRowCount() > 0) {
            int answer = JOptionPane.showConfirmDialog(null, "Looks like you have some passwords in use,"
                    + "make sure to encrypt it; then save it, before importing other passwords "
                    + "(previous passwords will be overwritten in the Manager table).."
                    + "\nContinue?", "Warning!", JOptionPane.YES_NO_OPTION);

            // if answer=NO; then exit this function execution.
            // X=-1
            // No=1
            // Yes=0

            if (answer == 1 | answer == -1) {
                return false;
            }
            return true;
            // else the execution will continue.

        }
        return true;
    }

    private static void insert_all(DefaultTableModel table_model, JSONObject parser) {
        for (String key:parser.keySet()) {
            int index = table_model.getRowCount() + 1;
            String password = parser.getString(key);
            Object[] a = {index, key, password, Vars.unknwon_length};
            table_model.addRow(a);
        }
    }

    private static void clear_table(DefaultTableModel table_model) {
        for (int r = 0; r < table_model.getRowCount(); r++) {
            table_model.removeRow(r); // to clear the table
        }
        table_model.setRowCount(0); // ;then -----^
    }

    public static void passwords_from_file(DefaultTableModel table_model) {
        if (confirm_import(table_model)) {
            FileDialog import_passwords_from = new FileDialog(new JFrame(), "Import passwords from:", FileDialog.LOAD);
            import_passwords_from.setAlwaysOnTop(true);
            import_passwords_from.setVisible(true);
            File passwords_file = new File(import_passwords_from.getDirectory() + import_passwords_from.getFile());

            if (passwords_file.exists()) {
                Path passwords_file_path = passwords_file.toPath();
                try {
                    String content = Files.readString(passwords_file_path);
                    JSONObject parser = new JSONObject(content);
 
                    clear_table(table_model);

                    insert_all(table_model, parser);
                } catch (IOException | JSONException error) {
                }
            }
        }
    }

    public static void passwords_from_clipboard(DefaultTableModel table_model) {
        if (confirm_import(table_model)) {
            String clipboard_content = Read.clipboard(); // as::String

            try {
                JSONObject parser = new JSONObject(clipboard_content);

                clear_table(table_model);

                insert_all(table_model, parser);
            } catch (Exception error) {
                Prompt.Error("The clipboard contains no valid json data");
            }
        }
    }

    public static void passwords_from_url(DefaultTableModel table_model) {
        if (confirm_import(table_model)) {
            /*
                if i replaced the last two values of the below input object ..
                .. the input will be a drop box.
                Object[] possibleValues = {}, String default_value = ""
             */
            Object url = JOptionPane.showInputDialog(null, "Url (must be direct url):", "Import passwords from url", JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (!(url == null)) {
                try {
//                    URL url_obj = new URL(url.toString());
//                    HttpURLConnection

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder(URI.create(url.toString()))
                            .setHeader("User-Agent", Vars.default_UserAgent)
                            .timeout(Duration.ofSeconds(Vars.default_timeout))
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    String data = response.body();
                    System.out.println(response.statusCode());
                    System.out.println(data);
                    
                    JSONObject parser = new JSONObject(data);
                    System.out.println(response.statusCode());
                    System.out.println(parser.toString());

                    clear_table(table_model);

//                    insert_all(table_model, parser);
                } catch (Exception error) {
                    Prompt.Error(error.getMessage());
                }
            }
        }
    }
}
