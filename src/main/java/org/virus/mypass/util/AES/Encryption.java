package org.virus.mypass.util.AES;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SecVirus
 */
public class Encryption {
    private static final byte[] salt = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // 18
    private static final byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // 16

    public static byte[] encrypt(String msg, String key) throws InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(vars.encryption_algorithm);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, vars.iterationCount, vars.keyLength);
            SecretKey temp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), vars.algorithm);

            Cipher cipher = Cipher.getInstance(vars.encryption_transformation);
            cipher.init(vars.encryption_mode, secretKey, ivspec);

            return cipher.doFinal(msg.getBytes(vars.charset));
        } catch (InvalidKeyException | BadPaddingException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException error) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, error);
            return null;
        }
    }
}
