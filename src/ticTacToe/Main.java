package ticTacToe;

import java.util.Scanner;
import java.util.Random;

public class Main {
    /* ---Variables--- */
    public static Scanner scanner = new Scanner(System.in);
    public static int fieldSize = 3;
    public static char[][] field = new char[fieldSize][fieldSize];
    /*-----*/
    public static void main(String[] args) {
       startMenu();
   }

    /* ----Functions--- */
    /* Menu */
    public static void startMenu(){
        String command;
        String firstPlayer;
        String secondPlayer;
        do{
            System.out.print("Input command: ");
            command = scanner.next();
            if(command.equals("start")){
                firstPlayer = scanner.next();
                secondPlayer = scanner.next();
                if(checkMenuInput(firstPlayer) && checkMenuInput(secondPlayer)){
                    startGame(firstPlayer, secondPlayer);
                }
                else{
                    System.out.println("Bad parameters");
                }
            }
            else if(!command.equals("exit")){
                System.out.println("Bad parameters");
                scanner.nextLine();
            }
        }while(!command.equals("exit"));
    }
    /*-----*/
    /*Check for winnings*/
    public static boolean checkDiagonal(char symb){
        boolean flag = true;
        for(int i = 0; i < fieldSize; i++){
            if(field[i][i] != symb){
                flag =  false;
            }
        }
        if(flag){
            return true;
        }
        flag = true;
        for(int i = 0; i < fieldSize; i++){
            if(field[i][fieldSize - i -1] != symb){
                return  false;
            }
        }
        return true;
    }
    public static boolean checkLine(char symb){
        boolean horizontalFlag;
        boolean verticalFlag;
        for(int i =0; i < fieldSize; i++){
            horizontalFlag = true;
            verticalFlag = true;
            for(int j = 0; j < fieldSize; j++){
                horizontalFlag &= field[i][j] == symb;
                verticalFlag &= field[j][i] == symb;
            }
            if(horizontalFlag || verticalFlag) return true;
        }
        return false;
    }
    public static boolean checkDraw(){
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                if(field[i][j] == ' '){
                    return false;
                }
            }

        }
        return true;
    }
    public static boolean isWin(){
        if(checkLine('X') || checkDiagonal('X')){
            System.out.println("X wins");
            return true;
        }
        else if(checkLine('O') || checkDiagonal('O')){
            System.out.println("O wins");
            return true;
        }
        else if(checkDraw()){
            System.out.println("Draw");
            return true;
        }
        System.out.println();
        return false;
    }
    /*-----*/
    /* Check */
    public static boolean checkField(int x, int y){
        if(x < 0 || x >= fieldSize || y < 0 || y >= fieldSize) {
            System.out.println("Coordinates should be from 1 to " + fieldSize + "!");
            return false;
        }
        else if(field[x][y] != ' '){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }
    public static boolean checkMenuInput(String str){
        switch (str){
            case "user":
                return true;
            case "easy":
                return true;
            case "medium":
                return true;
            case "hard":
                return true;
            default:
                return false;
        }
    }
    /*-----*/
    /*Start*/
    public static void startGame(String firstMode, String secondMode){
        clearField();
        if (firstMode.equals("user") && secondMode.equals("user")){
            humanVsHuman();
        }
        else if (firstMode.equals("user")){
            humanVsSBot(secondMode);
        }
        else if (secondMode.equals("user")){
            botVsHuman(firstMode);
        }
        else {
            botVsBot(firstMode, secondMode);
        }
    }
    /*Game modes*/
    public static void humanVsHuman(){
        do{
            humanMove('X');
            if(isWin()){ break; }
            humanMove('O');
        }while(!isWin());
    }
    public static void humanVsSBot(String mode){
        switch(mode){
            case "easy":
                do{
                    humanMove('X');
                    if(isWin()){ break;}
                    easyLevel('O');
                }while(!isWin());
                break;
            case "medium":
                do{
                    humanMove('X');
                    if(isWin()){ break;}
                    mediumLevel('O');
                }while(!isWin());
                break;
        }

    }
    public static void botVsHuman(String mode){
        switch(mode){
            case "easy":
                do{
                    easyLevel('X');
                    if(isWin()){ break;}
                    humanMove('O');
                }while(!isWin());
                break;
            case "medium":
                do{
                    mediumLevel('O');
                    if(isWin()){ break;}
                    humanMove('X');
                }while(!isWin());
                break;
        }

    }
    public static void botVsBot(String firstMode, String secondMode){
        do {
            switch (firstMode){
                case "easy":
                    easyLevel('X');
                    break;
                case "medium":
                    mediumLevel('X');
                    break;
                case "hard":
                    break;
            }
            if(isWin()){ break;}
             switch (secondMode){
                case "easy":
                    easyLevel('O');
                    break;
                case "medium":
                    mediumLevel('O');
                    break;
                case "hard":
                break;
                }
        }while (!isWin());
    }
    /* ----- */
    /* Move */
    public static void humanMove(char symb){
        showField();
        int x;
        int y;
        do {
            System.out.print("Enter the coordinates: ");
            if(!scanner.hasNextInt() || !scanner.hasNextInt()) {
                scanner.next();
                scanner.next();
                System.out.println("You should enter numbers!");
            }
            else {

                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                if(checkField(x, y)){
                    field[x][y] = symb;
                    break;
                }
            }

        }while(true);
        showField();
    }
    /*-----*/
    /* Level Difficulty */
    public static void easyLevel(char symb){
        showField();
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int indexI;
        int indexJ;
        while(true){
            indexI = random.nextInt(3);
            indexJ = random.nextInt(3);
            if(field[indexI][indexJ] == ' '){
                field[indexI][indexJ] = symb;
                break;
            }
        }
        showField();
    }
    public static void mediumLevel(char symb){
        showField();
        System.out.println("Making move level \"medium\"");
        Random random = new Random();
        int indexI;
        int indexJ;
        char rival;
        switch (symb){
            case 'X':
                rival = 'O';
                break;
            case 'O':
                rival = 'X';
                break;
            default:
                return;
        }

        while(true){
            // Check winning
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    // Check winning vertical rectangle
                    // Check 'X' 'X' ' '
                    if (field[i][j] == symb && field[i][j + 1] == symb && field[i][j + 2] == ' ') {
                        field[i][j + 2] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'X' 'X'
                    if (field[i][j + 1] == symb && field[i][j + 2] == symb && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    // Check 'X' ' ' 'X'
                    if (field[i][j] == symb && field[i][j + 2] == symb && field[i][j + 1] == ' ') {
                        field[i][j + 1] = symb;
                        showField();
                        return;
                    }
                    // Check winning horizontal rectangle
                    // Check 'X' 'X' 'O'
                    if (field[j][i] == symb && field[j + 1][i] == symb && field[j + 2][i] == ' ') {
                        field[j + 2][i] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'X' 'X'
                    if (field[j + 1][i] == symb && field[j + 2][i] == symb && field[j][i] == ' ') {
                        field[j][i] = symb;
                        showField();
                        return;
                    }
                    // Check 'X' ' ' 'X'
                    if (field[j][i] == symb && field[j+ 2][i] == symb && field[j  + 1][i] == ' ') {
                        field[j  + 1][i] = symb;
                        showField();
                        return;
                    }

                }
            }
            // Check winning diagonal
            for(int i = 0; i < fieldSize - 2; i++){
                for(int j = 0; j < fieldSize -2; j++) {
                    if (field[i][j] == symb && field[i + 1][j + 1] == symb && field[i + 2][j + 2] == ' ') {
                        field[i + 2][j + 2] = symb;
                        showField();
                        return;
                    }
                    if (field[i + 1][j + 1] == symb && field[i + 2][j + 2] == symb && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j] == symb && field[i + 2][j + 2] == symb && field[i + 1][j + 1] == ' ') {
                        field[i + 1][j + 1] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j + fieldSize - 1] == symb && field[i + 1][j + 1] == symb && field[i + fieldSize - 1][j] == ' ') {
                        field[i + fieldSize - 1][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i  + fieldSize - 1][j] == symb && field[i + 1][j + 1] == symb && field[i][j + fieldSize - 1] == ' ') {
                        field[i][j + fieldSize - 1] = symb;
                        showField();
                        return;
                    }
                }
            }
            // Check block
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize - 2; j++) {
                    // Check block horizontal rectangle
                    // Check 'O' 'O' ' '
                    if (field[i][j] == rival && field[i][j + 1] == rival && field[i][j + 2] == ' ') {
                        field[i][j + 2] = symb;
                        showField();
                        return;
                    }
                    // Check 'O' ' ' 'O'
                    if (field[i][j] == rival && field[i][j + 2] == rival && field[i][j + 1] == ' ') {
                        field[i][j + 1] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'O' 'O'
                    if (field[i][j + 1] == rival && field[i][j + 2] == rival && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    //Check block vertical rectangle
                    // Check 'O' 'O' ' '
                    if (field[j][i] == rival && field[j  + 1][i] == rival && field[j + 2][i] == ' ') {
                        field[j + 2][i] = symb;
                        showField();
                        return;
                    }
                    // Check 'O' ' ' 'O'
                    if (field[j][i] == rival && field[j+ 2][i] == rival && field[j+ 2][i] == ' ') {
                        field[j + 1][i] = symb;
                        showField();
                        return;
                    }
                    // Check ' ' 'O' 'O'
                    if (field[j + 1][i] == rival && field[j + 2][i] == rival && field[j][i] == ' ') {
                        field[j][i] = symb;
                        showField();
                        return;
                    }
                }
            }
            // Check block diagonal
            for(int i = 0; i < fieldSize - 2; i++){
                for(int j = 0; j < fieldSize -2; j++) {
                    if (field[i][j] == rival && field[i + 1][j + 1] == rival && field[i + 2][j + 2] == ' ') {
                        field[i + 2][j + 2] = symb;
                        showField();
                        return;
                    }
                    if (field[i + 1][j + 1] == rival && field[i + 2][j + 2] == rival && field[i][j] == ' ') {
                        field[i][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j] == rival & field[i + 2][j + 2] == rival && field[i + 1][j + 1] == ' ') {
                        field[i + 1][j + 1] = symb;
                        showField();
                        return;
                    }
                    if (field[i][j + fieldSize - 1] == rival && field[i + 1][j + 1] == rival && field[i + fieldSize - 1][j] == ' ') {
                        field[i + fieldSize - 1][j] = symb;
                        showField();
                        return;
                    }
                    if (field[i  + fieldSize - 1][j] == rival && field[i + 1][j + 1] == rival && field[i][j + fieldSize - 1] == ' ') {
                        field[i][j + fieldSize - 1] = symb;
                        showField();
                        return;
                    }
                }
            }
            while(true){
                indexI = random.nextInt(3);
                indexJ = random.nextInt(3);
                if(field[indexI][indexJ] == ' '){
                    field[indexI][indexJ] = symb;
                    showField();
                    return;
                }
            }

        }
    }
    /* ----- */
    /*Clear Field*/
    public static void clearField(){
        for(int i = 0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++) {
                field[i][j] = ' ';
            }
        }
    }
    /* Show */
    public static void showField(){
        System.out.println("---------");
        for(int i =0; i < fieldSize; i++){
            System.out.print("| ");
            for(int j = 0; j < fieldSize; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    /*----------*/
}