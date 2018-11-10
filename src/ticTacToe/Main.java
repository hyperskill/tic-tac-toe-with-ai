package ticTacToe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] field = new char[3][3];

    private static void initField() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(field[i], ' ');
        }
    }

    private static boolean isGameFinished() {
        int[][][] rows = {
                {{0, 0}, {1, 1}, {2, 2}},
                {{0, 2}, {1, 1}, {2, 0}},
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}}
        };
        boolean chanceToWin = false;
        boolean gameFinished = false;

        for (int i = 0; i < 8; i++) {
            int cx = 0;
            int co = 0;
            int ce = 0;
            for (int j = 0; j < 3; j++) {
                int x = rows[i][j][0];
                int y = rows[i][j][1];
                if (field[x][y] == 'X') {
                    cx++;
                } else if (field[x][y] == 'O') {
                    co++;
                } else {
                    ce++;
                }
            }
            if (cx == 3) {
                gameFinished = true;
                System.out.println("X wins");
                break;
            } else if (co == 3) {
                gameFinished = true;
                System.out.println("O wins");
                break;
            } else if (ce + cx == 3 || ce + co == 3) {
                chanceToWin = true;
            }
        }
        if (!gameFinished && !chanceToWin) {
            System.out.println("Draw");
            gameFinished = true;
        }
        return gameFinished;
    }

    private static void printGamesState() {
        System.out.println(" ------- ");
        System.out.println(new char[] {'|', ' ', field[2][0], ' ', field[2][1], ' ', field[2][2], ' ','|'});
        System.out.println(new char[] {'|', ' ', field[1][0], ' ', field[1][1], ' ', field[1][2], ' ','|'});
        System.out.println(new char[] {'|', ' ', field[0][0], ' ', field[0][1], ' ', field[0][2], ' ','|'});
        System.out.println(" ------- ");
    }

    private static void userMove(char unit) {
        while (true) {
            System.out.print("Enter the coordinates: ");

            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                // Read end of line
                scanner.nextLine();

                if (x > 3 || y > 3 || x < 1 || y < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[y - 1][x - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field[y - 1][x - 1] = unit;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!on");
            }
        }
    }

    private static void makeEasyLevelMove(char unit) {
        System.out.println("Making move level \"easy\"");
        int c = 0;
        int[][] freeCells = new int[9][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    freeCells[c] = new int[] {i, j};
                    c++;
                }
            }
        }
        if (c > 0) {
            int move = new Random().nextInt(c);
            field[freeCells[move][0]][freeCells[move][1]] = unit;
        }
    }

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit) {
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().split(" ");

            switch (input[0]) {
                case "exit":
                    isExit = true;
                    break;
                case "start":
                    if (input.length >= 3 && isValidUser(input[1]) && isValidUser(input[2])) {
                        initField();
                        play(input[1], input[2]);
                        break;
                    }
                default:
                    System.out.println("Bad parameters!");
            }
        }
    }

    private static boolean isValidUser(String user) {
        return user.equals("user") || user.equals("easy");
    }

    private static void play(String ux, String uo) {
        boolean gameFinished = false;
        boolean isXMove = true;

        printGamesState();

        while (!gameFinished) {
            String currentUser = isXMove ? ux : uo;
            switch (currentUser) {
                case "user":
                    userMove(isXMove ? 'X' : 'O');
                    break;
                case "easy":
                case "medium":
                case "hard":
                    makeEasyLevelMove(isXMove ? 'X' : 'O');
                    break;
                default:
            }
            isXMove = !isXMove;

            printGamesState();
            gameFinished = isGameFinished();
        }
    }
}