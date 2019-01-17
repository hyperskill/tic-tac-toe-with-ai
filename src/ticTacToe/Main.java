package ticTacToe;


import java.util.Random;

public class Main {
    public static void main(String[] args) {
        char[][] fieldValues = init();
        draw(fieldValues);
        System.out.println(checkState(fieldValues));

    }

    private static char[][] init() {
        Random random = new Random();
        char[][] fieldValues = new char[3][3];
        String values = "XO ";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = random.nextInt(3);
                fieldValues[i][j] = values.charAt(index);
            }
        }
        return  fieldValues;
    }

    private static String checkState(char[][] fieldValues) {

        int col = 0;
        int row = 0;

        for (int i = 0; i < 3; i++) {

            //row check{
            if (fieldValues[i][0] == fieldValues[i][1] && fieldValues[i][0] == fieldValues[i][2]) {
                row++;
            }

            if (row == 1) {
                if (fieldValues[i][0] == 'X') {
                    return "X wins.";
                } else {
                    return "O wins.";
                }
            }

            //col check
            if (fieldValues[0][i] == fieldValues[1][i] && fieldValues[0][i] == fieldValues[2][i]) {
                col++;
            }

            if (col == 1) {
                if (fieldValues[0][i] == 'X') {
                    return "X wins.";
                } else {
                    return "O wins.";
                }
            }
        }

        //diagonal check
        if (fieldValues[0][0] == fieldValues[1][1] && fieldValues[0][0] == fieldValues[2][2]) {
            if (fieldValues[0][0] == 'X') {
                return "X wins.";
            } else {
                return "O wins.";
            }
        }
        if (fieldValues[0][2] == fieldValues[1][1] && fieldValues[0][2] == fieldValues[2][0]) {
            if (fieldValues[0][2] == 'X') {
                return "X wins.";
            } else {
                return "O wins.";
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fieldValues[i][j] == ' ') {
                    return "Game not finished.";
                }
            }
        }

        return "Draw.";
    }

    private static void draw(char[][] fieldValues) {
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