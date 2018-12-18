package ticTacToe.game;

import ticTacToe.ai.ComputerRival;
import ticTacToe.ai.MediumLevel;
import ticTacToe.game.Game;

/**
 * Class implements players specifications
 */
public class Player {

    /**
     * for which figure player is playing 0 or X
     */
    private int figure;

    /**
     * Is player human(false), or computer (true)
     */
    private boolean computer;

    /**
     * Level if player is computer
     */
    private Game.Levels level;

    public Player(int figure) {
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

    /**
     * Getters and setters for player settings
     */

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
