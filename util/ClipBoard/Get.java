package com.virus.MyPass.util.ClipBoard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

/**
 *
 * @author SecVirus
 */
public class Get {
    public static String asString() {
        try {
            String clipboard_content = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            return clipboard_content;
        } catch (Exception error) {
            return "";
        }
    }
}
