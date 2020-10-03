package com.example.aiproject;

import java.util.ArrayList;

public class State implements Cloneable{

    int[][] self;
    int[][] left;
    int[][] right;
    int[][] up;

    public int[][] getSelf() {
        return self;
    }

    public void setSelf(int[][] self) {
        this.self = self;
    }

    public int[][] getLeft() {
        return left;
    }

    public void setLeft(int[][] left) {
        this.left = left;
    }

    public int[][] getRight() {
        return right;
    }

    public void setRight(int[][] right) {
        this.right = right;
    }

    public int[][] getUp() {
        return up;
    }

    public void setUp(int[][] up) {
        this.up = up;
    }

    public int[][] getDown() {
        return down;
    }

    public void setDown(int[][] down) {
        this.down = down;
    }

    int[][] down;

    public void setChild(){

        int x=0,y=0;
        int[][] temp1 = new int[3][3];
        int[][] temp2 = new int[3][3];
        int[][] temp3 = new int[3][3];
        int[][] temp4 = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                temp1[i][j] = self[i][j];
                temp2[i][j] = self[i][j];
                temp3[i][j] = self[i][j];
                temp4[i][j] = self[i][j];
                if(self[i][j] ==0){
                    x=j;
                    y=i;
                }
            }
        }

        //up
        try{
            int angka = temp1[y-1][x];
            temp1[y-1][x] = temp1[y][x];
            temp1[y][x] = angka;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    up[i][j] = temp1[i][j];
                }
            }
        }catch (Exception ex){
            up = null;
        }

        //down
        try{
            int angka = temp2[y+1][x];
            temp2[y+1][x] = temp2[y][x];
            temp2[y][x] = angka;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    down[i][j] = temp2[i][j];
                }
            }
        }catch (Exception ex){
            down=null;
        }

        //left
        try{
            int angka = temp3[y][x-1];
            temp3[y][x-1] = temp3[y][x];
            temp3[y][x] = angka;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    left[i][j] = temp3[i][j];
                }
            }
        }catch (Exception ex){left=null;}

        //right
        try{
            int angka = temp4[y][x+1];
            temp4[y][x+1] = temp4[y][x];
            temp4[y][x] = angka;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    right[i][j] = temp4[i][j];
                }
            }
        }catch (Exception ex){
            right=null;
        }
//
//        if(up!=null){
//            System.out.println("child Up");
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(up[i][j]+"-");
//                }
//                System.out.println();
//            }
//
//        }
//
//        if(right!=null){
//
//            System.out.println("child right");
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(right[i][j]+"-");
//                }
//                System.out.println();
//            }
//        }
//
//        if(down!=null){
//            System.out.println("child down");
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(down[i][j]+"-");
//                }
//                System.out.println();
//            }
//
//        }
//
//       if(left!=null){
//
//            System.out.println("child left");
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(left[i][j]+"-");
//                }
//                System.out.println();
//            }
//
//       }
//




    }
    public State(int[][] arr) {
        left  = new int[3][3];
        right = new int[3][3];
        up    = new int[3][3];
        down  = new int[3][3];
        self  = new int[3][3];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                self[i][j] = arr[i][j];
            }
        }



    }
}
