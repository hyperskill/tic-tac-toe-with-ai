package ticTacToe.hardlevels;

import ticTacToe.Main;

public class RowAnalyzer {
    char[] row;
    String type;
    int matrixRowPointer;

    static final String TYPE_COL = "col";
    static final String TYPE_ROW = "row";
    static final String TYPE_DIAG_TOP_LEFT = "diagtopleft";
    static final String TYPE_DIAG_TOP_RIGHT = "diagtopright";

    static public RowAnalyzer col(char[][] matrix, int x){
        return new RowAnalyzer(matrix[x], TYPE_COL, x);
    }

    static public RowAnalyzer row(char[][] matrix, int y){
        char[] row =new char[matrix.length];
        for (int x = 0; x < matrix.length; x++) {
            row[x] = matrix[x][y];
        }
        return new RowAnalyzer(row, TYPE_ROW, y);
    }

    static public RowAnalyzer diagonalLeftTop(char[][] matrix){
        char[] row = new char[matrix.length];
        for (int x = 0; x < matrix.length; x++) {
            row[x] = matrix[x][x];
        }
        return new RowAnalyzer(row, TYPE_DIAG_TOP_LEFT);
    }

    static public RowAnalyzer diagonalRightTop(char[][] matrix){
        char[] row = new char[matrix.length];
        for (int x = matrix.length - 1; x >= 0; x--) {
            row[x] = matrix[x][matrix.length - x - 1];
        }
        return new RowAnalyzer(row, TYPE_DIAG_TOP_RIGHT);
    }


    public RowAnalyzer(char[] row, String type, int matrixRowPointer) {
        this.row = row;
        this.type = type;
        this.matrixRowPointer = matrixRowPointer;
    }

    public RowAnalyzer(char[] row, String type) {
        this.row = row;
        this.type = type;
    }

    public int[] getOneLeftorNull(char symbol){
        int symbolCounter = 0;
        int emptyCounter = 0;
        int[] spot = new int[2];
        for (int i = 0; i < row.length; i++) {
            if(row[i] == symbol){
                symbolCounter++;
            }
            if(row[i] == Main.EMPTY){
                emptyCounter++;
                switch (type){
                    case TYPE_COL:
                        spot[0] = matrixRowPointer;
                        spot[1] = i;
                        break;
                    case TYPE_ROW:
                        spot[0] = i;
                        spot[1] = matrixRowPointer;
                        break;
                    case TYPE_DIAG_TOP_LEFT:
                        spot[0] = i;
                        spot[1] = spot[0];
                        break;
                    case TYPE_DIAG_TOP_RIGHT:
                        spot[0] = i;
                        spot[1] = row.length - i - 1;
                        break;
                }
            }
        }

//        return emptyCounter == row.length - 1;
        if((row.length - symbolCounter) == 1 && emptyCounter == 1){
            return spot;
        }
        return null;
    }
}
