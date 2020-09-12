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
    protected Integer[][] wincond ;

    int [][] movement = {{0,-1},{0,1},{0,1},{-1,0} }; //arah jarum jam dari atas
    Button[][] map = new Button[3][3];
    Integer[][] mapInt = new Integer[3][3];
    ArrayList<Button[][]> SavedStaasd = new ArrayList<>();
    ArrayList<Integer[][]> SavedState = new ArrayList<>();
    ArrayList<Integer[][]> FinishState = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int ctr =1;
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if(ctr < 9 ){
                    wincond[i][j] = ctr;
                    ctr++;
                }else if(ctr==9){
                    wincond[i][j] = 0;
                }
            }
        }

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

        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                mapInt[i][j] =  Integer.parseInt(map[i][j].getText().toString());
            }
        }

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

    public Boolean cekWin(Integer[][] board){
        if(board == wincond){
            return  true;
        }else{
            return false;
        }
    }

    public void saveState(Integer[][] board){
        SavedState.add(board);
    }

    public Boolean cekMovement(int x,int y , int xMove, int yMove){
        Boolean temp = true;
        if (x+xMove <0 || x+xMove>=3)temp = false;
        if (y+yMove <0 || y+yMove>=3)temp = false;
        return  temp;
    }


    public Boolean cekRecuring(Integer[][] board,int x,int y,int xMove,int yMove){
        //xy posisi 0
        Boolean temp = false;
        if(cekMovement(x,y,xMove,yMove)==true){ //engga renegade
            //swap
            int angka=board[yMove][xMove];
            board[y+yMove][x+xMove]=board[y][x];
            board[y][x] = angka;

            if(SavedState.contains(board)){
                temp=false;
            }else{
                temp = true;
            }
        }


        return temp;
    }


    public Integer[][] swap(Integer[][] papan , int x, int y , int xMove,int yMove){
        int angka=papan[yMove][xMove];
        papan[y+yMove][x+xMove]=papan[y][x];
        papan[y][x] = angka;
        return papan;
    }

    public Boolean DFS(int x,int y,Integer[][] papan){ //xy  lokasi 0
        if(cekWin(papan)){
            return true;
        }else{
            saveState(papan);
            int ctr=0;
            Boolean done=false;
            while(ctr<4 && done==false ){
                if(cekMovement(x,y,movement[ctr][0],movement[ctr][1]) &&
                        cekRecuring(mapInt,x,y,movement[ctr][0],movement[ctr][1])){
                    papan = swap(papan,x,y,x+movement[ctr][0],x+movement[ctr][1]);
                    done = DFS(x+movement[ctr][0],y+movement[ctr][1],papan);
                    if(done ==true){
                        FinishState.add(papan);
                    }
                    ctr++;
                }
            }
            return done;
        }


    }


}