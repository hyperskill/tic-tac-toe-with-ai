package ticTacToe;

import javax.swing.*;
import java.awt.*;

/**
 * Class that implements UI
 */
public class UserInterface extends JFrame {

    /**
     *  buttons matrix that bound to field with matrix values
     * @see GameButton
     * @see Game
     */
    public static GameButton[][] button = new GameButton[Game.getFieldValues().length][Game.getFieldValues().length];

    /**
     * Text fields for gamer names and turn indication
     */
    public static GameTextField player1 =
            new GameTextField("Player 1", true, true,null);
    public static GameTextField player2 =
            new GameTextField("Player 2", true, true,null);
    public static GameTextField whoMoves =
            new GameTextField("Who Moves", true, true,null);

    public void window() {
        Dimension dimension = new Dimension(480,610);

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

    /**
     * Methods bounds buttons to matrix, creates bars of menu,labels, players name and buttons
     *
     */
    private void createField() {
        JMenuBar menu = new Menu().menuCreator();
        JPanel headLabels = new HeadLabels().createHeadLabels();
        JPanel head = createHead();
        JPanel[] line = new JPanel[button.length];
        int btnSize = GameButton.foundSize(button.length);
        for ( int i = 0; i < button.length; i++) {
            for ( int j = 0; j < button.length; j++) {
                button[i][j] = new GameButton(i,j, btnSize);
            }
            line[i] = formLine(button[i]);
        }
        createLayout(line, menu, headLabels, head);
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

    /**
     * Methods form the string of buttons matrix
//
     * @return line of buttons
     */
    private JPanel formLine(GameButton b[]) {

        JPanel buttonsLine = new JPanel();

        GroupLayout groupLayout = new GroupLayout(buttonsLine);
        buttonsLine.setLayout(groupLayout);
        GroupLayout.ParallelGroup parallelGroup = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
       // groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        for (GameButton aB : b) {
            sequentialGroup.addComponent(aB.getButton());
            parallelGroup.addComponent(aB.getButton());
        }
        groupLayout.setVerticalGroup(parallelGroup);
        groupLayout.setHorizontalGroup(sequentialGroup);


        return buttonsLine;
    }


    /**
     * creates layout compiling table of stings :
     *
     */
    private void createLayout(JComponent[] lines, JComponent... component) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);
        GroupLayout.ParallelGroup parallelGroup = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);


        for (JComponent aComponent : component) {
            parallelGroup.addComponent(aComponent);
            sequentialGroup.addComponent(aComponent);
        }

        for (JComponent line : lines) {
            parallelGroup.addComponent(line);
            sequentialGroup.addComponent(line);
        }

        groupLayout.setHorizontalGroup(parallelGroup);
        groupLayout.setVerticalGroup(sequentialGroup);

        groupLayout.linkSize(component);

    //    groupLayout.linkSize(1,menu,head,headLabels);
    //    groupLayout.linkSize(0,head, headLabels);
//        pack();
    }
}
