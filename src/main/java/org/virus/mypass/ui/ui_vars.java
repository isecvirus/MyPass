package org.virus.mypass.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import org.virus.mypass.ui.Accent;

/**
 *
 * @author SecVirus
 */
public interface ui_vars {

    // title ------------------------
    final String author = "SecVirus";
    final String tool_name = "MyPass";
    final String tool_version = "4.3.1v";

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
    Color default_accent = new Color(Accent.getRed(), Accent.getGreen(), Accent.getBlue(), Accent.getAlpha()); // alpha=255 (means no alpha is used)

    public static Color color(int alpha) {
        return new Color(ui_vars.default_accent.getRed(), ui_vars.default_accent.getGreen(), ui_vars.default_accent.getBlue(), alpha);
    }

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

    String[] manager_columns = {"", "Entity", "Created", "Modified"};
    List<String> manager_columns_list = Arrays.asList(manager_columns);
    int category_column_index = manager_columns_list.indexOf("");
    int entity_column_index = manager_columns_list.indexOf("Entity");
    int created_column_index = manager_columns_list.indexOf("Created");
    int modified_column_index = manager_columns_list.indexOf("Modified");
    // end ------------------------------------------------------
}
