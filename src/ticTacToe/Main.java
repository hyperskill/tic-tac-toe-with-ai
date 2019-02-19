package ticTacToe;

public class Main {
    public static void main(String[] args) {
        char[][] arr1 = {
            {'X', 'X', 'X'},
            {'O', 'O', ' '},
            {' ', 'O', ' '}
        };

        char[][] arr2 = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'X', 'X', 'O'}
        };

        char[][] arr3 = {
            {'X', 'O', 'O'},
            {'O', 'X', 'O'},
            {'X', 'X', 'O'}
        };

        char[][] arr4 = {
            {'X', 'O', 'X'},
            {'O', 'O', 'X'},
            {'X', 'X', 'O'}
        };

        char[][] arr5 = {
            {'X', 'O', ' '},
            {'O', 'O', 'X'},
            {' ', 'X', ' '}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("X wins" + '\n');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("X wins" + '\n');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr3[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("O wins" + '\n');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Draw" + '\n');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr5[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Game not finished" + '\n');
    }
}
