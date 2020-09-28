package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    /*
    * Initialize Variabels to be used
    * */
    protected Button b0,b1,b2,b3,b4,b5,b6,b7,b8,bSolve,bReset;
    protected Integer[] numbers = {0,1,2,3,4,5,6,7,8};
    protected Integer[][] boards = {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}}; // board = posisi number
    protected static final Integer[][] GOAL_STATE = {{1,2,3},{4,5,6},{7,8,0}}; // GOAL STATE

    /*
    * STATE CLASS
    * */

    static public class Node implements Comparable<Node> {
        public ArrayList<Node> children = new ArrayList<>();
        public Node parent;
        public Integer[][] state;
        public int depth, cost;
        public Solver.MOVES action;
        private int[] zeroIndex;

        public Node(Node parent, Integer[][] state, int depth, int cost, Solver.MOVES action) {
            this.parent = parent;
            this.state = state;
            this.depth = depth;
            this.cost = cost;
            this.action = action;

            FillZeroIndex();
        }

        private void FillZeroIndex()
        {
            //SEARCH ZERO INDEX
            for (int i = 0 ; i < 3; i++)
            {
                for(int j = 0 ; j < 3 ; j++)
                {
                    if ( this.state[i][j] == 0)
                    {
                        zeroIndex[0] = i;
                        zeroIndex[1] = j;
                        break;
                    }
                }
            }
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public boolean CanMoveUp()
        {
            return ( zeroIndex[0] >= 0 && zeroIndex[0] < 3  && zeroIndex[1] - 1 >= 0 && zeroIndex[1] - 1 < 3 );
        }
        public boolean CanMoveDown()
        {
            return ( zeroIndex[0] >= 0 && zeroIndex[0] < 3  && zeroIndex[1] + 1 >= 0 && zeroIndex[1] + 1 < 3 );
        }
        public boolean CanMoveLeft()
        {
            return ( zeroIndex[0] - 1 >= 0 && zeroIndex[0] - 1 < 3  && zeroIndex[1] >= 0 && zeroIndex[1] < 3 );
        }
        public boolean CanMoveRight()
        {
            return ( zeroIndex[0] + 1 >= 0 && zeroIndex[0] + 1 < 3  && zeroIndex[1] >= 0 && zeroIndex[1] < 3 );
        }

        public Node MoveUp()
        {
            Integer[][] NewState_ = this.state;
            NewState_[zeroIndex[0]][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]^NewState_[zeroIndex[0]][zeroIndex[1]-1]^(NewState_[zeroIndex[0]][zeroIndex[1]-1] = NewState_[zeroIndex[0]][zeroIndex[1]]);
            return new Node( this.parent,NewState_,this.depth,this.cost, Solver.MOVES.MOVE_UP );
        }
        public Node MoveDown()
        {
            Integer[][] NewState_ = this.state;
            NewState_[zeroIndex[0]][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]^NewState_[zeroIndex[0]][zeroIndex[1]+1]^(NewState_[zeroIndex[0]][zeroIndex[1]+1] = NewState_[zeroIndex[0]][zeroIndex[1]]);
            return new Node( this.parent,NewState_,this.depth,this.cost, Solver.MOVES.MOVE_DOWN );
        }
        public Node MoveLeft()
        {
            Integer[][] NewState_ = this.state;
            NewState_[zeroIndex[0]][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]^NewState_[zeroIndex[0]-1][zeroIndex[1]]^(NewState_[zeroIndex[0]-1][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]);
            return new Node( this.parent,NewState_,this.depth,this.cost, Solver.MOVES.MOVE_LEFT );
        }
        public Node MoveRight()
        {
            Integer[][] NewState_ = this.state;
            NewState_[zeroIndex[0]][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]^NewState_[zeroIndex[0]+1][zeroIndex[1]]^(NewState_[zeroIndex[0]+1][zeroIndex[1]] = NewState_[zeroIndex[0]][zeroIndex[1]]);
            return new Node( this.parent,NewState_,this.depth,this.cost, Solver.MOVES.MOVE_RIGHT );
        }

        public int MisplacedCount() {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.state[i][j] == 0) continue;
                    if (!this.state[i][j].equals(MainActivity.GOAL_STATE[i][j]))
                        count++;
                }
            }
            return count;
        }

        public boolean isGoal()
        {
            for (int i=0;i<3;i++)
            {
                for (int j=0;j<3;j++)
                {
                    if (!state[i][j].equals(MainActivity.GOAL_STATE[i][j]))
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        public static void GeneratePossibleChildren(Node n)
        {
            if(n.CanMoveUp())
            {
                n.getChildren().add(new Node(n.MoveUp(), n.state, n.depth + 1, n.MisplacedCount(), Solver.MOVES.MOVE_UP ));
            }
            if (n.CanMoveDown())
            {
                n.getChildren().add(new Node(n.MoveDown(), n.state, n.depth + 1, n.MisplacedCount(), Solver.MOVES.MOVE_DOWN ));
            }
            if (n.CanMoveLeft())
            {
                n.getChildren().add(new Node(n.MoveLeft(), n.state, n.depth + 1, n.MisplacedCount(), Solver.MOVES.MOVE_LEFT ));
            }
            if (n.CanMoveRight())
            {
                n.getChildren().add(new Node(n.MoveRight(), n.state, n.depth + 1, n.MisplacedCount(), Solver.MOVES.MOVE_RIGHT ));
            }
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static public class Solver{
        public static ArrayList<Node> visitedNode = new ArrayList<>();
        public static enum MOVES{
            NONE,
            MOVE_UP,
            MOVE_LEFT,
            MOVE_RIGHT,
            MOVE_DOWN
        }

        public static void BFS(Integer[][] node)
        {
            visitedNode.clear();

            LinkedList<Node> processedNode = new LinkedList<>();

            Node solution = null;

            Node n = new Node(null,node,0,0,MOVES.NONE);
            processedNode.addLast(n);
            visitedNode.add(n);

            int maxNodes = 1;
            while(!processedNode.isEmpty())
            {
                n = processedNode.getFirst();
                processedNode.removeFirst();
                if (n.isGoal())
                {
                    solution = n;
                    break;
                }
                Node.GeneratePossibleChildren(n);
                for (Node child: n.getChildren()) {
                    if (!visitedNode.contains(child))
                    {
                        visitedNode.add(child);
                        processedNode.addLast(child);
                    }
                    if (processedNode.size() > maxNodes)
                    {
                        maxNodes = processedNode.size();
                    }
                }
            }
            if (solution != null)
            {
                Stack<Node> step = new Stack<>();

                while(solution.parent != null)
                {
                    step.push(solution);
                    solution = solution.parent;
                }
                int totalCost = 0;
                for (Node s:step) {
                    totalCost += s.cost;
                    System.out.println( String.format("Move %s", s.action.toString()) );
                }
            }
            else
            {
                System.out.println(" Tidak bisa solving ");
            }
        }

        public static void Astar(Integer[][] node)
        {
            visitedNode.clear();

            PriorityQueue<Node> NodeProcessed = new PriorityQueue<>();

            Node solution = null;

            Node n = new Node(null,node,0,0,MOVES.NONE);
            NodeProcessed.add(n);
            visitedNode.add(n);

            int maxNodes = 1;
            while(!NodeProcessed.isEmpty())
            {
                int priority;
                n = NodeProcessed.peek();
                priority = n.cost; // help me :'D
                if (n.isGoal())
                {
                    System.out.println( String.format("Depth: %s, MaxNode : %s", n.depth,maxNodes) );
                    solution = n;
                    break;
                }
                Node.GeneratePossibleChildren(n);

                for (Node v : n.getChildren() ) {
                    if (!visitedNode.contains(v))
                    {
                        visitedNode.add(v);
                        NodeProcessed.add(v);
                    }
                    if (NodeProcessed.size() > maxNodes)
                    {
                        maxNodes = NodeProcessed.size();
                    }
                }
            }
            if (solution != null)
            {
                Stack<Node> step = new Stack<>();

                while(solution.parent != null)
                {
                    step.push(solution);
                    solution = solution.parent;
                }

                int totalCost = 0;
                for (Node s:step) {
                    totalCost += s.cost;
                    System.out.println( String.format("Move %s", s.action.toString()) );
                }
            }
            else
            {
                System.out.println(" Tidak bisa solving ");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        bSolve = findViewById(R.id.buttonSolve);
        bReset = findViewById(R.id.buttonReset);

        Reset();
        RefreshBoard();
    }

    public void butClick(View v)
    {
        /*
        * Get every button pressed id and filter the button
        * */
        int id = v.getId();
        if(id == R.id.buttonSolve)
        {
            Solve();
        }
        else if (id == R.id.buttonReset)
        {
            Reset();
            RefreshBoard();
        }
        else
        {
            /*
            * Temporary var to get the button text
            * */
            Button b = (Button) v;
            String num_ = b.getText().toString();
            int num = 0;
            if (!num_.isEmpty())
                num = Integer.parseInt(num_);
            int[] idx_now = getIndex2Dto2D(boards, num);
            //System.out.println( String.format("DEBUGMOVE %s , %s", idx_now[0],idx_now[1]) );

            doMove(boards,idx_now[0],idx_now[1]); // Move Board

            RefreshBoard();
        }
    }

    private void doMove(Integer[][] boards, int x, int y)
    {
        // MOVE UP IF VALID
        MoveUp(boards, x,y);
        // MOVE DOWN IF VALID
        MoveDown(boards, x,y);
        // MOVE LEFT IF VALID
        MoveLeft(boards,x,y);
        // MOVE RIGHT IF VALID
        MoveRight(boards,x,y);
    }

    private void MoveUp(Integer[][] boards, int x, int y)
    {
        if (isValidMove(x+1,y))
        {
            if (boards[x+1][y] == 0)
            {
                boards[x][y] = boards[x][y]^boards[x+1][y]^(boards[x+1][y] = boards[x][y]); //SWAP
            }
        }
    }
    private void MoveDown(Integer[][] boards, int x, int y)
    {
        if (isValidMove(x-1,y))
        {
            if (boards[x-1][y] == 0)
            {
                boards[x][y] = boards[x][y]^boards[x-1][y]^(boards[x-1][y] = boards[x][y]); //SWAP
            }
        }
    }
    private void MoveLeft(Integer[][] boards, int x, int y)
    {
        if (isValidMove(x,y-1))
        {
            if (boards[x][y-1] == 0)
            {
                boards[x][y] = boards[x][y]^boards[x][y-1]^(boards[x][y-1] = boards[x][y]); //SWAP
            }
        }
    }
    private void MoveRight(Integer[][] boards, int x, int y)
    {
        if (isValidMove(x,y+1))
        {
            if (boards[x][y+1] == 0)
            {
                boards[x][y] = boards[x][y]^boards[x][y+1]^(boards[x][y+1] = boards[x][y]); //SWAP
            }
        }
    }


    private boolean isValidMove(int i, int j)
    {
        return ((i>=0&&i<3) && (j>=0&&j<3));
    }

    // a = a^b^(b = a); SWAP
    public void Solve()
    {
        /*
        * To Be Added Solver function
        * BFS
        * DFS
        * (+)
        * */
    }

    public void Reset()
    {
        /*
        * Reset State of button and assign new number for each Button
        * */
        // Random Every Number in a List
        Collections.shuffle(Arrays.asList(numbers));

        boards[0][0] = numbers[0];
        boards[0][1] = numbers[1];
        boards[0][2] = numbers[2];
        boards[1][0] = numbers[3];
        boards[1][1] = numbers[4];
        boards[1][2] = numbers[5];
        boards[2][0] = numbers[6];
        boards[2][1] = numbers[7];
        boards[2][2] = numbers[8];
    }

    public void RefreshBoard()
    {
        /*
        * List all button into array for further used
        * Get the number 0 index from the boards
        * set the text of the buttons as the same as board
        * set the background of 0 number to differentiate the color
        */
        ArrayList<Button> arr = new ArrayList<>(Arrays.asList(b0,b1,b2,b3,b4,b5,b6,b7,b8));
        int ctr = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            if (i == 3 || i == 6 )
                ctr++;
            arr.get(i).setText( String.format("%s", boards[ctr][i%3]) );
        }

        int zero_index = getIndex2Dto1D(boards, 0);
        for (int i = 0; i < arr.size(); i++)
        {
            arr.get(i).setBackgroundColor(Color.argb(170,179,153,255)); // biru
            if (i == zero_index)
            {
                arr.get(i).setBackgroundColor(Color.argb(170,255,153,153)); // merah
                arr.get(i).setText(""); //Remove 0 from boards
            }
        }
    }

    private int getIndex2Dto1D(Integer[][] boards, int idx)
    {
        /*
        * GET INDEX 0 FROM 2D ARRAY AND CONVERT TO 1D ARRAY INDEX
        * */
        int[] b = {-1,-1};
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (boards[i][j] == idx)
                {
                    b[0] = i;
                    b[1] = j;
                }
            }
        }
        return (b[0] * 3 + b[1]); // return 0-25
    }

    private int[] getIndex2Dto2D(Integer[][] boards, int idx)
    {
        /*
         * GET INDEX 0 FROM 2D ARRAY AND CONVERT TO 2D ARRAY INDEX
         * */
        int[] b = {-1,-1};
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (boards[i][j] == idx)
                {
                    b[0] = i;
                    b[1] = j;
                }
            }
        }
        return b; // return i,j
    }
}