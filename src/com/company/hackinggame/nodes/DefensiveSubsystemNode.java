package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.NodeState;
import com.company.hackinggame.Virus;
import com.company.hackinggame.handlers.IKillHandler;

public class DefensiveSubsystemNode extends ActorNode {
    public int getCoherence(){
        return actor.getCoherence();
    }
    public int getStrength(){
        return actor.getStrength();
    }
    public DefensiveSubsystemNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);
        final DefensiveSubsystemNode ds =this;
        actor.setKillHandler(new IKillHandler() {
            @Override
            public void onKill() {
                container.unblockSurroundings(ds);
                container.exposeSurroundings(ds);
            }
        });
    }
    public void hit(Virus virus){
        virus.setCoherence(virus.getCoherence()-this.actor.getStrength());
    }
    @Override
    public void trigger(Virus virus) {
        switch (state){
            case explorable:
                state = NodeState.explored;
                container.blockSurroundings(this);
                break;
            case explored:
                getHit(virus);
                if(getCoherence() > 0){
                    hit(virus);
                }
                break;
        }
    }
}
