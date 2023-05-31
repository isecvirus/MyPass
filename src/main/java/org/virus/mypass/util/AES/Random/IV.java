package org.virus.mypass.util.AES.Random;

import java.util.Random;

/**
 *
 * @author SecVirus
 */
public class IV {
    private static final int iv_len = 16;
        
    public static byte[] getRandom() {
        byte[] iv = new byte[iv_len];
        
        for (int j=0;j<iv_len;j++) {
            Random rb = new Random();
            rb.nextBytes(iv);
        }
        
        return iv;
    }
}
