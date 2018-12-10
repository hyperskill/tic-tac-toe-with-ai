package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameButton {

    public JButton button = new JButton();

    private int string;
    private int row;

    GameButton(int string, int row){
        this.string = string;
        this.row = row;

        Font font = new Font(null,Font.BOLD,100);
        Dimension dimension = new Dimension(120,120);
        button.setPreferredSize(new Dimension(dimension));
        button.setFont(font);
        printFieldElement();
    }

    private void printFieldElement() {
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
}
