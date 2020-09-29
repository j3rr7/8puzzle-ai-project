package com.example.aiproject;

import java.util.ArrayList;

public class State {

    Integer[][] now,down,up,left,right;
    public Integer[][] getNow() {
        return now;
    }

    public void setNow(Integer[][] now) {
        this.now = now;
    }

    public Integer[][] getDown() {
        return down;
    }

    public void setDown(Integer[][] down) {
        this.down = down;
    }

    public Integer[][] getUp() {
        return up;
    }

    public void setUp(Integer[][] up) {
        this.up = up;
    }

    public Integer[][] getLeft() {
        return left;
    }

    public void setLeft(Integer[][] left) {
        this.left = left;
    }

    public Integer[][] getRight() {
        return right;
    }

    public void setRight(Integer[][] right) {
        this.right = right;
    }




    public void setChild(ArrayList<Integer[][]> recur){

        int x=0,y=0;
        //buat nyari posisi e angka 0
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(now[i][j]==0){
                    x=j;
                    y=i;
                    break;
                }
            }
        }

        // ben ga ngganti value dari state now
        Integer[][] temp = now;
        //moveUp
        try {
            int angka = temp[y-1][x];
            temp[y-1][x]=temp[y][x];
            temp[y][x]=angka;

            up = temp;
            // lek dee gagal , up e tetep null
        }catch (Exception e){
            up =null;
            //jogo"
        }

         temp = now;
        //movDown
        try {
            int angka = temp[y+1][x];
            temp[y+1][x]=temp[y][x];
            temp[y][x]=angka;

            down = temp;
        }catch (Exception e){
            down = null;
        }

        temp = now;
        //movLeft
        try {
            int angka = temp[y][x-1];
            temp[y][x-1]=temp[y][x];
            temp[y][x]=angka;

            left = temp;
        }catch (Exception e){
            left = null;
        }

        temp = now;
        //moRight
        try {
            int angka = temp[y][x+1];
            temp[y][x+1]=temp[y][x];
            temp[y][x]=angka;

            right = temp;
        }catch (Exception e){
            right = null;
        }

        // ngecek child e iki recuring ta gak , kalo ternyata dee recur ya dinull in
        if(up!=null && recur.contains(up)){
            up=null;
        }
        if(down!=null && recur.contains(down)){
            up=null;
        }
        if(left!=null && recur.contains(left)){
            left=null;
        }
        if(right!=null && recur.contains(right)){
            right=null;
        }








    }

    public State(Integer[][] now) {
        this.now = now;
        this.down = null;
        this.up = null;
        this.left = null;
        this.right = null;
    }

}
