package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameButton {

    public JButton button = new JButton();

    public final int ZERO = 0;
    public final int CROSS = 1;
    public final int NULL = 2;

    private int boundX;
    private int boundY;

    GameButton(int boundX, int boundY){
        this.boundX = boundX;
        this.boundY = boundY;

        Font font = new Font(null,Font.BOLD,100);
        Dimension dimension = new Dimension(120,120);
        button.setPreferredSize(new Dimension(dimension));
        button.setFont(font);
        printFieldElement();
    }

    private void printFieldElement() {
        int val = Game.getFieldValue(boundX,boundY);

        switch (val) {
            case ZERO : {
                button.setText("O");
                break;
            }
            case CROSS : {
                button.setText("X");
                break;
            }
            case NULL : {
                button.setText("");
                break;
            }
            default : break;
        }
    }
}
