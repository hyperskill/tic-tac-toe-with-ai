package ticTacToe;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameBoard game = new GameBoard();
        int row;
        int col;
        do {
            System.out.print("Enter the coordinates: ");
            row = sc.nextInt() - 1;
            col = sc.nextInt() - 1;
        } while (game.turn(row, col));
    }
}
