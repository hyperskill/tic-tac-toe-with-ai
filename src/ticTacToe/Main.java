package ticTacToe;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    private static char[] field = {'X', ' ', 'X', 'X', 'O', ' ', 'O', 'X', ' '};

    private static void printField() {
        System.out.println("_________");
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.print("| ");
            }
            System.out.print(field[i] + " ");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("| ");
            }
        }
        System.out.println("---------");

    }

    private static void checkField() {
        if (field[0] == field[1] && field[1] == field[2]) {
            System.out.println(field[0] + " wins");
        } else if (field[3] == field[4] && field[4] == field[5]) {
            System.out.println(field[3] + " wins");
        } else if (field[6] == field[7] && field[7] == field[8]) {
            System.out.println(field[6] + " wins");
        } else if (field[0] == field[3] && field[3] == field[6]) {
            System.out.println(field[0] + " wins");
        } else if (field[1] == field[4] && field[4] == field[7]) {
            System.out.println(field[1] + " wins");
        } else if (field[2] == field[5] && field[5] == field[8]) {
            System.out.println(field[2] + " wins");
        } else if (field[0] == field[4] && field[4] == field[8]) {
            System.out.println(field[0] + " wins");
        } else if (field[2] == field[4] && field[4] == field[6]) {
            System.out.println(field[2] + " wins");
        } else {
            boolean isEnd = true;
            for (char a : field
            ) {
                if (a == ' ') {
                    isEnd = false;
                }
            }
            if (isEnd) {
                System.out.println("Draw");
            } else {
                System.out.println("Game not finished");
            }

        }
    }

    private static void turn(int x, int y) {
        int curCell = 0;
        if (y == 1) {
            curCell = 5 + x;
        }
        if (y == 2) {
            curCell = 2 + x;
        }
        if (y == 3) {
            curCell = -1 + x;
        }
        if (field[curCell] == ' ') {
            field[curCell] = 'X';
        }
    }

    public static void main(String[] args) {
        printField();
        System.out.print("Enter the coordinates: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (x <= 3 && x >= 1 && y <= 3 && y >= 1) {
            turn(x, y);
            printField();
        } else{
            System.out.println("Incorrect input");
        }
        checkField();
    }


}