package ticTacToe.ai;

import ticTacToe.game.Cell;

import static ticTacToe.game.Game.*;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class that implements medium level for game - it finished sequence if it found
 */
public class MediumLevel {
    private int string;
    private int row;

    /**
     * method scanning field upside down, left to right and diagonals to find sequence in field that could lead to win
     * of any player
     * @param figure which combination should be checked
     */
    public Cell scan(int figure) {

        Cell cell = scanDown(figure);
        if (cell != null) {
            return cell;
        }
        cell = scanRight(figure);
        if (cell != null) {
            return cell;
        }
        cell = scanDiagonal(figure);
        if (cell != null) {
            return cell;
        }
        return null;
    }

    private Cell scanDown(int value) {
        for( int r = 0; r < game.getFieldSize(); r++) {
            if (game.getFieldValue(0, r) == value &&
                    game.getFieldValue(1, r) == value &&
                    game.getFieldValue(2,r) == EMPTY) {
                string = 2;
                row = r;
                return new Cell(string,row);
            }

            if (game.getFieldValue(1, r) == value &&
                    game.getFieldValue(2, r) == value &&
                    game.getFieldValue(0,r) == EMPTY) {
                string = 0;
                row = r;
                return new Cell(string,row);
            }
        }
        return null;
    }



    private Cell scanRight(int value) {
        for( int s = 0; s < game.getFieldSize(); s++) {
            if ((game.getFieldValue(s, 0) == value) &&
                    (game.getFieldValue(s, 1) == value) &&
                    (game.getFieldValue(s, 2) == EMPTY)) {
                string = s;
                row = 2;
                return new Cell(string,row);
            }

            if (game.getFieldValue(s, 1) == value &&
                    game.getFieldValue(s, 2) == value &&
                    game.getFieldValue(s,0) == EMPTY) {
                string = s;
                row = 0;
                return new Cell(string,row);
            }
        }
        return null;
    }

    private  Cell scanDiagonal(int value) {
        /*
            Check that first diagonal equal and not null
         */
        if (game.getFieldValue(0, 0) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2,2) == EMPTY) {
            string = 2;
            row = 2;
            return new Cell(string,row);
        }


        if (game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2, 2) == value &&
                game.getFieldValue(0,0) == EMPTY) {
            string = 0;
            row = 0;
            return new Cell(string,row);
        }
        /*
            Check that second diagonal equal and not null
         */
        if (game.getFieldValue(0, 2) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(2,0) == EMPTY) {
            string = 2;
            row = 0;
            return new Cell(string,row);
        }


        if (game.getFieldValue(2, 0) == value &&
                game.getFieldValue(1, 1) == value &&
                game.getFieldValue(0,2) == EMPTY) {
            string = 0;
            row = 2;
            return new Cell(string,row);
        }
        return null;
    }
}
