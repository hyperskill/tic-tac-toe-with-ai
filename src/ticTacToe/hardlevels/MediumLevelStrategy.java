package ticTacToe.hardlevels;

import ticTacToe.Main;
import ticTacToe.Player;

public class MediumLevelStrategy extends EasyLevelStrategy {
    @Override
    public boolean move(char[][] matrix, Player player) {
        RowAnalyzer row;
        int[] lastSpot;
        char symbol = player.getSymbol();
        char oppositSymbol = symbol == Main.X ? Main.O : Main.X;
        for (int x = 0; x < matrix.length; x++) {
            row = RowAnalyzer.col(matrix, x);
            if(requestLast(matrix, row, player, symbol, oppositSymbol)){
                return true;
            }
        }

        for (int y = 0; y < Main.ROW; y++) {
            row = RowAnalyzer.row(matrix, y);

            lastSpot = row.getOneLeftorNull(symbol);
            if(requestLast(matrix, row, player, symbol, oppositSymbol)){
                return true;
            }
        }


        /*Diagonal 1*/
        row = RowAnalyzer.diagonalLeftTop(matrix);
        if(requestLast(matrix, row, player, symbol, oppositSymbol)){
            return true;
        }

        /*Diagonal 2*/
        row = RowAnalyzer.diagonalRightTop(matrix);
        if(requestLast(matrix, row, player, symbol, oppositSymbol)){
            return true;
        }

        return randomMove(matrix, player, "medium");
    }

    private boolean requestLast(char[][] matrix, RowAnalyzer row, Player player, char symbol, char oppositSymbol){
        int[] lastSpot = row.getOneLeftorNull(symbol);
        if(lastSpot != null){
            System.out.println("Making move level \"medium\"");
            return player.set(matrix, lastSpot[0], lastSpot[1], symbol);
        }

        lastSpot = row.getOneLeftorNull(oppositSymbol);
        if(lastSpot != null){
            System.out.println("Making move level \"medium\"");
            return player.set(matrix, lastSpot[0], lastSpot[1], symbol);
        }
        return false;
    }


}
