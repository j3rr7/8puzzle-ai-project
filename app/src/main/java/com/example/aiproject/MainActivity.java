package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    /*
    * Initialize Variabels to be used
    * */
    protected Button b0,b1,b2,b3,b4,b5,b6,b7,b8,bSolve,bReset;
    protected Integer[] numbers = {0,1,2,3,4,5,6,7,8};
    protected Integer[][] boards = {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}}; // board = posisi number

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

            System.out.println( String.format("DEBUGMOVE %s , %s", idx_now[0],idx_now[1]) );

            // MOVE UP IF VALID
            if (isValidMove(idx_now[0]+1,idx_now[1]))
                MoveUp(boards, idx_now[0],idx_now[1]);
            // MOVE DOWN IF VALID
            if (isValidMove(idx_now[0]-1,idx_now[1]))
                MoveDown(boards, idx_now[0],idx_now[1]);
            // MOVE LEFT IF VALID
            if (isValidMove(idx_now[0],idx_now[1]-1))
                MoveLeft(boards,idx_now[0],idx_now[1]);
            // MOVE RIGHT IF VALID
            if (isValidMove(idx_now[0],idx_now[1]+1))
                MoveRight(boards,idx_now[0],idx_now[1]);
            RefreshBoard();
        }
    }

    private void MoveUp(Integer[][] boards, int x, int y)
    {
        if (boards[x+1][y] == 0)
        {
            boards[x][y] = boards[x][y]^boards[x+1][y]^(boards[x+1][y] = boards[x][y]); //SWAP
        }
    }
    private void MoveDown(Integer[][] boards, int x, int y)
    {
        if (boards[x-1][y] == 0)
        {
            boards[x][y] = boards[x][y]^boards[x-1][y]^(boards[x-1][y] = boards[x][y]); //SWAP
        }
    }
    private void MoveLeft(Integer[][] boards, int x, int y)
    {
        if (boards[x][y-1] == 0)
        {
            boards[x][y] = boards[x][y]^boards[x][y-1]^(boards[x][y-1] = boards[x][y]); //SWAP
        }
    }
    private void MoveRight(Integer[][] boards, int x, int y)
    {
        if (boards[x][y+1] == 0)
        {
            boards[x][y] = boards[x][y]^boards[x][y+1]^(boards[x][y+1] = boards[x][y]); //SWAP
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