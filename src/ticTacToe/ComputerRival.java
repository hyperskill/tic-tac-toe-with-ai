package ticTacToe;

import java.util.Random;

public class ComputerRival {
    public void makeMove(){
        if( Game.getCurrentPlayerName() == Game.Players.PLAYER2 &&
        Game.isPlayWithComputerTriggered()) {
            easy();
        }
        new GameResult().checkGameResult();
    }

    private void easy() {
        Random rand = new Random();
        int cell;
        int string;
        int row;
        do {
            string = rand.nextInt(3);
            row = rand.nextInt(3);
            cell= Game.getFieldValue(string, row);
        } while (cell != Game.NULL);
        dataUpdate(string, row);
    }

    private void dataUpdate(int string, int row) {
        Game.setFieldValue(string, row, Game.getActiveFigure(),true);
        UserInterface.button[string][row].printFieldElement();
        UserInterface.button[string][row].setButtonEnabled(false);
    }
}
