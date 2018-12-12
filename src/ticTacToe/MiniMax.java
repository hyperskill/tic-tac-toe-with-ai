package ticTacToe;

import java.util.List;

import static ticTacToe.Game.*;

public class MiniMax extends GameResult {

    public Cell minimax (int[][] field, int activeFigure) {
        Cell cell = new Cell(0,0);
        int computer;
        int human;
        int nodeRate;
        int bestNodeIndex = 0;

        List<Cell> emptyCells = new GameResult().emptyCells();

        if ( getFirstPlayerTriggered() == Players.PLAYER2 ) {
            computer = CROSS;
            human = ZERO;
        } else {
            computer = ZERO;
            human = CROSS;
        }

        if (new GameResult().win(field, computer)) {
            cell.rate = 10;
            return
        } else if (new GameResult().win(field, human)) {
            return -10;
        } else if (emptyCells.isEmpty()) {
            return 0;
        }

        for ( int i = 0; i < emptyCells.size(); i++) {
            GameResult.Cell cell= emptyCells.get(i);
            field[cell.s][cell.r] = activeFigure;

            if (activeFigure == computer) {
                cell.rate = minimax(field,human).rate ;
            } else {
                cell.rate  = minimax(field,computer).rate ;
            }
            field[cell.s][cell.r] = EMPTY;
            emptyCells.set(i, cell);
        }

        if (activeFigure == computer) {
            nodeRate = -100;
        } else {
            nodeRate = 100;
        }

        for (int i = 0; i < emptyCells.size(); i++) {
            int rate = emptyCells.get(i).rate;
            if (activeFigure == computer) {
                if (rate > nodeRate) {
                    nodeRate = rate;
                    bestNodeIndex = i;
                }
            } else {
                if (nodeRate > rate) {
                    nodeRate = rate;
                    bestNodeIndex = i;
                }
            }
        }

        return emptyCells.get(bestNodeIndex);
    }
}
