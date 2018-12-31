package ticTacToe;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        char[] state = {'X', 'O', ' ', 'O', ' ', 'X', 'X', 'X', 'O'};

        for (int i = 0; i < state.length; i++) {
            System.out.print(state[i] + " ");
            if ((i+1)%3 == 0){
                System.out.println();
            }
        }
    }
}