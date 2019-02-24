package ticTacToe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
//        randomFill(matrix);
        fill(matrix, Main.EMPTY);
        printMatrix(matrix);
        char aiType = Main.O;
        char userType = Main.X;
        while(detectState(matrix).equals(Main.STATE_NOT_FINISHED)){
            while(!userRequest(matrix, userType));
            printMatrix(matrix);
            if(!detectState(matrix).equals(Main.STATE_NOT_FINISHED)) {
                break;
            }
            aiRequest(matrix, aiType);
            printMatrix(matrix);
        }
        System.out.print(detectState(matrix));
    }
    private static void printMatrix(char[][] matrix){
        System.out.println("---------");
        for (int y = 0; y < Main.ROW; y++) {
            System.out.print("| ");
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[x][y]);
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }
    private static void randomFill(char[][] matrix){
        matrix[0] = new char[]{' ', 'O', 'X'};
        matrix[1] = new char[]{'X', ' ', 'O'};
        matrix[2] = new char[]{' ', ' ', ' '};
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
    private static void fill(char[][] matrix, char type){
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                matrix[x][y] = type;
            }
        }


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

    private static boolean userRequest(char[][] matrix, char type){
        Scanner sc = new Scanner(System.in);
        int x, y;
        System.out.print("Enter the coordinates:");
        int counter = 0;
        try{
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            if(x >= 0 && y >= 0 && x <= Main.COL && y <= Main.ROW){
                y = Main.ROW - y - 1;
                return set(matrix, x, y, type);
            } else{
                System.out.println("Coordinates should be from 1 to 3!");
            }
            return false;
        } catch (InputMismatchException e){
            System.out.println("You should enter numbers!");
            return false;
        }
    }
    private static boolean aiRequest(char[][] matrix, char type){
        int x, y;
        int[][] emptyCells = getEmptyCoords(matrix);
        Random rnd = new Random();
        if(emptyCells .length > 0){
            int[] coord = emptyCells[rnd.nextInt(emptyCells.length)];
            boolean res = set(matrix, coord[0], coord[1], type);
            if (res){
                System.out.println("Making move level \"easy\"");
                return true;
            }
        }
        return false;
    }

    private static boolean set(char[][]matrix, int x, int y, char el){
        if(matrix[x][y] == Main.EMPTY){
            matrix[x][y] = el;
            return true;
        }else{
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    private static int[][] getEmptyCoords(char[][] matrix){
        int[][] arr = new int[Main.SIZE][2];
        int counter = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if(matrix[x][y] == Main.EMPTY){
                    arr[counter++] = new int[]{x,y};
                }
            }
        }
        //clear
        counter--;
        int[][] resArr = new int[counter][2];
        for (int i = 0; i < counter; i++) {
            resArr[i] = arr[i];
        }
        return resArr;
    }
}