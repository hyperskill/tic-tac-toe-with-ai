package ticTacToe;

import java.util.Random;

import static ticTacToe.Game.*;


/**
 *  Class for making moves against human
 */
public class ComputerRival {

    /**
     * Base method of class, after calling selects behaviour depending of settings
     *
     * @see MediumLevel
     * @see GameResult
     */
    public void makeMove(){
        if (getCurrentPlayerName() == Players.PLAYER2 && isPlayWithComputerTriggered()) {
            switch (getLevel()) {
                case EASY: easy(); break;
                case MEDIUM: new MediumLevel().medium(); break;
                case HARD: hard(); break;
            }

        }
        new GameResult().checkGameResult();
    }

    /**
     * Easy level method - randomly selects cell in field
     */
    public void easy() {
        int string;
        int row;
        Random rand = new Random();
        int cell;
        do {
            string = rand.nextInt(3);
            row = rand.nextInt(3);
            cell= getFieldValue(string, row);
        } while (cell != EMPTY);
        dataUpdate(string, row);
    }

    /**
     *  Hard Level makes first move randomly, in others used minimax algorithm
     */
    private void hard() {
        if (new GameResult().emptyCells().size() == 9) {
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
