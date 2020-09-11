package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    /*
    * Initialize Variabels to be used
    * */
    protected Button b0,b1,b2,b3,b4,b5,b6,b7,b8,bSolve,bReset;
    protected Integer[] numbers = {0,1,2,3,4,5,6,7,8};
    protected Integer[] boards = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    protected Integer[] finishState = {0,1,2,3,4,5,6,7,8};

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
            int num = Integer.parseInt(b.getText().toString());

            // If its not zero or 0
            if (num != 0)
            {
                System.out.println("Num: "+num + "\nIndex :" + Arrays.asList(boards).indexOf(num));

                // Check and move board :V maybe ill fix this later -Jere_ID
                if(     !CheckLeft(boards, Arrays.asList(boards).indexOf(num)) &&
                        !CheckRight(boards, Arrays.asList(boards).indexOf(num)) &&
                        !CheckUp(boards, Arrays.asList(boards).indexOf(num)) &&
                        !CheckDown(boards, Arrays.asList(boards).indexOf(num)))
                {

                }
            }
            RefreshBoard();
        }
    }

    // a = a^b^(b = a); SWAP
    /*
    * All check function return false if cant move or not zero
    * */
    public boolean CheckUp(Integer[] boards, int num)
    {
        if (IsThatValid(num - 3)) // -3 is constraint to check up
        {
            if (boards[num - 3] == 0)
            {
                // Move up
                boards[num - 3] = boards[num - 3]^boards[num]^(boards[num] = boards[num - 3]);
                return true;
            }
            else
                return false;
        }
        return false;
    }
    public boolean CheckDown(Integer[] boards, int num)
    {
        if (IsThatValid(num + 3)) // +3 is constraint to check down
        {
            if (boards[num + 3] == 0)
            {
                // Move down
                boards[num + 3] = boards[num + 3]^boards[num]^(boards[num] = boards[num + 3]);
                return true;
            }
            else
                return false;
        }
        return false;
    }
    public boolean CheckLeft(Integer[] boards, int num)
    {
        if (IsThatValid(num - 1)) // -1 is constraint to check left
        {
            if (boards[num - 1] == 0)
            {
                // Move left
                boards[num - 1] = boards[num - 1]^boards[num]^(boards[num] = boards[num - 1]);
                return true;
            }
            else
                return false;
        }
        return false;
    }
    public boolean CheckRight(Integer[] boards, int num)
    {
        if (IsThatValid(num + 1)) // +1 is constraint to check right
        {
            if (boards[num + 1] == 0)
            {
                // Move right
                boards[num + 1] = boards[num + 1]^boards[num]^(boards[num] = boards[num + 1]);
                return true;
            }
            else
                return false;
        }
        return false;
    }

    /*
    * IsThatValid(num) return true if index between 0 and 8
    * */
    public boolean IsThatValid(int n)
    {
        return (n >= 0 && n <= 8);
    }


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

        boards[0] = numbers[0];
        boards[1] = numbers[1];
        boards[2] = numbers[2];
        boards[3] = numbers[3];
        boards[4] = numbers[4];
        boards[5] = numbers[5];
        boards[6] = numbers[6];
        boards[7] = numbers[7];
        boards[8] = numbers[8];
    }

    public void RefreshBoard()
    {
        ArrayList<Button> arr = new ArrayList<Button>(Arrays.asList(b0,b1,b2,b3,b4,b5,b6,b7,b8));
        int zeroIndex = Arrays.asList(boards).indexOf(0);
        for (int i = 0; i<9; i++)
        {
            arr.get(i).setText(String.format("%s",boards[i]));
            if (i != zeroIndex)
            {
                arr.get(i).setBackgroundColor(Color.argb(170,255,153,153));
            }
            else
            {
                arr.get(i).setBackgroundColor(Color.argb(170,179,153,255));
            }
        }
    }
}