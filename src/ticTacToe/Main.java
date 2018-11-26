package ticTacToe;

import java.util.Random;

public class Main {
    static public char[][] arr = new char[3][3];
    public static void main(String[] args) {
        final Random random = new Random();
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                int a = random.nextInt(3);
                switch (a) {
                    case 0:
                        arr[i][j] = ' ';
                        break;
                    case 1:
                        arr[i][j] = 'X';
                        break;
                    case 2:
                        arr[i][j] = 'O';
                        break;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (j!=2) {
                    System.out.print(arr[i][j] + " ");
                } else {
                    System.out.print(arr[i][j]);
                }
            }
            System.out.println("");
        }
        switch (whatHappening()) {
            case 0:
                System.out.println("Game not finished");
                break;
            case 1:
                System.out.println("X wins");
                break;
            case 2:
                System.out.println("O wins");
                break;
            case 3:
                System.out.println("Draw");
                break;
            default:
                break;
        }

    }

    public static int whatHappening() {
        if (isWin('X')) {
            return 1;
        } else if (isWin('O')) {
            return 2;
        }

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (arr[i][j]==' '){
                    return 0;
                }
            }
        }

        return 3;
    }

    private static boolean isWin(char str){
        for (int i=0; i<3; i++){
            if ((arr[i][0]==str && arr[i][1]==str && arr[i][2]==str) ||
                    (arr[0][i]==str && arr[1][i]==str && arr[2][i]==str)){
                return true;
            }
        }
        if (arr[0][0]==str && arr[1][1]==str && arr[2][2]==str){
            return true;
        }
        if (arr[0][2]==str && arr[1][1]==str && arr[2][0]==str){
            return true;
        }
        return false;
    }
}