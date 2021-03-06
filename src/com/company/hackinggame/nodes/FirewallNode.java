package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;

public class FirewallNode extends DefensiveSubsystemNode {
    public FirewallNode(int strength, int coherence, final DataContainer container) {
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
                    return String.format("%03d\n/_\\\n%03d", actor.getCoherence(), actor.getStrength());
                } else {
                    return explored;
                }
        }
        return "wtf\ntfw\nfwt";

    }

}
