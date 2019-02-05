package ticTacToe;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static char[][] field;
    private static int turn = 1;
    private static int first = 0;
    private static int second = 0;
    private static boolean isGame = false;
    static private Scanner scanner = new Scanner(System.in);
    private static int turns = 0;

    public static void main(String[] args) {

//        while (!isGame){
//
//            try {
//                System.out.println("1 - TWO PLAYERS MODE\n2 - EASY PLAYER&AI MODE\n3 - HARD PLAYER&AI MODE\n4 - CUSTOM MODE\n5 - QUIT");
//                int i = scanner.nextInt();
//
//                switch (i){
//                    case 1:field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; mode = 1;print(); isGame = true; game(); break;
//                    case 2:
//                        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
//                        mode = 2;
//                        System.out.println("SELECT (X/O): ");
//                        if (scanner.next().equalsIgnoreCase("o")) isFirst = false;
//                        print();
//                        isGame = true;
//                        game();
//
//                        break;
//                    case 3:
//                        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
//                        mode = 3;
//                        print();
//                        System.out.println("SELECT (X/O): ");
//                        isGame = true;
////                        hardGame();
//                        break;
//                    case 4:
//
//                    case 5:
//                        System.exit(0);
//                        break;
//                    default: throw new IOException();
//                }
//            } catch (IOException ignored){
//                System.out.println("I/O error, try again...\n");
//            }
//        }
        while (!isGame) {
            try {
                first = 0;
                second = 0;
                System.out.print("TicTacToe > ");
                String[] line = scanner.nextLine().toLowerCase().split(" ");
                if (line.length == 0 || line.length > 2) {
                    throw new IOException();
                }
                for (int i = 0; i < line.length; i++) {
                    switch (line[i]) {
                        case "p":
                        case "player":

                            if (i == 0) first = 1;
                            else if (i == 1) second = 1;
                            else throw new IOException("1");

                            break;
                        case "e":
                        case "easy":

                            if (i == 0) first = 2;
                            else if (i == 1) second = 2;
                            else throw new IOException("2");

                            break;
                        case "h":
                        case "hard":

                            if (i == 0) first = 3;
                            else if (i == 1) second = 3;
                            else throw new IOException("3");

                            break;
                        case "q":
                        case "exit":
                        case "quit":
                            System.exit(0);
                            break;
                        default:
                            throw new IOException("default");
                    }
                }
                if (first != 0 && second != 0) {
                    isGame = true;
                    field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
                    print();
                    game();
                }
            } catch (IOException ignored) {

            }
        }
    }

    private static boolean shoot(int i, int j) {
        if (turn == 1 && field[i][j] == ' ') {
            field[i][j] = 'X';
            turn = 2;
            turns++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (turn == 2 && field[i][j] == ' ') {
            field[i][j] = 'O';
            turn = 1;
            turns++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private static void game() {
        Random r = new Random();
        while (isGame) {
            if (turn == 1 && first == 1 || turn == 2 && second == 1) {
                System.out.println("ENTER COORDINATES: ");
                String a1 = scanner.next();
                String b1 = scanner.next();

                try {
                    int a = Integer.parseInt(a1);
                    int b = Integer.parseInt(b1);
                    if (a >= 0 && a < 3 && b < 3 && b >= 0)
                        shoot(a, b);
                    else System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
                } catch (Exception e) {
                    System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
                }

            } else {
                if (turn == 1 && first == 2 || turn == 2 && second == 2) {
                    boolean ok = false;
                    while (!ok) {
                        int a = r.nextInt(3);
                        int b = r.nextInt(3);
                        ok = shoot(a, b);
                    }
                }
            }
            print();
        }
    }


    private static void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + ((j != 2) ? " | " : ""));
            }
            System.out.println(i != 2 ? "\n_________" : "\n");
        }
        String gs = getGameStatus();
        System.out.println(gs);
        if (gs.contains("WINS")) {
            isGame = false;
        }
        System.out.println();
    }


    private static String getGameStatus() {

        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != ' ') {
                return field[i][0] == 'X' ? "X WINS!" : "O WINS!";
            } else if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != ' ') {
                return field[0][i] == 'X' ? "X WINS!" : "O WINS!";
            }
        }
        if (field[1][1] == field[0][0] && field[1][1] == field[2][2] || field[1][1] == field[0][2] && field[1][1] == field[2][0]) {
            return field[1][1] == 'X' ? "X WINS!" : field[1][1] == 'O' ? "O WINS!" : "GAME IS RUNNING! " + turn + " is playing...";
        } else if (turns == 9) {
            isGame = false;
            return "DROW";
        } else return "GAME IS RUNNING! " + turn + " is playing...";


    }
}

