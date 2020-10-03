package com.example.aiproject;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node implements Cloneable {
        public Node parent;
        public ArrayList<Node> children = new ArrayList<>();
        public Integer[][] state;
        public int depth, cost;
        public String move;
        public int[] zeroIndex = {-1, -1};

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Integer[][] getState() {
        return state;
    }

    public int[] getZeroIndex() {
        return zeroIndex;
    }

    public void setZeroIndex(int[] zeroIndex) {
        this.zeroIndex = zeroIndex;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Node(Node parent, Integer[][] state, int depth, int cost, String move) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.cost = cost;
        this.move = move;

        FillZeroIndex();
    }

    private void FillZeroIndex(){
        //SEARCH ZERO INDEX
        for (int i = 0 ; i < 3; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if ( this.state[i][j] == 0) {
                    zeroIndex[0] = i;
                    zeroIndex[1] = j;
                    break;
                }
            }
        }
    }

    public boolean isGoal()
    {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (!state[i][j].equals(MainActivity.GOAL_STATE[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean CanMoveUp()
    {
        return ( zeroIndex[0] - 1 >= 0 && zeroIndex[0] - 1 < 3  && zeroIndex[1] >= 0 && zeroIndex[1] < 3 );
    }
    public boolean CanMoveDown()
    {
        return ( zeroIndex[0] + 1 >= 0 && zeroIndex[0] + 1 < 3  && zeroIndex[1] >= 0 && zeroIndex[1] < 3 );
    }
    public boolean CanMoveLeft()
    {
        return ( zeroIndex[0] >= 0 && zeroIndex[0] < 3  && zeroIndex[1] - 1 >= 0 && zeroIndex[1] - 1 < 3 );
    }
    public boolean CanMoveRight()
    {
        return ( zeroIndex[0] >= 0 && zeroIndex[0] < 3  && zeroIndex[1] + 1 >= 0 && zeroIndex[1] + 1 < 3 );
    }

    public static Integer[][] cloneArray(Integer[][] src) {
        int length = src.length;
        Integer[][] target = new Integer[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static Node MoveUp(Node n)
    {
        Integer[][] NewState_ = cloneArray(n.state);
        NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]-1][n.getZeroIndex()[1]]^(NewState_[n.getZeroIndex()[0]-1][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
        return new Node( n,NewState_,n.getDepth() + 1,n.cost, "up" );
    }
    public static Node MoveDown(Node n)
    {
        Integer[][] NewState_ = cloneArray(n.state);
        NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]+1][n.getZeroIndex()[1]]^(NewState_[n.getZeroIndex()[0]+1][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
        return new Node( n,NewState_,n.getDepth() + 1,n.cost, "down" );
    }
    public static Node MoveLeft(Node n)
    {
        Integer[][] NewState_ = cloneArray(n.state);
        NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]-1]^(NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]-1] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
        return new Node( n,NewState_,n.getDepth() + 1,n.cost, "left" );
    }
    public static Node MoveRight(Node n)
    {
        Integer[][] NewState_ = cloneArray(n.state);
        NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]+1]^(NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]+1] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
        return new Node( n,NewState_,n.getDepth()+ 1,n.cost, "right");
    }

    public int MisplacedCount() { //heuristic
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!this.state[i][j].equals(MainActivity.GOAL_STATE[i][j]))
                    count++;
            }
        }
        return count;
    }

    public static void GeneratePossibleChildren(Node n,String cara)
    {
        //String cara ="bfs"; // bfs | a* |

        if (cara.equalsIgnoreCase("a*"))
        {
            if(n.CanMoveUp()) {
                Integer[][] NewState_ = cloneArray(n.state);
                NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]-1][n.getZeroIndex()[1]]^(NewState_[n.getZeroIndex()[0]-1][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
                n.children.add(new Node(n, NewState_, n.depth + 1, n.MisplacedCount() + n.depth, "up" ));
            }
            if (n.CanMoveDown()) {
                Integer[][] NewState_ = cloneArray(n.state);
                NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]+1][n.getZeroIndex()[1]]^(NewState_[n.getZeroIndex()[0]+1][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
                n.children.add(new Node(n, NewState_, n.depth + 1, n.MisplacedCount() + n.depth, "down" ));
            }
            if (n.CanMoveLeft()) {
                Integer[][] NewState_ = cloneArray(n.state);
                NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]-1]^(NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]-1] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
                n.children.add(new Node(n, NewState_, n.depth + 1, n.MisplacedCount() + n.depth, "left" ));
            }
            if (n.CanMoveRight()) {
                Integer[][] NewState_ = cloneArray(n.state);
                NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]^NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]+1]^(NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]+1] = NewState_[n.getZeroIndex()[0]][n.getZeroIndex()[1]]);
                n.children.add(new Node(n, NewState_, n.depth + 1, n.MisplacedCount() + n.depth, "right" ));
            }
        }
        else if (cara.equalsIgnoreCase("bfs"))
        {
            if(n.CanMoveUp()) {
                n.children.add(Node.MoveUp(n));
            }
            if (n.CanMoveDown()) {
                n.children.add(Node.MoveDown(n));
            }
            if (n.CanMoveLeft()) {
                n.children.add(Node.MoveLeft(n));
            }
            if (n.CanMoveRight()) {
                n.children.add(Node.MoveRight(n));
            }
        }
    }

    public String printCurrentState(){
        String n = "";
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                n += Integer.toString(this.state[i][j]);
            }
            n+=" ";
        }
        return n;
    }
}
class ArrayComparator implements Comparator<Node> {
    public int compare(Node s1, Node s2) {
        if (s1.cost > s2.cost)
            return 1;
        else if (s1.cost < s2.cost)
            return -1;
        return 0;
    }
}