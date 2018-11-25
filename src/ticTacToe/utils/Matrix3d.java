package ticTacToe.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Matrix3d {

    private final static int DIMENSION = 3;
    private final String[] proto = new String[DIMENSION * DIMENSION];
    private String[] values = Collections.nCopies(DIMENSION * DIMENSION, " ").toArray(proto);

    public String get(int i, int j) {
        return values[i * DIMENSION + j];
    }

    public void set(final int i, final String val) {
        values[i] = val;
    }

    public void set(final int i, final int j, final String val) {
        values[i * DIMENSION + j] = val;
    }

    public void set(Coordinates coordinates, String val){
        this.set(coordinates.getRow(),coordinates.getCol(), val);
    }
    public String get(Coordinates coordinates){
         return this.get(coordinates.getRow(),coordinates.getCol());
    }

    public Matrix3d() {
    }
    public Matrix3d(Matrix3d orig) {
        values = orig.values;
    }

    public static int getDIMENSION() {
        return DIMENSION;
    }

    public String[] getValues() {
        return values;
    }

    public enum DiagonalType {
        POSITIVE, NEGATIVE
    }

    public String[] getDiagonal(DiagonalType type) {

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

    public String[] getRow(int i) {
        String[] rowElements = new String[DIMENSION];
        for (int j = 0; j < DIMENSION; j++) {
            rowElements[j] = get(i, j);
        }
        return rowElements;
    }

    public String[] getColon(int i) {
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


    public List<Coordinates> freeCoords() {
        List<Matrix3d.Coordinates> freeCoords = new LinkedList<>();

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            for (int j = 0; j < Matrix3d.getDIMENSION(); j++) {
                Coordinates coor = new Matrix3d.Coordinates(i,j);
                if (get(coor).equals(" "))
                    freeCoords.add(coor);
            }
        }
        return freeCoords;
    }

    public static class Coordinates{
        @Override
        public String toString() {
            return "Coordinates{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        int row;
        int col;

        public Coordinates(int i, int j){
            row = i;
            col = j;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
