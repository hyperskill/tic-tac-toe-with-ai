package ticTacToe;

import java.util.*;

public class Main {

    private static char huPlayer = 'O';
    private static char aiPlayer = 'X';
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
                        generateHard(state, n, 'X');
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
                    generateHard(state, n, 'O');
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
                    generateHard(state, n, 'O');
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
                        generateHard(state, n, 'X');
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

    private static boolean winning(char[][] state, char player){
        int winSum = player * 3;
        return
                (state[0][0] + state[0][1] + state[0][2] == winSum) ||
                (state[1][0] + state[1][1] + state[1][2] == winSum) ||
                (state[2][0] + state[2][1] + state[2][2] == winSum) ||
                (state[0][0] + state[1][0] + state[2][0] == winSum) ||
                (state[0][1] + state[1][1] + state[2][1] == winSum) ||
                (state[0][2] + state[1][2] + state[2][2] == winSum) ||
                (state[0][0] + state[1][1] + state[2][2] == winSum) ||
                (state[0][2] + state[1][1] + state[2][0] == winSum);
    }

    private static List<Integer> emptyIndexes(char[][]state, int size){
        List<Integer> arrayIndex = new ArrayList<Integer>();
        int temp = 1;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (state[i][j] == ' '){
                    arrayIndex.add(temp);
                    //arrayIndex.add(j);
                }
                temp++;
            }
        }
        return arrayIndex;
    }

    private static int[] minimax(char[][]newState, char player, int size){

        List <Integer> empty = new ArrayList<>(emptyIndexes(newState, size));
        int []n = new int[2];
        if (winning(newState, huPlayer)){
            n[0] = -10;
            n[1] = 0;
            return  n;//виграла людина
        }
        else if (winning(newState, aiPlayer)){
            n[0] = 10;
            n[1] = 0;
            return  n;//виграв АІ
        }
        else if (empty.size() == 0){
            n[0] = 0;
            n[1] = 0;
            return  n;//нічия
        }

        List <Integer> moves = new ArrayList<>();

        for (int i = 0; i < empty.size(); i++){
                //List <Integer> move = new ArrayList<Integer>();
                int temp = empty.get(i);
                int k = 1;
                int temp1 = 0, temp2 = 0;

                out:
                for (int z = 0; z < size; z++){
                    for (int j = 0; j < size; j++){
                        if (temp == k){
                            temp1 = z;
                            temp2 = j;
                            break out;
                        }
                        k++;
                    }
                }
                moves.add(temp);
                newState[temp1][temp2] = player;
                int scores;
                if (player == aiPlayer){
                    int []result = new int[2];
                    result = minimax(newState, huPlayer, 3);
                    scores = result[0];
                }
                else{
                    int []result = new int[2];
                    result = minimax(newState, aiPlayer, 3);
                    scores = result[0];
                }

                newState[temp1][temp2] = ' ';
                //System.out.println(scores);
                moves.add(scores);
        }
//        System.out.println(moves);

        int bestMove = 0;
        int []ret = new int[2];
        if(player == aiPlayer){
            int bestScore = -10000;

            for(int i = 0; i < moves.size(); i = i + 2){
                if(moves.get(i+1) > bestScore){
                    bestScore = moves.get(i+1);
                    bestMove = moves.get(i);//вибираємо номер кращого руху
                    ret[0] = bestScore;
                }
            }
        }else{
            int bestScore = 10000;

            for(int i = 0; i < moves.size(); i = i + 2){
                if(moves.get(i+1) < bestScore){
                    bestScore = moves.get(i+1);
                    bestMove = moves.get(i);
                    ret[0] = bestScore;
                }
            }
        }


        ret[1] = bestMove;
// return the chosen move (object) from the moves array
        return ret;
    }

    private static void generateHard(char[][]state, int n, char XO){
        drawState(state, n);
        System.out.println("Making move level \"hard\"");
        if (emptyIndexes(state,n).size() == 9){
            Random random = new Random();
            state[random.nextInt(3)][random.nextInt(3)] = XO;
            return;
        }
        if (XO == 'O') {
            aiPlayer = 'O';
            huPlayer = 'X';
        }else {
            aiPlayer = 'X';
            huPlayer = 'O';
        }

        int k = minimax(state, XO, n)[1];
        int m = 1;
        int tempI = 0;
        int tempJ = 0;
        out:
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (k == m){
                    tempI = i;
                    tempJ = j;
                    break out;
                }
                m++;
            }
        }
        state[tempI][tempJ] = XO;
    }
}