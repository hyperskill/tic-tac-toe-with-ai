package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JFrame{

    private JButton button = new JButton();

    private int string;
    private int row;

    GameButton(int string, int row){
        this.string = string;
        this.row = row;

        Font font = new Font(null,Font.BOLD,100);
        Dimension dimension = new Dimension(135,135);
        button.setPreferredSize(new Dimension(dimension));
        button.setFont(font);
        printFieldElement();
        button.addActionListener( actionEvent -> getUserSelection());
    }

    public void printFieldElement() {
        int val = Game.getFieldValue(string,row);

        switch (val) {
            case Game.ZERO : {
                button.setText("O");
                break;
            }
            case Game.CROSS : {
                button.setText("X");
                break;
            }
            case Game.NULL : {
                button.setText("");
                break;
            }
            default : break;
        }
    }

    private void getUserSelection () {
        if (Game.isGameStarted()) {
            Game.setFieldValue(string,row, Game.getActiveFigure() , true);
            printFieldElement();
            button.setEnabled(false);
            new GameResult().checkGameResult();
            if (Game.isGameStarted()) {
                new ComputerRival().makeMove();
            }
        }
    }

    public void setButtonEnabled(boolean state) {
        button.setEnabled(state);
    }

    public void setWaitingForGame () {
        button.setEnabled(false);
        printFieldElement();
    }

    public JButton getButton() {
        return button;
    }
}
