package ticTacToe;

import java.util.Random;

import static ticTacToe.Game.*;


public class ComputerRival {

    public void makeMove(){
        if( getCurrentPlayerName() == Players.PLAYER2 &&
        isPlayWithComputerTriggered()) {
            switch (getLevel()) {
                case EASY: easy(); break;
                case MEDIUM: new MediumLevel().medium(); break;
                case HARD: hard(); break;
            }

        }
        new GameResult().checkGameResult();
    }

    public void easy() {
        int string;
        int row;
        Random rand = new Random();
        int cell;
        do {
            string = rand.nextInt(3);
            row = rand.nextInt(3);
            cell= getFieldValue(string, row);
        } while (cell != NULL);
        dataUpdate(string, row);
    }


    private void hard() {

        //dataUpdate(string, row);
    }


    public static void dataUpdate(int string, int row) {
        setFieldValue(string, row, getActiveFigure(),true);
        UserInterface.button[string][row].printFieldElement();
        UserInterface.button[string][row].setButtonEnabled(false);
    }
}
