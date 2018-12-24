package ticTacToe.ui;


import ticTacToe.ai.LearningAlgorithm;

import javax.swing.*;
import java.awt.*;

import static ticTacToe.game.Game.*;
import static ticTacToe.ui.UserInterface.game;

/**
 *  Class for menu bar user interface element creation
 * @see UserInterface
 */
 class Menu extends JPanel{
    private Font font = new Font(null,Font.BOLD,15);

    JMenuBar menuCreator(){
        JMenuBar menuBar = new JMenuBar();
        JMenu game = menuGameCreator();
        JMenu p1 = menuPlayerCreator("Player 1  |",1,false);
        JMenu p2 = menuPlayerCreator("Player 2", 2, true);
        JMenu first = firstMoveCreator();

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

    private JMenu menuGameCreator(){
        JMenu file = new JMenu("Game  |");

        JMenuItem start = new JMenuItem("Start game");
        JMenuItem restart = new JMenuItem("Restart game");
        JMenuItem stop = new JMenuItem("Stop game");
        JMenuItem selfLearn = new JMenuItem("Self learning");

        start.addActionListener( actionEvent -> game.startTheGame());
        restart.addActionListener( actionEvent -> game.restartTheGame());
        stop.addActionListener( actionEvent -> game.stopTheGame());
        selfLearn.addActionListener( actionEvent -> new LearningAlgorithm().selfLearning(20000));

        file.add(start);
        file.add(restart);
        file.add(stop);
        file.add(selfLearn);

        return file;
    }

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

    private JMenu firstMoveCreator(){
        JMenu file = new JMenu("Move   |");

        JLabel label = new JLabel("  First move select");
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

        file.add(label);
        file.addSeparator();
        file.add(p1);
        file.add(p2);
        file.add(random);

        return file;
    }
}
