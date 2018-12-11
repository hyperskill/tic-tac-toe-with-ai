package ticTacToe;

import javax.swing.*;

import static ticTacToe.Game.*;

public class GameResult {
    public  void checkGameResult() {
        int result = scanDiagonal();
        if (result != NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        result = scanDown();
        if (result != NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        result = scanRight();
        if (result != NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        if (checkGameOver()){
            printGameResult(NOT_SEQUENCE);
        }


    }

    private  int scanDown() {

        for( int row = 0; row < 3; row++) {
            boolean sequenceFound = true;
            for (int string = 1; string < 3; string++) {
                if (getFieldValue(string - 1, row) != getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && getFieldValue(0, row) != NULL) {
                return getFieldValue(0, row);
            }
        }
        return NOT_SEQUENCE;
    }

    private  int scanRight() {

        for (int string = 0; string < 3; string++) {
            boolean sequenceFound = true;
            for (int row = 1; row < 3; row++) {
                if (getFieldValue(string, row - 1) != getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && getFieldValue(string,0) != NULL) {
                return getFieldValue(string,0);
            }
        }
        return NOT_SEQUENCE;
    }

    private  int scanDiagonal() {
        /*
            Check that first diagonal equal and not null
         */
        if (getFieldValue(0, 0) == getFieldValue(1, 1) &&
                getFieldValue(1, 1) == getFieldValue(2, 2)
                && getFieldValue(1,1) != NULL) {
            return getFieldValue(1,1);
        }
        /*
            Check that second diagonal equal and not null
         */
        if (getFieldValue(0, 2) == getFieldValue(1, 1) &&
                getFieldValue(1, 1) == getFieldValue(2, 0)
                && getFieldValue(1,1) != NULL) {
            return getFieldValue(1,1);
        }
        return NOT_SEQUENCE;
    }

    private  boolean checkGameOver() {
        for (int row = 0; row < 3; row++) {
            for (int string = 0; string < 3; string++) {
                if (getFieldValue(string, row) ==NULL) {
                    return false;
                }
            }
        }
        return true;
    }

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

            case NOT_SEQUENCE : {
                winnersName = "Friendship";
                break;
            }
            default : winnersName = "No one"; break;
        }

        JOptionPane.showMessageDialog(null,
                winnersName + " wins!!!");

        endTheGame();
    }


}
