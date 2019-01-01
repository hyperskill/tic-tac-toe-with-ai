package ticTacToe;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        char [][]state = new char[][] {
                        {'X', 'X', ' '},
                        {'O', 'O', ' '},
                        {' ', 'O', ' '}
        };
        boolean flag = false;
        int stepRow = 1;
        int stepCol = 1;
        for (int i = 0; i < n; i++) {
            stepRow = 1;
            stepCol = 1;
            for (int j = 0; j < n - 1; j++) { //check for rows
                if (state[i][j] == state[i][j+1]){
                    stepRow++;
                }
                else {
                    break;
                }
                if (stepRow == 3){
                    if (state[i][j] == 'X'){
                        System.out.println("X wins");
                        flag = true;
                    }
                    if (state[i][j] == 'O') {
                        System.out.println("O wins");
                        flag = true;
                    }
                }
            }
            if (flag){
                break;
            }
            for (int j = 0; j < n - 1; j++){//check for col
                if (state[j][i] == state[j+1][i]){
                    stepCol++;
                }
                else {
                    break;
                }
                if (stepCol == 3){
                    if (state[j][i] == 'X'){
                        System.out.println("X wins");
                        flag = true;
                    }
                    if (state[j][i] == 'O'){
                        System.out.println("O wins");
                        flag = true;
                    }
                }
            }
            if (flag){
                break;
            }
            if(state[0][0] == state[1][1] && state[0][0] == state[2][2] ||
               state[0][2] == state[1][1] && state[1][1] == state[2][0]){
                if (state[1][1] == 'X'){
                    System.out.println("X wins");
                    flag = true;
                }
                if (state[1][1] == 'O') {
                    System.out.println("O wins");
                    flag = true;
                }
            }
            if (flag){
                break;
            }
        }

        if(!flag){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (state[i][j] == ' '){
                        flag = true;
                        System.out.println("Game not finished");
                        break;
                    }
                }
                if (flag){
                    break;
                }
            }
            if (!flag){
                System.out.println("Draw");
            }
        }
    }
}