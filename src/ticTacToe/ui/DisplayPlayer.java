package ticTacToe.ui;

import ticTacToe.game.Game;
import ticTacToe.game.Player;

import java.awt.*;

import static ticTacToe.ui.UserInterface.game;

/**
 *  Class for indicate which player should moves now
 */
public class DisplayPlayer {

    /**
     *  Base method which changed name of active player in text field "Now moves"
     *  and coloring names of players depending of game turn
     */
    public void display() {
        Color moves = new Color(69, 222, 111);
        Color waiting = new Color(222, 85, 63);
        Color notStarted = new Color(179, 191, 216);
        String activeFigure;
        Player active = game.getCurrentPlayer();

        if (game.getActiveFigure() == Game.ZERO) {
            activeFigure = "O";
        } else {
            activeFigure = "X";
        }

        if (active == null) {
            UserInterface.getWhoMoves().setText("Not Started");
            UserInterface.getPlayer1().getTextField().setBackground(notStarted);
            UserInterface.getPlayer2().getTextField().setBackground(notStarted);
        } else {
            if (active == game.getPlayer(1)) {
                UserInterface.getWhoMoves().setText(UserInterface.getPlayer1().getText() + " - " + activeFigure);
                UserInterface.getPlayer1().getTextField().setBackground(moves);
                UserInterface.getPlayer2().getTextField().setBackground(waiting);
            } else {
                UserInterface.getWhoMoves().setText(UserInterface.getPlayer2().getText() + " -  " + activeFigure);
                UserInterface.getPlayer1().getTextField().setBackground(waiting);
                UserInterface.getPlayer2().getTextField().setBackground(moves);
            }
        }
    }
}
