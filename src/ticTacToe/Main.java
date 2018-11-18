package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[] elements = {'X', ' ', 'O', 'X', 'O', ' ', 'O', ' ', 'X'};
        for (int i = 0; i < 9; i++) {
            System.out.print(elements[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}