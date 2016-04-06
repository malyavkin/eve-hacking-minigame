package com.company;

import com.company.hackinggame.DataContainer;
import com.company.presets.DemoPreset;
import com.company.utils.Utils;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DemoPreset cp = new DemoPreset();
        DataContainer dataContainer = new DataContainer(cp);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try
        {
            while (true){
                dataContainer.draw();
                s = in.readLine();
                if(s == null || s.length() == 0){
                    break;
                }
                String[] arguments = s.trim().split("\\s+");
                if(arguments.length < 2){
                    System.out.println("too few arguments, try again");
                    continue;
                }
                int[] a = new int[2];

                for (int i = 0; i < 2; i++) {
                    a[i] = Integer.parseInt(arguments[i]);
                }
                try{
                    dataContainer.move(a[0],a[1]);
                } catch (Exception x){
                    System.out.println(x.getMessage()+", try again");

                }
            }
                
        }
        catch (IOException e)
        {
            System.out.println("ex" + e.toString());
        }
    }
}
