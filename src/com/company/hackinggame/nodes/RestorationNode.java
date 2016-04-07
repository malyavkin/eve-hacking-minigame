package com.company.hackinggame.nodes;

import com.company.hackinggame.DataContainer;
import com.company.hackinggame.NodeState;
import com.company.utils.Utils;

import java.util.ArrayList;

public class RestorationNode extends DefensiveSubsystemNode {

    public RestorationNode(int strength, int coherence, final DataContainer container) {
        super(strength, coherence, container);


    }

    @Override
    public void move() {
        if(isActive() && lifeCounter > 0){
            ArrayList<DefensiveSubsystemNode> nodes = container.getDefensiveSubsystems();
            ArrayList<DefensiveSubsystemNode> activeNodes = new ArrayList<>();
            DefensiveSubsystemNode target;
            //TODO: can Restoration node heal itself? If yes, remove this line:
            nodes.remove(this);
            if(nodes.size() > 0){
                for (DefensiveSubsystemNode node : nodes) {
                    if(node.isActive()){
                        activeNodes.add(node);
                    }
                }
            }
            if(activeNodes.size() > 0){
                int index = Utils.inRangeBetween(0,activeNodes.size()-1);
                target = activeNodes.get(index);
            } else {
                target = this;
            }
            target.setCoherence(target.getCoherence() + this.getStrength());
        }
        lifeCounter++;
    }

    @Override
    public String toString() {
        switch (state){
            case unexplorable:
                return unexplorable;
            case explorable:
                return explorable;
            case explored:
                if(isAlive()){
                    return String.format("%03d\n}|{\n%03d", actor.getCoherence(), actor.getStrength());
                } else {
                    return explored;
                }
        }
        return "wtf\ntfw\nfwt";
    }
}
