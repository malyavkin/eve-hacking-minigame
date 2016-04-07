package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.NodeState;
import com.company.hackinggame.Virus;
import com.company.hackinggame.handlers.IKillHandler;

public class SystemCoreNode extends DefensiveSubsystemNode {
    public SystemCoreNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);

        actor.setKillHandler(new IKillHandler() {
            @Override
            public void onKill() {
                container.open();
            }
        });
    }

    @Override
    public void trigger() {
        switch (state){
            case explorable:
                state = NodeState.explored;
                container.exposeSurroundings(this);
                break;
            case explored:
                getHit();
                if(getCoherence() > 0){
                    container.hitVirusWith(this);
                }
                break;
        }
    }

    @Override
    public String toString() {
        switch (state){
            case unexplorable:
                return unexplorable;
            case explorable:
                return explorable;
            case explored:
                return String.format("%03d\n>X<\n%03d", actor.getCoherence(), actor.getStrength());
        }
        return "wtf\ntfw\nfwt";
    }



}
