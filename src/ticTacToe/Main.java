package ticTacToe;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static char[][] field;
    private static char turn = 'X';
    private static boolean isGame = true;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        print();
        while (isGame){
            String a1 = scanner.next();
            String b1 = scanner.next();
            System.out.println("ENTER COORDINATES: ");
            try {
                int a = Integer.parseInt(a1);
                int b = Integer.parseInt(b1);
                if(a>=0&&a<3&&b<3&&b>=0)
                shoot(a, b);
                else System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
            }
            catch (Exception e){
                System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
            }
            print();
        }

        int i = System.in.read();

    }

    private static void shoot(int i, int j) {
        if (turn == 'X' && field[i][j] == ' ') {
            field[i][j] = 'X';
            turn = 'O';
        }
        if (turn == 'O' && field[i][j] == ' ') {
            field[i][j] = 'O';
            turn = 'X';
        }
    }

    private static void print() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + ((j!=2) ? " | " : "" ));
            }
            System.out.println(i != 2 ? "\n_________" : "\n");
        }
        String gs = getGameStatus();
        System.out.println(gs);
        if (gs.contains("WINS")){
            isGame = false;
        }
    }


    private static String getGameStatus() {
        for (int i = 0; i < 3; i++) {

            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != ' ') {
                return field[i][0] == 'X' ? "X WINS!" : "O WINS!";
            } else if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != ' ') {
                return field[0][i] == 'X' ? "X WINS!" : "O WINS!";
            }
        }
        if (field[1][1] == field[0][0] && field[1][1] == field[2][2] || field[1][1] == field[0][2] && field[1][1] == field[2][0]) {
            return field[1][1] == 'X' ? "X WINS!" : field[1][1] == 'O' ? "O WINS!" : "GAME IS RUNNING! " + turn + " is playing...";
        } else return "GAME IS RUNNING! " + turn + " is playing...";
    }
}
