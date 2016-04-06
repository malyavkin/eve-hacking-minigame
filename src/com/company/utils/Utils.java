package com.company.utils;

import com.company.graph.HexNode;

public class Utils {
    public  static String getID(HexNode o){
        String str = o.toString();
        int atmark = str.indexOf("@");
        return str.substring(atmark+1, atmark+4);

    }
}
