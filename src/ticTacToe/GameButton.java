package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameButton {

    public JButton button = new JButton();

    GameButton(){
        Font font = new Font(null,Font.BOLD,100);
        Dimension dimension = new Dimension(120,120);
        button.setPreferredSize(new Dimension(dimension));
        button.setFont(font);
        button.setText("O");
    }
}
