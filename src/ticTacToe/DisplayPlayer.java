package ticTacToe;

import java.awt.*;

/**
 *  Class for indicate which player should move now
 */
public class DisplayPlayer {


    /**
     *  Base method which changed name of active player in text field "Now moves"
     *  and coloring names of players depending of game turn
     */
    public static void display() {
        Color moves = new Color(69, 222, 111);
        Color waiting = new Color(222, 85, 63);
        Color notStarted = new Color(179, 191, 216);

        Player active = Game.getCurrentPlayer();

        if (active == null) {
            UserInterface.whoMoves.setText("Not Started");
            UserInterface.player1.getTextField().setBackground(notStarted);
            UserInterface.player2.getTextField().setBackground(notStarted);
        } else {
            if (active == Game.player1) {
                UserInterface.whoMoves.setText(UserInterface.player1.getText() + " - " + Game.player1.getFigure());
                UserInterface.player1.getTextField().setBackground(moves);
                UserInterface.player2.getTextField().setBackground(waiting);
            } else {
                UserInterface.whoMoves.setText(UserInterface.player2.getText() + " -  " + Game.player1.getFigure());
                UserInterface.player1.getTextField().setBackground(waiting);
                UserInterface.player2.getTextField().setBackground(moves);
            }
        }
    }
}
