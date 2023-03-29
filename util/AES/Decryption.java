package com.virus.MyPass.util.AES;

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
import com.virus.MyPass.util.AES.Random.Salt;
import com.virus.MyPass.util.AES.Random.IV;
import javax.swing.JOptionPane;

/**
 *
 * @author SecVirus
 */
public class Decryption {
    private static final byte[] salt = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // 18
    private static final byte[] iv = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // 16
    

    public static String decrypt(byte[] encrypted_text, String key) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(vars.decryption_algorithm);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, vars.iterationCount, vars.keyLength);
            SecretKey temp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), vars.algorithm);

            Cipher cipher = Cipher.getInstance(vars.decryption_transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            
            return new String(cipher.doFinal(encrypted_text));
        } catch (Exception error) {return null;}
//        catch (BadPaddingException | IllegalBlockSizeException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException error) {
//            return null;
//        } catch (InvalidKeyException ike) {
//            return null;
//        }
    }
}
