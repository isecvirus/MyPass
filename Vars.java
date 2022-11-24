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

    final String author = "SecVirus";
    final String tool_name = "MyPass";
    final String tool_version = "3.0.0v";
    Formatter tool_title_formatter = new Formatter();
    final Formatter tool_title = tool_title_formatter.format("%s (%s)", tool_name, tool_version);

    final String dark_theme = "dark";
    final String light_theme = "light";
    final String default_theme = dark_theme;

    String upper_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower_letters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String punctuations = "~`!@#$%^&*()_-+=}]{[\"':;?/>.<,|\\";
    
    
    Dimension screen_XY = Toolkit.getDefaultToolkit().getScreenSize();
    int screen_width = screen_XY.width;
    int screen_height = screen_XY.height;
    
    int min_len = 1;
    int max_len = 1000;
    int initial_len = 30;
    int steps = 1;

    String[] manager_columns = {"#", "id", "password"};
    
    int $512_bytes = 512;
    int $1024_bytes = 1024;
    int $2048_bytes = 2048;
    int $3072_bytes = 3072;
    int $4096_bytes = 4096;
    
    int default_key_len = $4096_bytes;
    String default_public_name = "public.rsa";
    String default_private_name = "private.rsa";
}