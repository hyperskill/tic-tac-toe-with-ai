package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner scn = new Scanner(System.in);
    public static char userSymbol;
    public static boolean game = true;
    public static char[][] fields = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        System.out.print("Enter ur symbol (X or O)");
        userSymbol = scn.next().charAt(0);
        String winner = "";
        while (game) {
            showFields(fields);
            enterCoords(fields);
            winner = checkWinner(fields);
            if (game) {
                enterCoordsBot(fields);
            }
            showFields(fields);
        }
        System.out.println(winner);
    }

    public static void enterCoordsBot(char[][] fields) {
        Random r = new Random();
        while (true) {
            int xR = r.nextInt(3);
            int yR = r.nextInt(3);
            if (fields[xR][yR] == ' ') {
                if (userSymbol == 'X') {
                    fields[xR][yR] = 'O';
                } else {
                    fields[xR][yR] = 'X';
                }
                break;
            }
        }
    }

    public static void enterCoords(char[][] fields) {
        System.out.print("Enter the coordinates: ");
        while (true) {
            int x = scn.nextInt();
            int y = scn.nextInt();
            if (fields[x][y] == ' ') {
                fields[x][y] = userSymbol;
                break;
            } else {
                System.out.println("Enter other coords");
            }
        }
    }

    public static String checkWinner(char[][] fields) {
        String resultOfGame = "";
        boolean draw = true;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j] != ' ') {
                    draw = false;
                }
            }
        }
        if (draw) {
            game = false;
            resultOfGame = "Draw";
        }
        for (int i = 0; i < fields.length; i++) {
            if (checkFields(fields[i][0], fields[i][1], fields[i][0]) ||
                    checkFields(fields[0][i], fields[1][i], fields[2][i])) {
                resultOfGame = fields[i][0] + " wins";
                game = false;
            }
        }
        if (checkFields(fields[0][0], fields[1][1], fields[2][2]) ||
                checkFields(fields[0][2], fields[1][1], fields[2][0])) {
            resultOfGame = fields[1][1] + " wins";
            game = false;
        }
        return resultOfGame;
    }

    private static boolean checkFields(char... fields) {
        for (int i = 1; i < fields.length; i++) {
            if (fields[i - 1] != fields[i] || fields[i - 1] == ' ' && fields[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void showFields(char[][] fields) {
        System.out.println("---------");
        for (int i = 0; i < fields.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < fields.length; j++) {
                System.out.print(fields[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
