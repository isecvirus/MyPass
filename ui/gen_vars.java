package com.virus.MyPass.ui;

/**
 *
 * @author SecVirus
 */
public interface gen_vars {
    // charachers --------------------------------------
    String upper_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower_letters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String punctuations = "~`!@#$%^&*()_-+=}]{[\"':;?/>.<,|\\";
    // end ----------------------------------------------------
    
    // password ----
    int min_len = 1;
    int max_len = 1000;
    int initial_len = 16;
    int steps = 1;
    // end --------------
}
