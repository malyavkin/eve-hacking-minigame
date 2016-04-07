package com.company.hackinggame;

import com.company.hackinggame.nodes.ActorNode;
import com.company.hackinggame.powerups.perks;

import java.util.ArrayList;

public class Virus extends Actor{
    public ArrayList<perks> powerups;
    public Virus(int strength, int coherence, DataContainer container) {
        super(strength, coherence, container);
        powerups = new ArrayList<>(3);

    }

    public void attack(ActorNode actorNode){
        actorNode.getHit();
    }
}
