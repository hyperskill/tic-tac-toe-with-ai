package ticTacToe;


import java.util.Random;
import java.util.Scanner;

public class Main {
    private static char[][] fieldValues = init();
    private static final char CROSS = 'X';
    private static final char ZERO = 'O';

    public static void main(String[] args) {
        boolean gameOver = false;
        String result;

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            draw();
            result = checkState();
            if ("Game not finished.".equals(result)) {
                System.out.print("Enter the coordinates: ");
                String input = scanner.nextLine();
                while (!checkUserInput(input)) {
                    System.out.print("Enter the coordinates: ");
                    input = scanner.nextLine();
                }
                draw();
            } else {
                System.out.println();
                System.out.println(result);
                gameOver = true;
            }

            result = checkState();
            if ("Game not finished.".equals(result)) {
                computerMoveEasyLevel();
            }
        }
    }

    private static char[][] init() {
        char[][] fieldValues = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '},};
        return  fieldValues;
    }

    private static boolean checkUserInput(String input) {
        input = input.replaceAll(" ", "");
        System.out.println();

        if (input.length() != 2) {
            System.out.println("Enter two numbers separated by space");
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("You should enter numbers!");
                return false;
            }
        }

        int x = Character.getNumericValue(input.charAt(0)) - 1;
        int y = Character.getNumericValue(input.charAt(1)) - 1;

        if ((x > 2 || y > 2) || (x < 0 || y < 0)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (fieldValues[x][y] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        move(x, y, CROSS);
        return true;
    }

    private static void move(int x, int y, char type) {
        fieldValues[x][y] = type;
    }

    private static String checkState() {


        for (int i = 0; i < 3; i++) {
            int col = 0;
            int row = 0;

            //row check{
            if (fieldValues[i][0] == fieldValues[i][1] && fieldValues[i][0] == fieldValues[i][2] && fieldValues[i][0] != ' ') {
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
            if (fieldValues[0][i] == fieldValues[1][i] && fieldValues[0][i] == fieldValues[2][i] && fieldValues[0][i] != ' ') {
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
        if (fieldValues[0][0] == fieldValues[1][1] && fieldValues[1][1] == fieldValues[2][2] && fieldValues[0][0] != ' ') {
            if (fieldValues[0][0] == 'X') {
                return "X wins.";
            } else {
                return "O wins.";
            }
        }
        if (fieldValues[0][2] == fieldValues[1][1] && fieldValues[1][1] == fieldValues[2][0] && fieldValues[0][2] != ' ') {
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

    private static void draw() {
        System.out.println("\t\t1 2 3 ");
        System.out.println("\t  ---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("\t" + (i + 1) + " | ");
            for (int j = 0; j < 3; j++) {
                if (j < 2) {
                    System.out.print(fieldValues[i][j] + " ");
                } else {
                    System.out.print(fieldValues[i][j]);
                }
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("\t  ---------");
    }

    private static void computerMoveEasyLevel() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (fieldValues[x][y] == ' ') {
                System.out.println("Making move level \"easy\"...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                move(x, y, ZERO);
                break;
            }
        }
    }
}