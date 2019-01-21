package ticTacToe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game implements Runnable {

    private char[][] fieldValues;
    private boolean gameOver = false;
    private Player playerOne;
    private Player playerTwo;

    @Override
    public void run() {
        String result;
        fieldValues = init();
        while (!gameOver) {
            draw();
            result = checkState();

            if ("Game not finished.".equals(result)) {
                fieldValues = playerOne.move(fieldValues);
                draw();
            } else {
                System.out.println();
                System.out.println(result);
                gameOver = true;
            }

            result = checkState();

            if ("Game not finished.".equals(result)) {
                fieldValues = playerTwo.move(fieldValues);
            }
        }
    }

    private char[][] init() {
        char[][] fieldValues = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '},};

        Scanner scanner = new Scanner(System.in);
        List<String> userInput;

        while (true) {
            System.out.print("Input command: ");
            userInput = Arrays.asList(scanner.nextLine().split(" "));

            if ("exit".equals(userInput.get(0))) {
                System.out.println();
                System.out.println("Game over");
                System.exit(0);
            }

            if ("start".equals(userInput.get(0)) && userInput.size() == 3) {
                if (userChoose(userInput)) {
                    System.out.println(playerOne.getFigure() + " " + playerTwo.getFigure());
                    break;
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }

        return fieldValues;
    }

    private boolean userChoose(List<String> userInput) {

        Player player = null;

        for (int i = 1; i < userInput.size(); i++) {
            char figure = i == 1 ? 'X' : 'O';
            switch (userInput.get(i)) {
                case "easy":
                    player = PlayerFactory.getInstance(Players.EASY, figure);
                    break;
                case "medium":
                    player = PlayerFactory.getInstance(Players.MEDIUM, figure);
                    break;
                case "hard":
                    player = PlayerFactory.getInstance(Players.HARD, figure);
                    break;
                case "user":
                    player = PlayerFactory.getInstance(Players.USER, figure);
                    break;
                default:
                    player = null;
            }

            if (i == 1) {
                playerOne = player;
            } else {
                playerTwo = player;
            }
        }

        return playerOne != null && playerTwo != null;
    }

    private String checkState() {
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

    private void draw() {
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
}
