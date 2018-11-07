package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[][] field = new char[][] {
            {'O', 'X', 'O'},
            {'O', 'X', 'X'},
            {'O', 'O', 'X'}
        };
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
}