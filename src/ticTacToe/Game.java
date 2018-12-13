package ticTacToe;

import java.util.Random;


/**
 *  This class saves all game settings, and current parameters
 */
public class Game {

    /**
     *  Used for game turn identification
     */
    public enum Players {
        PLAYER1,
        PLAYER2,
        NOT_STARTED
    }

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
        HARD
    }

    /**
     *  Constants for cell state identification
     */
    public static final int ZERO = 0;
    public static final int CROSS = 1;
    public static final int EMPTY = 2;

    /**
     *  Contains setting for game start- which player should make first move
     */
    private static FirstPlayerSelect firstPlayerUserSelection = FirstPlayerSelect.RANDOM;

    /**
     *  Contains information which player turn now
     */
    private static Players currentPlayer;

    /**
     *  Contains information about first player who made a move,  for winner identification
     *  so it should be memorised at game start for case of settings change while game in process
     */
    private static Players firstPlayerTriggered;

    private static Levels level = Levels.MEDIUM;

    /**
     *  Parameters for rival selection(human or computer). Should be memorised in start of a game
     */
    private static boolean playWithComputer = true;
    private static boolean playWithComputerTriggered;

    /**
     *  Parameter deactivating start game method after game start until it finished
     */
    private static boolean gameStarted = false;

    /**
     *  Contains game field
     */
    private static int[][] fieldValues = new int[3][3];

    /**
     *  Contains which figure is active now - zero or cross
     */
    private static int activeFigure = CROSS;

    /**
     *  Contains text field data, because it changed in case of computer rival selection.
     */
    private static String player2Name = "Player 2";

    /**
     *  method that configure game to a start position depending of game settings
     */
    public static void startTheGame(){
        if (!gameStarted) {

            gameStarted = true;
            activeFigure = CROSS;

            switch (firstPlayerUserSelection) {
                case PLAYER1 : {
                    currentPlayer = Players.PLAYER1;
                    break;
                }
                case PLAYER2 : {
                    currentPlayer = Players.PLAYER2;
                    break;
                }
                case RANDOM : {
                    if (new Random().nextBoolean()) {
                        currentPlayer = Players.PLAYER1;
                    } else {
                        currentPlayer = Players.PLAYER2;
                    }
                    break;
                }
            }
            DisplayPlayer.display();
            firstPlayerTriggered = currentPlayer;

            for ( int i = 0; i < 3; i++) {
                for ( int j = 0; j < 3; j++) {
                    Game.setFieldValue(i,j, EMPTY, false);
                    UserInterface.button[i][j].printFieldElement();
                    UserInterface.button[i][j].setButtonEnabled(true);
                }
            }

            playWithComputerTriggered = playWithComputer;

            if (playWithComputerTriggered){
                player2Name = UserInterface.player2.getText();
                UserInterface.player2.setText("Computer");
                UserInterface.player2.getTextField().setEditable(false);
                new ComputerRival().makeMove();
            }
        }
    }

    /**
     *  method updating ui to the start condition
     */
    public static void restartTheGame(){
        gameStarted = false;
        UserInterface.player2.setText(player2Name);
        UserInterface.player2.getTextField().setEditable(true);
        startTheGame();
    }

    /**
     *  method resets field but not starting a new game
     */
    public static void stopTheGame(){
        gameStarted = false;
        setCurrentPlayerName(Players.NOT_STARTED);
        UserInterface.player2.setText(player2Name);
        UserInterface.player2.getTextField().setEditable(true);
        DisplayPlayer.display();
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                Game.setFieldValue(i,j, EMPTY, false);
                UserInterface.button[i][j].setWaitingForGame();
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
        setCurrentPlayerName(Game.Players.NOT_STARTED);
        UserInterface.player2.setText(player2Name);
        UserInterface.player2.getTextField().setEditable(true);
        DisplayPlayer.display();
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                UserInterface.button[i][j].setWaitingForGame();
            }
        }
    }

    /**
     * method for update field matrix with new value and current game turn
     *
     * @param string string number in matrix
     * @param row row number in matrix
     * @param fieldValue value which should be write
     * @param changeGameProgress true if game should be updated
     */
    public static void setFieldValue(int string, int row, int fieldValue, boolean changeGameProgress) {
        fieldValues[string][row] = fieldValue;
        if (changeGameProgress) {
            if (getActiveFigure() == Game.CROSS) {
                setActiveFigure(Game.ZERO);
            } else {
                setActiveFigure(Game.CROSS);
            }

            if (getCurrentPlayerName() == Players.PLAYER1) {
                setCurrentPlayerName(Players.PLAYER2);
            } else {
                setCurrentPlayerName(Players.PLAYER1);
            }
            DisplayPlayer.display();
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

    public static void setActiveFigure(int whoMoves) {
        Game.activeFigure = whoMoves;
    }

    public static Players getCurrentPlayerName() {
        return currentPlayer;
    }

    public static void setCurrentPlayerName(Players currentPlayerName) {
        Game.currentPlayer = currentPlayerName;
    }

    public static void setFirstPlayerUserSelection(FirstPlayerSelect firstPlayerUserSelection) {
        Game.firstPlayerUserSelection = firstPlayerUserSelection;
    }

    public static void setPlayWithComputer(boolean playWithComputer) {
        Game.playWithComputer = playWithComputer;
    }

    public static boolean isPlayWithComputerTriggered() {
        return playWithComputerTriggered;
    }

    public static void setLevel(Levels level) {
        Game.level = level;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static Players getFirstPlayerTriggered() {
        return firstPlayerTriggered;
    }

    public static Levels getLevel() {
        return level;
    }
}
