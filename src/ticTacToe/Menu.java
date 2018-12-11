package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    private Font font = new Font(null,Font.BOLD,15);

    public JMenuBar menuCreator(){
        JMenuBar menuBar = new JMenuBar();
        JMenu game = menuGameCreator();
        JMenu rival = menuRivalCreator();
        JMenu first = firstMoveCreator();

        game.setFont(font);
        rival.setFont(font);
        first.setFont(font);

        menuBar.add(game);
        menuBar.add(first);
        menuBar.add(rival);

        return menuBar;
    }

    private JMenu menuGameCreator(){
        JMenu file = new JMenu("Game  |");

        JMenuItem start = new JMenuItem("Start game");
        JMenuItem restart = new JMenuItem("Restart game");
        JMenuItem stop = new JMenuItem("Stop game");

        start.addActionListener( actionEvent -> Game.startTheGame());
        restart.addActionListener( actionEvent -> Game.restartTheGame());
        stop.addActionListener( actionEvent -> Game.stopTheGame());

        file.add(start);
        file.add(restart);
        file.add(stop);

        return file;
    }

    private JMenu menuRivalCreator(){
        JMenu file = new JMenu("Rival  ");

        JMenuItem computerRival = new JCheckBoxMenuItem("Play with computer");
        JMenuItem easy = new JRadioButtonMenuItem("Easy");
        JMenuItem medium = new JRadioButtonMenuItem("Medium");
        JMenuItem hard = new JRadioButtonMenuItem("Hard");

        computerRival.addActionListener( actionEvent ->
                Game.setPlayWithComputer(((JCheckBoxMenuItem) computerRival).getState()));

        computerRival.setSelected(true);

        easy.setSelected(true);
        easy.addActionListener( actionEvent -> {
            Game.setLevel(Game.Level.EASY);
            medium.setSelected(false);
            hard.setSelected(false);
        });
        medium.addActionListener( actionEvent -> {
            Game.setLevel(Game.Level.MEDIUM);
            easy.setSelected(false);
            hard.setSelected(false);
        });
        hard.addActionListener( actionEvent -> {
            Game.setLevel(Game.Level.HARD);
            easy.setSelected(false);
            medium.setSelected(false);
        });

        file.add(computerRival);
        file.addSeparator();
        file.add(easy);
        file.add(medium);
        file.add(hard);

        return file;
    }

    private JMenu firstMoveCreator(){
        JMenu file = new JMenu("Move  | ");

        JLabel label = new JLabel("  First move select");
        JMenuItem p1 = new JRadioButtonMenuItem("Player 1");
        JMenuItem p2 = new JRadioButtonMenuItem("Player 2");
        JMenuItem random = new JRadioButtonMenuItem("Random");

        random.setSelected(true);

        p1.addActionListener( actionEvent -> {
            Game.setFirstPlayerUserSelection(Game.FirstPlayerSelect.PLAYER1);
            p2.setSelected(false);
            random.setSelected(false);
        });
        p2.addActionListener( actionEvent -> {
            Game.setFirstPlayerUserSelection(Game.FirstPlayerSelect.PLAYER2);
            p1.setSelected(false);
            random.setSelected(false);
        });
        random.addActionListener( actionEvent -> {
            Game.setFirstPlayerUserSelection(Game.FirstPlayerSelect.RANDOM);
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
