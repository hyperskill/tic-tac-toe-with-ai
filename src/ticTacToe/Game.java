package ticTacToe;

import javax.swing.filechooser.FileSystemView;
import java.util.Random;

public class Game {

    public static final int ZERO = 0;
    public static final int CROSS = 1;
    public static final int NULL = 2;
    public static final int NOT_SEQUENCE = 3;

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


    private static FirstPlayerSelect firstPlayerSelect = FirstPlayerSelect.RANDOM;
    private static int activeFigure = CROSS;
    private static Players currentPlayerName;
    private static boolean playWithComputer = false;
    private static Level level;

    private static boolean gameIsStarted = false;

    private static int[][] fieldValues = new int[3][3];



    public static void startTheGame(){
        if (!gameIsStarted) {

            gameIsStarted = true;
            activeFigure = CROSS;

            switch (firstPlayerSelect) {
                case PLAYER1 : {
                    currentPlayerName = Players.PLAYER1;
                    break;
                }
                case PLAYER2 : {
                    currentPlayerName = Players.PLAYER2;
                    break;
                }
                case RANDOM : {
                    if (new Random().nextBoolean()) {
                        currentPlayerName = Players.PLAYER1;
                    } else {
                        currentPlayerName = Players.PLAYER2;
                    }
                }
            }

            DisplayPlayer.display();

            for ( int i = 0; i < 3; i++) {
                for ( int j = 0; j < 3; j++) {
                    Game.setFieldValue(i,j, NULL);
                    UserInterface.button[i][j].setButtonEnabled(true);
                }
            }
        }
    }

    public static void restartTheGame(){
        gameIsStarted = false;
        setCurrentPlayerName(Players.NOT_STARTED);
        DisplayPlayer.display();
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                Game.setFieldValue(i,j, NULL);
                UserInterface.button[i][j].setWaitingForGame();
            }
        }
    }

    public static void printArray() {
        System.out.print("\n New Array: \n");
        for ( int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                System.out.print(getFieldValue(i,j)+" ");
            }
            System.out.print("\n");
        }
    }

    public static void checkGameResult() {
        int result = scanDiagonal();
        if (result != NOT_SEQUENCE) {
            printResult(result);
            return;
        }

        result = scanDown();
        if (result != NOT_SEQUENCE) {
            printResult(result);
            return;
        }

        result = scanRight();
        if (result != NOT_SEQUENCE) {
            printResult(result);
            return;
        }

        if (checkGameOver()){
            printResult(NOT_SEQUENCE);
        } else {
            printResult(NULL);
        }


    }

    private static int scanDown() {

        for( int row = 0; row < 3; row++) {
            boolean sequenceFound = true;
            for (int string = 1; string < 3; string++) {
                if (getFieldValue(string - 1, row) != getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && getFieldValue(0, row) != NULL) {
                return getFieldValue(0, row);
            }
        }
        return NOT_SEQUENCE;
    }

    private static int scanRight() {

        for( int string = 0; string < 3; string++) {
            boolean sequenceFound = true;
            for (int row = 1; row < 3; row++) {
                if (getFieldValue(string, row - 1) != getFieldValue(string, row)) {
                    sequenceFound = false;
                }
            }
            if (sequenceFound && getFieldValue(string,0) != NULL) {
                return getFieldValue(string,0);
            }
        }
        return NOT_SEQUENCE;
    }

    private static int scanDiagonal() {
        /*
            Check that first diagonal equal and not null
         */
        if(getFieldValue(0, 0) == getFieldValue(1, 1) &&
                getFieldValue(1, 1) == getFieldValue(2, 2)
         && getFieldValue(1,1) != NULL) {
                return getFieldValue(1,1);
        }
        /*
            Check that second diagonal equal and not null
         */
        if(getFieldValue(0, 2) == getFieldValue(1, 1) &&
                getFieldValue(1, 1) == getFieldValue(2, 0)
                && getFieldValue(1,1) != NULL) {
            return getFieldValue(1,1);
        }
        return NOT_SEQUENCE;
    }

    private static boolean checkGameOver() {
        for( int row = 0; row < 3; row++) {
            for (int string = 0; string < 3; string++) {
                if (getFieldValue(string, row) == NULL) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printResult(int result) {
        switch (result) {
            case ZERO : {
                System.out.println("O wins!");
                break;
            }
            case CROSS : {
                System.out.println("X wins!");
                break;
            }
            case Game.NULL : {
                System.out.println("Game not finished");
                break;
            }

            case Game.NOT_SEQUENCE : {
                System.out.println("Friendship wins");
                break;
            }

            default : break;
        }
    }

    public static int getFieldValue(int string, int row) {
        return fieldValues[string][row];
    }

    public static void setFieldValue(int string, int row, int fieldValue) {
        fieldValues[string][row] = fieldValue;
    }

    public static int getWhoMoves() {
        return activeFigure;
    }

    public static void setWhoMoves(int whoMoves) {
        Game.activeFigure = whoMoves;
    }

    public static Players getCurrentPlayerName() {
        return currentPlayerName;
    }

    public static void setCurrentPlayerName(Players currentPlayerName) {
        Game.currentPlayerName = currentPlayerName;
    }

    public static void setFirstPlayerSelect(FirstPlayerSelect firstPlayerSelect) {
        Game.firstPlayerSelect = firstPlayerSelect;
    }
    public static void setPlayWithComputer(boolean playWithComputer) {
        Game.playWithComputer = playWithComputer;
    }
    public static void setLevel(Level level) {
        Game.level = level;
    }
}
