package ticTacToe.ui;

import javax.swing.*;

import static ticTacToe.ui.UserInterface.*;

public class Head {
    public JPanel createHead() {
        JPanel head = new JPanel();


        GroupLayout groupLayout = new GroupLayout(head);
        head.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(getPlayer1().getTextField())
                .addGap(5)
                .addComponent(getWhoMoves().getTextField())
                .addGap(5)
                .addComponent(getPlayer2().getTextField())

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(getPlayer1().getTextField())
                .addComponent(getWhoMoves().getTextField())
                .addComponent(getPlayer2().getTextField())
        );

        groupLayout.linkSize( getPlayer1().getTextField(), getPlayer2().getTextField(), getWhoMoves().getTextField());

        return head;
    }
}
