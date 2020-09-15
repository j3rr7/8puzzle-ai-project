package com.example.aiproject;

import java.util.ArrayList;
import java.util.Arrays;

public class node {
    Integer[] boards = new Integer[9];
    node parent;
    int depth;

    public node(Integer[] boards, node parent, int depth) {
        this.boards = boards;
        this.parent = parent;
        this.depth = depth;
    }

    public int findZeroIndex()
    {
        return Arrays.asList(boards).indexOf(0);
    }

    public Integer[] getBoards() {
        return boards;
    }

    public void setBoards(Integer[] boards) {
        this.boards = boards;
    }

    public node getParent() {
        return parent;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
