package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {

    public static GameButton[][] button = new GameButton[3][3];
    public static GameTextField player1 =
            new GameTextField("Player 1", true, true,null);
    public static GameTextField player2 =
            new GameTextField("Player 2", true, true,null);
    public static GameTextField whoMoves =
            new GameTextField("Who Moves", true, true,null);

    public void window() {
        Dimension dimension = new Dimension(450,600);

        createField();
        Game.stopTheGame();

        setSize(dimension);
        setResizable(false);
        setMinimumSize(dimension);
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createField() {
        JMenuBar menu = new Menu().menuCreator();
        JPanel headLabels = new HeadLabels().createHeadLabels();
        JPanel head = createHead();
        JPanel[] line = new JPanel[3];

        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                button[i][j] = new GameButton(i,j);
            }
            line[i] = formLine(button[i][0].getButton(), button[i][1].getButton(), button[i][2].getButton());
        }


        createLayout(menu, headLabels, head, line[0], line[1], line[2]);
    }

    private JPanel createHead() {
        JPanel head = new JPanel();


        GroupLayout groupLayout = new GroupLayout(head);
        head.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(player1.getTextField())
                .addGap(5)
                .addComponent(whoMoves.getTextField())
                .addGap(5)
                .addComponent(player2.getTextField())

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(player1.getTextField())
                .addComponent(whoMoves.getTextField())
                .addComponent(player2.getTextField())
        );

        groupLayout.linkSize( player1.getTextField(), player2.getTextField(), whoMoves.getTextField());

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


    private void createLayout(JComponent menu, JComponent headLabels, JComponent head, JComponent line1,
                              JComponent line2, JComponent line3) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(menu)
                .addComponent(headLabels)
                .addComponent(head)
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(menu)
                .addComponent(headLabels)
                .addComponent(head)
                .addComponent(line1)
                .addComponent(line2)
                .addComponent(line3)
        );

        groupLayout.linkSize(1,menu,head,headLabels);
        groupLayout.linkSize(0,head, headLabels, line1);
        pack();
    }
}
