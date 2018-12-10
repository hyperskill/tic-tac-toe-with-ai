package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    public void window() {
        Dimension dimension = new Dimension(410,510);

        createField();

        setSize(dimension);
        setResizable(false);
        setMinimumSize(dimension);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void createField() {
        GameButton a1 = new GameButton();
        GameButton a2 = new GameButton();
        GameButton a3 = new GameButton();

        GameButton b1 = new GameButton();
        GameButton b2 = new GameButton();
        GameButton b3 = new GameButton();

        GameButton c1 = new GameButton();
        GameButton c2 = new GameButton();
        GameButton c3 = new GameButton();

        JPanel line1 = formLine(a1.button, a2.button, a3.button);
        JPanel line2 = formLine(b1.button, b2.button, b3.button);
        JPanel line3 = formLine(c1.button, c2.button, c3.button);

        createLayout(line1, line2, line3);


    }


    private JPanel formLine(JButton b1, JButton b2, JButton b3) {

        JPanel fileSelectGroup = new JPanel();

        GroupLayout groupLayout = new GroupLayout(fileSelectGroup);
        fileSelectGroup.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(b1)
                .addGap(5)
                .addComponent(b2)
                .addGap(5)
                .addComponent(b3)
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(b1)
                .addComponent(b2)
                .addComponent(b3)
        );

        groupLayout.linkSize( b1, b2, b3);

        return fileSelectGroup;
    }

    private void createLayout(JComponent line1,JComponent line2,JComponent line3) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        pack();
    }
}
