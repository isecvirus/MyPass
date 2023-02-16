package com.virus.MyPass;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
/**
 *
 * @author SecVirus
 */
public class Read {
    public static String clipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String clipboard_content = (String) clipboard.getData(DataFlavor.stringFlavor);
            return clipboard_content;
        } catch (Exception error) {}
        return "";
    }
}
