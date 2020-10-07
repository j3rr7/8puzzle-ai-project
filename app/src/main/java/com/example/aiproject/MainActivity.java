package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView txtMove;

    /*
    * STATE CLASS
    * */

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

        txtMove = findViewById(R.id.txtMove);

        //Reset();
        boards = new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        RefreshBoard();
        txtMove.setText("");
    }

    public void butClick(View v)
    {
        /*
        * Get every button pressed id and filter the button
        * */
        int id = v.getId();
        if(id == R.id.buttonSolve)
        {
            Solve(v);
        }
        else if (id == R.id.buttonReset)
        {
            Reset();
            RefreshBoard();
        }
        else if (id == R.id.button)
        {
            boards = new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
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
    public void Solve(View v)
    {
        /*
        * To Be Added Solver function
        * BFS
        * A* with heuristic
        *
        * (+)
        * */
        //
        //
        PopupMenu popup = new PopupMenu(MainActivity.this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bfs:
                        // do your
                        BFS();
                        return true;
                    case R.id.menu_astar:
                        // do your code
                        Astar();
                        return true;
                    case R.id.menu_iterative:
                        IterativeDeepening();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.solver_menu);
        popup.show();
    }

    private void BFS()
    {
        String s = Solver.BFS(boards);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        txtMove.setText(s);
    }
    private void Astar()
    {
        String s = Solver.Astar(boards);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        txtMove.setText(s);
    }
    private void IterativeDeepening()
    {
        String s = Solver.IterativeDeepening(boards);
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        txtMove.setText(s);
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