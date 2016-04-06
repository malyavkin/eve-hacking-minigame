package com.company;

import com.company.hackinggame.DataContainer;
import com.company.presets.DemoPreset;
import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DemoPreset cp = new DemoPreset();
        DataContainer dataContainer = new DataContainer(cp);
        dataContainer.draw();
		dataContainer.virus.setCoherence(1);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try
        {
            while ((s = in.readLine()) != null && s.length() != 0){
                String[] strs = s.trim().split("\\s+");
                int[] a = new int[2];
                for (int i = 0; i < 1; i++) {
                    a[i] = Integer.parseInt(strs[i]);
                }
                dataContainer.map.get(a[0],a[1]).content.trigger();
                dataContainer.draw();
            }
                
        }
        catch (IOException e)
        {}
        // An empty line or Ctrl-Z terminates the program
    }
}
