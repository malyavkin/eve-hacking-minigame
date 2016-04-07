package com.company.hackinggame.nodes;

import com.company.hackinggame.*;

public class ActorNode extends Node {
    public int getCoherence(){
        return actor.getCoherence();
    }
    public void setCoherence(int coherence){
        this.actor.setCoherence(coherence);
    }
    public int getStrength(){
        return actor.getStrength();
    }

    Actor actor;
    public boolean isAlive(){
        return actor.isAlive();
    }
    public boolean isActive(){
        return isAlive() && state == NodeState.explored;
    }
    public ActorNode (int strength, int coherence, final DataContainer container) {
        super(container);
        actor = new Actor(strength, coherence, container);
    }
    public void getHit(){
        actor.setCoherence(actor.getCoherence()-container.getEffectiveVirusStrength());
    }


}