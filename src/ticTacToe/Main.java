package ticTacToe;

import java.util.Random;

public class Main {
    private static char X = 'X';
    private static char O = 'O';
    private static char EMPTY = ' ';
    private final static int ROW = 3;
    private final static int COL = ROW;
    private final static int SIZE = ROW * COL;
    private final static String STATE_NOT_FINISHED = "Game not finished";
    private final static String STATE_DRAW = "Draw";
    private final static String STATE_WIN_X = "X wins";
    private final static String STATE_WIN_O = "O wins";
    private final static Random rnd = new Random();


    public static void main(String[] args) {
        char[][] matrix = new char[Main.COL][Main.ROW];
        randomFill(matrix);
        printMatrix(matrix);
        System.out.print(detectState(matrix));
    }
    private static void printMatrix(char[][] matrix){
        for (int y = 0; y < Main.ROW; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[x][y]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    private static void randomFill(char[][] matrix){
        matrix[0] = new char[]{'O', 'O', 'X'};
        matrix[1] = new char[]{'X', 'X', 'O'};
        matrix[2] = new char[]{'X', 'X', 'O'};
        /*
        int stepsAmount= rnd.nextInt(Main.SIZE);
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                int step = x * Main.ROW + y;
                if (stepsAmount > step) {
                    matrix[x][y] = step % 2 == 0 ? Main.X : Main.O;
                }
            }
        }
        */

    }
    private static String detectState(char[][] matrix){
        for (int i = 0; i < Main.ROW; i++) {
            if(isRowFull(matrix, i, Main.X)){
               return Main.STATE_WIN_X;
            }
            if(isRowFull(matrix, i, Main.O)){
                return Main.STATE_WIN_O;
            }
        }
        for (int i = 0; i < Main.COL; i++) {
            if(isColFull(matrix, i, Main.X)){
                return Main.STATE_WIN_X;
            }
            if(isColFull(matrix, i, Main.O)){
                return Main.STATE_WIN_O;
            }
        }

        if(isDiag1Full(matrix, Main.X) || isDiag2Full(matrix, Main.X)){
            return Main.STATE_WIN_X;
        }
        if(isDiag1Full(matrix, Main.O) || isDiag2Full(matrix, Main.O)){
            return Main.STATE_WIN_X;
        }

        int emptyCell = 0;
        for (int i = 0; i < matrix.length; i++) {
            emptyCell += count(matrix[i], Main.EMPTY);
        }
        if(emptyCell  == 0){
            return Main.STATE_DRAW;
        }
        return Main.STATE_NOT_FINISHED;
    }

    private static boolean isRowFull(char[][] matrix, int rowNumber, char el){
        int sameCounter = 0;
        char[] row = new char[Main.COL];
        for (int i = 0; i < row.length; i++) {
            row[i] = matrix[i][rowNumber];
        }
        return (count(row, el) == Main.COL);
    }

    private static boolean isColFull(char[][] matrix, int col, char el){
        return (count(matrix[col], el) == Main.ROW);
    }

    private static boolean isDiag1Full(char[][] matrix, char el){
        char[] row = new char[Main.COL];
        for (int i = 0; i < Main.COL; i++) {
            row[i] = matrix[i][i];
        }
        return (count(row, el) == Main.COL);
    }

    private static boolean isDiag2Full(char[][] matrix, char el){
        char[] row = new char[]{matrix[2][0], matrix[1][1], matrix[0][2]};

        return (count(row, el) == Main.COL);
    }

    private static int count(char[] arr, char el){
        int sameCounter = 0;
        for (char cell:arr) {
            sameCounter = cell == el ? sameCounter+1 : sameCounter;
        }
        return sameCounter;
    }
}