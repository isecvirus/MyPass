package org.virus.mypass.util.Misc;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author SecVirus
 */
public class red2green {
    public static List Red2Green() {
        int r = 255;
        int g = 0;
        int size = 100;
        String[] red2green = new String[size];
        
        for (int i=0;i<size;i++) {
            int red = (int) (r - (r * (i / (float)size)));
            int green = (int) (g + (255 * (i / (float)size)));
            String hex = String.format("#%02x%02x%02x", red, green, 0);
            red2green[i] = hex;
        }
        
        return Arrays.asList(red2green);
    }
}
