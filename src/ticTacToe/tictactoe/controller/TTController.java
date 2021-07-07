package ticTacToe.tictactoe.controller;


import ticTacToe.tictactoe.model.FieldModel;
import ticTacToe.tictactoe.service.TTGameService;

import java.util.List;

public class TTController implements IGameController {
    private final TTGameService svc;

    public TTController(TTGameService svc){
        this.svc = svc;
    }

    @Deprecated
    public void processGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j< 3; j++){
                if( i == 0){
                    svc.makeMove(i,j,'X');
                };
                if( (i ==  1)&&(j < 2) ){
                    svc.makeMove(i,j,'O');
                }
            }
        }
        svc.makeMove(2,1,'O');
        if(svc.isFinished())
            svc.printWinner();
    }

    @Override
    public void makeMove(FieldModel model, Character ch) {
        svc.makeMove(model,ch);
    }

    @Override
    public void makeMoves(List<FieldModel> modelList, List<Character> lstCh) {
        for(int i = 0; i < modelList.size(); i++){
            makeMove(modelList.get(i),lstCh.get(i));
        }
    }

    @Override
    public void getResult() {
        svc.getField();
        if(svc.isFinished()){
            svc.printWinner();
        }else{
            System.out.println("no winner");
        }
    }
}
