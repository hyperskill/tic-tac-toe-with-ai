package ticTacToe;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<char[][]> list = new ArrayList<>();
        list.add(new char[][]{{'X', 'X', 'X'}, {'O', 'O', ' '}, {' ', 'O', ' '}});
        list.add(new char[][]{{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'X', 'O'}});
        list.add(new char[][]{{'X', 'O', 'O'}, {'O', 'X', 'O'}, {'X', 'X', 'O'}});
        list.add(new char[][]{{'X', 'O', 'X'}, {'O', 'O', 'X'}, {'X', 'X', 'O'}});
        list.add(new char[][]{{'X', 'O', ' '}, {'O', 'O', 'X'}, {' ', 'X', ' '}});
        for (char[][] cha : list) {
            displayStatus(cha);
        }

    }

    private static void displayStatus(char[][] ch) {
        printBoard(ch);
        char winner = checkRows(ch);
        if (winner != '-') {
            System.out.println(winner + " wins" + '\n');
        } else if (!isFull(ch)) {
            System.out.println("Game not finished" + '\n');
        } else {
            System.out.println("Draw" + '\n');
        }

    }

    private static void printBoard(char[][] ch) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ch[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char checkRows(char[][] ch) {
        for (int i = 0; i < 3; i++) {
            if ((ch[i][0] != ' ') && (ch[i][0] == ch[i][1]) && (ch[i][1] == ch[i][2])) {
                return ch[i][0];
            }
        }
        return checkColumn(ch);
    }

    private static char checkColumn(char[][] ch) {
        for (int i = 0; i < 3; i++) {
            if ((ch[0][i] != ' ') && (ch[0][i] == ch[1][i]) && (ch[1][i] == ch[2][i])) {
                return ch[0][1];
            }
        }
        return checkDiagonals(ch);
    }

    private static char checkDiagonals(char[][] ch) {
        return ((ch[0][0] != ' ') && (ch[0][0] == ch[1][1]) && (ch[1][1] == ch[2][2]) || (ch[0][2] != ' ') && (ch[0][2] == ch[1][1]) && (ch[1][1] == ch[2][0])) ? ch[1][1] : '-';
    }

    private static boolean isFull(char[][] ch) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ch[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
