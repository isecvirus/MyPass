package org.virus.mypass.ui;

import java.awt.Color;

/**
 *
 * @author SecVirus
 */
public class Accent {

    // default accent: 77, 122, 254, 255
    private static int red = 77; // Default red value
    private static int green = 122; // Default green value
    private static int blue = 254; // Default blue value
    private static int alpha = 255; // Default alpha value

    public static int getRed() {
        return red;
    }

    public static void setRed(int redValue) {
        red = redValue;
    }

    public static int getGreen() {
        return green;
    }

    public static void setGreen(int greenValue) {
        green = greenValue;
    }

    public static int getBlue() {
        return blue;
    }

    public static void setBlue(int blueValue) {
        blue = blueValue;
    }

    public static int getAlpha() {
        return alpha;
    }

    public static void setAlpha(int alphaValue) {
        alpha = alphaValue;
    }

    public static Color getColor() {
        return new Color(red, green, blue, alpha);
    }

    public static void setColor(int redValue, int greenValue, int blueValue, int alphaValue) {
        red = redValue;
        green = greenValue;
        blue = blueValue;
        alpha = alphaValue;
    }

    public static Color RedSetGet(int new_red) {
        return new Color(new_red, green, blue, alpha);
    }

    public static Color GreenSetGet(int new_green) {
        return new Color(red, green, blue, new_green);
    }

    public static Color BlueSetGet(int new_blue) {
        return new Color(red, green, new_blue, alpha);
    }

    public static Color AlphaSetGet(int new_alpha) {
        return new Color(red, green, blue, new_alpha);
    }

    public static String RedSetGetHex(int new_red) {
        return String.format("#%02x%02x%02x", new_red, green, blue, alpha);
    }

    public static String GreenSetGetHex(int new_green) {
        return String.format("#%02x%02x%02x", red, new_green, blue, alpha);
    }

    public static String BlueSetGetHex(int new_blue) {
        return String.format("#%02x%02x%02x", red, green, new_blue, alpha);
    }

    public static String AlphaSetGetHex(int new_alpha) {
        return String.format("#%02x%02x%02x", red, green, blue, new_alpha);
    }

    public static String ColorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
