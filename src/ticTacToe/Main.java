package ticTacToe;

import java.util.Scanner;
import java.util.Random;

public class Main {
    /* ---Variables--- */
    public static Scanner scanner = new Scanner(System.in);
    public static int fieldSize = 3;
    public static char[][] field = {
                                    {' ', ' ', ' '},
                                    {' ', ' ', ' '},
                                    {' ', ' ', ' '} };
    public static char computer = 'O';
    public static char human = 'X';
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
        if(x < 0 || x >= 3 || y < 0 || y >= 3) {
            System.out.println("Coordinates should be from 1 to 3!");
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
            huamanVsUser();
        }
        else if (firstMode.equals("user")){
            huamanVsSBot(secondMode);
        }
        else if (secondMode.equals("user")){
            botVsHuaman(firstMode);
        }
        else {
            botVsBot(firstMode, secondMode);
        }
    }
    /*Game modes*/
    public static void huamanVsUser(){
        do{
            humanMove('X');
            if(isWin()){ break;}
            humanMove('O');
        }while(!isWin());
    }
    public static void huamanVsSBot(String mode){
        do{
            humanMove('X');
            if(isWin()){ break;}
            easyLevel('O');
        }while(!isWin());
    }
    public static void botVsHuaman(String mode){
        do{
            easyLevel('X');
            if(isWin()){ break;}
            humanMove('O');
        }while(!isWin());
    }
    public static void botVsBot(String firstMode, String secondMode){
        do{
            easyLevel('X');
            if(isWin()){ break;}
            easyLevel('O');
        }while(!isWin());
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
    /* ----- */
    /*Clear Field*/
    public static void clearField(){
        char[][] temp = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '} };
        field = temp;
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