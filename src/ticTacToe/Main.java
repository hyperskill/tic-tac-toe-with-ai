package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = i==0 ? 'X' : 'O';
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }
}