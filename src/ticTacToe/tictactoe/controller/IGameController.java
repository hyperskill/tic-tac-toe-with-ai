package ticTacToe.tictactoe.controller;

import ticTacToe.tictactoe.model.FieldModel;

import java.util.List;

public interface IGameController {
    //
    void makeMove(FieldModel model, Character ch);
    void makeMoves(List<FieldModel> modelList, List<Character> lstCh);
    void getResult();
}
