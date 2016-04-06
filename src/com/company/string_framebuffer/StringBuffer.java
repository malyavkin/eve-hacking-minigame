package com.company.string_framebuffer;

import java.util.Arrays;

public class StringBuffer {
    private char[][] chars;
    public StringBuffer(int cols, int lines) {
        chars = new char[lines][cols];
        for (char[] aChar : chars){
            Arrays.fill(aChar, ' ');
        }
    }
    public void pushString(int line, int column, String string){
        int ptrLine = line, ptrCol = column;
        for (int i = 0; i < string.length(); i++) {
            try {
                if(string.charAt(i) == '\n'){
                    ptrLine++;
                    ptrCol = column;
                } else {
                    if(chars[ptrLine].length > ptrCol){
                        chars[ptrLine][ptrCol] = string.charAt(i);
                        ptrCol++;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException x){
                System.out.printf(x.getMessage());
            }

        }
    }
    public void render(){
        for (char[] aChar : chars) {
            System.out.printf(new String(aChar) + '\n');
        }
    }
}
