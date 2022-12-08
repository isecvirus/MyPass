package com.virus.MyPass;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Formatter;
/**
 * @author SecVirus
 *
 * This file contains project variables;
 */
interface Vars {
    // title ------------------------
    final String author = "SecVirus";
    final String tool_name = "MyPass";
    final String tool_version = "3.1.0v";
    
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
}