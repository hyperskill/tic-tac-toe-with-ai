package ticTacToe;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*System.out.print("X O X \n" +
                "O X O \n" +
                "X X O ");*/
        char[] arr = new char[9];
        final Random random = new Random();
        for (int i = 0; i<9; i++) {
            int a = random.nextInt(3);
            switch (a) {
                case 0:
                    arr[i] = ' ';
                    break;
                case 1:
                    arr[i] = 'X';
                    break;
                case 2:
                    arr[i] = 'O';
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i<9; i++) {
            if (((i+1)%3) == 0) {
                System.out.print(arr[i]+"\n");
            } else {
                System.out.print(arr[i]+" ");
            }
        }
    }
}