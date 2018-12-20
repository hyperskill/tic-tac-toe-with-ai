package ticTacToe.ai;

import static ticTacToe.game.Game.*;
import static ticTacToe.game.Game.CROSS;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class that implements medium level for game - it finished sequence if it found
 */
public class MediumLevel {
    private int string;
    private int row;

    /**
     * method calls scanning for sequence, if it not found making a random move
     * @see ComputerRival
     */
    public void medium() {
        int valueOfComputer = game.getActiveFigure();
        int valueOfHuman;

        if (scan(valueOfComputer)) {
            return;
        }

        if (valueOfComputer == CROSS) {
            valueOfHuman = ZERO;
        } else {
            valueOfHuman = CROSS;
        }

        if (scan(valueOfHuman)) {
            return;
        }

        ComputerRival.easy();
    }

    /**
     * method scanning field upside down, left to right and diagonals to find sequence in field that could lead to win
     * of any player
     * @param value which combination should be checked
     */
    private boolean scan(int value) {
        if (scanDown(value)) {
            ComputerRival.dataUpdate(string, row);
            return true;
        }

        if (scanRight(value)) {
            ComputerRival.dataUpdate(string, row);
            return true;
        }

        if (scanDiagonal(value)) {
            ComputerRival.dataUpdate(string, row);
            return true;
        }
        return false;
    }

    private boolean scanDown(int value) {
        for( int r = 0; r < game.getFieldSize(); r++) {
            if (game.getFieldValue(0, r) == value &&
                    game.getFieldValue(1, r) == value &&
                    game.getFieldValue(2,r) == EMPTY) {
                string = 2;
                row = r;
                return true;
            }

            if (game.getFieldValue(1, r) == value &&
                    game.getFieldValue(2, r) == value &&
                    game.getFieldValue(0,r) == EMPTY) {
                string = 0;
                row = r;
                return true;
            }
        }
        return false;
    }



    private boolean scanRight(int value) {
        for( int s = 0; s < game.getFieldSize(); s++) {
            if ((game.getFieldValue(s, 0) == value) &&
                    (game.getFieldValue(s, 1) == value) &&
                    (game.getFieldValue(s, 2) == EMPTY)) {
                string = s;
                row = 2;
                return true;
            }

            if (game.getFieldValue(s, 1) == value &&
                    game.getFieldValue(s, 2) == value &&
                    game.getFieldValue(s,0) == EMPTY) {
                string = s;
                row = 0;
                return true;
            }
        }
        return false;
    }

    private  boolean scanDiagonal(int value) {
        /*
            Check that first diagonal equal and not null
         */
        if (game.getFieldValue(0, 0) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2,2) == EMPTY) {
            string = 2;
            row = 2;
            return true;
        }


        if (game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2, 2) == value &&
                game.getFieldValue(0,0) == EMPTY) {
            string = 0;
            row = 0;
            return true;
        }
        /*
            Check that second diagonal equal and not null
         */
        if (game.getFieldValue(0, 2) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2,0) == EMPTY) {
            string = 2;
            row = 0;
            return true;
        }


        if (game.getFieldValue(2, 0) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(0,2) == EMPTY) {
            string = 0;
            row = 2;
            return true;
        }
        return false;
    }
}
