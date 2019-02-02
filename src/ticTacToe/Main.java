package ticTacToe;

public class Main {
    private static char[][] field;

    public static void main(String[] args) {

        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        print();

    }

    static void print() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(gameStatus());
    }

    static String gameStatus() {
        for (int i = 0; i < 3; i++) {

            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != ' ') {
                return field[i][0] == 'X' ? "X WINS!" : "O WINS!";
            } else if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != ' ') {
                return field[0][i] == 'X' ? "X WINS!" : "O WINS!";
            }
        }
        if (field[1][1] == field[0][0] && field[1][1] == field[2][2] || field[1][1] == field[0][2] && field[1][1] == field[2][0]) {
            return field[1][1] == 'X' ? "X WINS!" : field[1][1] == 'O' ? "O WINS!" : "GAME IS RUNNING";
        } else return "GAME IS RUNNING";
    }
}
