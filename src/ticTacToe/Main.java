package ticTacToe;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        char[][] field = new char[3][3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (random.nextInt(3)) {
                    case 0:
                        field[i][j] = ' ';
                        break;
                    case 1:
                        field[i][j] = 'X';
                        break;
                    case 2:
                        field[i][j] = 'O';
                        break;
                }
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }
}
