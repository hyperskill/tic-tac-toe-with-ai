package ticTacToe;


import ticTacToe.tictactoe.controller.TTController;
import ticTacToe.tictactoe.model.FieldModel;
import ticTacToe.tictactoe.model.GameModel;
import ticTacToe.tictactoe.service.TTGameService;
import ticTacToe.tictactoe.service.XOService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameModel gm  = new GameModel();
        TTGameService   svc = new XOService(gm);
//
        TTController    cntr = new TTController(svc);
        List<FieldModel> lstMoves =new ArrayList<>();
        lstMoves.add(new FieldModel(1,1));
        lstMoves.add(new FieldModel(1,2));
        lstMoves.add(new FieldModel(1,3));
        lstMoves.add(new FieldModel(2,1));
        lstMoves.add(new FieldModel(2,2));
        lstMoves.add(new FieldModel(2,3));
        lstMoves.add(new FieldModel(3,1));
        lstMoves.add(new FieldModel(3,2));
        lstMoves.add(new FieldModel(3,3));
        List<Character> lstMean = Arrays.asList('X','O','O','O','X','O','O','O','X');
        cntr.makeMoves(lstMoves,lstMean);
        cntr.getResult();


    }
}