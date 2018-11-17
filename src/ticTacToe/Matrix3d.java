package ticTacToe;

import java.util.Collections;

public class Matrix3d {

    private final static int DIMENSION = 3;
    private final static String[] proto = new String[DIMENSION * DIMENSION];
    private String[] values = Collections.nCopies(DIMENSION * DIMENSION, " ").toArray(proto);

    String get(int i, int j) {
        return values[i * DIMENSION + j];
    }

    String[] set(final int i, final int j, final String val) {
        values[i * DIMENSION + j] = val;
        return values;
    }

    Matrix3d(String[] matrixInStringArr) {
        values = matrixInStringArr;
    }

    static int getDIMENSION() {
        return DIMENSION;
    }

    String[] getValues() {
        return values;
    }

    enum DiagonalType {
        POSITIVE, NEGATIVE
    }
    String[] getDiagonal(DiagonalType type) {

        String[] values = new String[DIMENSION];

        switch (type) {
            case POSITIVE:
                for (int i = 0; i < DIMENSION; i++) {
                    values[i] = get(i, i);
                }
                return values;
            case NEGATIVE:
                for (int i = 0; i < DIMENSION; i++) {
                    values[i] = get(i, DIMENSION - i - 1);
                }
                return values;
            default:
                throw new IllegalArgumentException("There is no such diagonal is defined");

        }
    }

    String[] getRow(int i) {
        String[] rowElements = new String[DIMENSION];
        for (int j = 0; j < DIMENSION; j++) {
            rowElements[j] = get(i, j);
        }
        return rowElements;
    }

    String[] getColon(int i) {
        String[] rowElements = new String[DIMENSION];
        for (int j = 0; j < DIMENSION; j++) {
            rowElements[j] = get(j, i);
        }
        return rowElements;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                result.append(get(i, j));
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
