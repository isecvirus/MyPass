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
 */
public class Decryption {

    public static void decrypt(DefaultTableModel table_model) {
        if (table_model.getRowCount() > 0) {
            FileDialog key_file = new FileDialog(new JFrame(), "Import (RSA private) key:", FileDialog.LOAD);
            key_file.setAlwaysOnTop(true);
            key_file.setVisible(true);

            if (key_file.getFile() != null) {
                File import_privatekey_from_file = new File(key_file.getDirectory() + key_file.getFile());
                if (import_privatekey_from_file.exists()) {
                    Path privatekey_file_path = import_privatekey_from_file.toPath();
                    try {
                        Cipher decryptCipher = RSA.read_PrivateFile(privatekey_file_path);

                        if (decryptCipher != null) {
                            for (int row = 0; row < table_model.getRowCount(); row++) {
                                String b64password = table_model.getValueAt(row, 2).toString();
                                byte[] passwordbytes = Base64.getDecoder().decode(b64password.getBytes());

                                byte[] decryptSecretBytes = decryptCipher.doFinal(passwordbytes);
                                String password = new String(decryptSecretBytes, StandardCharsets.UTF_8);
                                // decryptedSecretString = password (as plaintext)
                                int password_length = password.length();
                                table_model.setValueAt(password, row, Vars.password_index);
                                table_model.setValueAt(password_length, row, Vars.password_length_index);
                            }
                        }
                    } catch (Exception error) { // BadPaddingException | IllegalBlockSizeException | IllegalArgumentException
                    }
                }
            }
        }
    }
}
