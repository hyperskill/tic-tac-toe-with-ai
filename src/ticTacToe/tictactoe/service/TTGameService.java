package ticTacToe.tictactoe.service;

import ticTacToe.tictactoe.model.FieldModel;

public interface TTGameService {

    public void    makeMove(int x, int y, Character sign);
    public void    makeMove(FieldModel mdl, Character sign);
    public void    getField();
    public void    printWinner();
    public boolean isFinished();
    public boolean canPlay();
}
