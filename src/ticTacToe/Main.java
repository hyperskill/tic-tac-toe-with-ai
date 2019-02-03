package ticTacToe;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static char[][] field;
    private static char turn = 'X';
    private static boolean isFirst = true;
    private static boolean isGame = false;
    private static int mode=0;
    static private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        while (!isGame){

            try {
                System.out.println("1 - TWO PLAYERS MODE\n2 - EASY AI MODE\n3 - HARD AI MODE\n4 - QUIT");
                int i = scanner.nextInt();
                
                switch (i){
                    case 1:field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; mode = 1;print(); isGame = true; game(); break;
                    case 2:
                        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
                        mode = 2;
                        System.out.println("SELECT (X/O): ");
                        if (scanner.next().equalsIgnoreCase("o")) isFirst = false;
                        print();
                        isGame = true;
                        game();

                        break;
                    case 3:
                        field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
                        mode = 3;
                        print();
                        System.out.println("SELECT (X/O): ");
                        isGame = true;
//                        hardGame();
                        break;
                    case 4:
                        System.exit(0);
                    default: throw new IOException();
                }
            } catch (IOException ignored){
                System.out.println("I/O error, try again...\n");
            }
        }
    }
    private static int turns=0;
    private static boolean shoot(int i, int j) {
        if (turn == 'X' && field[i][j] == ' ') {
            field[i][j] = 'X';
            turn = 'O';
            turns++;
            return true;
        }
        if (turn == 'O' && field[i][j] == ' ') {
            field[i][j] = 'O';
            turn = 'X';
            turns++;
            return true;
        }
        return false;
    }
    private static void game(){
        Random r = new Random();
        while (isGame) {
            if (isFirst && turn == 'X'||!isFirst && turn == 'O') {
                String a1 = scanner.next();
                String b1 = scanner.next();
                System.out.println("ENTER COORDINATES: ");
                try {
                    int a = Integer.parseInt(a1);
                    int b = Integer.parseInt(b1);
                    if (a >= 0 && a < 3 && b < 3 && b >= 0)
                        shoot(a, b);
                    else System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
                } catch (Exception e) {
                    System.out.println("ILLEGAL COORDINATE, TRY AGAIN");
                }

            } else {
                if (mode==2){
                    boolean ok = false;
                    while (!ok){
                        int a = r.nextInt(3);
                        int b = r.nextInt(3);
                        ok = shoot(a,b);
                    }
                }
            }
            print();
        }
    }

   static void getCoord(){

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
        System.out.println();
    }


    private static String getGameStatus() {
        if (turns==9){
            isGame=false;
            return "DROW";
        }
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
