package ticTacToe;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String []fightFieldDemo = {
                                    "O", " ", "O",
                                    "X", "X", "O",
                                    " ", "X", "X"};
     Matrix3d fightField = new Matrix3d(fightFieldDemo);
        System.out.println(fightField);


    }


}