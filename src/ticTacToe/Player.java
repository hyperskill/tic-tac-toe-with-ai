package ticTacToe;

public interface Player {
    char[][] move(char[][] fieldValues);
    char getFigure();
}
