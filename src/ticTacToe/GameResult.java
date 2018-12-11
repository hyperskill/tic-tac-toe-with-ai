package ticTacToe;

import javax.swing.*;

public class GameResult {
    public  void checkGameResult() {
        int result = scanDiagonal();
        if (result != Game.NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        result = scanDown();
        if (result != Game.NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        result = scanRight();
        if (result != Game.NOT_SEQUENCE) {
            printGameResult(result);
            return;
        }

        if (checkGameOver()){
            printGameResult(Game.NOT_SEQUENCE);
        }


    }

    private  int scanDown() {

        for( int row = 0; row < 3; row++) {
            boolean sequenceFound = true;
            for (int string = 1; string < 3; string++) {
                if (Game.getFieldValue(string - 1, row) != Game.getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && Game.getFieldValue(0, row) != Game.NULL) {
                return Game.getFieldValue(0, row);
            }
        }
        return Game.NOT_SEQUENCE;
    }

    private  int scanRight() {

        for (int string = 0; string < 3; string++) {
            boolean sequenceFound = true;
            for (int row = 1; row < 3; row++) {
                if (Game.getFieldValue(string, row - 1) != Game.getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && Game.getFieldValue(string,0) !=Game. NULL) {
                return Game.getFieldValue(string,0);
            }
        }
        return Game.NOT_SEQUENCE;
    }

    private  int scanDiagonal() {
        /*
            Check that first diagonal equal and not null
         */
        if (Game.getFieldValue(0, 0) == Game.getFieldValue(1, 1) &&
                Game.getFieldValue(1, 1) == Game.getFieldValue(2, 2)
                && Game.getFieldValue(1,1) != Game.NULL) {
            return Game.getFieldValue(1,1);
        }
        /*
            Check that second diagonal equal and not null
         */
        if (Game.getFieldValue(0, 2) == Game.getFieldValue(1, 1) &&
                Game.getFieldValue(1, 1) == Game.getFieldValue(2, 0)
                && Game.getFieldValue(1,1) != Game.NULL) {
            return Game.getFieldValue(1,1);
        }
        return Game.NOT_SEQUENCE;
    }

    private  boolean checkGameOver() {
        for (int row = 0; row < 3; row++) {
            for (int string = 0; string < 3; string++) {
                if (Game.getFieldValue(string, row) ==Game.NULL) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printGameResult(int result){
        String winnersName;
        switch (result) {
            case Game.ZERO : {
                if (Game.getFirstPlayerTriggered() == Game.Players.PLAYER1) {
                    winnersName = UserInterface.player2.getText();
                } else {
                    winnersName = UserInterface.player1.getText();
                }

                break;
            }
            case Game.CROSS : {
                if (Game.getFirstPlayerTriggered() == Game.Players.PLAYER1) {
                    winnersName = UserInterface.player1.getText();
                } else {
                    winnersName = UserInterface.player2.getText();
                }
                break;
            }

            case Game.NOT_SEQUENCE : {
                winnersName = "Friendship";
                break;
            }
            default : winnersName = "No one"; break;
        }

        JOptionPane.showMessageDialog(null,
                winnersName + " wins!!!");

        Game.endTheGame();
    }


}
