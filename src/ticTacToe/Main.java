package ticTacToe;

public class TicTacToe {
     System.out.print("Welcome Tic-Tac-Toe");
        public static char board [][] = new char [3][3];
    
    public static void main(String[] args) {
       for (int i=0;i<3;i++)
           for(int j=0;j<3;j++)
               board[i][j]='-';
        board[0][0]='O';
        board[0][1]='X';
        board[0][2]='O';
        board[1][0]='X';
        board[1][1]='O';
        board[1][2]='X';
        board[2][0]='O';
        board[2][1]='O';
        board[2][2]='X';
    }
}
