package ticTacToe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private Scanner scn = new Scanner(System.in);
    private char[][] fields = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private char userMove = 'X';
    private String resultOfGame = "";
    private boolean game = true;

    private enum level {
        EASY, MEDIUM, HARD
    }

    ;
    private String firstPlayer;
    private String secondPlayer;

    public static void main(String[] args) {
        Main main = new Main();
        main.startGame();
    }

    public void startGame() {
        System.out.println();
        String mainCmd = "";
        boolean read = true;
        while (read) {
            mainCmd = scn.nextLine();
            String[] mainCmdArray = mainCmd.split(" ");
            switch (mainCmdArray[0]) {
                case "start":
                    while (true) {
                        printFields();
                        switch (mainCmdArray[1]) {
                            case "user":
                                inputCoords();
                                break;
                            case "easy":
                                inputRandomCoordsBot();
                        }
                        if (checkWinner()) {
                            break;
                        }
                        printFields();
                        switch (mainCmdArray[2]) {
                            case "user":
                                inputCoords();
                                break;
                            case "easy":
                                inputRandomCoordsBot();
                        }
                        if (checkWinner()) {
                            break;
                        }
                    }
                    read = false;
                    break;
                case "exit":
                    read = false;
                    break;
                default:
                    break;
            }
        }
        printFields();
        System.out.println(resultOfGame);
    }

    public boolean checkWinner() {
        boolean draw = true;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j] == ' ') {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            resultOfGame = "Draw";
            return true;
        }
        for (int i = 0; i < fields.length; i++) {
            if (checkFields(fields[0][i], fields[1][i], fields[2][i])) {
                return winner(fields[0][i]);
            } else if (checkFields(fields[i][0], fields[i][1], fields[i][2])) {
                return winner(fields[i][0]);
            }
        }
        if (checkFields(fields[1][1], fields[0][0], fields[2][2])) {
            return winner(fields[1][1]);
        } else if (checkFields(fields[0][2], fields[1][1], fields[2][0])) {
            return winner(fields[1][1]);
        }
        return false;
    }

    private boolean winner(char c) {
        if (c != ' ') {
            resultOfGame = c + " wins";
            return true;
        }
        return false;
    }

    private boolean checkFields(char... fields) {
        for (int i = 1; i < fields.length; i++) {
            if ((fields[i - 1] != fields[i]) && (fields[i - 1] != ' ' || fields[i] != ' ')) {
                return false;
            }
        }
        return true;
    }

    public void printFields() {
        System.out.println("---------");
        for (char[] ch : fields) {
            System.out.print("| ");
            for (char c : ch) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void inputRandomCoordsBot() {
        System.out.println("Making move level \"easy\"");
        Random r = new Random();
        while (true) {
            int xR = r.nextInt(3);
            int yR = r.nextInt(3);
            if (fields[xR][yR] == ' ') {
                fields[xR][yR] = userMove;
                if (userMove == 'X') {
                    userMove = 'O';
                } else if (userMove == 'O') {
                    userMove = 'X';
                }
                break;
            }
        }
    }

    public void inputCoords() {
        boolean read = true;
        int x = -1;
        int y = -1;
        while (read) {
            System.out.print("Enter the coordinates: ");
            if (scn.hasNextInt()) {
                x = scn.nextInt();
                if (scn.hasNextInt()) {
                    y = scn.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                }
                if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
                    if (fields[x][y] == ' ') {
                        fields[x][y] = userMove;
                        if (userMove == 'X') {
                            userMove = 'O';
                        } else if (userMove == 'O') {
                            userMove = 'X';
                        }
                        read = false;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 0 to 2!");
                }
            } else {
                System.out.println("You should enter numbers!");
                scn = new Scanner(System.in);
            }
        }
    }
}
