package ticTacToe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public  final static char O = 'O';
    public  final static char X = 'X';
    public  final static char EMPTY = ' ';
    public final static int ROW = 3;
    public final static int COL = ROW;
    public final static int SIZE = ROW * COL;
    public final static String STATE_NOT_FINISHED = "Game not finished";
    public final static String STATE_DRAW = "Draw";
    public final static String STATE_WIN_X = "X wins";
    public final static String STATE_WIN_O = "O wins";
    private final static Random rnd = new Random();
    private final static String BAD_PARAMS = "Bad parameters!";

    static Player player1;
    static Player player2;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Input command:");
            String input = scanner.nextLine();
            if (input.equals("exit")){
                break;
            }
            boolean isInited = initGame(input.split(" "));
            if(isInited){
                char[][] matrix = new char[Main.COL][Main.ROW];
                fill(matrix, Main.EMPTY);
                printMatrix(matrix);

                while(detectState(matrix).equals(Main.STATE_NOT_FINISHED)){
                    while(!player1.request(matrix));
                    printMatrix(matrix);
                    if(!detectState(matrix).equals(Main.STATE_NOT_FINISHED)) {
                        break;
                    }
                    while(!player2.request(matrix));
                    printMatrix(matrix);
                }
                System.out.print(detectState(matrix));
                System.out.println("\n");
            }
        }

    }
    private static boolean initGame(String[] args){
        if(args.length == 3){
            if(args[0].equals("start")){
                player1 = createPlayer(args[1], X);
                player2 = createPlayer(args[2], O);
                return true;
            }
        } else{
            System.out.println(Main.BAD_PARAMS);
        }
        return false;
    }
    private static Player createPlayer(String type, char symbol){
        type = type.toLowerCase();
        switch (type){
            case "easy":
            case "medium":
            case "hard":
                return new AiPlayer(symbol, type);
            case "user":
                return new UserPlayer(symbol);
            default:
                return new UserPlayer(symbol);
        }
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
    public static String detectState(char[][] matrix){
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
            return Main.STATE_WIN_O;
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