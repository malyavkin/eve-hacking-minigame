package com.company;

import com.company.hackinggame.DataContainer;
import com.company.presets.DemoPreset;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DemoPreset cp = new DemoPreset();
        DataContainer dataContainer = new DataContainer(cp);
        dataContainer.draw();
		dataContainer.virus.setCoherence(1);
    }
}
