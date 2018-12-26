package ticTacToe.ui;


import ticTacToe.ai.SelfLearning;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static ticTacToe.game.Game.*;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class for menu bar user interface element creation
 * @see UserInterface
 */
 class Menu extends JPanel{
    private Font font = new Font(null,Font.BOLD,15);

    /**
     * Method creates menu bar with next menus - game, move, player1, player2
     * @return bar
     */
    public JMenuBar menuCreator(){
        JMenuBar menuBar = new JMenuBar();
        JMenu game = menuGameCreator();
        JMenu p1 = menuPlayerCreator("Player 1  |",1,false);
        JMenu p2 = menuPlayerCreator("Player 2", 2, true);
        JMenu first = moveCreator();

        game.setFont(font);
        p1.setFont(font);
        p2.setFont(font);
        first.setFont(font);

        menuBar.add(game);
        menuBar.add(first);
        menuBar.add(p1);
        menuBar.add(p2);

        return menuBar;
    }

    /**
     * Method creates game menu
     * @return game menu
     */
    private JMenu menuGameCreator(){
        JMenu file = new JMenu("Game  |");

        JMenuItem start = new JMenuItem("Start game");
        JMenuItem restart = new JMenuItem("Restart game");
        JMenuItem stop = new JMenuItem("Stop game");


        start.addActionListener( actionEvent -> game.startTheGame());
        restart.addActionListener( actionEvent -> game.restartTheGame());
        stop.addActionListener( actionEvent -> game.stopTheGame());

        file.add(start);
        file.add(restart);
        file.add(stop);

        return file;
    }

    /**
     * Method creates player menu
     * @param name
     * @param playerID
     * @param isComputer
     * @return player menu
     */
    private JMenu menuPlayerCreator(String name, int playerID, boolean isComputer){
        JMenu file = new JMenu(name);

        JCheckBoxMenuItem computerRival = new JCheckBoxMenuItem("Play with computer");
        JMenuItem easy = new JRadioButtonMenuItem("Easy");
        JMenuItem medium = new JRadioButtonMenuItem("Medium");
        JMenuItem hard = new JRadioButtonMenuItem("Hard");
        JMenuItem learning = new JRadioButtonMenuItem("Self-learning");

        computerRival.addActionListener(actionEvent -> {
            game.getPlayer(playerID).setisComputer(computerRival.getState());
            easy.setEnabled(computerRival.getState());
            medium.setEnabled(computerRival.getState());
            hard.setEnabled(computerRival.getState());
            learning.setEnabled(computerRival.getState());
        });

        computerRival.setSelected(isComputer);
        easy.setEnabled(isComputer);
        medium.setEnabled(isComputer);
        hard.setEnabled(isComputer);
        learning.setEnabled(isComputer);
        game.getPlayer(playerID).setisComputer(isComputer);


        game. getPlayer(playerID).setLevel(Levels.MEDIUM);
        medium.setSelected(true);
        easy.addActionListener( actionEvent -> {
            game.getPlayer(playerID).setLevel(Levels.EASY);
            medium.setSelected(false);
            hard.setSelected(false);
            learning.setSelected(false);

        });
        medium.addActionListener( actionEvent -> {
            game.getPlayer(playerID).setLevel(Levels.MEDIUM);
            easy.setSelected(false);
            hard.setSelected(false);
            learning.setSelected(false);
        });
        hard.addActionListener( actionEvent -> {
            game.getPlayer(playerID).setLevel(Levels.HARD);
            easy.setSelected(false);
            medium.setSelected(false);
            learning.setSelected(false);
        });
        learning.addActionListener( actionEvent -> {
            game.getPlayer(playerID).setLevel(Levels.LEARNING);
            easy.setSelected(false);
            medium.setSelected(false);
            hard.setSelected(false);
        });

        file.add(computerRival);
        file.addSeparator();
        file.add(easy);
        file.add(medium);
        file.add(hard);
        file.add(learning);

        return file;
    }

    /**
     * Menu move creator
     * @return menu move
     */
    private JMenu moveCreator(){
        JMenu file = new JMenu("Move   |");

        JMenu firstMoveSubmenu = firstMoveSubmenu();
        JMenu selfLearnSubmenu = selfLearnSubmenu();


        file.add(firstMoveSubmenu);
        file.add(selfLearnSubmenu);

        return file;
    }

    /**
     * method creates submenu which using for selecting which player should go first
     * @return first move submenu
     */
    private JMenu firstMoveSubmenu(){
        JMenu submenu = new JMenu("First move selection");

        JLabel label = new JLabel("  First move player selection");
        JMenuItem p1 = new JRadioButtonMenuItem("Player 1");
        JMenuItem p2 = new JRadioButtonMenuItem("Player 2");
        JMenuItem random = new JRadioButtonMenuItem("Random");

        random.setSelected(true);

        p1.addActionListener( actionEvent -> {
            game.setFirstPlayerUserSelection(FirstPlayerSelect.PLAYER1);
            p2.setSelected(false);
            random.setSelected(false);
        });
        p2.addActionListener( actionEvent -> {
            game.setFirstPlayerUserSelection(FirstPlayerSelect.PLAYER2);
            p1.setSelected(false);
            random.setSelected(false);
        });
        random.addActionListener( actionEvent -> {
            game.setFirstPlayerUserSelection(FirstPlayerSelect.RANDOM);
            p1.setSelected(false);
            p2.setSelected(false);
        });
        submenu.add(label);
        submenu.addSeparator();
        submenu.add(p1);
        submenu.add(p2);
        submenu.add(random);
        return submenu;
    }

    /**
     * Method creates submenu for control selflearn process
     * @return
     */
    private JMenu selfLearnSubmenu(){
        JMenu submenu = new JMenu("Self-learning");

        JMenuItem startSelfLearn = new JMenuItem("Start self-learning");
        JMenuItem stopSelfLearn = new JMenuItem("Stop self-learning");

        startSelfLearn.addActionListener( actionEvent -> startLearning());

        stopSelfLearn.addActionListener( actionEvent ->  game.setLearningInProcess(false));

        submenu.add(startSelfLearn);
        submenu.add(stopSelfLearn);
        return submenu;
    }

    /**
     *  method starts learning process thread if it's not already started and nobody playing at this moment
     */
    private void startLearning() {
        if (game.isGameStarted()) {
            JOptionPane.showMessageDialog(null,
                    "Please, finish game first");
            return;
        }

        if (game.isLearningInProcess()) {
            JOptionPane.showMessageDialog(null,
                    "Please, wait while previous learn will be finished");
            return;
        }
        int iterations = 50000;
        String iterationsGot = JOptionPane.showInputDialog(null,
                "Please, select learning iterations number. By default is 50000.");

        if ( iterationsGot == null) {
            return;
        }

        Scanner sc = new Scanner(iterationsGot);

        if (sc.hasNextInt()) {
            iterations = sc.nextInt();
        }

        new SelfLearning(iterations).start();
    }
}
