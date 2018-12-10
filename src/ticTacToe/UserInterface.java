package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    public static GameButton[][] button = new GameButton[3][3];
    public static GameTextField player1 =
            new GameTextField("Player 1", true, true, 50);
    public static GameTextField player2 =
            new GameTextField("Player 2", true, true, 50);
    public static GameTextField whoMoves =
            new GameTextField("Who Moves", true, true, 50);

    public void window() {
        Dimension dimension = new Dimension(410,510);


        createField();
        Game.restartTheGame();

        setSize(dimension);
        setResizable(false);
        setMinimumSize(dimension);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void createField() {
        JPanel headLabels = createHeadLabels();
        JPanel head = createHead();
        JPanel[] line = new JPanel[3];

        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                button[i][j] = new GameButton(i,j);
            }
            line[i] = formLine(button[i][0].getButton(), button[i][1].getButton(), button[i][2].getButton());
        }


        createLayout(headLabels, head, line[0], line[1], line[2]);
    }

    private JPanel createHeadLabels() {
        JPanel head = new JPanel();

        GameTextField player1Label =
                new GameTextField("Player 1", false, false, 20);
        GameTextField player2Label =
                new GameTextField("Player 2", false, false, 20);
        GameTextField whoMovesLabel =
                new GameTextField("Who Moves", false, false, 20);
        JButton restart = new JButton("Restart");

        restart.setPreferredSize(new Dimension(90,20));
        restart.addActionListener( actionEvent -> Game.restartTheGame());

        GroupLayout groupLayout = new GroupLayout(head);
        head.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(player1Label.getTextField())
                .addGap(5)
                .addComponent(whoMovesLabel.getTextField())
                .addGap(5)
                .addComponent(player2Label.getTextField())
                .addComponent(restart)
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(player1Label.getTextField())
                .addComponent(whoMovesLabel.getTextField())
                .addComponent(player2Label.getTextField())
                .addComponent(restart)
        );

        groupLayout.linkSize( player1Label.getTextField(), player2Label.getTextField(),whoMovesLabel.getTextField(), restart);

        return head;
    }

    private JPanel createHead() {
        JPanel head = new JPanel();
        JButton start = new JButton("Start");

        start.setPreferredSize(new Dimension(90,50));
        start.addActionListener( actionEvent -> Game.startTheGame());

        GroupLayout groupLayout = new GroupLayout(head);
        head.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(player1.getTextField())
                .addGap(5)
                .addComponent(whoMoves.getTextField())
                .addGap(5)
                .addComponent(player2.getTextField())
                .addComponent(start)
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(player1.getTextField())
                .addComponent(whoMoves.getTextField())
                .addComponent(player2.getTextField())
                .addComponent(start)
        );

        groupLayout.linkSize( player1.getTextField(), player2.getTextField(), whoMoves.getTextField(), start);

        return head;
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

    private void createLayout(JComponent headLabels, JComponent head,JComponent line1,JComponent line2,JComponent line3) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(headLabels)
                .addComponent(head)
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(headLabels)
                .addComponent(head)
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        groupLayout.linkSize(0,head, headLabels, line1);
        pack();
    }
}
