package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;

public class RestorationNode extends DefensiveSubsystemNode {
    public RestorationNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);


    }


    @Override
    public String toString() {
        return String.format("%03d\n}|{\n%03d", actor.getCoherence(), actor.getStrength());
    }
}
