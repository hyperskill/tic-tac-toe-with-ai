package ticTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class Main {
    /* ---Variables--- */
    private static Scanner scanner = new Scanner(System.in);
    private static int fieldSize = 3;
    private static char[][] field = new char[fieldSize][fieldSize];
    private static char huPlayer = 'X';
    private static char aiPlayer = 'O';

    /*-----*/
    public static void main(String[] args) {
        showRules();
        startMenu();
    }

    /* ----Functions--- */
    /* Menu */
    private static void startMenu() {
        String command;
        String firstPlayer;
        String secondPlayer;
        do {
            System.out.print("Input command: ");
            command = scanner.next();
            if (command.equals("start")) {
                firstPlayer = scanner.next();
                secondPlayer = scanner.next();
                if (checkMenuInput(firstPlayer) && checkMenuInput(secondPlayer)) {
                    startGame(firstPlayer, secondPlayer);
                } else {
                    System.out.println("Bad parameters");
                }
            } else if (!command.equals("exit")) {
                System.out.println("Bad parameters");
                scanner.nextLine();
            }
        } while (!command.equals("exit"));
    }

    /*-----*/
    /*Check for winnings*/
    private static boolean checkDiagonal(char[][] board, char symb) {
        boolean flag = true;
        for (int i = 0; i < fieldSize; i++) {
            if (board[i][i] != symb) {
                flag = false;
            }
        }
        if (flag) {
            return true;
        }
        flag = true;
        for (int i = 0; i < fieldSize; i++) {
            if (board[i][fieldSize - i - 1] != symb) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkLine(char[][] board, char symb) {
        boolean horizontalFlag;
        boolean verticalFlag;
        for (int i = 0; i < fieldSize; i++) {
            horizontalFlag = true;
            verticalFlag = true;
            for (int j = 0; j < fieldSize; j++) {
                horizontalFlag &= board[i][j] == symb;
                verticalFlag &= board[j][i] == symb;
            }
            if (horizontalFlag || verticalFlag) return true;
        }
        return false;
    }

    private static boolean checkDraw(char[][] board) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean isWin() {
        if (checkLine(field, 'X') || checkDiagonal(field, 'X')) {
            System.out.println("X wins");
            return true;
        } else if (checkLine(field, 'O') || checkDiagonal(field, 'O')) {
            System.out.println("O wins");
            return true;
        } else if (checkDraw(field)) {
            System.out.println("Draw");
            return true;
        }
        System.out.println();
        return false;
    }

    /*-----*/
    /* Check */
    private static boolean checkField(int x, int y) {
        if (x < 0 || x >= fieldSize || y < 0 || y >= fieldSize) {
            System.out.println("Coordinates should be from 1 to " + fieldSize + "!");
            return false;
        } else if (field[x][y] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    private static boolean checkMenuInput(String str) {
        switch (str) {
            case "user":
                return true;
            case "easy":
                return true;
            case "medium":
                return true;
            case "hard":
                return true;
            default:
                return false;
        }
    }

    /*-----*/
    /*Start*/
    private static void startGame(String firstMode, String secondMode) {
        clearField();
        if (firstMode.equals("user") && secondMode.equals("user")) {
            humanVsHuman();
        } else if (firstMode.equals("user")) {
            humanVsSBot(secondMode);
        } else if (secondMode.equals("user")) {
            botVsHuman(firstMode);
        } else {
            botVsBot(firstMode, secondMode);
        }
    }

    /*Game modes*/
    private static void humanVsHuman() {
        do {
            humanMove('X');
            if (isWin()) {
                break;
            }
            humanMove('O');
        } while (!isWin());
    }

    private static void humanVsSBot(String mode) {
        switch (mode) {
            case "easy":
                do {
                    humanMove('X');
                    if (isWin()) {
                        break;
                    }
                    easyLevel('O');
                } while (!isWin());
                break;
            case "medium":
                do {
                    humanMove('X');
                    if (isWin()) {
                        break;
                    }
                    mediumLevel('O');
                } while (!isWin());
                break;
            case "hard":
                do {
                    humanMove('X');
                    if (isWin()) {
                        break;
                    }
                    hardLevel('O');
                } while (!isWin());
                break;
        }

    }

    private static void botVsHuman(String mode) {
        switch (mode) {
            case "easy":
                do {
                    easyLevel('X');
                    if (isWin()) {
                        break;
                    }
                    humanMove('O');
                } while (!isWin());
                break;
            case "medium":
                do {
                    mediumLevel('X');
                    if (isWin()) {
                        break;
                    }
                    humanMove('O');
                } while (!isWin());
                break;
            case "hard":
                do {
                    hardLevel('X');
                    if (isWin()) {
                        break;
                    }
                    humanMove('O');
                } while (!isWin());
                break;
        }

    }

    private static void botVsBot(String firstMode, String secondMode) {
        do {
            switch (firstMode) {
                case "easy":
                    easyLevel('X');
                    break;
                case "medium":
                    mediumLevel('X');
                    break;
                case "hard":
                    hardLevel('X');
                    break;
            }
            if (isWin()) {
                break;
            }
            switch (secondMode) {
                case "easy":
                    easyLevel('O');
                    break;
                case "medium":
                    mediumLevel('O');
                    break;
                case "hard":
                    hardLevel('O');
                    break;
            }
        } while (!isWin());
    }

    /* ----- */
    /* Move */
    private static void humanMove(char symb) {
        showField();
        int x;
        int y;
        do {
            System.out.print("Enter the coordinates: ");
            if (!scanner.hasNextInt() || !scanner.hasNextInt()) {
                scanner.next();
                scanner.next();
                System.out.println("You should enter numbers!");
            } else {

                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                if (checkField(x, y)) {
                    field[x][y] = symb;
                    break;
                }
            }

        } while (true);
        showField();
    }

    /*-----*/
    /* Level Difficulty */
    private static void easyLevel(char symb) {
        showField();
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int indexI;
        int indexJ;
        while (true) {
            indexI = random.nextInt(3);
            indexJ = random.nextInt(3);
            if (field[indexI][indexJ] == ' ') {
                field[indexI][indexJ] = symb;
                break;
            }
        }
        showField();
    }

    private static void mediumLevel(char symb) {
        showField();
        System.out.println("Making move level \"medium\"");
        Random random = new Random();
        int indexI;
        int indexJ;
        char rival;
        switch (symb) {
            case 'X':
                rival = 'O';
                break;
            case 'O':
                rival = 'X';
                break;
            default:
                return;
        }
        while (true) {
            // Check winning
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    // Check winning vertical rectangle
                    // Check 'X' 'X' ' '
                    if (field[i][j] == symb && field[i][j + 1] == symb && field[i][j + 2] == ' ') {
                        field[i][j + 2] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'X' 'X'
                    if (field[i][j + 1] == symb && field[i][j + 2] == symb && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    // Check 'X' ' ' 'X'
                    if (field[i][j] == symb && field[i][j + 2] == symb && field[i][j + 1] == ' ') {
                        field[i][j + 1] = symb;
                        showField();
                        return;
                    }
                    // Check winning horizontal rectangle
                    // Check 'X' 'X' 'O'
                    if (field[j][i] == symb && field[j + 1][i] == symb && field[j + 2][i] == ' ') {
                        field[j + 2][i] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'X' 'X'
                    if (field[j + 1][i] == symb && field[j + 2][i] == symb && field[j][i] == ' ') {
                        field[j][i] = symb;
                        showField();
                        return;
                    }
                    // Check 'X' ' ' 'X'
                    if (field[j][i] == symb && field[j + 2][i] == symb && field[j + 1][i] == ' ') {
                        field[j + 1][i] = symb;
                        showField();
                        return;
                    }

                }
            }
            // Check winning diagonal
            for (int i = 0; i < fieldSize - 2; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    if (field[i][j] == symb && field[i + 1][j + 1] == symb && field[i + 2][j + 2] == ' ') {
                        field[i + 2][j + 2] = symb;
                        showField();
                        return;
                    }
                    if (field[i + 1][j + 1] == symb && field[i + 2][j + 2] == symb && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j] == symb && field[i + 2][j + 2] == symb && field[i + 1][j + 1] == ' ') {
                        field[i + 1][j + 1] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j + fieldSize - 1] == symb && field[i + 1][j + 1] == symb && field[i + fieldSize - 1][j] == ' ') {
                        field[i + fieldSize - 1][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i + fieldSize - 1][j] == symb && field[i + 1][j + 1] == symb && field[i][j + fieldSize - 1] == ' ') {
                        field[i][j + fieldSize - 1] = symb;
                        showField();
                        return;
                    }
                }
            }
            // Check block
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    // Check block horizontal rectangle
                    // Check 'O' 'O' ' '
                    if (field[i][j] == rival && field[i][j + 1] == rival && field[i][j + 2] == ' ') {
                        field[i][j + 2] = symb;
                        showField();
                        return;
                    }
                    // Check 'O' ' ' 'O'
                    if (field[i][j] == rival && field[i][j + 2] == rival && field[i][j + 1] == ' ') {
                        field[i][j + 1] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'O' 'O'
                    if (field[i][j + 1] == rival && field[i][j + 2] == rival && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    //Check block vertical rectangle
                    // Check 'O' 'O' ' '
                    if (field[j][i] == rival && field[j + 1][i] == rival && field[j + 2][i] == ' ') {
                        field[j + 2][i] = symb;
                        showField();
                        return;
                    }
                    // Check 'O' ' ' 'O'
                    if (field[j][i] == rival && field[j + 2][i] == rival && field[j + 1][i] == ' ') {
                        field[j + 1][i] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'O' 'O'
                    if (field[j + 1][i] == rival && field[j + 2][i] == rival && field[j][i] == ' ') {
                        field[j][i] = symb;
                        showField();
                        return;
                    }
                }
            }
            // Check block diagonal
            for (int i = 0; i < fieldSize - 2; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    if (field[i][j] == rival && field[i + 1][j + 1] == rival && field[i + 2][j + 2] == ' ') {
                        field[i + 2][j + 2] = symb;
                        showField();
                        return;
                    }
                    if (field[i + 1][j + 1] == rival && field[i + 2][j + 2] == rival && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j] == rival & field[i + 2][j + 2] == rival && field[i + 1][j + 1] == ' ') {
                        field[i + 1][j + 1] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j + fieldSize - 1] == rival && field[i + 1][j + 1] == rival && field[i + fieldSize - 1][j] == ' ') {
                        field[i + fieldSize - 1][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i + fieldSize - 1][j] == rival && field[i + 1][j + 1] == rival && field[i][j + fieldSize - 1] == ' ') {
                        field[i][j + fieldSize - 1] = symb;
                        showField();
                        return;
                    }
                }
            }
            while (true) {
                indexI = random.nextInt(3);
                indexJ = random.nextInt(3);
                if (field[indexI][indexJ] == ' ') {
                    field[indexI][indexJ] = symb;
                    showField();
                    return;
                }
            }

        }
    }

    /* Hard Level */
    private static void hardLevel(char symb) {
        showField();
        System.out.println("Making move level \"hard\"");
        if (symb == 'X') {
            aiPlayer = 'X';
            huPlayer = 'O';
        } else {
            aiPlayer = 'O';
            huPlayer = 'X';
        }
        int res = miniMax(field, symb)[0];
        int temp = 0, indexI = 0, indexJ = 0;
        loop:
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (temp == res) {
                    indexI = i;
                    indexJ = j;
                    break loop;
                }
                temp++;
            }
        }
        field[indexI][indexJ] = symb;
        showField();
    }

    private static List<Integer> getEmptyCells(char board[][]) {
        List<Integer> emptyCells = new ArrayList<Integer>();
        int temp = 0;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (board[i][j] == ' ') {
                    emptyCells.add(temp);
                }
                temp++;
            }
        }
        return emptyCells;
    }

    public static boolean winning(char board[][], char player) {
        if (checkLine(board, player) || checkDiagonal(board, player)) {
            return true;
        }
        return false;
    }

    private static int[] miniMax(char newBoard[][], char player) {
        List<Integer> availCells = new ArrayList<Integer>(getEmptyCells(newBoard));
        int[] arr = new int[2];
        if (winning(newBoard, aiPlayer)) {
            arr[0] = 0;
            arr[1] = 10;
            return arr;
        } else if (winning(newBoard, huPlayer)) {
            arr[0] = 0;
            arr[1] = -10;
            return arr;
        } else if (availCells.size() == 0) {
            arr[0] = 0;
            arr[1] = 0;
            return arr;
        }
        List<Integer> moves = new ArrayList<Integer>();

        for (int i = 0; i < availCells.size(); i++) {
            int temp = availCells.get(i);
            int indexI = 0, indexJ = 0, l = 0;
            loop:
            for (int m = 0; m < fieldSize; m++) {
                for (int k = 0; k < fieldSize; k++) {
                    if (temp == l) {
                        indexI = m;
                        indexJ = k;
                        break loop;
                    }
                    l++;
                }
            }
            moves.add(temp);
            newBoard[indexI][indexJ] = player;
            int[] result;
            int scores;
            if (player == aiPlayer) {
                result = miniMax(newBoard, huPlayer);
                scores = result[1];
            } else {
                result = miniMax(newBoard, aiPlayer);
                scores = result[1];
            }
            newBoard[indexI][indexJ] = ' ';

            moves.add(scores);
        }
        //System.out.println(moves);
        int[] best = new int[2];
        int bestMove = 0, bestScore;
        if (player == aiPlayer) {
            bestScore = -10000;
            for (int i = 0; i < moves.size(); i += 2) {
                if (moves.get(i + 1) > bestScore) {
                    bestScore = moves.get(i + 1);
                    best[1] = bestScore;
                    bestMove = moves.get(i);
                }
            }
        } else {
            bestScore = 10000;
            for (int i = 0; i < moves.size(); i += 2) {
                if (moves.get(i + 1) < bestScore) {
                    bestScore = moves.get(i + 1);
                    bestMove = moves.get(i);
                    best[1] = bestScore;
                }
            }
        }
        best[0] = bestMove;
        return best;
    }

    /* ----- */
    /*Clear Field*/
    private static void clearField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = ' ';
            }
        }
        /*char[][] arr = {{' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}};
        field = arr;*/
    }

    /* Show */
    private static void showRules() {
        System.out.println("To start game enter start and two game modes, to quit enter exit");
        System.out.println("Game modes : user easy medium hard \n");
    }

    private static void showField() {
        System.out.println("---------");
        for (int i = 0; i < fieldSize; i++) {
            System.out.print("| ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    /*----------*/
}