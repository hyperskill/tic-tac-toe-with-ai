package ticTacToe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuGame();
    }

    private static void menuGame(){
        Scanner scan = new Scanner(System.in);

        String chooseActionGame;
        String firstPlayer;
        String secondPlayer;

        do {
            int n = 3;
            char [][]state = new char[][] {
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };
            System.out.print("Input command: ");

            chooseActionGame = scan.next();
            if (chooseActionGame.equals("start")) {
                boolean flag = true;
                firstPlayer = scan.next();
                secondPlayer = scan.next();

                switch (firstPlayer){
                    case "user": case "easy": case "medium": case "hard": break;
                    default:
                        System.out.println("Bad parameters");
                        flag = false;
                        break;
                }

                if (flag){
                    switch (secondPlayer){
                        case "user": case "easy": case "medium": case "hard": break;
                        default:
                            System.out.println("Bad parameters");
                            flag = false;
                            break;
                    }
                }
                if (flag) {
                    startGame(state, firstPlayer, secondPlayer);
                }
            }
            scan.nextLine();
        }while (!chooseActionGame.equals("exit"));
        scan.close();
    }

    private static void startGame(char[][]state, String player1, String player2){
        if (player1.equals("user") && player2.equals("user")){
            userUser(state, 3);
        }
        else if (player1.equals("user")){
            userBot(state, player2, 3);
        }
        else if (player2.equals("user")){
            botUser(state, player1, 3);
        }
        else {
            botBot(state, player1, player2, 3);
        }
    }

    private static void userBot(char[][]state, String player2, int n){
        boolean flag;
        do {
            inputHuman(state, n, 'O');
            flag = checkGame(state,n);
            if (!flag) {
                switch (player2){
                    case "easy":
                        generateEasy(state, n, 'X');//for easy bot
                        break;

                    case "medium":
                        generateMedium(state, n, 'X');
                        //for medium bot
                        break;

                    case "hard":
                        //for hard bot
                        break;
                }
                flag = checkGame(state,n);
            }
        }while (!flag);
    }

    private static void botUser(char[][]state, String player1, int n){
        boolean flag;
        do {
            switch (player1){
                case "easy":
                    generateEasy(state, n, 'O');//for easy bot
                    break;

                case "medium":
                    generateMedium(state, n, 'O');
                    //for medium bot
                    break;

                case "hard":
                    //for hard bot
                    break;
            }
            flag = checkGame(state,n);
            if (!flag) {
                inputHuman(state, n, 'X');
                flag = checkGame(state,n);
            }
        }while (!flag);
    }

    private static void userUser(char[][]state, int n){
        boolean flag;
        do {
            inputHuman(state, n, 'O');
            flag = checkGame(state,n);
            if (!flag) {
                inputHuman(state, n, 'X');
                flag = checkGame(state,n);
            }
        }while (!flag);
    }

    private static void botBot(char[][]state, String player1, String player2, int n){
        boolean flag;
        do {
            switch (player1){
                case "easy":
                    generateEasy(state, n, 'O');//for easy bot
                    break;

                case "medium":
                    generateMedium(state, n, 'O');
                    //for medium bot
                    break;

                case "hard":
                    //for hard bot
                    break;
            }
            flag = checkGame(state,n);
            if (!flag) {
                switch (player2){
                    case "easy":
                        generateEasy(state, n, 'X');//for easy bot
                        break;

                    case "medium":
                        generateMedium(state, n, 'X');
                        //for medium bot
                        break;

                    case "hard":
                        //for hard bot
                        break;
                }
                flag = checkGame(state,n);
            }
        }while (!flag);
    }

    private static void inputHuman(char[][]state, int n, char XO){
        Scanner scan = new Scanner(System.in);
        int indexI;
        int indexJ;
        drawState(state,n);

        do{
            System.out.println("Enter the coordinates: ");

            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("You should enter numbers!");
            }
            indexI =  scan.nextInt() - 1;
            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.out.println("You should enter numbers!");
            }
            indexJ = scan.nextInt() - 1;
            if(checkSell(state,indexI,indexJ)){
                break;
            }

        } while (true);
        state[indexI][indexJ] = XO;
    }

    private static void generateEasy(char[][]state, int n, char XO){
        drawState(state, n);
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        do {
            int randI = random.nextInt(3);
            int randJ = random.nextInt(3);

            if(state[randI][randJ] == ' '){
                state[randI][randJ] = XO;
                break;
            }
        }while (true);
    }

    private static void generateMedium(char[][]state, int n, char XO){
        drawState(state, n);
        System.out.println("Making move level \"medium\"");
        int cell1 = ' ' + 'X' + 'X';
        int cell2 = ' ' + 'O' + 'O';

        Random random = new Random();
        point:
        do {
            int sumD1 = 0;
            int sumD2 = 0;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (i == j) {
                        sumD1 += state[i][j];
                    }
                    if (i + j == 2){
                        sumD2 += state[i][j];
                    }
                }
            }
            if (sumD1 == cell1 || sumD1 == cell2){
                for (int i = 0; i < n;i++){
                    if (state[i][i] == ' '){
                        state[i][i] = XO;
                        break point;
                    }
                }
            }else if (sumD2 == cell1 || sumD2 == cell2){
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        if (i + j == 2 && state[i][j] == ' '){
                            state[i][j] = XO;
                            break point;
                        }
                    }
                }
            }




            for (int i = 0; i < n; i++){
                int sum1 = 0, sum2 = 0;

                for (int j = 0; j < n; j++) {
                    sum1 += state[i][j];
                    sum2 += state[j][i];
                }

                if (sum1 == cell1 || sum1 == cell2){
                    for (int j = 0; j < n; j++){
                        if (state[i][j] == ' '){
                            state[i][j] = XO;
                            break point;
                        }
                    }
                }
                else if(sum2 == cell1 || sum2 == cell2){
                    for (int j = 0; j < n; j++){
                        if (state[j][i] == ' '){
                            state[j][i] = XO;
                            break point;
                        }
                    }
                }
            }

            int randI = random.nextInt(3);
            int randJ = random.nextInt(3);

            if(state[randI][randJ] == ' '){
                state[randI][randJ] = XO;
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
        int num = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (state[i][j] == ' '){
                    num++;
                }
            }
        }
        if (num == 0){
            drawState(state,3);
            System.out.println("\na draw");
            flag = true;
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