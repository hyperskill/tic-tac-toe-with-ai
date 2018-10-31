package ticTacToe;
import java.util.*;
public class TicTacToe {
     System.out.print("Welcome Tic-Tac-Toe");
     static String[] board;
     board = new String[9];
    Random generator = new Random();
    public static void main(String[] args) {
       for (int i=0;i<9;i++)
           {
               if(generator.nextInt()%3==0)
               {board[i]='-';}
               else if(generator.nextInt()%3==1)
               {board[i]='O';}
               else
               {board[i]='X';}
           }
        System.out.println(board[0]+" "+board[1]+" "+board[2]);
        System.out.println(board[3]+" "+board[4]+" "+board[5]);
        System.out.println(board[6]+" "+board[7]+" "+board[8]);
        String state = checkWinner();
         if(state=="X")
         {
              System.out.println("X wins");
         }
         else if(state=="O")
         {
              System.out.println("O wins");
         }
         else if(state=="Draw")
         {
              System.out.println("Draw");
         }
         else
         {
              System.out.println("Game not finished");
         }   
      
    }
     static String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a+1))) {
				break;
			}
			else if (a == 8) return "Draw";
		}
		return null;
	}
}
