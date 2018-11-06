package ticTacToe;

import com.sun.javafx.binding.SelectBinding;

import java.util.Random;

public class Main {

    static void field(){
        String firstPlayer = "X ";
        String secondPlayer = "O ";
        int step = 0;
        for(int i =0; i<3; i++){
            for(int b =0; b<3; b++){
                if(step%2==0){
                    System.out.print(firstPlayer);
                    ++step;
                } else {
                    System.out.print(secondPlayer);
                    ++step;
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Main.field();
    }
}