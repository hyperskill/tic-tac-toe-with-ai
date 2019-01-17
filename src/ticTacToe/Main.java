package ticTacToe;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        char[][] fieldValues = new char[3][3];
        String values = "XO ";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = random.nextInt(3);
                fieldValues[i][j] = values.charAt(index);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j < 2) {
                    System.out.print(fieldValues[i][j] + " ");
                } else {
                    System.out.print(fieldValues[i][j]);
                }
            }
            System.out.println();
        }
    }
}