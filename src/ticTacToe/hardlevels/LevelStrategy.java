package ticTacToe.hardlevels;

import ticTacToe.Player;

public abstract class LevelStrategy {
    public abstract boolean move(char[][] matrix, Player player);
}
