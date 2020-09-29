package com.example.aiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    /*
    * Initialize Variabels to be used
    * */
    protected Button b0,b1,b2,b3,b4,b5,b6,b7,b8,bSolve,bReset;
    protected Integer[] numbers = {0,1,2,3,4,5,6,7,8};
    protected Integer[][] boards = {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}}; // board = posisi number
    Integer[][] win = {{1,2,3},{4,5,6},{7,8,0}};
    protected Button[][] buttons = new Button[3][3];

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


        buttons[0][0] = b0;
        buttons[0][1] = b1;
        buttons[0][2] = b2;
        buttons[1][0] = b3;
        buttons[1][1] = b4;
        buttons[1][2] = b5;
        buttons[2][0] = b6;
        buttons[2][1] = b7;
        buttons[2][2] = b8;

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





    Queue<State>queue = new LinkedList<State>();
    Integer[][] root = new Integer[3][3];
    public ArrayList<Integer[][]> recur = new ArrayList<>();


    public void BFS(){

        String angka="";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                angka+=buttons[i][j].getText().toString()+" - ";
                try{
                    root[i][j]=Integer.parseInt(buttons[i][j].getText().toString());

                }catch (Exception  ex){
                    root[i][j]=0;
                }
            }
        }
        System.out.println("angka : "+angka);


        State tState;  //temp
        Integer[][] tArr = new Integer[3][3];
        State rootState = new State(root);
        queue.add(rootState);
    int step = 0;
    if(queue.isEmpty()){
        System.out.println("asd");
    }else{
        System.out.println("sss");
    }

    while(!queue.isEmpty() && step<5 ){
        step++;
        System.out.println("Step : "+step);
        tState = queue.remove();

        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tArr[i][j] = tState.getNow()[i][j];
            }
        }

        if(tArr == win){
            System.out.println(" -----win---------");
            System.out.println(" -----win---------");
            System.out.println(" -----win---------");
            System.out.println(" -----win---------");
            System.out.println(" -----win---------");
            System.out.println(" -----win---------");
        }else{
            // karna ga win , state now dimasukin list e recuring
            recur.add(tState.getNow());


            //ndapetin child dari state sekarang buat dimasukno ke eueue
            tState.setChild();
            tState.setNow(tArr);
            System.out.println("now e nde Main");
            for (int i=0;i<3;i++){
                System.out.println(tState.getNow()[i][0]+"-"+tState.getNow()[i][1]+"-"+tState.getNow()[i][2]);
            }

//             cek renagade di class
            if(tState.getLeft()!=null && cekContain(recur,tState.getLeft()) ){
                tState.setLeft(null);

            } else if(tState.getLeft()!=null && !cekContain(recur,tState.getLeft())){
                recur.add(tState.getLeft());
            }


            if(tState.getRight()!=null && cekContain(recur,tState.getRight()) ){
                tState.setRight(null);

            } else if(tState.getRight()!=null && !cekContain(recur,tState.getRight())){

                recur.add(tState.getRight());
            }


            if(tState.getUp()!=null && cekContain(recur,tState.getUp())  ){
                tState.setUp(null);

            } else if(tState.getUp()!=null && !cekContain(recur,tState.getUp())){

                recur.add(tState.getUp());
            }

            if(tState.getDown()!=null && cekContain(recur,tState.getDown())  ){
                tState.setDown(null);

            } else if( tState.getDown()!=null && !cekContain(recur,tState.getDown())){

                recur.add(tState.getDown());
            }


//---------------------------------------------------------------------
            if(tState.getDown()!=null){
                queue.add(new State(tState.getDown()));

            }
            if(tState.getUp()!=null){
                queue.add(new State(tState.getUp()));

            }
            if(tState.getLeft()!=null){
                queue.add(new State(tState.getLeft()));

            }
            if(tState.getRight()!=null){
                queue.add(new State(tState.getRight()));

            }

        }
    }

        System.out.println("exit");
    }

    public Boolean cekContain(ArrayList<Integer[][]> list , Integer[][] state){
       // kalo false ga contain
        Boolean cek=false;
        for(int i=0;i<list.size();i++){

            if(list.get(i) == state){
                cek=true;
                break;
            }
        }


        return cek;
    }


    public void Solve()
    {
        /*
         * To Be Added Solver function
         * BFS
         * DFS
         * (+)
         * */
        int[][] temp1 = new int[3][3];
        int[][] temp2 = new int[3][3];
        for(int i=0 ;i<3;i++){
            for(int j=0;j<3;j++){
                temp1[i][j]=i+j;
                temp2[i][j]=i+j;
            }
        }
        ArrayList<int[][]>list = new ArrayList<>();
        list.add(temp1);

        if(list.contains(temp1)){
            System.out.println("ada");
        }
        System.out.println(list.size());




//        BFS();


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