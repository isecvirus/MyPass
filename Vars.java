package com.virus.MyPass;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Formatter;
/**
 * @author SecVirus
 *
 * This file contains project variables;
 */

/*
    ToDo:
        1 => Make the * asterisk in the app title (indicator for unsaved stuff, settings/passwords.. etc).
        2 => Warning before close the app if there is any changes that haven't been saved.
        3 => Support CSV data type as json currently to import, export ..
*/

interface Vars {
    // title ------------------------
    final String author = "SecVirus";
    final String tool_name = "MyPass";
    final String tool_version = "3.2.0v";
    
    Formatter tool_title_formatter = new Formatter();
    final Formatter tool_title = tool_title_formatter.format("%s (%s)", tool_name, tool_version);
    // end --------------------------------------------------------------------------------------
    
    
    // theme ------------------------
    final String dark_theme = "dark";
    final String light_theme = "light";
    
    final String default_theme = dark_theme;
    // end ---------------------------------
    
    
    // charachers --------------------------------------
    String upper_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower_letters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String punctuations = "~`!@#$%^&*()_-+=}]{[\"':;?/>.<,|\\";
    // end ----------------------------------------------------
    
    
    // monitor -------------------------------------------------------
    Dimension screen_XY = Toolkit.getDefaultToolkit().getScreenSize();
    
    int screen_width = screen_XY.width;
    int screen_height = screen_XY.height;
    // end -----------------------------------------------------------
    
    
    // password ----
    int min_len = 1;
    int max_len = 1000;
    int initial_len = 30;
    int steps = 1;
    // end --------------
    
    
    // manager table --------------------------------------------
        // search ----------------------------
        String valid_search_color = "#c0c0c0";
        String invalid_search_color = "#ff1a1a";
        // end ---------------------------------
        
    String[] manager_columns = {"#", "id", "password", "length"};
    
    int no_index = 0; // no=# or number
    int id_index = 1;
    int password_index = 2;
    int password_length_index = 3;
    
    String unknwon_length = "-";
    // end ------------------------------------------------------
    
    // rsa --------------
    int $512_bytes = 512;
    int $1024_bytes = 1024;
    int $2048_bytes = 2048;
    int $3072_bytes = 3072;
    int $4096_bytes = 4096;
    
    int default_key_len = $4096_bytes;
    
    String default_public_name = "public.rsa";
    String default_private_name = "private.rsa";
    // end -------------------------------------
    
    
    // url request -------------------------
    int default_timeout = 30;
    
    String default_UserAgent = "BlahBlah/0.0 (compatible; AAAA 0.0; BBBB CCC 0.0; .DDDD EEE 0.0.00000)"; // hackers must be anonymous (:
    // -------------------------------------------------------------------------------------------------
    
    // icons -----------------------------
    int icons_size = 14;
    
    // #fe9801
    Color icons_color = new Color(254, 152, 1, 255); // alpha=255 (means no alpha is used)
}