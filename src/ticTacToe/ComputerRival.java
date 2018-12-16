package ticTacToe;

import java.util.List;
import java.util.Random;

import static ticTacToe.Game.*;


/**
 *  Class for making moves against human
 */
public class ComputerRival {

     /**
     * Easy level method - randomly selects cell in field
     */
    public void easy() {
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
    public void hard() {
        if (new GameResult().emptyCells().size() > 20) {
            easy();
            return;
        }

        GameResult.Cell cell = new MiniMax().minimax(getFieldValues(),getActiveFigure());
        dataUpdate(cell.s, cell.r);
    }

    /**
     * This method updates a text inside a button after computer makes a move, and disable it for user
     *
     * @param string number in field matrix
     * @param row number in field matrix
     */
    public static void dataUpdate(int string, int row) {
        setFieldValue(string, row, getActiveFigure(),true);
        UserInterface.button[string][row].printFieldElement();
        UserInterface.button[string][row].setButtonEnabled(false);
    }
}
