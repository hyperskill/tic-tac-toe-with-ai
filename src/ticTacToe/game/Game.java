package ticTacToe.game;

import ticTacToe.ai.ComputerRival;
import ticTacToe.ui.DisplayPlayer;
import ticTacToe.ui.UserInterface;

import java.util.Arrays;
import java.util.Random;

/**
 *  This class saves all game settings, and current parameters
 */
public class Game {

    private GameLog gameLog = new GameLog();

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
    private Player player1;
    private Player player2;

    /**
     *  Contains setting for game start- which player should make first move
     */
    private FirstPlayerSelect firstPlayerUserSelection;

    /**
     *  Contains information which player turn now
     */
    private Player currentPlayer;

    /**
     *  Parameter deactivating start game method after game start until it finished
     */
    private boolean gameStarted;

    /**
     *  Contains game field and it's size. Size of array equal to maximum possible size of game field
     */
    private  int fieldSize;

    private  Integer[][] fieldValues;

    /**
     *  Contains which figure is active now - zero or cross
     */
    private int activeFigure;

    public Game(int fieldSize) {
        this.player1 = new Player(CROSS);
        this.player2 = new Player(ZERO);
        this.firstPlayerUserSelection = FirstPlayerSelect.RANDOM;
        this.gameStarted = false;
        this.fieldValues = new Integer[fieldSize][fieldSize];
        this.fieldSize = fieldSize;
        this.activeFigure = CROSS;
    }

    /**
     *  method that configure game to a start position depending of game settings
     */
    public  void startTheGame(){
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
                    updateField(i,j, EMPTY);
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
    public void restartTheGame(){
        gameStarted = false;
        startTheGame();
    }

    /**
     *  method resets field but not starting a new game
     */
    public void stopTheGame(){
        gameStarted = false;
        currentPlayer = null;
        DisplayPlayer.display();
        for ( int i = 0; i < fieldSize; i++) {
            for ( int j = 0; j < fieldSize; j++) {
                updateField(i,j, EMPTY);
                UserInterface.getButton(i,j).setWaitingForGame();
            }
        }
    }

    /**
     *  method freezing game, calling from results checker. Used for watching the field after game finished.
     *
     * @see GameResult
     */
    public void endTheGame(int result){
        if (player1.getLevel() == Levels.LEARNING || player2.getLevel() == Levels.LEARNING) {
            ComputerRival.learningAlgorithm.writeResults(result);
        }
        gameLog.clear();
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
     */
    public void nextMove(int string, int row, int fieldValue) {
        updateField(string,row,fieldValue);
        UserInterface.getButton(string,row).printFieldElement();
        gameLog.addMove(new FieldCopier().copy(fieldValues));

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

    public void updateField(int string, int row, int fieldValue) {
        fieldValues[string][row] = fieldValue;
    }

    /**
     * getters and setters for game parameters
     */
    public  int getActiveFigure() {
        return activeFigure;
    }

    public  Integer getFieldValue(Integer string, Integer row) {
        return fieldValues[string][row];
    }

    public  Integer[][] getFieldValues() {
        return fieldValues;
    }

    public  void setFirstPlayerUserSelection(FirstPlayerSelect firstPlayerUserSelection) {
        this.firstPlayerUserSelection = firstPlayerUserSelection;
    }

    public  boolean isGameStarted() {
        return gameStarted;
    }

    public  Player getCurrentPlayer() {
        return currentPlayer;
    }

    public  Player getPlayer(int id) {
        if (id == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    public  int getFieldSize() {
        return fieldSize;
    }

    public GameLog getLog() {
        return gameLog;
    }

}
