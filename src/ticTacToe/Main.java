package ticTacToe;
import java.io.Reader;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int n = 3;
        char [][]state = new char[][] {
                {' ', 'O', 'X'},
                {' ', 'O', ' '},
                {' ', ' ', 'X'}
        };

        //inputHuman(state, n);
        generateEasy(state, n);
    }

    static void drawState(char[][] state, int size){
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

    static boolean checkSell(char[][] state, int indexI, int indexJ){
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

    static void inputHuman(char[][]state, int n){
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
        drawState(state,n);
    }

    static void generateEasy(char[][]state, int n){
        drawState(state, n);
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        do {
            int randI = random.nextInt(3);
            int randJ = random.nextInt(3);

            if(state[randI][randJ] == ' '){
                state[randI][randJ] = 'X';
                break;
            }
        }while (true);
        drawState(state, n);
    }
}