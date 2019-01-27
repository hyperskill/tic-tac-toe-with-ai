package ticTacToe;

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 3;
        char[][] field = {{'O',' ', 'O'},{'X','X','O'},{' ', 'X', 'X'}};
        for(int i =0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}