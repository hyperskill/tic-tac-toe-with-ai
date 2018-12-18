package ticTacToe.game;

import ticTacToe.ai.ComputerRival;
import ticTacToe.ai.MiniMax;
import ticTacToe.ui.UserInterface;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static ticTacToe.game.Game.*;

/**
 *  Class for field analyse and winner detecting
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
        for (int s = 0; s < getFieldSize(); s++) {
            for(int r = 0; r < getFieldSize(); r++) {
                if (field[s][r] != figure) {
                    break;
                }
                if (r == getFieldSize() - 1) {
                    return true;
                }
            }
        }

        for (int r = 0; r < getFieldSize(); r++) {
            for(int s = 0; s < getFieldSize(); s++) {
                if (field[s][r] != figure) {
                    break;
                }
                if (s == getFieldSize() - 1) {
                    return true;
                }
            }
        }

        for (int s = 0; s < getFieldSize(); s++) {
            if (field[s][s] != figure) {
                break;
            }
            if (s == getFieldSize() - 1) {
                return true;
            }
        }

        int r = 0;
        for (int s = getFieldSize()-1; s >= 0; s--) {

            if (field[s][r] != figure) {
                break;
            }
            if (r == getFieldSize() - 1) {
                return true;
            }
            r++;
        }
        return false;
    }

    /**
     * Method creates list of cells free to move
     *
     * @return list of cells
     */
    public List<Cell> emptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < getFieldSize(); row++) {
            for (int string = 0; string < getFieldSize(); string++) {
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
                if (getPlayer(1).getFigure() == ZERO) {
                    winnersName = UserInterface.getPlayer1().getText();
                } else {
                    winnersName = UserInterface.getPlayer2().getText();
                }
                break;
            }
            case CROSS : {
                if (getPlayer(1).getFigure() == CROSS) {
                    winnersName = UserInterface.getPlayer1().getText();
                } else {
                    winnersName = UserInterface.getPlayer2().getText();
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

        ComputerRival.learningAlgorithm.writeResults(result);

        endTheGame();
    }

    /**
     * Class that contain coordinate and rate of cell(using for minimax algorithm)
     *
     * @see MiniMax
     */
    public class Cell {
        public int s;
        public int r;
        public int rate;

        public Cell(int string, int row) {
            this.s = string;
            this.r = row;
            this.rate = 0;
        }
    }
}
