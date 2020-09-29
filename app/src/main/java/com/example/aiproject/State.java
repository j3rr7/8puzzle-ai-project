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




    public void setChild(){

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
        //moveUp
        Integer[][] temp = now;
        try {
            int angka = temp[y-1][x];
            temp[y-1][x]=temp[y][x];
            temp[y][x]=angka;
            System.out.println("Up child is available");
            up = temp;
            for (int i=0;i<3;i++){
                    System.out.println(up[i][0]+"-"+up[i][1]+"-"+up[i][2]);
            }
            // lek dee gagal , up e tetep null
        }catch (Exception e){
            up =null;
            //jogo"
        }




        //movDown
         temp = now;
        try {
            int angka = temp[y+1][x];
            temp[y+1][x]=temp[y][x];
            temp[y][x]=angka;
            System.out.println("Down child is available");
            down = temp;

            for (int i=0;i<3;i++){
                System.out.println(down[i][0]+"-"+down[i][1]+"-"+down[i][2]);
            }

        }catch (Exception e){
            down = null;
        }

        //movLeft
        temp = now;
        try {
            int angka = temp[y][x-1];
            temp[y][x-1]=temp[y][x];
            temp[y][x]=angka;
            System.out.println("Left child is available");
            left = temp;

            for (int i=0;i<3;i++){
                System.out.println(left[i][0]+"-"+left[i][1]+"-"+left[i][2]);
            }

        }catch (Exception e){
            left = null;
        }

        //moRight
        temp = now;
        try {
            int angka = temp[y][x+1];
            temp[y][x+1]=temp[y][x];
            temp[y][x]=angka;
            System.out.println("Right child is available");
            right = temp;

            for (int i=0;i<3;i++){
                System.out.println(right[i][0]+"-"+right[i][1]+"-"+right[i][2]);
            }

        }catch (Exception e){
            right = null;
        }

        System.out.println("now e");
        for (int i=0;i<3;i++){
            System.out.println(now[i][0]+"-"+now[i][1]+"-"+now[i][2]);
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
