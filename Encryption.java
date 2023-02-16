package com.virus.MyPass;

import java.awt.FileDialog;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SecVirus
 *
 */
public class Encryption {

    public static void encrypt_from_file(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            FileDialog import_publickey_from = new FileDialog(new JFrame(), "Import (RSA public) key for encryption:", FileDialog.LOAD);
            import_publickey_from.setAlwaysOnTop(true);
            import_publickey_from.setVisible(true);

            if (import_publickey_from.getFile() != null) {
                File import_publickey_from_file = new File(import_publickey_from.getDirectory() + import_publickey_from.getFile());
                if (import_publickey_from_file.exists()) {
                    Path publickey_file_path = import_publickey_from_file.toPath();

                    Cipher encryptCipher = RSA.read_PublicFile(publickey_file_path);
                    for (int row = 0; row < table_model.getRowCount(); row++) {
                        try {
                            String password = table_model.getValueAt(row, 2).toString();
                            byte[] secretStringBytes = password.getBytes(StandardCharsets.UTF_8);
                            byte[] encryptSecretString = encryptCipher.doFinal(secretStringBytes);
                            String base64Password = Base64.getEncoder().encodeToString(encryptSecretString);

                            table_model.setValueAt(base64Password, row, Vars.password_index);
                            table_model.setValueAt(Vars.unknwon_length, row, Vars.password_length_index);
                        } catch (Exception error) { // BadPaddingException | IllegalBlockSizeException | IllegalArgumentException
                            continue;
                        }
                    }
                }
            }
        } else {
            Prompt.Error("There is no passwords to encrypt");
        }
    }

    public static void encrypt_from_clipboard(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            Cipher encryptCipher = RSA.read_PublicClipboard();

            if (encryptCipher != null) {
                for (int row = 0; row < table_model.getRowCount(); row++) {
                    try {
                        String password = table_model.getValueAt(row, 2).toString();
                        byte[] secretStringBytes = password.getBytes(StandardCharsets.UTF_8);
                        byte[] encryptSecretString = encryptCipher.doFinal(secretStringBytes);
                        String base64Password = Base64.getEncoder().encodeToString(encryptSecretString);

                        table_model.setValueAt(base64Password, row, Vars.password_index);
                        table_model.setValueAt(Vars.unknwon_length, row, Vars.password_length_index);
                    } catch (Exception error) { // BadPaddingException | IllegalBlockSizeException | IllegalArgumentException
                        continue;
                    }
                }
            } else {
                Prompt.Error("The data within the clipboard is not an encryption key");
            }
        } else {
            Prompt.Error("There is no passwords to encrypt");
        }
    }
}
