package com.virus.MyPass;

import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;

/*
 * @author SecVirus
 *
 * This file controls and help interacting..
 * with RSA encryption.
 */
public class RSA {

    public static void generate() /* throws HeadlessException, IOException,  SecurityException,  NoSuchAlgorithmException */ {
        JFrame window = new JFrame();
        FileDialog save_public_to = new FileDialog(window, "Save RSA public key to:", FileDialog.SAVE);

        save_public_to.setFile(Vars.default_public_name);
        save_public_to.setAlwaysOnTop(true);
        save_public_to.setVisible(true);

        if (save_public_to.getFile() != null) {

            FileDialog save_private_to = new FileDialog(window, "Save RSA private key to:", FileDialog.SAVE);

            save_private_to.setFile(Vars.default_private_name);
            save_private_to.setAlwaysOnTop(true);
            save_private_to.setVisible(true);

            String public_file = save_public_to.getDirectory() + save_public_to.getFile();
            String private_file = save_private_to.getDirectory() + save_private_to.getFile();

            if (save_private_to.getFile() != null) {
                try {
                    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
                    generator.initialize(Vars.default_key_len);
                    KeyPair key = generator.generateKeyPair();

                    PrivateKey private_key = key.getPrivate();
                    PublicKey public_key = key.getPublic();

                    write_PublicFile(public_file, public_key);
                    write_PrivateFile(private_file, private_key);

                } catch (HeadlessException | SecurityException | NoSuchAlgorithmException error) {
                }
            }
        }
    }

    public static void write_PublicFile(String public_filename, PublicKey public_key) {
        try ( FileWriter public_key_file_object = new FileWriter(public_filename)) {
            byte[] public_bytes = public_key.getEncoded();
            String public_base64_string = Base64.getEncoder().encodeToString(public_bytes);
            public_key_file_object.write(public_base64_string);
        } catch (IOException error) {
        }
    }

    public static void write_PrivateFile(String private_filename, PrivateKey private_key) {
        try ( FileWriter private_key_file_object = new FileWriter(private_filename)) {
            byte[] private_bytes = private_key.getEncoded();
            String private_base64_string = Base64.getEncoder().encodeToString(private_bytes);
            private_key_file_object.write(private_base64_string);
        } catch (Exception error) {
        }
    }

    public static Cipher read_PublicFile(Path filename) {
        try {
            byte[] publicKeyBytes = Files.readAllBytes(filename);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBytes));
            Key publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encryptCipher;

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException error) {
        }
        return null; // if faild will return null
    }

    public static Cipher read_PrivateFile(Path filename) {
        try {
            String KeyBase64String = Files.readString(filename);
            byte[] KeyBytes = Base64.getDecoder().decode(KeyBase64String);

            PKCS8EncodedKeySpec KeySpec = new PKCS8EncodedKeySpec(KeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(KeySpec);

            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return decryptCipher;
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException error) {
        }
        return null; // if faild will return null
    }

}
