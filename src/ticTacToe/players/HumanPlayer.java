package ticTacToe.players;

import ticTacToe.utils.Matrix3d;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private String playerSign;

    @Override
    public void setPlayerSign(String sign) {
        playerSign = sign;
    }

    @Override
    public void makeTurn(Matrix3d fightField) {
        String[] parametersAsStrings = new String[0];
        do {
            System.out.println("Enter the coordinates (like 1 3):");
            Scanner scannerInput = new Scanner(System.in);
            String parameters = scannerInput.nextLine();
            if(!inputIsValid(parameters, fightField))continue;
            parametersAsStrings = parameters.split(" ");
        }
        while (parametersAsStrings.length < 2);
        int rowId = Integer.parseInt(parametersAsStrings[0]);
        int colId = Integer.parseInt(parametersAsStrings[1]);

         setFightFieldValue(rowId, colId, fightField);
    }

    private boolean inputIsValid(String parameters, Matrix3d fightField) {

        String [] parametersAsStrings = parameters.split(" ");

        if (parametersAsStrings.length == 0 || parametersAsStrings.length!=2) {
            System.out.println("You should enter 2 numbers slitted by space!");
            return false;
        }

        int rowId;
        int colId;

        try{
            rowId = Integer.parseInt(parametersAsStrings[0]);
            colId = Integer.parseInt(parametersAsStrings[1]);
        }catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (rowId>Matrix3d.getDIMENSION() || colId>Matrix3d.getDIMENSION() || rowId<=0 || colId<=0){
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if(!getFightFieldValue(rowId, colId, fightField).equals(" ")){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;

    }

    private void setFightFieldValue(int row, int col, Matrix3d fightField) {
        //convert user input in digital system starts with 0
        row--;
        col--;
        //reverse original coordination to the down to up
        row = Matrix3d.getDIMENSION() - 1 - row;
        fightField.set(row, col, playerSign);
    }

    private String getFightFieldValue(int row, int col, Matrix3d fightField) {
        //convert user input in digital system starts with 0
        row--;
        col--;
        //reverse original coordination to the down to up
        row = Matrix3d.getDIMENSION() - 1 - row;
        return fightField.get(row, col);
    }

}
