package ticTacToe.utils;

import ticTacToe.enums.PlayerSign;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Matrix3d {


    private final static int DIMENSION = 3;
    private final String[] values = new String[DIMENSION * DIMENSION];

    public String get(int i, int j) {
        return values[i * DIMENSION + j];
    }

    public void set(final int i, final int j, final String val) {
        values[i * DIMENSION + j] = val;
    }

    public void set(Coordinates coordinates, PlayerSign val){
        this.set(coordinates.getRow(),coordinates.getCol(), val.toString());
    }

    public void set(Coordinates coordinates, String val){
        this.set(coordinates.getRow(),coordinates.getCol(), val);
    }

    public Matrix3d() {
        Arrays.setAll(values, i -> " ");
    }
    public Matrix3d(Matrix3d orig) {
        System.arraycopy(orig.values, 0, values, 0, values.length);
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


    public List<Coordinates> freeTile() {
        List<Matrix3d.Coordinates> freeTile = new LinkedList<>();

        for (int i = 0; i < Matrix3d.getDIMENSION(); i++) {
            for (int j = 0; j < Matrix3d.getDIMENSION(); j++) {
                Coordinates tile = new Matrix3d.Coordinates(i,j);
                if (get(i,j).equals(" "))
                    freeTile.add(tile);
            }
        }
        return freeTile;
    }

    public static class Coordinates{

        final int row;
        final int col;

         Coordinates(int i, int j){
            row = i;
            col = j;
        }

        int getRow() {
            return row;
        }

        int getCol() {
            return col;
        }
    }
}
