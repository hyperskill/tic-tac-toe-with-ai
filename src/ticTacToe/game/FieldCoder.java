package ticTacToe.game;

public class FieldCoder {
    public long getCode(int[][] field) {
        long code = 0;
        for (int s = 0; s < field.length; s++) {
            for (int r = 0; r < field.length; r++) {
                code += field[s][r] * (Math.pow(3, (s*field.length + r)));
            }
        }
        return code;
    }
}
