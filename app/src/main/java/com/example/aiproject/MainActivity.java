package com.example.aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
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

    int [][] movement = {{0,-1},{0,1},{0,1},{-1,0} }; //arah jarum jam dari atas
    Button[][] map = new Button[3][3];
    ArrayList<Button[][]> SavedState = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map[0][0] = findViewById(R.id.button0);
        map[0][1] = findViewById(R.id.button1);
        map[0][2] = findViewById(R.id.button2);
        map[1][0] = findViewById(R.id.button3);
        map[1][1] = findViewById(R.id.button4);
        map[1][2] = findViewById(R.id.button5);
        map[2][0] = findViewById(R.id.button6);
        map[2][1] = findViewById(R.id.button7);
        map[2][2] = findViewById(R.id.button8);

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
    public void signButton()
    {
        map[0][0] = findViewById(R.id.button0);
        map[0][1] = findViewById(R.id.button1);
        map[0][2] = findViewById(R.id.button2);
        map[1][0] = findViewById(R.id.button3);
        map[1][1] = findViewById(R.id.button4);
        map[1][2] = findViewById(R.id.button5);
        map[2][0] = findViewById(R.id.button6);
        map[2][1] = findViewById(R.id.button7);
        map[2][2] = findViewById(R.id.button8);
    }
    public int[] findZero(){
        int[] xy = new int[2]; //[0] -> X  , [1]-> Y
        signButton(); //bek e lali naruh button e
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if(map[i][j].getText().toString().equalsIgnoreCase("0")){
                    xy[0] = j ;
                    xy[1] = i ;
                    break;
                }
            }
        }



        return xy;
    }
    public Boolean cekMovement(int xMap,int yMap,int xMove,int yMove){
        Boolean aman=true;
        if(xMap+xMove <0 || xMap+xMove >3) aman=false;
        if(yMap+yMove <0 || yMap+ yMove >3) aman=false;
        return aman;
    };

    public void swapButton(int moveIdx){

    }


    //iki  kudu dianggep seolah" ngeswap se
    public Boolean cekRecuring(){
        signButton(); //bek e lali var e map



        return false;
    }

    public Boolean winCondition(){
        Boolean win = true;
//        123
//        456
//        780
        int ctr = 1;
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               if(!map[i][j].getText().toString().equalsIgnoreCase(ctr+"")) win=false;
                ctr++;
                if(ctr==9)ctr=0;
           }
       }
        return win;
    }

//            ngecek renegade movement
//                    nyimpen state yang pernah
//                    kalo ga renegade ,misal diswap recursion gak.
//                        kalo engga cek win , if not win then cek anak lain e
//                    setelah cek semua dan engga win , lanjut ke anak pertama
    public Boolean BFS(int x ,int y){ //buat xy  buat  posisi 0 e
        if(winCondition()){
            //yey
            return true;
        }else{
            int ctrMove=0;


            return false;
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

        int[] zeroLoc = findZero();
        //let the magic do the work
        BFS(zeroLoc[0],zeroLoc[1]);






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