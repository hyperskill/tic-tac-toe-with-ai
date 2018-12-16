package ticTacToe;

import java.util.List;

public class LearningAlgorithm {
    public void makeMove(int[][] field){
        List<GameResult.Cell> emptyCells = new GameResult().emptyCells();
        int currentRate = Integer.MIN_VALUE;
        int selectedMoveIndex = 0;

        for (int i = 0; i < emptyCells.size(); i++) {
            GameResult.Cell cell = emptyCells.get(i);
            field[cell.s][cell.r] = Game.getActiveFigure();
           // cell.rate = getRate(field);
            if (cell.rate > currentRate) {
                selectedMoveIndex = i;
            }
            field[cell.s][cell.r] = Game.EMPTY;
        }
    }

}
