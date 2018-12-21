package ticTacToe.tictactoe.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Belyaev Alexei (lebllex) on 11.12.18.
 */
public class GameModel {
    private static int ROW_CNT = 3;
    private static int COL_CNT = 3;
    private static int LINE_FACTOR  = 3;
    private static int X_CODE  = 'X';
    private static int O_CODE  = 'O';
    private static int SPC_CODE  = ' ';
    private static int[][] arrGameField = new int[ROW_CNT][COL_CNT];

    private static Character[] arrGameFieldModel = new Character[ROW_CNT*COL_CNT+1]; // а захотел индексовать с 1 а не 0 во как



    private int win_code = 0;

    public GameModel(){
        arrGameField[0] = new int[] {0,0,0};
        arrGameField[1] = new int[] {0,0,0};
        arrGameField[2] = new int[] {0,0,0};

        for(int i = 0; i <= (ROW_CNT*COL_CNT); i++ ){
            arrGameFieldModel[i] = null;
        }

    }

// new style
    public boolean makeYourMove(FieldModel mdl,Character ch){
        if((ch != X_CODE) && (ch != O_CODE)){
            System.out.println(String.format("Not known symbol %d, %d",mdl.getX(),mdl.getY()));
            return false;
        };
        int idx = mdl.getX() + LINE_FACTOR*(mdl.getY()-1);
        if(arrGameFieldModel[idx] != null) return false;
        arrGameFieldModel[idx] = ch;
        return true;
    }

    public boolean moveIsAvailable(){
        for(int i = 0; i < (ROW_CNT * COL_CNT); i++){
            if( arrGameFieldModel[i] == null) return  true;
        };
        return  false;
    }

    public boolean someOneWone(){
        return (wonByCol()||wonByCross()||wonByRow());
    };

    public void printGameField(){
        for(int i = 1; i <= ROW_CNT; i++){
            for(int j = 1; j <= COL_CNT ; j++) {
                if(arrGameFieldModel[i+LINE_FACTOR*(j-1)] != null)
                    System.out.print(arrGameFieldModel[i+LINE_FACTOR*(j-1)]+" ");
                else
                    System.out.print(" "+" ");
            }
            System.out.println();
        }
    }


    private boolean wonByCross(){
        if((arrGameFieldModel[1] == arrGameFieldModel[5]) && (arrGameFieldModel[1] == arrGameFieldModel[9]) && (arrGameFieldModel[1] != null)) {
            win_code = (int) arrGameFieldModel[1].charValue();
            return true;
        }
        if((arrGameFieldModel[3] == arrGameFieldModel[5]) && (arrGameFieldModel[3] == arrGameFieldModel[7]) && (arrGameFieldModel[3] != null)){
            win_code = (int) arrGameFieldModel[3].charValue();
            return true;
        }
        return false;
    }

    private boolean wonByRow(){
        if((arrGameFieldModel[1] == arrGameFieldModel[2]) && (arrGameFieldModel[2] == arrGameFieldModel[3]) && (arrGameFieldModel[1] != null)) {
            win_code = (int) arrGameFieldModel[1].charValue();
            return true;
        }
        if((arrGameFieldModel[4] == arrGameFieldModel[5]) && (arrGameFieldModel[5] == arrGameFieldModel[6]) && (arrGameFieldModel[4] != null)) {
            win_code = (int) arrGameFieldModel[4].charValue();
            return true;
        }
        if((arrGameFieldModel[7] == arrGameFieldModel[8]) && (arrGameFieldModel[8] == arrGameFieldModel[9]) && (arrGameFieldModel[7] != null)){
            win_code = (int) arrGameFieldModel[7].charValue();
            return true;
        }
        return false;
    }

    private boolean wonByCol(){
        for(int i = 1; i <= 3 ; i++){
            if((arrGameFieldModel[i] == arrGameFieldModel[i+3]) && (arrGameFieldModel[i+3] == arrGameFieldModel[i+6]) && (arrGameFieldModel[i] != null)){
                win_code = (int) arrGameFieldModel[i].charValue();
                return true;
            }
        }
        return false;
    }


//~ new style

// old style
    @Deprecated
    public boolean isFinished(){

        int code = 0;
//        rows ok
        code = (int)arrGameField[0][0] * arrGameField[0][1] * arrGameField[0][2];
        if(actIsFinished(code)) return true;
        code = (int)arrGameField[1][0] * arrGameField[1][1] * arrGameField[1][2];
        if(actIsFinished(code)) return true;
        code = (int)arrGameField[2][0] * arrGameField[2][1] * arrGameField[2][2];
        if(actIsFinished(code)) return true;

//        colls ok
        code = (int)arrGameField[0][0] * arrGameField[1][0] * arrGameField[2][0];
        if(actIsFinished(code)) return true;
        code = (int)arrGameField[0][1] * arrGameField[1][1] * arrGameField[2][1];
        if(actIsFinished(code)) return true;
        code = (int)arrGameField[0][2] * arrGameField[1][2] * arrGameField[2][2];
        if(actIsFinished(code)) return true;
//      cross is ok
        code = (int)arrGameField[0][0] * arrGameField[1][1] * arrGameField[2][2];
        if(actIsFinished(code)) return true;
        code = (int)arrGameField[0][2] * arrGameField[1][1] * arrGameField[2][0];
        return (actIsFinished(code));
    }




    @Deprecated
    private boolean actIsFinished(int code){

        if ((code != 0 )) {
            if((code % X_CODE == 0))
            win_code = (X_CODE);
            else
                win_code =  (O_CODE);
            return true;
        };

        return false;
    }

    @Deprecated
    public void pasteInField(int row, int col,Character sign){
        int value = arrGameField[row][col];
        if((sign != X_CODE) && (sign != O_CODE)){
            System.out.println(String.format("Not known symbol %d, %d",row,col));
            return;
        };
        if(value != 0){
            System.out.println(String.format("can`t place %d, %d",row,col));
            return;
        };
        arrGameField[row][col] = (int) sign;
    }

    @Deprecated
    public void printAll(){
        for(int i = 0; i < ROW_CNT; i++){
            System.out.println(printRow(i));
        }
    }

    @Deprecated
    public String printRow(int rowNum){
        if(rowNum > ROW_CNT) rowNum = ROW_CNT-1;
        StringBuilder bld = new StringBuilder();
        for(int i = 0; i < COL_CNT; i++)
            if(arrGameField[rowNum][i] == X_CODE) {
                bld.append("X");
                bld.append(" ");
            }
            else
            if(arrGameField[rowNum][i] == O_CODE){
                bld.append("O");
                bld.append(" ");
            }
            else{
                bld.append(" ");
                bld.append(" ");
            }
        return bld.toString();
    }


    public void printWinner(){
        System.out.println("Winner is "+(char)win_code);
    }

//~ old style
}
