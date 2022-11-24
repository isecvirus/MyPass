package com.virus.MyPass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import org.json.JSONObject;

/**
 *
 * @author SecVirus
 */

public class Export {

    public static void passwords(String filename, Hashtable data_dict) {
        JSONObject json_data = new JSONObject(data_dict);
        try {
            PrintWriter output_file = new PrintWriter(new FileWriter(filename));
            output_file.write(json_data.toString());
            output_file.close();
        } catch (IOException error) {}
    }
}
