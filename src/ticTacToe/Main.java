package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[][] ch = new char[][]{{'O', ' ', 'O',}, {'X', 'X', 'O'}, {' ', 'X', 'X'}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ch[i][j]);
            }
            System.out.print('\n');
        }
    }
}