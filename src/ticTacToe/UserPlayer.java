package ticTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserPlayer extends Player {
    public UserPlayer(char symbol) {
        super(symbol);
    }

    @Override
    boolean request(char[][] matrix) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        System.out.print("Enter the coordinates:");
        int counter = 0;
        try{
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            if(x >= 0 && y >= 0 && x <= Main.COL && y <= Main.ROW){
                y = Main.ROW - y - 1;
                return set(matrix, x, y, symbol);
            } else{
                System.out.println("Coordinates should be from 1 to 3!");
            }
            return false;
        } catch (InputMismatchException e){
            System.out.println("You should enter numbers!");
            return false;
        }
    }
}
