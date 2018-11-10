package ticTacToe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static void checkGameState(char[][] field) {
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
        boolean gameFinished = false;
        boolean chanceToWin = false;
        for (int i = 0; i < 8; i++) {
            int cx = 0;
            int co = 0;
            int ce = 0;
            for (int j = 0; j < 3; j ++) {
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
        if (!gameFinished) {
            if (chanceToWin) {
                System.out.println("Game not finished");
            } else {
                System.out.println("Draw");
            }
        }
    }

    private static void printGamesState(char[][] field) {
        System.out.println(" ------- ");
        System.out.println(new char[] {'|', ' ', field[2][0], ' ', field[2][1], ' ', field[2][2], ' ','|'});
        System.out.println(new char[] {'|', ' ', field[1][0], ' ', field[1][1], ' ', field[1][2], ' ','|'});
        System.out.println(new char[] {'|', ' ', field[0][0], ' ', field[0][1], ' ', field[0][2], ' ','|'});
        System.out.println(" ------- ");
    }

    private static void userMove(char[][] field) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner scanner = new Scanner(System.in);

            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                if (x > 3 || y > 3 || x < 1 || y < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[y - 1][x - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field[y - 1][x - 1] = 'X';
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!on");
            }
        }
    }

    public static void main(String[] args) {
        char[][] field = new char[][] {
            {'O', 'X', 'O'},
            {' ', 'X', 'X'},
            {' ', 'O', 'X'}
        };
        printGamesState(field);
        makeEasyLevelMove(field);
        printGamesState(field);
//        checkGameState(field);
//        userMove(field)
    }

    private static void makeEasyLevelMove(char[][] field) {
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
            field[freeCells[move][0]][freeCells[move][1]] = 'X';
        }
    }
}