package ticTacToe.ai;

import ticTacToe.game.Game;
import ticTacToe.game.GameResult;

import java.util.List;

import static ticTacToe.game.Game.*;

/**
 *  Class that implements algorithm minimax to find the best move *
 */
public class MiniMax extends GameResult {


    /**
     * minimax method that completes recursively until the terminal game state will not found
     *
     * a result of this algorithm work is a tree with all possible game combinations from which selected move with
     * minimum probability of win for user and maximum for computer
     *
     * @param field game field that should be analysed
     * @param activeFigure figure that should moves now
     * @return cell with best move coordinates and it rate
     */
    public Cell minimax (int[][] field, int activeFigure) {
        Cell cell = new Cell(0,0);
        int computer;
        int rival;
        int nodeRate;
        int bestNodeIndex = 0;

        List<Cell> emptyCells = emptyCells();


        if ( Game.getCurrentPlayer().getFigure() == CROSS ) {
            computer = CROSS;
            rival = ZERO;
        } else {
            computer = ZERO;
            rival = CROSS;
        }

        if (win(field, computer)) {
            cell.rate = 10;
            return cell;
        } else if (win(field, rival)) {
            cell.rate = -10;
            return cell;
        } else if (emptyCells.isEmpty()) {
            cell.rate = 0;
            return cell;
        }

        for ( int i = 0; i < emptyCells.size(); i++) {
            cell= emptyCells.get(i);
            field[cell.s][cell.r] = activeFigure;

            if (activeFigure == computer) {
                cell.rate = minimax(field,rival).rate ;
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
