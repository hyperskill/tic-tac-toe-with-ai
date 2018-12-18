package ticTacToe.game;

import ticTacToe.ai.ComputerRival;
import ticTacToe.ai.MediumLevel;
import ticTacToe.game.Game;

/**
 * Class implements players specifications
 */
public class Player {

    private String name;
    private int figure;
    private boolean computer;
    private Game.Levels level;

    public Player(String name, int figure) {
        this.name = name;
        this.figure = figure;
        this.computer = false;
        this.level = Game.Levels.MEDIUM;
    }

    public void makeMove() {
        if (computer && Game.isGameStarted()) {
            switch (level) {
                case EASY: new ComputerRival().easy();break;
                case MEDIUM: new MediumLevel().medium(); break;
                case HARD: new ComputerRival().hard(); break;
            }
        } else {
            return;
        }

    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public void setisComputer(boolean computer) {
        this.computer = computer;
    }

    public void setLevel(Game.Levels level) {
        this.level = level;
    }

    public int getFigure() {
        return figure;
    }
}
