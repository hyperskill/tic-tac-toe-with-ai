package ticTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] arr = {
            {'X', ' ', 'X'},
            {' ', 'O', ' '},
            {' ', ' ', ' '}
        };

        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print('|');
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.print(" ");
            System.out.println('|');
        }

        System.out.println("---------");

        System.out.print("Enter the coordinates: ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        if (x == 1) x = 2;
        if (x == 2) x = 1;
        if (x == 3) x = 0;

        if (y == 1) y = 2;
        if (y == 2) y = 1;
        if (y == 3) y = 0;

        arr[x][y ] = 'X';

        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print('|');
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.print(" ");
            System.out.println('|');
        }

        System.out.println("---------");
    }
}
