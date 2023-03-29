package com.virus.MyPass.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Formatter;

/**
 *
 * @author SecVirus
 */
public interface ui_vars {
    // title ------------------------
    final String author = "SecVirus";
    final String tool_name = "MyPass";
    final String tool_version = "4.2.0v";
    
    Formatter tool_title_formatter = new Formatter();
    final Formatter tool_title = tool_title_formatter.format("%s (%s)", tool_name, tool_version);
    // end --------------------------------------------------------------------------------------
    
    
    // theme ------------------------
    final String dark_theme = "dark";
    final String light_theme = "light";
    
    final String default_theme = dark_theme;
    // end ---------------------------------
    
    int icons_size = 14;
    
    // #fe9801
    Color color = new Color(77, 122, 254, 255); // alpha=255 (means no alpha is used)
    public static Color color(int alpha) {return new Color(ui_vars.color.getRed(), ui_vars.color.getGreen(), ui_vars.color.getBlue(), alpha);}
    
    // monitor -------------------------------------------------------
    Dimension screen_XY = Toolkit.getDefaultToolkit().getScreenSize();
    
    int screen_width = screen_XY.width;
    int screen_height = screen_XY.height;
    // end -----------------------------------------------------------   
    
    // manager table --------------------------------------------
    // search start ------------------------
    String valid_search_color = "#c0c0c0";
    String invalid_search_color = "#ff1a1a";
    // end ---------------------------------
        
    String[] manager_columns = {"Entity", "Created", "Modified"};
    // end ------------------------------------------------------
}
