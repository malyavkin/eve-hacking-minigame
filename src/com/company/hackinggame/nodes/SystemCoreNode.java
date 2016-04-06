package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.handlers.IKillHandler;

public class SystemCoreNode extends ActorNode {
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
