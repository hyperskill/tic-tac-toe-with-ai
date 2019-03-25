package ticTacToe.hardlevels;

import ticTacToe.AiPlayer;
import ticTacToe.Main;
import ticTacToe.Player;

public class HardLevelStrategy extends EasyLevelStrategy {
    Player player;
    Player virtualPlayer;
    @Override
    public boolean move(char[][] matrix, Player player) {
        this.player = player;
        this.virtualPlayer = new AiPlayer(player.getSymbol() == Main.X ? Main.O : Main.X);
        int[] move = minimax(matrix, player);
        player.set(matrix, move[0], move[1], player.getSymbol());
        System.out.println("Making move level \"hard\"");
        return true;
    }
    private int[] minimax(char[][] matrix, Player turnPlayer){
        int[][] spots = getEmptyCoords(matrix);

        int bestScore = player == turnPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int x = -1;
        int y = -1;
        int currentScore;
        int result = evaluate(matrix);

        if(spots.length == 0 || result != -2){
            bestScore =  result;
        } else{
            for (int[] spot: spots) {
                turnPlayer.set(matrix, spot[0], spot[1], turnPlayer.getSymbol());
                if(player == turnPlayer){
                    currentScore = minimax(matrix, virtualPlayer)[2];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        x = spot[0];
                        y = spot[1];
                    }
                } else {
                    currentScore = minimax(matrix, player)[2];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        x = spot[0];
                        y = spot[1];
                    }
                }
                matrix[spot[0]][spot[1]] = Main.EMPTY;
            }
        }
        //System.out.println("minimax: " + Arrays.deepToString(matrix) + "(" + x + "," + y + ")" + ":" + bestScore);
        return new int[] {x, y, bestScore};
    }

    private int evaluate(char[][] matrix){
        String result = Main.detectState(matrix);
        switch (result){
            case Main.STATE_WIN_O:
                if(player.getSymbol() == Main.O){
                    return 1;
                } else{
                    return -1;
                }
            case Main.STATE_WIN_X:
                if(player.getSymbol() == Main.X){
                    return 1;
                } else{
                    return -1;
                }
            case Main.STATE_DRAW:
                return 0;
        }
        return -2;
    }
}
