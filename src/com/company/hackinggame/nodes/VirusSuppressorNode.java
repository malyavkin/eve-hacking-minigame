package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.NodeState;
import com.company.hackinggame.handlers.IKillHandler;

public class VirusSuppressorNode extends ActorNode {
    public VirusSuppressorNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);

        actor.setKillHandler(new IKillHandler() {
            @Override
            public void onKill() {
                container.exposeSurroundings(VirusSuppressorNode.this);
                VirusSuppressorNode.this.state = NodeState.explored;
            }
        });
    }
    @Override
    public void trigger() {
        state = NodeState.explored;
        container.blockSurroundings(this);
    }

    @Override
    public String toString() {
        return String.format("%03d\n{S}\n%03d", actor.getCoherence(), actor.getStrength());
    }
}
