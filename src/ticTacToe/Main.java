package ticTacToe;

import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 3;
        char [][]state = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        System.out.println("Choose level of game: \n" +
                "1.Easy\n" +
                "2.Medium\n" +
                "3.Hard");
        int choose;
        do {
            System.out.print("Your select: ");
            while (!scan.hasNextInt()) {
                String input = scan.next();
                System.out.println("You should enter numbers!");
            }
            choose =  scan.nextInt();
        }while (choose < 1 || choose > 3);

        if (choose == 1){
            easyGame(state, n);
        }
        else if (choose == 2){
            //medium
        }
        else {
            //hard
        }

    }

    private static void easyGame(char[][]state, int n){
        boolean flag;
        do {
            inputHuman(state, n);
            flag = checkGame(state,n);
            if (!flag) {
                generateEasy(state, n);
                flag = checkGame(state,n);

            }

        }while (!flag);
    }

    private static void inputHuman(char[][]state, int n){
        Scanner scan = new Scanner(System.in);
        int indexI;
        int indexJ;
        drawState(state,n);

        do{
            System.out.println("Enter he coordinates: ");
            while (!scan.hasNextInt()) {
                String input = scan.next();
                System.out.println("You should enter numbers!");
            }
            indexI =  scan.nextInt() - 1;
            while (!scan.hasNextInt()) {
                String input = scan.next();
                System.out.println("You should enter numbers!");
            }
            indexJ = scan.nextInt() - 1;
            if(checkSell(state,indexI,indexJ)){
                break;
            }


        }while (true);
        state[indexI][indexJ] = 'X';
    }

    private static void generateEasy(char[][]state, int n){
        drawState(state, n);
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        do {
            int randI = random.nextInt(3);
            int randJ = random.nextInt(3);

            if(state[randI][randJ] == ' '){
                state[randI][randJ] = 'O';
                break;
            }
        }while (true);
    }

    private static boolean checkGame(char[][]state, int n) {
        boolean flag = false;
        int stepRow;
        int stepCol;
        for (int i = 0; i < n; i++) {
            stepRow = 1;
            stepCol = 1;
            for (int j = 0; j < n - 1; j++) { //check for rows
                if (state[i][j] == state[i][j + 1]) {
                    stepRow++;
                } else {
                    break;
                }
                if (stepRow == 3) {
                    if (state[i][j] == 'X') {
                        drawState(state,n);
                        System.out.println("X wins");
                        flag = true;
                    }
                    if (state[i][j] == 'O') {
                        drawState(state,n);
                        System.out.println("O wins");
                        flag = true;
                    }
                }
            }
            if (flag) {
                break;
            }
            for (int j = 0; j < n - 1; j++) {//check for col
                if (state[j][i] == state[j + 1][i]) {
                    stepCol++;
                } else {
                    break;
                }
                if (stepCol == 3) {

                    if (state[j][i] == 'X') {
                        drawState(state,n);
                        System.out.println("X wins");
                        flag = true;
                    }
                    if (state[j][i] == 'O') {
                        drawState(state,n);
                        System.out.println("O wins");
                        flag = true;
                    }
                }
            }
            if (flag) {
                break;
            }
            if (state[0][0] == state[1][1] && state[0][0] == state[2][2] ||
                    state[0][2] == state[1][1] && state[1][1] == state[2][0]) {

                if (state[1][1] == 'X') {
                    drawState(state,n);
                    System.out.println("X wins");
                    flag = true;
                }
                if (state[1][1] == 'O') {
                    drawState(state,n);
                    System.out.println("O wins");
                    flag = true;
                }
            }
            if (flag) {
                break;
            }
        }

        return flag;
    }

    private static boolean checkSell(char[][] state, int indexI, int indexJ){
        if(indexI < 0 || indexI >= 3 || indexJ < 0 || indexJ >= 3){
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (state[indexI][indexJ] != ' '){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    private static void drawState(char[][] state, int size){
        System.out.println("---------");
        for (int i = 0; i < size; i++){
            System.out.print("| ");
            for (int j = 0; j < size; j++){
                System.out.print(state[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}