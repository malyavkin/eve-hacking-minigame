package com.company.hackinggame;

import com.company.graph.HexMesh;
import com.company.graph.HexNode;
import com.company.hackinggame.nodes.SystemCoreNode;
import com.company.presets.IContainerPreset;
import com.company.string_framebuffer.StringBuffer;
import com.company.utils.Utils;

import java.util.ArrayList;

public class DataContainer {
    private HexMesh<Node> map;
    private int width, height;
    private Virus virus;
    public DataContainer(IContainerPreset containerPreset) {
        this.width = containerPreset.getFieldSizeWidth();
        this.height = containerPreset.getFieldSizeHeight();
        map = new HexMesh<>(width, height);
        virus = new Virus(containerPreset.getVirusStrength(), containerPreset.getVirusCoherence(), this);
        populate(containerPreset);

    }
    public  void move(int i, int j){
        this.map.get(i,j).content.trigger();
        String id = Utils.getID(map.get(i,j));
        System.out.println("triggered "+ id + "; current status "+ map.get(i,j).content.state);
    }
    private void populate(IContainerPreset containerPreset){
        for (ArrayList<HexNode<Node>> hexNodes : map.mesh) {
            for (HexNode<Node> hexNode : hexNodes) {
                hexNode.content = new Node(this);
            }
        }
        map.get(2,2).content = new SystemCoreNode(123,456,this);
        //map.get(6,6).content.state = NodeState.explored;
        //exposeSurroundings(map.get(6,6).content);
        map.get(2,1).content.state = NodeState.explorable;
        exposeSurroundings(map.get(2,1).content);
        //map.get(2,2).content.trigger();
    }

    public void exposeSurroundings(Node node) {
        HexNode<Node> hexNode = map.get(node);
        if(hexNode == null){
            System.out.println("wtf");
        }
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

                String id = Utils.getID(hexNodes.get(j));

                HexNode<Node> hexNode= hexNodes.get(j);
                int hoffset = j*8;
                sb.pushString(voffset+3, hoffset+indent, id);
                sb.pushString(voffset, hoffset+indent, hexNode.content.toString());
            }
        }
        sb.render();



    }
}
