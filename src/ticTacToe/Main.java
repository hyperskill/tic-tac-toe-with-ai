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
                        System.out.println("make move " + mainCmdArray[1]);
                        makeMove(mainCmdArray[1]);
                        if (checkWinner()) {
                            break;
                        }
                        printFields();
                        System.out.println("make move " + mainCmdArray[2]);
                        makeMove(mainCmdArray[2]);
                        if (checkWinner()) {
                            break;
                        }
                    }
                    outputFinish();
                    break;
                case "exit":
                    read = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void outputFinish() {
        printFields();
        System.out.println(resultOfGame);
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = ' ';
            }
        }
    }

    public void makeMove(String move) {
        switch (move) {
            case "user":
                inputCoords();
                break;
            case "easy":
                inputRandomCoordsBot();
                break;
            case "medium":
                inputMediumCoordsBot();
                break;
            case "hard":
                inputHardCoordsBot();
                break;
        }
    }

    public void inputHardCoordsBot() {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i][0] == fields[i][1] && fields[i][1] == userMove && fields[i][2] == ' ') {
                setMove(i, 2);
                break;
            } else if (fields[i][1] == fields[i][2] && fields[i][2] == userMove && fields[i][0] == ' ') {
                setMove(i, 0);
                break;
            } else if (fields[i][0] == fields[i][2] && fields[i][2] == userMove && fields[i][1] == ' ') {
                setMove(i, 1);
                break;
            } else if (fields[0][i] == fields[1][i] && fields[1][i] == userMove && fields[2][i] == ' ') {
                setMove(2, i);
                break;
            } else if (fields[1][i] == fields[2][i] && fields[2][i] == userMove && fields[0][i] == ' ') {
                setMove(0, i);
                break;
            } else if (fields[0][i] == fields[2][i] && fields[2][i] == userMove && fields[1][i] == ' ') {
                setMove(1, i);
                break;
            } else if (fields[0][0] == fields[1][1] && fields[1][1] == userMove && fields[2][2] == ' ') {
                setMove(2, 2);
                break;
            } else if (fields[0][0] == fields[2][2] && fields[2][2] == userMove && fields[1][1] == ' ') {
                setMove(1, 1);
                break;
            } else if (fields[1][1] == fields[2][2] && fields[2][2] == userMove && fields[0][0] == ' ') {
                setMove(0, 0);
                break;
            } else if (fields[0][2] == fields[1][1] && fields[1][1] == userMove && fields[2][0] == ' ') {
                setMove(2, 0);
                break;
            } else if (fields[0][2] == fields[2][0] && fields[2][0] == userMove && fields[1][1] == ' ') {
                setMove(1, 1);
                break;
            } else if (fields[2][0] == fields[1][1] && fields[1][1] == userMove && fields[0][2] == ' ') {
                setMove(0, 2);
                break; // end first
            } else if (fields[i][0] == fields[i][1] && fields[i][1] != ' ' && fields[i][2] == ' ') {
                setMove(i, 2);
                break;
            } else if (fields[i][1] == fields[i][2] && fields[i][2] != ' ' && fields[i][0] == ' ') {
                setMove(i, 0);
                break;
            } else if (fields[i][0] == fields[i][2] && fields[i][2] != ' ' && fields[i][1] == ' ') {
                setMove(i, 1);
                break;
            } else if (fields[0][i] == fields[1][i] && fields[1][i] != ' ' && fields[2][i] == ' ') {
                setMove(2, i);
                break;
            } else if (fields[1][i] == fields[2][i] && fields[2][i] != ' ' && fields[0][i] == ' ') {
                setMove(0, i);
                break;
            } else if (fields[0][i] == fields[2][i] && fields[2][i] != ' ' && fields[1][i] == ' ') {
                setMove(1, i);
                break;
            } else if (fields[0][0] == fields[1][1] && fields[1][1] != ' ' && fields[2][2] == ' ') {
                setMove(2, 2);
                break;
            } else if (fields[0][0] == fields[2][2] && fields[2][2] != ' ' && fields[1][1] == ' ') {
                setMove(1, 1);
                break;
            } else if (fields[1][1] == fields[2][2] && fields[2][2] != ' ' && fields[0][0] == ' ') {
                setMove(0, 0);
                break;
            } else if (fields[0][2] == fields[1][1] && fields[1][1] != ' ' && fields[2][0] == ' ') {
                setMove(2, 0);
                break;
            } else if (fields[0][2] == fields[2][0] && fields[2][0] != ' ' && fields[1][1] == ' ') {
                setMove(1, 1);
                break;
            } else if (fields[2][0] == fields[1][1] && fields[1][1] != ' ' && fields[0][2] == ' ') {
                setMove(0, 2);
                break;
            } else if (fields[1][1] == ' ') {
                setMove(1, 1);
                break;
            } else {
                System.out.println("random Hard");
                inputRandomCoordsBot();
                break;
            }
        }
    }

    public void inputMediumCoordsBot() {
        Random r = new Random();
        boolean f = r.nextBoolean();
        if (f) {
            inputRandomCoordsBot();
            return;
        }
        boolean ifMove = true;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i][0] == fields[i][1] && fields[i][2] == ' ') {
                setMove(i, 2);
                ifMove = false;
                break;
            } else if (fields[i][1] == fields[i][2] && fields[i][0] == ' ') {
                setMove(i, 0);
                ifMove = false;
                break;
            } else if (fields[i][0] == fields[i][2] && fields[i][1] == ' ') {
                setMove(i, 1);
                ifMove = false;
                break;
            } else if (fields[0][i] == fields[1][i] && fields[2][i] == ' ') {
                setMove(2, i);
                ifMove = false;
                break;
            } else if (fields[1][i] == fields[2][i] && fields[0][i] == ' ') {
                setMove(0, i);
                ifMove = false;
                break;
            } else if (fields[0][i] == fields[2][i] && fields[1][i] == ' ') {
                setMove(1, i);
                ifMove = false;
                break;
            } else if (fields[0][0] == fields[1][1] && fields[2][2] == ' ') {
                setMove(2, 2);
                ifMove = false;
                break;
            } else if (fields[0][0] == fields[2][2] && fields[1][1] == ' ') {
                setMove(1, 1);
                ifMove = false;
                break;
            } else if (fields[1][1] == fields[2][2] && fields[0][0] == ' ') {
                setMove(0, 0);
                ifMove = false;
                break;
            } else if (fields[0][2] == fields[1][1] && fields[2][0] == ' ') {
                setMove(2, 0);
                ifMove = false;
                break;
            } else if (fields[0][2] == fields[2][0] && fields[1][1] == ' ') {
                setMove(1, 1);
                ifMove = false;
                break;
            } else if (fields[2][0] == fields[1][1] && fields[0][2] == ' ') {
                setMove(0, 2);
                ifMove = false;
                break;
            } else if (fields[1][1] == ' ') {
                setMove(1, 1);
                ifMove = false;
                break;
            }
        }
        if (ifMove) {
            inputRandomCoordsBot();
        }
    }

    public void setMove(int x, int y) {
        System.out.println(x + " " + y + " |" + userMove + "|");
        fields[x][y] = userMove;
        if (userMove == 'X') {
            userMove = 'O';
        } else if (userMove == 'O') {
            userMove = 'X';
        }
    }

    public boolean checkWinner() {
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
        Random r = new Random();
        while (true) {
            int xR = r.nextInt(3);
            int yR = r.nextInt(3);
            if (fields[xR][yR] == ' ') {
                setMove(xR, yR);
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
                        setMove(x, y);
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
