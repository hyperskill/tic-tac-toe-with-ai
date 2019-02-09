package ticTacToe;

public class Main {

    private static char[] field = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};

    private static void printField() {
        for (int i = 0; i < 9; i++) {
            System.out.print(field[i] + " ");
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
        System.out.println();

    }

    private static void checkField() {
        if (field[0] == field[1] && field[1] == field[2]) {
            System.out.println(field[0] + " wins");
        } else if (field[3] == field[4] && field[4] == field[5]) {
            System.out.println(field[3] + " wins");
        } else if (field[6] == field[7] && field[7] == field[8]) {
            System.out.println(field[6] + " wins");
        } else if (field[0] == field[3] && field[3] == field[6]) {
            System.out.println(field[0] + " wins");
        } else if (field[1] == field[4] && field[4] == field[7]) {
            System.out.println(field[1] + " wins");
        } else if (field[2] == field[5] && field[5] == field[8]) {
            System.out.println(field[2] + " wins");
        } else if (field[0] == field[4] && field[4] == field[8]) {
            System.out.println(field[0] + " wins");
        } else if (field[2] == field[4] && field[4] == field[6]) {
            System.out.println(field[2] + " wins");
        } else {
            boolean isEnd = true;
            for (char a : field
            ) {
                if (a == ' ') {
                    isEnd = false;
                }
            }
            if (isEnd){
                System.out.println("Draw");
            } else{
                System.out.println("Game not finished");
            }

        }
    }

    public static void main(String[] args) {
        printField();
        checkField();
    }


}