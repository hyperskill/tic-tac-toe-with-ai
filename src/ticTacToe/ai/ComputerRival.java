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
 *  Class for making moves against human
 */
public class ComputerRival {

    public static LearningAlgorithm learningAlgorithm = new LearningAlgorithm();

     /**
     * Easy level method - randomly selects cell in field
     */
    public static Cell easy(int[][] field) {
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
     * @see ComputerRival
     */
    public static Cell medium() {
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

        return ComputerRival.easy(game.getFieldValues());
    }

    /**
     *  Hard Level makes first move randomly, in others used minimax algorithm
     */
    public static Cell hard() {
        if (new GameResult().emptyCells(game.getFieldValues()).size() > 8) {
            return easy(game.getFieldValues());
        }

        Cell cell = new MiniMax().minimax(game.getFieldValues(), game.getActiveFigure());
        return cell;
    }

    public static Cell learning() {
        return learningAlgorithm.makeMove(game.getFieldValues());
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
