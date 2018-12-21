package ticTacToe.tictactoe.service;

import ticTacToe.tictactoe.model.FieldModel;
import ticTacToe.tictactoe.model.GameModel;

public class XOService implements TTGameService {
    private final GameModel gameModel;


    public XOService(GameModel gm){
        this.gameModel = gm;
    }

    @Override
    public void makeMove(int x, int y, Character sign) {
        gameModel.pasteInField(x,y,sign);
    }

    @Override
    public void makeMove(FieldModel mdl, Character sign) {
        gameModel.makeYourMove(mdl,sign);
    }

    @Override
    public void getField() {
        gameModel.printGameField();
    }

    @Override
    public void printWinner() {
        gameModel.printAll();
        gameModel.printWinner();
    }

    @Override
    public boolean isFinished() {
        return gameModel.someOneWone();
    }

    @Override
    public boolean canPlay() {
        return gameModel.moveIsAvailable();
    }
}
