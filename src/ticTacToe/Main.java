package ticTacToe;
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
        drawState(state,n);
        System.out.println("Enter he coordinates: ");
        int indexI = scan.nextInt() - 1;
        int indexJ = scan.nextInt() - 1;
        state[indexI][indexJ] = 'X';
        drawState(state,n);
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
}