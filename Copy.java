package com.virus.MyPass;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author SecVirus
 * 
 * This file will allow MyPass to copy..
 * data parsed as a String to ClipBoard.
 */
public class Copy {

    public static void string(String text) {
        if (text.length() > 0) {
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}
