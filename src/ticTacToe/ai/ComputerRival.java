package ticTacToe.ai;

import ticTacToe.game.Cell;
import ticTacToe.game.GameResult;
import ticTacToe.ui.UserInterface;

import java.util.List;
import java.util.Random;

import static ticTacToe.game.Game.CROSS;
import static ticTacToe.game.Game.ZERO;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class for making moves against human or another computer
*/
public class ComputerRival {

     /**
     * Easy level method - randomly selects cell in field
      * @param field where should be found a coordinate of move
      * @return move coordinate Cell
     */
    public Cell easy(int[][] field) {
        List<Cell> emptyCells = new GameResult().emptyCells(field);
        int size = emptyCells.size();
        if (size > 0) {
            return emptyCells.get(new Random().nextInt(size));
        } else {
            return null;
        }
    }

    /**
     * method calls scanning of sequence, if it not found making a random move
     * @return move coordinate Cell
     * @see ComputerRival
     */
    public Cell medium() {
        MediumLevel mediumLevel = new MediumLevel();
        int valueOfComputer = game.getActiveFigure();
        int valueOfHuman;
        Cell cell = mediumLevel.scan(valueOfComputer);
        if (cell != null) {
            return cell;
        }

        if (valueOfComputer == CROSS) {
            valueOfHuman = ZERO;
        } else {
            valueOfHuman = CROSS;
        }

        cell = mediumLevel.scan(valueOfHuman);
        if (cell != null) {
            return cell;
        }

        return easy(game.getFieldValues());
    }

    /**
     *  Hard level using minimax algorithm at small field sizes
     * @param field where should be found a coordinate of move
     * @param activeFigure figure which should moves now
     * @param playerFigure is a figure of player which considering
     *                     mininmax algorithm
     * @return move coordinate Cell
     */
    public Cell hard(int[][] field, int activeFigure, int playerFigure) {

        if (new GameResult().emptyCells(game.getFieldValues()).size() > 8) {
            return easy(game.getFieldValues());
        }


        Cell cell = new MiniMax().minimax(field, activeFigure, playerFigure);
        return cell;
    }

    /**
     *  If learning algorithm now is free and loaded it used.
     *  @return move coordinate Cell
     */
    public Cell learning() {
        if (game.isLearningInProcess()) {
            return hard(game.getFieldValues(), game.getActiveFigure(), game.getActiveFigure());
        } else {
            if ( game.learningAlgorithm.isLoadedFromFile()) {
                return game.learningAlgorithm.makeMove(game.getFieldValues());
            } else {
                return hard(game.getFieldValues(), game.getActiveFigure(), game.getActiveFigure());
            }
        }
    }

    /**
     * This method updates a text inside a button after computer makes a move, and disable it for user
     *
     * @param string number in field matrix
     * @param row number in field matrix
     */
    public static void dataUpdate(int string, int row) {
        UserInterface.getButton(string, row).printFieldElement();
        UserInterface.getButton(string, row).setButtonEnabled(false);
        game.nextMove(string, row, game.getActiveFigure());
    }
}
