package ticTacToe.game;

public class FieldCoder {
    public Integer getCode(Integer[][] field) {
        Integer code = 0;
        for (int s = 1; s < field.length + 1; s++) {
            for (int r = 1; r < field.length + 1; r++) {
                code += field[s-1][r-1] * (s * field.length + r);
            }
        }
        return code;
    }
}
