package ticTacToe;

public class Main {
    public static void main(String[] args) {
        Field(3);
    }

    static void Field(int width) {
        char[][] field = new char[width][width];
        for (int i=0; i<width; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = GenerateXorO();
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    static char GenerateXorO () {
        char xoro;
        xoro = 'O';
        if ((int)(Math.random() * 2) + 0 == 1) xoro = 'X';
        return xoro;
    }
}