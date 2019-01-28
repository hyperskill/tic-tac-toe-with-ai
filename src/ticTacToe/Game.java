package ticTacToe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game implements Runnable {

    private final String MESSAGE_EASY_AI = "Making move level \"easy\"...";
    private final String MESSAGE_MEDIUM_AI = "Making move level \"medium\"...";
    private final String MESSAGE_HARD_AI = "Making move level \"hard\"...";
    private final String MESSAGE_PLAYER = "Enter the coordinates: ";

    private char[][] fieldValues;
    private boolean gameOver = false;
    private Player playerOne;
    private Player playerTwo;
    private String messagePlayerOne;
    private String messagePlayerTwo;

    @Override
    public void run() {
        System.out.println("Игра крестики нолики.\n" +
                "\n" +
                "    1. Ввести команду \"start\" с двумя аргументами через пробел(например - start user easy):\n" +
                "        а. \"user\" - оппонент человек,\n" +
                "        b. \"easy\" - оппонент компьютер уровень сложности легкий,\n" +
                "        c. \"medium\" - оппонент компьютер уровень сложности средний,\n" +
                "        d. \"hard\" - оппонент компьютер уровень сложности тяжелый.\n" +
                "    2. Ввести координаты - две цифры через пробел.\n" +
                "    3. Выйграть или проиграть.\n" +
                "    4. Ввести exit для выхода из программы.\n" +
                "\n" +
                "    Подсказка по координатам:\n" +
                "    ------------\n" +
                "    | 11 12 13 |\n" +
                "    | 21 22 23 |\n" +
                "    | 31 32 33 |\n" +
                "    ------------");

        System.out.println("\n");

        String result;
        fieldValues = init();
        draw();

        while (!gameOver) {
            result = checkState();

            if ("Game not finished.".equals(result)) {
                fieldValues = playerOne.move(fieldValues, messagePlayerOne);
                draw();
            } else {
                System.out.println();
                System.out.println(result);
                gameOver = true;
            }

            result = checkState();

            if ("Game not finished.".equals(result)) {
                fieldValues = playerTwo.move(fieldValues, messagePlayerTwo);
                draw();
            }
        }
    }

    private char[][] init() {
        char[][] fieldValues = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};

        Scanner scanner = new Scanner(System.in);
        List<String> userInput;

        while (true) {
            System.out.print("Input command: ");
            userInput = Arrays.asList(scanner.nextLine().split(" "));

            if ("exit".equals(userInput.get(0))) {
                System.out.println("Game over");
                System.exit(0);
            }

            if ("start".equals(userInput.get(0)) && userInput.size() == 3) {
                if (userChoose(userInput)) {
                    System.out.println("\nThe game has begun\n");
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
                    setMessage(MESSAGE_EASY_AI, i);
                    break;
                case "medium":
                    player = PlayerFactory.getInstance(Players.MEDIUM, figure);
                    setMessage(MESSAGE_MEDIUM_AI, i);
                    break;
                case "hard":
                    player = PlayerFactory.getInstance(Players.HARD, figure);
                    setMessage(MESSAGE_HARD_AI, i);
                    break;
                case "user":
                    player = PlayerFactory.getInstance(Players.USER, figure);
                    setMessage(MESSAGE_PLAYER, i);
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
            System.out.print(" |\n");
        }
        System.out.println("\t  ---------");
    }

    private void setMessage(String message, int i) {
        if (i == 1) {
            messagePlayerOne = message;
        } else {
            messagePlayerTwo = message;
        }
    }
}
