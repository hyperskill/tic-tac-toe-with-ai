package ticTacToe;

import java.util.Scanner;
public class Main {
    /**
     * Variables
     */
    public static Scanner scanner = new Scanner(System.in);
    public static int fieldSize = 3;
    public static char[][] field = new char[fieldSize][fieldSize];
    public static char computer = 'O';
    public static char human = 'X';
    /**
     * Functions
     */

    /**
     * Check
    */
    public static boolean checkDiagonal(char symb){
        boolean flag = true;
        for(int i = 0; i < fieldSize; i++){
                if(field[i][i] != symb || field[i][fieldSize - i -1] != symb){
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
        for(int i =0; i < fieldSize; i++){
            for(int j = 0; j < fieldSize; j++){
                if(field[i][j] == ' '){
                    return false;
                }
            }

        }
        return true;
    }
    public static void isWin(){
        if(checkLine('X') || checkDiagonal('X')){
            System.out.println("X wins");
        }
        else if(checkLine('O') || checkDiagonal('O')){
            System.out.println("O wins");
        }
        else if(checkDraw()){
            System.out.println("Draw");
        }
        else{
            System.out.println("Game not finished");
        }
        System.out.println();
    }
    /**
     * Move
     */
    public static void humanMove(){
        System.out.print("Enter the coordinates: ");
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        field[x][y ] = human;
}
    /**
     *Show
     */
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

    /**
     * Main
     */
    public static void main(String[] args) {
        char[][] arr = {
                {'X', ' ', 'X'},
                {' ', 'O', ' '},
                {' ', ' ', ' '}
        };
        field = arr;
        showField();
        humanMove();
        showField();
        //isWin();
       /* char[][] arr1 = {
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };
        field = arr1;
        showField();
        isWin();
        char[][] arr2 = {
                {'X', 'O', 'O'},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };
        field = arr2;
        showField();
        isWin();
        char[][] arr3 = {
                {'X', 'O', 'X'},
                {'O', 'O', 'X'},
                {'X', 'X', 'O'}
        };
        field = arr3;
        showField();
        isWin();
        char[][] arr4 = {
                {'X', 'O', ' '},
                {'O', 'O', 'X'},
                {' ', 'X', ' '}
        };
        field = arr4;
        showField();
        isWin();*/
   }
}