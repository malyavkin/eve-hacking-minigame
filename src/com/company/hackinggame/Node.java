package com.company.hackinggame;

import com.company.string_framebuffer.StringBuffer;

public class Node {
    public NodeState state;
    public DataContainer container;
    public int blockCounter;
    public boolean inDataCache;
    protected final String unexplorable ="###\n###\n###";
    protected final String explorable = "===\n#?#\n===";
    protected final String explored = "---\n( )\n---";
    public Node(DataContainer container) {
        this.container = container;
        blockCounter = 0;

        state = NodeState.unexplorable;
    }
    public void trigger(){
        if (state == NodeState.explorable) {
            state = NodeState.explored;
            container.exposeSurroundings(this);
        }
    }

    @Override
    public String toString() {
        switch (state){
            case unexplorable:
                return unexplorable;
            case explorable:
                return explorable;
            case explored:
                return explored;
        }
        return "wtf\ntfw\nfwt";
    }
}
