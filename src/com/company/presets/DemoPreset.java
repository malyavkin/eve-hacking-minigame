package com.company.presets;

import com.company.utils.Utils;

public class DemoPreset implements IContainerPreset {

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
        return Utils.inRangeBetween(3,6);
    }

    @Override
    public int getFirewallNodesNumber() {
        return Utils.inRangeBetween(2,3);
    }

    @Override
    public int getRestorationNodesNumber() {
        return Utils.inRangeBetween(0,2);
    }

    @Override
    public int getVirusSuppressorNodesNumber() {
        return Utils.inRangeBetween(0,1);
    }

    @Override
    public int getSelfRepairNodesNumber() {
        return Utils.inRangeBetween(1,3);
    }

    @Override
    public int getKernelRotNodesNumber() {
        return Utils.inRangeBetween(0,2);
    }

    @Override
    public int getPolymorphicShieldNodesNumber() {
        return Utils.inRangeBetween(1,3);
    }

    @Override
    public int getSecondaryVectorNodesNumber() {
        return Utils.inRangeBetween(0,2);
    }

    @Override
    public int getAmountOfHiddenBeneathDataCache() {
        return 50; // %
    }
}
