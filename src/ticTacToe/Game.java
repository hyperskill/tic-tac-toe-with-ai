package ticTacToe;

import java.util.Random;

public class Game {

    public enum Players {
        PLAYER1,
        PLAYER2,
        NOT_STARTED
    }

    public enum FirstPlayerSelect {
        PLAYER1,
        PLAYER2,
        RANDOM
    }

    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    public static final int ZERO = 0;
    public static final int CROSS = 1;
    public static final int NULL = 2;
    public static final int NOT_SEQUENCE = 3;


    private static FirstPlayerSelect firstPlayerUserSelection = FirstPlayerSelect.RANDOM;
    private static Players currentPlayer;
    private static Players firstPlayerTriggered;

    private static Level level = Level.MEDIUM;

    private static boolean playWithComputer = true;
    private static boolean playWithComputerTriggered;

    private static boolean gameStarted = false;

    private static int[][] fieldValues = new int[3][3];
    private static int activeFigure = CROSS;

    private static String player2Name = "Player 2";

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
                }
            }
            DisplayPlayer.display();
            firstPlayerTriggered = currentPlayer;

            for ( int i = 0; i < 3; i++) {
                for ( int j = 0; j < 3; j++) {
                    Game.setFieldValue(i,j, NULL, false);
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

    public static void restartTheGame(){
        gameStarted = false;
        UserInterface.player2.setText(player2Name);
        UserInterface.player2.getTextField().setEditable(true);
        startTheGame();
    }

    public static void stopTheGame(){
        gameStarted = false;
        setCurrentPlayerName(Players.NOT_STARTED);
        UserInterface.player2.setText(player2Name);
        UserInterface.player2.getTextField().setEditable(true);
        DisplayPlayer.display();
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                Game.setFieldValue(i,j, NULL, false);
                UserInterface.button[i][j].setWaitingForGame();
            }
        }
    }

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


    public static int getActiveFigure() {
        return activeFigure;
    }

    public static int getFieldValue(int string, int row) {
        return fieldValues[string][row];
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

    public static void setLevel(Level level) {
        Game.level = level;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static Players getFirstPlayerTriggered() {
        return firstPlayerTriggered;
    }

    public static Level getLevel() {
        return level;
    }

}
