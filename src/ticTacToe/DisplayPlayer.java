package ticTacToe;

import java.awt.*;

public class DisplayPlayer {

    public static void display() {
        Color moves = new Color(69, 222, 111);
        Color waiting = new Color(222, 85, 63);
        Color notStarted = new Color(179, 191, 216);
        Game.Players combination = Game.getCurrentPlayerName();
        String activeFigure;

        if (Game.getActiveFigure() == Game.CROSS) {
            activeFigure = "X";
        } else {
            activeFigure = "O";
        }

        switch (combination) {
            case NOT_STARTED : {
                UserInterface.whoMoves.setText("Not Started");
                UserInterface.player1.getTextField().setBackground(notStarted);
                UserInterface.player2.getTextField().setBackground(notStarted);
                break;
            }
            case PLAYER1 : {
                UserInterface.whoMoves.setText(UserInterface.player1.getText() + " - " + activeFigure);
                UserInterface.player1.getTextField().setBackground(moves);
                UserInterface.player2.getTextField().setBackground(waiting);
                break;
            }
            case PLAYER2 : {
                UserInterface.whoMoves.setText(UserInterface.player2.getText() + " -  " + activeFigure);
                UserInterface.player1.getTextField().setBackground(waiting);
                UserInterface.player2.getTextField().setBackground(moves);
                break;
            }
        }
    }
}
