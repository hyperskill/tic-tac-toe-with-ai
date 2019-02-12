import java.util.Arrays;
import java.util.Random;

public class project {

    private static String result(char[][] field) {
        int i, j;
        boolean xWon = false;
        boolean oWon = false;
        boolean notFinished = false;

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    notFinished = true;
                }
            }
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2]) {
                if (field[i][0] == 'O') {
                    oWon = true;
                } else if (field[i][0] == 'X') {
                    xWon = true;
                }
            }
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i]) {
                if (field[0][i] == 'O') {
                    oWon = true;
                } else if (field[0][i] == 'X') {
                    xWon = true;
                }
            }
        }


        if (xWon && oWon) {
            return "It is not real.";
        } else if (xWon) {
            return "X wins";
        } else if (oWon) {
            return "O wins";
        } else if (notFinished) {
            return "Game not finished";
        } else {
            return "Draw";
        }

    }

    public static void main(String[] args) {
        char[][] field = new char[3][3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (random.nextInt(3)) {
                    case 0:
                        field[i][j] = ' ';
                        break;
                    case 1:
                        field[i][j] = 'X';
                        break;
                    case 2:
                        field[i][j] = 'O';
                        break;
                }
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(result(field));
    }
}
