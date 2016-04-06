package com.company.presets;

import java.util.Random;

public class DemoPreset implements IContainerPreset {
    Random rand;
    private int inRangeBetween(int min, int max){
        return rand.nextInt((max - min) + 1) + min;

    }
    @Override
    public int getVirusCoherence() {
        return 100;
    }
    @Override
    public int getVirusStrength() {
        return 20;
    }

    @Override
    public int getFieldSizeWidth() {
        return 7;
    }

    @Override
    public int getFieldSizeHeight() {
        return 7;
    }

    @Override
    public int getAntivirusNodesNumber() {
        return inRangeBetween(3,6);
    }

    @Override
    public int getFirewallNodesNumber() {
        return inRangeBetween(2,3);
    }

    @Override
    public int getRestorationNodesNumber() {
        return inRangeBetween(0,2);
    }

    @Override
    public int getVirusSuppressorNodesNumber() {
        return inRangeBetween(0,1);
    }

    @Override
    public int getSelfRepairNodesNumber() {
        return inRangeBetween(1,3);
    }

    @Override
    public int getKernelRotNodesNumber() {
        return inRangeBetween(0,2);
    }

    @Override
    public int getPolymorphicShieldNodesNumber() {
        return inRangeBetween(1,3);
    }

    @Override
    public int getSecondaryVectorNodesNumber() {
        return inRangeBetween(0,2);
    }

    @Override
    public int getAmountOfHiddenBeneathDataCache() {
        return 50; // %
    }
}
