package ticTacToe.ai;

import ticTacToe.game.GameResult;
import ticTacToe.ui.UserInterface;

import java.util.List;
import java.util.Random;

import static ticTacToe.game.Game.*;


/**
 *  Class for making moves against human
 */
public class ComputerRival {

    public static LearningAlgorithm learningAlgorithm = new LearningAlgorithm();

     /**
     * Easy level method - randomly selects cell in field
     */
    public static void easy() {
        List<GameResult.Cell> emptyCells = new GameResult().emptyCells();
        GameResult.Cell cell;
        int size = emptyCells.size();
        if (size > 0) {
            cell = emptyCells.get(new Random().nextInt(size));
        } else {
            return;
        }

        dataUpdate(cell.s, cell.r);
    }

    /**
     *  Hard Level makes first move randomly, in others used minimax algorithm
     */
    public static void hard() {
        if (new GameResult().emptyCells().size() > 8) {
            easy();
            return;
        }

        GameResult.Cell cell = new MiniMax().minimax(getFieldValues(),getActiveFigure());
        dataUpdate(cell.s, cell.r);
    }

    public static void learning() {
        GameResult.Cell cell = learningAlgorithm.makeMove(getFieldValues());

        dataUpdate(cell.s, cell.r);
    }


    /**
     * This method updates a text inside a button after computer makes a move, and disable it for user
     *
     * @param string number in field matrix
     * @param row number in field matrix
     */
    public static void dataUpdate(int string, int row) {
        nextMove(string, row, getActiveFigure(),true);
        UserInterface.getButton(string, row).printFieldElement();
        UserInterface.getButton(string, row).setButtonEnabled(false);
    }
}
