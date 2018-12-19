package ticTacToe.ui;

import ticTacToe.game.Game;

import javax.swing.*;
import java.awt.*;

import static ticTacToe.game.Game.*;

/**
 *  Class for Game Button ui element, contains button, and it coordinate according to field matrix
 */
public class GameButton extends JFrame{

    private JButton button = new JButton();
    private int string;
    private int row;

    GameButton(int string, int row, int size){
        this.string = string;
        this.row = row;
        int fontSize;
        switch (Game.getFieldSize()) {
            case 3 : fontSize = 140; break;
            case 4 : fontSize = 90; break;
            case 5 : fontSize = 65; break;
            case 6 : fontSize = 45; break;
            default : fontSize = 15; break;
        }

        Font font = new Font(null,Font.BOLD,fontSize);
        Dimension dimension = new Dimension(size,size);
        button.setMinimumSize(new Dimension(dimension));
        button.setMaximumSize(new Dimension(dimension));
        button.setFont(font);
        printFieldElement();
        button.addActionListener( actionEvent -> getUserSelection());
    }

    /**
     *  method that updates button text according to field matrix element
     *  that it was bound by string and row parameter
     *
     * @see Game
     */
    public void printFieldElement() {
        Integer value = getFieldValue(string,row);
        int val = 0;
        if (value != null) {
            val = value;
        }

        switch (val) {
            case ZERO : {
                button.setText("O");
                break;
            }
            case CROSS : {
                button.setText("X");
                break;
            }
            case EMPTY: {
                button.setText("");
                break;
            }
            default : break;
        }
    }

    /**
     *  method that process user selection to button
     */
    private void getUserSelection () {
        if (isGameStarted()) {
            button.setEnabled(false);
            nextMove(string,row, getActiveFigure() , true);
        }
    }

    /**
     * Button state setter
     * @param state enable button when true
     */
    public void setButtonEnabled(boolean state) {
        button.setEnabled(state);
    }
    /**
     * Method disables button before game starts and updates text in it
     *
     */
    public void setWaitingForGame () {
        button.setEnabled(false);
        printFieldElement();
    }

    /**
        button getter
     */
    public JButton getButton() {
        return button;
    }

    public static int foundSize(int qnt) {
        int width = 445;
        return ((width - 5 * (qnt - 1)) / qnt) -1;
    }
}
