package com.virus.MyPass.util.AES;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author SecVirus
 * 
 * This file contains the AES static variables
 */
public class vars {
    static final String algorithm = "AES";
    
    
    // Settings
    static final int iterationCount = 65536;
    static final int keyLength = 256;
    static final Charset charset = StandardCharsets.UTF_8;
    
    
    // Encryption ----------
    static final int encryption_mode = 1; // Cipher.ENCRYPT_MODE
    static final String encryption_algorithm = "PBKDF2WithHmacSHA256";
    static final String encryption_transformation = "AES/CBC/PKCS5Padding";
    // Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    
    
    // Decryption ----------
    static final int decryption_mode = 2; // Cipher.DECRYPT_MODE
    static final String decryption_algorithm = "PBKDF2WithHmacSHA256";
    static final String decryption_transformation = "AES/CBC/PKCS5PADDING";
    // new String(cipher.doFinal(Base64.getDecoder().decode(encrypted_text)));
    
}
