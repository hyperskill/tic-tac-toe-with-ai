package ticTacToe.game;

import ticTacToe.ui.DisplayPlayer;
import ticTacToe.ui.UserInterface;

import java.util.Random;


/**
 *  This class saves all game settings, and current parameters
 */
public class Game {

    /**
     *  Used for game setting who moves first
     */
    public enum FirstPlayerSelect {
        PLAYER1,
        PLAYER2,
        RANDOM
    }

    /**
     *  Used for game level identification
     */
    public enum Levels {
        EASY,
        MEDIUM,
        HARD,
        LEARNING
    }

    /**
     *  Constants for cell state identification
     */
    public static final int ZERO = 0;
    public static final int CROSS = 1;
    public static final int EMPTY = 2;

    /**
     *  Players declaration
     */
    private static Player player1 = new Player(CROSS);
    private static Player player2 = new Player(ZERO);

    /**
     *  Contains setting for game start- which player should make first move
     */
    private static FirstPlayerSelect firstPlayerUserSelection = FirstPlayerSelect.RANDOM;

    /**
     *  Contains information which player turn now
     */
    private static Player currentPlayer;

    /**
     *  Parameter deactivating start game method after game start until it finished
     */
    private static boolean gameStarted = false;

    /**
     *  Contains game field and it's size. Size of array equal to maximum possible size of game field
     */
    private static int fieldSize = 3;
    private static int[][] fieldValues = new int[6][6];

    /**
     *  Contains which figure is active now - zero or cross
     */
    private static int activeFigure = CROSS;

    /**
     *  method that configure game to a start position depending of game settings
     */
    public static void startTheGame(){
        if (!gameStarted) {

            gameStarted = true;
            activeFigure = CROSS;

            switch (firstPlayerUserSelection) {
                case PLAYER1 : {
                    currentPlayer = player1;
                    player1.setFigure(CROSS);
                    player2.setFigure(ZERO);
                    break;
                }
                case PLAYER2 : {
                    currentPlayer = player2;
                    player1.setFigure(ZERO);
                    player2.setFigure(CROSS);
                    break;
                }
                case RANDOM : {
                    if (new Random().nextBoolean()) {
                        currentPlayer = player2;
                        player1.setFigure(ZERO);
                        player2.setFigure(CROSS);
                    } else {
                        currentPlayer = player1;
                        player1.setFigure(CROSS);
                        player2.setFigure(ZERO);
                    }
                    break;
                }
            }
            DisplayPlayer.display();

            for ( int i = 0; i < fieldSize; i++) {
                for ( int j = 0; j < fieldSize; j++) {
                    Game.nextMove(i,j, EMPTY, false);
                    UserInterface.getButton(i,j).printFieldElement();
                    UserInterface.getButton(i,j).setButtonEnabled(true);
                }
            }

            currentPlayer.makeMove();
        }
    }

    /**
     *  method updating ui to the start condition
     */
    public static void restartTheGame(){
        gameStarted = false;
        startTheGame();
    }

    /**
     *  method resets field but not starting a new game
     */
    public static void stopTheGame(){
        gameStarted = false;
        currentPlayer = null;
        DisplayPlayer.display();
        for ( int i = 0; i < fieldSize; i++) {
            for ( int j = 0; j < fieldSize; j++) {
                Game.nextMove(i,j, EMPTY, false);
                UserInterface.getButton(i,j).setWaitingForGame();
            }
        }
    }

    /**
     *  method freezing game, calling from results checker. Used for watching the field after game finished.
     *
     * @see GameResult
     */
    public static void endTheGame(){
        gameStarted = false;
        currentPlayer = null;
        DisplayPlayer.display();
        for ( int i = 0; i < fieldSize; i++) {
            for ( int j = 0; j < fieldSize; j++) {
                UserInterface.getButton(i,j).setWaitingForGame();
            }
        }
        System.out.println("game finished");
    }

    /**
     * method for update field matrix with new value and current game turn
     *
     * @param string string number in matrix
     * @param row row number in matrix
     * @param fieldValue value which should be write
     * @param changeGameProgress true if game should be updated
     */
    public static void nextMove(int string, int row, int fieldValue, boolean changeGameProgress) {
        fieldValues[string][row] = fieldValue;
        if (changeGameProgress) {
            UserInterface.getButton(string,row).printFieldElement();
            new GameResult().checkGameResult();

            if (getActiveFigure() == CROSS) {
                activeFigure = ZERO;
            } else {
                activeFigure = CROSS;
            }

            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
            DisplayPlayer.display();



            if (currentPlayer != null) {
                currentPlayer.makeMove();
            }

        }
    }

    /**
     * getters and setters for game parameters
     */
    public static int getActiveFigure() {
        return activeFigure;
    }

    public static int getFieldValue(int string, int row) {
        return fieldValues[string][row];
    }

    public static int[][] getFieldValues() {
        return fieldValues;
    }

    public static void setFirstPlayerUserSelection(FirstPlayerSelect firstPlayerUserSelection) {
        Game.firstPlayerUserSelection = firstPlayerUserSelection;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static Player getPlayer(int id) {
        if (id == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    public static int getFieldSize() {
        return fieldSize;
    }

    public static void setFieldSize(int fieldSize) {
        Game.fieldSize = fieldSize;
    }

}
