package ticTacToe.game;

import ticTacToe.ai.MiniMax;

/**
 * Class that contain coordinate and rate of cell of field
 *
 * @see MiniMax
 * @see ticTacToe.ai.SelfLearning
 */
public class Cell {
    public int s;
    public int r;
    public int rate;

    public Cell(int string, int row) {
        this.s = string;
        this.r = row;
        this.rate = 0;
    }
}