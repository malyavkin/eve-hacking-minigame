package com.company.hackinggame.nodes;

import com.company.hackinggame.Actor;
import com.company.hackinggame.DataContainer;
import com.company.hackinggame.Node;
import com.company.hackinggame.Virus;

public class ActorNode extends Node {
    Actor actor;
    boolean isDead = false;
    public ActorNode (int strength, int coherence, final DataContainer container) {
        super(container);
        actor = new Actor(strength, coherence, container);
    }
    public void getHit(Virus virus){
        actor.setCoherence(actor.getCoherence()-virus.getStrength());
        if(actor.getCoherence()<=0)
            isDead = true;
    }


}