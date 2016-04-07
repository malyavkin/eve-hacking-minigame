package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;

public class VirusSuppressorNode extends DefensiveSubsystemNode {
    public VirusSuppressorNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);


    }


    @Override
    public String toString() {
        switch (state){
            case unexplorable:
                return unexplorable;
            case explorable:
                return explorable;
            case explored:
                if(isAlive()){
                    return String.format("%03d\n{S}\n%03d", actor.getCoherence(), actor.getStrength());
                } else {
                    return explored;
                }
        }
        return "wtf\ntfw\nfwt";
    }
}
