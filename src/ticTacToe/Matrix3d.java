package ticTacToe;

import java.util.Collections;

public class Matrix3d {
    private static int DIMENSION = 3;
    private String[] values = new String[DIMENSION*DIMENSION];

    String get(int i, int j) {
        return values[i*DIMENSION+j];
    }
    String[] set(final int i, final int j, final String val) {
        values[i* DIMENSION+j] = val;
        return values;
    }
    Matrix3d(String []matrixInString){
        values = matrixInString;
    }

    Matrix3d(){
        Collections.nCopies(DIMENSION*DIMENSION, " ").toArray(values);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i =0 ; i< DIMENSION; i++) {
            for (int j=0;j<DIMENSION;j++) {
                result.append(get(i, j));
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
