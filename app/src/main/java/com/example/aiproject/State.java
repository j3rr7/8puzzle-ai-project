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
        Integer[][] temp1 = new Integer[3][3];
        Integer[][] temp2 = new Integer[3][3];
        Integer[][] temp3 = new Integer[3][3];
        Integer[][] temp4 = new Integer[3][3];

        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                temp1[i][j] = now[i][j];
                temp2[i][j] = now[i][j];
                temp3[i][j] = now[i][j];
                temp4[i][j] = now[i][j];
            }
        }

        System.out.println("now awal");
        for (int i=0;i<3;i++){
            System.out.println(now[i][0]+"-"+now[i][1]+"-"+now[i][2]);
        }




        try {
            int angka = temp1[y-1][x];
            temp1[y-1][x]=temp1[y][x];
            temp1[y][x]=angka;
            System.out.println("Up child is available");
            up = temp1;
            for (int i=0;i<3;i++){
                    System.out.println(up[i][0]+"-"+up[i][1]+"-"+up[i][2]);
            }
            // lek dee gagal , up e tetep null
        }catch (Exception e){
            up =null;
            //jogo"
        }




        //movDown
        try {
            int angka = temp2[y+1][x];
            temp2[y+1][x]=temp2[y][x];
            temp2[y][x]=angka;
            System.out.println("Down child is available");
            down = temp2;
            for (int i=0;i<3;i++){
                System.out.println(down[i][0]+"-"+down[i][1]+"-"+down[i][2]);
            }
        }catch (Exception e){
            down = null;
        }

        //movLeft
        try {
            int angka = temp3[y][x-1];
            temp3[y][x-1]=temp3[y][x];
            temp3[y][x]=angka;
            System.out.println("Left child is available");
            left = temp3;

            for (int i=0;i<3;i++){
                System.out.println(left[i][0]+"-"+left[i][1]+"-"+left[i][2]);
            }

        }catch (Exception e){
            left = null;
        }

        //moRight
        temp4 = now;
        try {
            int angka = temp4[y][x+1];
            temp4[y][x+1]=temp4[y][x];
            temp4[y][x]=angka;
            System.out.println("Right child is available");
            right = temp4;

            for (int i=0;i<3;i++){
                System.out.println(right[i][0]+"-"+right[i][1]+"-"+right[i][2]);
            }

        }catch (Exception e){
            right = null;
        }

        System.out.println("now e akhir");
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
