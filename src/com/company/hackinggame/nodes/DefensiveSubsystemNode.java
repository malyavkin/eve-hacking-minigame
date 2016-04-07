package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.NodeState;
import com.company.hackinggame.Virus;
import com.company.hackinggame.handlers.IKillHandler;

public class DefensiveSubsystemNode extends ActorNode {
    public int lifeCounter = 0;
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

    /**
     * Activity performed by (each) Defensive Subsystem each time virus makes a move
     */
    public void move(){

    }
    @Override
    public void trigger() {
        switch (state){
            case explorable:
                state = NodeState.explored;
                container.blockSurroundings(this);
                break;
            case explored:
                getHit();
                if(isAlive()){
                    container.hitVirusWith(this);
                }
                break;
        }
    }
}
