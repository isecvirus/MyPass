package com.virus.MyPass.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author SecVirus
 */
public class Log {
    public static void log(String msg) {
        history_vars.LogHistory.add("[" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + "]: " + msg);
    }
}
