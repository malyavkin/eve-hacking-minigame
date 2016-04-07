package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;

public class AntivirusNode extends DefensiveSubsystemNode {
    public AntivirusNode (int strength, int coherence, final DataContainer container) {
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
                    return String.format("%03d\n[A]\n%03d", actor.getCoherence(), actor.getStrength());
                } else {
                    return explored;
                }
        }
        return "wtf\ntfw\nfwt";
    }
}
