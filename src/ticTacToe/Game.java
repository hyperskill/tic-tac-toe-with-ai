package ticTacToe;

import java.util.Random;

public class Game {
    private static int[][] fieldValues = new int[3][3];

    public static int getFieldValue(int x, int y) {
        return fieldValues[x][y];
    }

    public static void setFieldValue(int x, int y, int fieldValue) {
        fieldValues[x][y] = fieldValue;
    }

    public static void fillFieldRand() {
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                Random random = new Random();
                int rand = random.nextInt(3);
                setFieldValue(i,j,rand);
            }
        }
    }
}
