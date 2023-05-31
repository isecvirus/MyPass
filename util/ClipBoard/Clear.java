package org.virus.mypass.util.ClipBoard;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author SecVirus
 */
public class Clear {
    public static void content() {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(""), null);
    }
}
