package ticTacToe;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[][] values = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = fillValue();
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s ", values[i][j]);
            }
            System.out.println();
        }

    }

    private static String fillValue() {
        int randomInt = new Random().nextInt(10);
        if (randomInt % 3 == 0) {
            return "X";
        } else if (randomInt % 2 == 0) {
            return "O";
        } else {
            return " ";
        }
    }
}