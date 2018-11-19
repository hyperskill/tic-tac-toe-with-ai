package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] fields = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'}
        };
        showFields(fields);
        System.out.println(checkWinner(fields));
    }

    public static String checkWinner(char[][] fields) {
        String resultOfGame = "Draw";
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j] == ' ') {
                    resultOfGame = "Game not finished";
                }
            }
        }
        for (int i = 0; i < fields.length; i++) {
            if (checkFields(fields[i][0], fields[i][1], fields[i][0]) ||
                    checkFields(fields[0][i], fields[1][i], fields[2][i])) {
                resultOfGame = fields[i][0] + " wins";
            }
        }
        if (checkFields(fields[0][0], fields[1][1], fields[2][2]) ||
                checkFields(fields[0][2], fields[1][1], fields[2][0])) {
            resultOfGame = fields[1][1] + " wins";
        }
        return resultOfGame;
    }

    private static boolean checkFields(char... fields) {
        for (int i = 1; i < fields.length; i++) {
            if (fields[i - 1] != fields[i]) {
                return false;
            }
        }
        return true;
    }

    public static void showFields(char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                System.out.print(fields[i][j] + " ");
            }
            System.out.println();
        }
    }
}
