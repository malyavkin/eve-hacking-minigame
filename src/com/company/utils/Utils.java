package com.company.utils;

import com.company.graph.HexNode;

import java.util.Random;

public class Utils {
    public  static String getID(HexNode o){
        String str = o.toString();
        int atmark = str.indexOf("@");
        return str.substring(atmark+1, atmark+4);

    }
    private static Random rand = new Random();
    public static int inRangeBetween(int min, int max){
        return rand.nextInt((max - min) + 1) + min;

    }
}
