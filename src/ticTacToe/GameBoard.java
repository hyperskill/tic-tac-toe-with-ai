package ticTacToe;

public class GameBoard {
    private char[][] board;
    private char currentPlayer;

    GameBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        boardLoad();
    }

    public void boardLoad() {
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                board[i][j] = ' ';
            }
        }
        printBoard();
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 2; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 2; j >= 0; j--) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");
    }

//    public void checkStatus() {
//        if (checkRows()) {
//            System.out.println(currentPlayer + " wins" + '\n');
//            System.exit(0);
//        } else if (!isFull()) {
//            System.out.println("Game not finished" + '\n');
//        } else {
//            System.out.println("Draw" + '\n');
//            System.exit(0);
//        }

    public void changePlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

//    public boolean checkRows() {
//        for (int i = 0; i < 3; i++) {
//            if ((board[i][0] != ' ') && (board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
//                return true;
//            }
//        }
//        return checkColumn();
//    }
//
//    public boolean checkColumn() {
//        for (int i = 0; i < 3; i++) {
//            if ((board[0][i] != ' ') && (board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
//                return true;
//            }
//        }
//        return checkDiagonals();
//    }
//
//    public boolean checkDiagonals() {
//        return (board[0][0] != ' ') && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) || (board[0][2] != ' ') && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0]);
//    }

//    public boolean isFull() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == ' ') {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public boolean turn(int row, int col) {
        if ((row >= 0 && row < 3) && (col >= 0 && col < 3)) {
            row = row == 1 ? 1 : row == 2 ? 0 : 2;
            if (board[col][row] == ' ') {
                board[col][row] = currentPlayer;
                changePlayer();
                printBoard();
                return true;
            }
        }

        return false;
    }
}
