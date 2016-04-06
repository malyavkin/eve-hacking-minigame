package com.company.hackinggame;

import com.company.hackinggame.handlers.IKillHandler;
import com.company.hackinggame.nodes.ActorNode;
import com.company.hackinggame.powerups.Powerup;

import java.util.ArrayList;

public class Virus extends Actor{
    public ArrayList<Powerup> IPowerups;
    public Virus(int strength, int coherence, DataContainer container) {
        super(strength, coherence, container);
        IPowerups = new ArrayList<>(3);
        this.setKillHandler(new IKillHandler() {
            @Override
            public void onKill() {
                System.out.println("lose");
            }
        });
    }

    public void attack(ActorNode actorNode){
        actorNode.getHit(this);
    }
}
