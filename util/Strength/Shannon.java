package org.virus.mypass.util.Strength;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SecVirus
 */
public class Shannon {

    // H = -Σ(Pi * log2(Pi))
    public static int passwordStrength(String password) {
        int strength = 0;
        double ent = entropy(password);

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String puncs = "~`!@#$%^&*()_+=-{[]}\\|\"';:/?.>,<";

        for (char c : password.toCharArray()) {
            if (upper.indexOf(c) != -1) {
                strength += 4;
            } else if (lower.indexOf(c) != -1) {
                strength += 3;
            } else if (numbers.indexOf(c) != -1) {
                strength += 5;
            } else if (puncs.indexOf(c) != -1) {
                strength += 6;
            } else {
                strength += 1;
            }
        }

        int length = password.length();
        if (length <= 4) {
            strength += 1;
        } else if (length <= 7) {
            strength += 2;
        } else if (length <= 10) {
            strength += 3;
        } else if (length <= 15) {
            strength += 4;
        } else if (length <= 21) {
            strength += 5;
        } else if (length <= 32) {
            strength += 10;
        } else if (length <= 50) {
            strength += 15;
        } else {
            strength += 50;
        }

        if (ent < 2.0) {
            strength = (int) (strength * 0.5);
        } else if (ent < 3.0) {
            strength = (int) (strength * 0.8);
        } else if (ent < 4.0) {
            strength = (int) (strength * 0.9);
        } else if (ent < 5.0) {
            strength = (int) (strength * 0.95);
        } else if (ent < 6.0) {
            strength = (int) (strength * 0.97);
        } else if (ent < 7.0) {
            strength = (int) (strength * 0.98);
        } else if (ent < 8.0) {
            strength = (int) (strength * 0.99);
        }

        return Math.min(strength, 100);
    }

    public static double entropy(String password) {
        int passwordLength = password.length();
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < passwordLength; i++) {
            char c = password.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        double entropy = 0.0;
        for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
            double frequency = (double) entry.getValue() / passwordLength;
            entropy -= frequency * (Math.log(frequency) / Math.log(2));
        }

        return entropy;
    }
}
