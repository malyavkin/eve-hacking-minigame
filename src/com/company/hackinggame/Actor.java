package com.company.hackinggame;

import com.company.hackinggame.handlers.IKillHandler;

public class Actor {
    private int strength;
    private int coherence;
    private IKillHandler killHandler;
    public boolean isAlive(){
        return coherence > 0;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        
    }

    public int getCoherence() {
        return coherence;
    }

    public void setCoherence(int coherence) {
        this.coherence = coherence;
		if (this.coherence <= 0 && killHandler != null) {
            killHandler.onKill();
        }
    }

    public void setKillHandler(IKillHandler killHandler){
        this.killHandler = killHandler;
    }

    public DataContainer container;
    public Actor(int strength, int coherence, DataContainer container) {
        this.strength = strength;
        this.coherence = coherence;
        this.container = container;
    }
}
