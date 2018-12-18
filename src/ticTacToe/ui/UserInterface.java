package ticTacToe.ui;

import ticTacToe.game.Game;

import javax.swing.*;
import java.awt.*;

import static ticTacToe.game.Game.getFieldSize;

/**
 * Class that implements UI
 */
public class UserInterface extends JFrame {

    /**
     *  buttons matrix that bound to field with matrix values
     * @see GameButton
     * @see Game
     */
    private static GameButton[][] button = new GameButton[6][6];

    /**
     * Text fields for gamer names and turn indication
     */
    private static GameTextField player1 =
            new GameTextField("Player 1", true, true,null);

    private static GameTextField player2 =
            new GameTextField("Player 2", true, true,null);

    private static GameTextField whoMoves =
            new GameTextField("Who Moves", true, true,null);

    private JComboBox fieldSizeSetup;

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
        JPanel topBar = createTopBar();
        JPanel headLabels = new HeadLabels().createHeadLabels();
        JPanel head = new Head().createHead();
        JPanel[] line = new JPanel[getFieldSize()];
        int btnSize = GameButton.foundSize(getFieldSize());
        for ( int i = 0; i < getFieldSize(); i++) {
            for ( int j = 0; j < getFieldSize(); j++) {
                button[i][j] = new GameButton(i,j, btnSize);
            }
            line[i] = formLine(button[i]);
        }

        createLayout(line, topBar, headLabels, head);
    }

    /**
     * Methods form the string of buttons matrix
     * @return line of buttons
     */
    private JPanel formLine(GameButton b[]) {

        JPanel buttonsLine = new JPanel();

        GroupLayout groupLayout = new GroupLayout(buttonsLine);
        buttonsLine.setLayout(groupLayout);
        GroupLayout.ParallelGroup parallelGroup = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        groupLayout.setAutoCreateGaps(true);

        for (int i = 0; i < getFieldSize(); i++) {
            GameButton aB = b[i];
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

    }

    public JPanel createTopBar() {
        JPanel topBar = new JPanel();


        JMenuBar menu = new Menu().menuCreator();
        String[] items = {"3x3","4x4", "5x5"};
        fieldSizeSetup = new JComboBox<>(items);

        fieldSizeSetup.setSelectedIndex(getFieldSize() - 3);
        fieldSizeSetup.addActionListener( a -> changeFieldSize());

        GroupLayout groupLayout = new GroupLayout(topBar);
        topBar.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(menu)
                .addGap(70)
                .addComponent(fieldSizeSetup)

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                .addComponent(menu)
                .addComponent(fieldSizeSetup)
        );

        return topBar;
    }

     private void changeFieldSize() {
        Game.setFieldSize(fieldSizeSetup.getSelectedIndex() + 3);

        dispose();
        new UserInterface().window();
    }

    public static GameButton getButton(int s, int r) {
        return button[s][r];
    }

    public static GameTextField getPlayer1() {
        return player1;
    }

    public static GameTextField getPlayer2() {
        return player2;
    }

    public static GameTextField getWhoMoves() {
        return whoMoves;
    }
}
