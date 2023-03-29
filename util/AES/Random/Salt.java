package com.virus.MyPass.util.AES.Random;

import java.util.Random;

/**
 *
 * @author SecVirus
 */

public class Salt {
    private static final int salt_len = 18;
    
    public static byte[] getRandom() {
        byte[] salt = new byte[salt_len];
        
        for (int i=0;i<salt_len;i++) {
            Random rb = new Random();
            rb.nextBytes(salt);
        }
        
        return salt;
    }
}
