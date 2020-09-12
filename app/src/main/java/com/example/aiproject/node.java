package com.example.aiproject;

import java.util.ArrayList;

public class node {
    node parent;
    ArrayList<node> children = new ArrayList<>();
    int depth;
    Integer[] boards = new Integer[9];

    public node(node parent, ArrayList<node> children, int depth, Integer[] boards) {
        this.parent = parent;
        this.children = children;
        this.depth = depth;
        this.boards = boards;
    }
    public node(ArrayList<node> children, int depth, Integer[] boards) {
        this(null, children, depth, boards);
    }
    
}
