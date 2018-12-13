package ticTacToe;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static ticTacToe.Game.*;

/**
 *  Class for field analyse- detects winner and wnd of game
 */

public class GameResult {


    /**
     * Method checks game win and tie for players 1 and 2
     */
    public  void checkGameResult() {
        for (int i = 0; i < 2; i++ ){
            if (win(getFieldValues(), i)) {
                printGameResult(i);
                return;
            }
        }
        if (emptyCells().isEmpty()) {
            printGameResult(EMPTY);
        }
    }

    /**
     * Method gets field and check all winning states for given figure
     * @param field game field that should be checked
     * @param figure figure for which game result should be checked
     * @return true if winning combination for given figure exists in field
     */
    public boolean win(int[][] field, int figure) {
        if (
                (field[0][0] == figure && field[1][1] == figure && field[2][2] == figure) || //diagonal 1
                (field[2][0] == figure && field[1][1] == figure && field[0][2] == figure) || //diagonal 2
                (field[0][0] == figure && field[0][1] == figure && field[0][2] == figure) || //string 1
                (field[1][0] == figure && field[1][1] == figure && field[1][2] == figure) || //string 2
                (field[2][0] == figure && field[2][1] == figure && field[2][2] == figure) || //string 3
                (field[0][0] == figure && field[1][0] == figure && field[2][0] == figure) || //row 1
                (field[0][1] == figure && field[1][1] == figure && field[2][1] == figure) || //row 2
                (field[0][2] == figure && field[1][2] == figure && field[2][2] == figure)    //row 3
        ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method creates list of cells free to move
     *
     * @return list of cells
     */
    public List<Cell> emptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int string = 0; string < 3; string++) {
                if (getFieldValue(string, row) == EMPTY) {
                    emptyCells.add(new Cell(string,row));
                }
            }
        }
        return emptyCells;
    }

    /**
     * Open new information window according game result
     * @param result figure that win the game
     */
    private void printGameResult(int result){
        String winnersName;
        switch (result) {
            case ZERO : {
                if (getFirstPlayerTriggered() == Players.PLAYER1) {
                    winnersName = UserInterface.player2.getText();
                } else {
                    winnersName = UserInterface.player1.getText();
                }
                break;
            }
            case CROSS : {
                if (getFirstPlayerTriggered() == Players.PLAYER1) {
                    winnersName = UserInterface.player1.getText();
                } else {
                    winnersName = UserInterface.player2.getText();
                }
                break;
            }

            case EMPTY : {
                winnersName = "Friendship";
                break;
            }
            default : winnersName = "No one"; break;
        }

        JOptionPane.showMessageDialog(null,
                winnersName + " wins!!!");

        endTheGame();
    }

    /**
     * Class that contain coordinate and rate of cell(using for minimax algorithm)
     *
     * @see MiniMax
     */
    public class Cell {
        int s;
        int r;
        int rate;

        Cell(int string, int row) {
            this.s = string;
            this.r = row;
        }
    }
}
