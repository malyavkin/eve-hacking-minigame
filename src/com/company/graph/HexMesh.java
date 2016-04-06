package com.company.graph;

import java.util.ArrayList;

/**
 *  Here's graphical representation:
 *
 *     0   1   2   3        0 0 1 1 2 2 3 3
 *  0  * - * - * - *        * - * - * - *
 *     | / | / | / |         \ / \ / \ / \
 *  1  * - * - * - *          * - * - * - *
 *     | \ | \ | \ |         / \ / \ / \ /
 *  2  * - * - * - *        * - * - * - *
 *     | / | / | / |         \ / \ / \ / \
 *  3  * - * - * - *          * - * - * - *
 */


public class HexMesh<T> {
    public ArrayList<ArrayList<HexNode<T>>> mesh;

    public HexNode<T> get(int i, int j){
        return  mesh.get(i).get(j);
    }

    public HexNode<T> get(T value){
        for (ArrayList<HexNode<T>> hexNodes : mesh) {
            for (HexNode<T> hexNode : hexNodes) {
                if (hexNode.content == value){
                    return hexNode;
                }
            }
        }
        return null;
    }

    /**
     * @param node this HexNode
     * @return array of HexNodes surrounding this HexNode.
     * Function doesn't take into account value of content
     */
    public ArrayList<HexNode<T>> getSurroundings(HexNode<T> node){
        ArrayList<HexNode<T>> nodes = new ArrayList<>();
        if(node.down_L != null){
            nodes.add(node.down_L);
        }
        if(node.down_R != null){
            nodes.add(node.down_R);
        }
        if(node.top_L != null){
            nodes.add(node.top_L);
        }
        if(node.top_R != null){
            nodes.add(node.top_R);
        }
        if(node.left != null){
            nodes.add(node.left);
        }
        if(node.right != null){
            nodes.add(node.right);
        }


        return nodes;
    }


    public HexMesh(int cols, int rows) {

        mesh = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            mesh.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                 mesh.get(i).add(new HexNode<>());
            }
        }
        for (int i = 0; i < rows; i++) {


            if( i % 2 == 0) { // even, *-right is straight, *-left is diagonal
                for (int j = 0; j < cols; j++) {
                    HexNode<T> current = mesh.get(i).get(j);
                    HexNode<T> other;
                    if(j > 0){
                        //can go left
                        other = mesh.get(i).get(j-1);
                        current.left = other;
                        other.right = current;
                    }
                    if(j < cols-1) {
                        // can go right
                        other = mesh.get(i).get(j+1);
                        current.right = other;
                        other.left = current;
                    }
                    if(i>0){
                        if(j > 0){
                            // can go up-left (diagonal)
                            other = mesh.get(i-1).get(j-1);
                            current.top_L = other;
                            other.down_R = current;

                        }
                        // can go up-right (straight)
                        other = mesh.get(i-1).get(j);
                        current.top_R = other;
                        other.down_L = current;
                    }
                    if(i< rows-1){
                        if(j > 0) {
                            // can go down-left
                            other = mesh.get(i+1).get(j-1);
                            current.down_L = other;
                            other.top_R = current;
                        }
                        // can go down-right (straight)
                        other = mesh.get(i+1).get(j);
                        current.down_R = other;
                        other.top_L = current;

                    }


                }
            } else {//odd *-right is diagonal, *-left is straight
                for (int j = 0; j < cols; j++) {
                    HexNode<T> current = mesh.get(i).get(j);
                    HexNode<T> other;
                    if (j > 0) {
                        //can go left
                        other = mesh.get(i).get(j - 1);
                        current.left = other;
                        other.right = current;
                    }
                    if (j < cols - 1) {
                        // can go right
                        other = mesh.get(i).get(j + 1);
                        current.right = other;
                        other.left = current;
                    }
                    if (i > 0) {

                        // can go up-left (straight)
                        other = mesh.get(i - 1).get(j);
                        current.top_L = other;
                        other.down_R = current;

                        if (j < cols - 1) {
                            // can go up-right (diagonal)
                            other = mesh.get(i - 1).get(j + 1);
                            current.top_R = other;
                            other.down_L = current;
                        }


                    }
                    if (i < rows - 1) {

                        // can go down-left (straight)
                        other = mesh.get(i + 1).get(j);
                        current.down_L = other;
                        other.top_R = current;
                        if (j < cols - 1) {
                            // can go down-right (diagonal)
                            other = mesh.get(i + 1).get(j + 1);
                            current.down_R = other;
                            other.top_L = current;
                        }
                    }
                }
            }
        }
    }
}

