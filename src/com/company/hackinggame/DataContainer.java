package com.company.hackinggame;

import com.company.graph.HexMesh;
import com.company.graph.HexNode;
import com.company.hackinggame.nodes.SystemCoreNode;
import com.company.presets.IContainerPreset;
import com.company.string_framebuffer.StringBuffer;

import java.util.ArrayList;

public class DataContainer {
    public HexMesh<Node> map;
    public int width, height;
    public Virus virus;
    public DataContainer(IContainerPreset containerPreset) {
        this.width = containerPreset.getFieldSizeWidth();
        this.height = containerPreset.getFieldSizeHeight();
        map = new HexMesh<>(width, height);
        virus = new Virus(containerPreset.getVirusStrength(), containerPreset.getVirusCoherence(), this);
        populate(containerPreset);

    }

    private void populate(IContainerPreset containerPreset){
        for (ArrayList<HexNode<Node>> hexNodes : map.mesh) {
            for (HexNode<Node> hexNode : hexNodes) {
                hexNode.content = new Node(this);
            }
        }
        map.get(2,2).content = new SystemCoreNode(123,456,this);
        map.get(6,6).content.state = NodeState.explored;
        exposeSurroundings(map.get(6,6).content);
     //   exposeSurroundings(map.get(2,1).content);
    }

    public void exposeSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {
            if(neighbour.content.state == NodeState.unexplorable) {
                neighbour.content.state = NodeState.explorable;
            }
        }

    }
    public void blockSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {
            neighbour.content.blockCounter++;
        }
    }
    public void unblockSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        ArrayList<HexNode<Node>> nodes = map.getSurroundings(hexNode);
        for (HexNode<Node> neighbour : nodes) {
            neighbour.content.blockCounter--;
            if (neighbour.content.blockCounter < 0) {
                neighbour.content.blockCounter = 0;
            }
        }
    }
    public void open() {
        System.out.println("you won");
    }

    public void draw(){
        StringBuffer sb = new StringBuffer(this.width*8, this.height*4);

        for (int i = 0; i < map.mesh.size(); i++) {
            ArrayList<HexNode<Node>> hexNodes = map.mesh.get(i);
            int indent = (i%2)*4;
            int voffset = i*4;
            for (int j = 0; j < hexNodes.size(); j++) {
                HexNode<Node> hexNode= hexNodes.get(j);
                int hoffset = j*8;
                sb.pushString(voffset, hoffset+indent, hexNode.content.toString());
            }
        }
        sb.render();



    }
}
