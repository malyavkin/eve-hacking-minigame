package com.company.presets;

public interface IContainerPreset {
    int getVirusCoherence();
    int getVirusStrength();
    int getFieldSizeWidth();
    int getFieldSizeHeight();
    int getAntivirusNodesNumber();
    int getFirewallNodesNumber();
    int getRestorationNodesNumber();
    int getVirusSuppressorNodesNumber();
    int getAmountOfHiddenBeneathDataCache(); // DAE like long names????

    int getSelfRepairNodesNumber();
    int getKernelRotNodesNumber();
    int getPolymorphicShieldNodesNumber();
    int getSecondaryVectorNodesNumber();

}

