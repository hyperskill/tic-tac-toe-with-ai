package ticTacToe;

public class Main {

    private static char[] field = {'X', ' ', 'O', 'X', 'O', ' ', 'O', ' ', 'X'};

    private static void printField() {
        for (int i = 0; i < 9; i++) {
            System.out.print(field[i]+" ");
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        printField();
    }


}