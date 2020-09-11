package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        }
        else
        {
            /*
            * Temporary var to get the button text
            * */
            Button b = (Button) v;
            String num = b.getText().toString();
        }
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

        b0.setText( String.format("%s",boards[0]) );
        b1.setText( String.format("%s",boards[1]) );
        b2.setText( String.format("%s",boards[2]) );
        b3.setText( String.format("%s",boards[3]) );
        b4.setText( String.format("%s",boards[4]) );
        b5.setText( String.format("%s",boards[5]) );
        b6.setText( String.format("%s",boards[6]) );
        b7.setText( String.format("%s",boards[7]) );
        b8.setText( String.format("%s",boards[8]) );
    }
}