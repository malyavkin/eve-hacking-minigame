package com.company.hackinggame;

import com.company.graph.HexMesh;
import com.company.graph.HexNode;
import com.company.hackinggame.handlers.IGameTerminator;
import com.company.hackinggame.handlers.IKillHandler;
import com.company.hackinggame.nodes.AntivirusNode;
import com.company.hackinggame.nodes.DefensiveSubsystemNode;
import com.company.hackinggame.nodes.FirewallNode;
import com.company.hackinggame.nodes.SystemCoreNode;
import com.company.presets.IContainerPreset;
import com.company.string_framebuffer.StringBuffer;
import com.company.utils.Utils;

import java.util.ArrayList;

public class DataContainer {
    private HexMesh<Node> map;
    private int width, height;
    private Virus virus;
    private IGameTerminator terminator;

    public DataContainer(IContainerPreset containerPreset) {
        this.width = containerPreset.getFieldSizeWidth();
        this.height = containerPreset.getFieldSizeHeight();
        map = new HexMesh<>(width, height);
        virus = new Virus(containerPreset.getVirusStrength(), containerPreset.getVirusCoherence(), this);
        virus.setKillHandler(new IKillHandler() {
            @Override
            public void onKill() {
                if(terminator != null){
                    terminator.onVirusDestruction();
                }
            }
        });
        populate(containerPreset);

    }
    public  void move(int i, int j){
        HexNode<Node> target = this.map.get(i,j);
        String id = Utils.getID(target);
        System.out.println("triggered "+ id + "; current status "+ target.content.state);
        this.map.get(i,j).content.trigger(virus);

    }

    private void populate(IContainerPreset containerPreset){
        for (ArrayList<HexNode<Node>> hexNodes : map.mesh) {
            for (HexNode<Node> hexNode : hexNodes) {
                hexNode.content = new Node(this);
            }
        }
        map.get(2,2).content = new SystemCoreNode(10,50,this);
        map.get(2,3).content = new AntivirusNode(40,30,this);
        map.get(2,0).content = new FirewallNode(20,60,this);
        //map.get(6,6).content.state = NodeState.explored;
        //exposeSurroundings(map.get(6,6).content);
        map.get(2,1).content.state = NodeState.explorable;
        map.get(2,1).content.trigger(virus);
        //map.get(2,2).content.trigger();
        map.get(3,3).content = null;

    }

    public void exposeSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        if(hexNode == null){
            System.out.println("wtf");
        }
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {

            if(neighbour.content != null && neighbour.content.state == NodeState.unexplorable) {
                neighbour.content.state = NodeState.explorable;
            }
        }

    }
    public void blockSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {
            if(neighbour.content != null) {
                neighbour.content.blockCounter++;
            }
        }
    }
    public void unblockSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {
            if(neighbour.content != null){
                neighbour.content.blockCounter--;
                if (neighbour.content.blockCounter < 0) {
                    neighbour.content.blockCounter = 0;
                }
            }
        }
    }
    public void open() {
        if(terminator != null){
            terminator.onContainerOpen();
        }
    }

    public void draw(){
        StringBuffer sb = new StringBuffer(this.width*8, this.height*4+2);

        for (int i = 0; i < map.mesh.size(); i++) {
            ArrayList<HexNode<Node>> hexNodes = map.mesh.get(i);
            int indent = (i%2)*4;
            int voffset = i*4;
            for (int j = 0; j < hexNodes.size(); j++) {
                HexNode<Node> hexNode= hexNodes.get(j);
                if(hexNode.content != null){
                    String id = Utils.getID(hexNode);
                    int hoffset = j*8;
                    //sb.pushString(voffset+3, hoffset+indent, id);
                    sb.pushString(voffset+3, hoffset+indent, Integer.toString(hexNode.content.blockCounter));
                    sb.pushString(voffset, hoffset+indent, hexNode.content.toString());
                    if(hexNode.right != null && hexNode.right.content != null){
                        sb.pushString(voffset +1, hoffset+indent+4, " - ");
                    }
                    if(hexNode.down_R != null && hexNode.down_R.content != null){
                        sb.pushString(voffset +3, hoffset+indent+3, "\\");
                    }
                    if(hexNode.down_L != null && hexNode.down_L.content != null){
                        sb.pushString(voffset +3, hoffset+indent-1, "/");
                    }
                }
            }
        }
        sb.pushString(map.mesh.size() * 4, 0, "Your strength: " + virus.getStrength());
        sb.pushString(map.mesh.size() * 4+1, 0, "Your coherence: " + virus.getCoherence());
        sb.render();
    }
    public void setGameTerminator(IGameTerminator terminator){
        this.terminator = terminator;
    }


}
