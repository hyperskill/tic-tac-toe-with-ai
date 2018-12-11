package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class HeadLabels {
    public JPanel createHeadLabels() {
        JPanel head = new JPanel();
        Font font = new Font(null,Font.BOLD,13);

        GameTextField player1Label =
                new GameTextField("Player 1 Name", false, false, font);
        GameTextField player2Label =
                new GameTextField("Player 2 Name", false, false, font);
        GameTextField whoMovesLabel =
                new GameTextField("Now Moves", false, false, font);

        GroupLayout groupLayout = new GroupLayout(head);
        head.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(player1Label.getTextField())
                .addGap(5)
                .addComponent(whoMovesLabel.getTextField())
                .addGap(5)
                .addComponent(player2Label.getTextField())

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(player1Label.getTextField())
                .addComponent(whoMovesLabel.getTextField())
                .addComponent(player2Label.getTextField())
        );

        groupLayout.linkSize(player1Label.getTextField(), player2Label.getTextField(),whoMovesLabel.getTextField());

        return head;
    }
}
