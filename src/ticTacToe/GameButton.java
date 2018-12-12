package ticTacToe;

import javax.swing.*;
import java.awt.*;

import static ticTacToe.Game.*;

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
        int val = getFieldValue(string,row);

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

    private void getUserSelection () {
        if (isGameStarted()) {
            setFieldValue(string,row, getActiveFigure() , true);
            printFieldElement();
            button.setEnabled(false);
            new GameResult().checkGameResult();
            if (isGameStarted()) {
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
